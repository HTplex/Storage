/* *********************************************************************** *
 * project: org.matsim.*
 * RebuildCountComparisonFromLinkStatsFile.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2011 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
/**
 *
 */
package playground.yu.counts.scaleFactor.fromEvents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.matsim.analysis.VolumesAnalyzer;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.ControlerConfigGroup;
import org.matsim.core.config.groups.CountsConfigGroup;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.counts.Count;
import org.matsim.counts.CountSimComparison;
import org.matsim.counts.CountSimComparisonImpl;
import org.matsim.counts.Counts;
import org.matsim.counts.MatsimCountsReader;
import org.matsim.counts.Volume;
import org.matsim.counts.algorithms.CountSimComparisonKMLWriter;

/**
 * playground for rebuilding google earth files in MATSim simulations from
 * Events given arbitrary countsScaleFactors
 *
 * @author yu
 *
 */
public class CountsScaleFactorLab {
	protected class CountsComparisonAlgorithm {
		/**
		 * The VolumesAnalyzer of the simulation
		 */
		private final VolumesAnalyzer volumes;
		/**
		 * The counts object
		 */
		private final Counts counts;
		/**
		 * The result list
		 */
		private final List<CountSimComparison> countSimComp;

		private Node distanceFilterNode = null;

		private Double distanceFilter = null;

		private final Network network;

		private double countsScaleFactor;

		private final Logger log = Logger
				.getLogger(CountsComparisonAlgorithm.class);

		public CountsComparisonAlgorithm(final VolumesAnalyzer volumes,
				final Counts counts, final Network network,
				final double countsScaleFactor) {
			this.volumes = volumes;
			this.counts = counts;
			countSimComp = new ArrayList<CountSimComparison>();
			this.network = network;
			this.countsScaleFactor = countsScaleFactor;
		}

		/**
		 * Creates the List with the counts vs sim values stored in the
		 * countAttribute Attribute of this class.
		 */
		private void compare() {
			double countValue;

			for (Count count : counts.getCounts().values()) {
				if (!isInRange(count.getLocId())) {
					continue;
				}
				double[] volumes = this.volumes.getVolumesPerHourForLink(count
						.getLocId());
				if (volumes.length == 0) {
					log.warn("No volumes for link: "
							+ count.getLocId().toString());
					volumes = new double[24];
					// continue;
				}
				for (int hour = 1; hour <= 24; hour++) {
					// real volumes:
					Volume volume = count.getVolume(hour);
					if (volume != null) {
						countValue = volume.getValue();
						double simValue = volumes[hour - 1];
						simValue *= countsScaleFactor;
						countSimComp.add(new CountSimComparisonImpl(count
								.getLocId(), hour, countValue, simValue));
					} else {
						countValue = 0.0;
					}
				}
			}
		}

		/**
		 *
		 * @return the result list
		 */
		public List<CountSimComparison> getComparison() {
			return countSimComp;
		}

		/**
		 *
		 * @param linkid
		 * @return <code>true</true> if the Link with the given Id is not farther away than the
		 * distance specified by the distance filter from the center node of the filter.
		 */
		private boolean isInRange(final Id<Link> linkid) {
			if (distanceFilterNode == null || distanceFilter == null) {
				return true;
			}
			Link l = network.getLinks().get(linkid);
			if (l == null) {
				log.warn("Cannot find requested link: " + linkid.toString());
				return false;
			}
			double dist = CoordUtils.calcDistance(l.getCoord(),
					distanceFilterNode.getCoord());
			return dist < distanceFilter.doubleValue();
		}

		public void run() {
			compare();
		}

		public void setCountsScaleFactor(final double countsScaleFactor) {
			this.countsScaleFactor = countsScaleFactor;
		}

		/**
		 * Set a distance filter, dropping everything out which is not in the
		 * distance given in meters around the given Node Id.
		 *
		 * @param distance
		 * @param nodeId
		 */
		public void setDistanceFilter(final Double distance, final String nodeId) {
			distanceFilter = distance;
			distanceFilterNode = network.getNodes().get(Id.create(nodeId, Node.class));
		}

		/**
		 * Creates the List with the counts vs sim values stored in the
		 * countAttribute Attribute of this class.
		 */
		public double getScaleFactor4_0bias(int startHour, int endHour) {
			double simValSquareSum = 0, countSimProductSum = 0;

			if (startHour < 1 || startHour > endHour || endHour > 24) {
				throw new RuntimeException(
						"Illegal start- and endHour for count comparison! [1-24]");
			}

			double countValue;

			for (Count count : counts.getCounts().values()) {
				if (!isInRange(count.getLocId())) {
					continue;
				}
				double[] volumes = this.volumes.getVolumesPerHourForLink(count
						.getLocId());
				if (volumes.length == 0) {
					log.warn("No volumes for link: "
							+ count.getLocId().toString());
					volumes = new double[24];
				}
				for (int hour = startHour; hour <= endHour; hour++) {
					// real volumes:
					Volume volume = count.getVolume(hour);
					if (volume != null) {
						countValue = volume.getValue();
						double simValue = volumes[hour - 1];
						simValue *= countsScaleFactor;

						simValSquareSum += simValue * simValue;
						countSimProductSum += countValue * simValue;

					} else {
						countValue = 0.0;
					}
				}
			}
			if (simValSquareSum <= 0) {
				throw new RuntimeException(
						"sigma(sim^2)==0, no agents were simulated?");
			}
			return countsScaleFactor * countSimProductSum / simValSquareSum;
		}
	}

	private final String eventsFilename;
	private final double[] scaleFactors;

	private final Logger log = Logger.getLogger(CountsScaleFactorLab.class);

	private final Scenario scenario;
	private Config config;
	private Counts counts;

	public CountsScaleFactorLab(String configFilename, String eventsFilename,
			double[] scaleFactors) {
		scenario = ScenarioUtils.loadScenario(ConfigUtils
				.loadConfig(configFilename));
		this.eventsFilename = eventsFilename;
		this.scaleFactors = scaleFactors;
	}

	public void run() {
		config = scenario.getConfig();

		counts = new Counts();
		new MatsimCountsReader(counts).readFile(config.counts()
				.getCountsFileName());

		// SET COUNTS_SCALE_FACTOR
		for (double scaleFactor : scaleFactors) {
			runCountsComparisonAlgorithmAndOutput(scaleFactor);
		}
	}

	private void runCountsComparisonAlgorithmAndOutput(double scaleFactor) {
		CountsConfigGroup countsConfigGroup = config.counts();

		log.info("compare with counts, scaleFactor =\t" + scaleFactor);

		Network network = scenario.getNetwork();

		VolumesAnalyzer volumes = new VolumesAnalyzer(3600, 24 * 3600 - 1,
				network);

		EventsManager events = EventsUtils.createEventsManager();
		events.addHandler(volumes);
		new MatsimEventsReader(events).readFile(eventsFilename);

		CountsComparisonAlgorithm cca = new CountsComparisonAlgorithm(volumes,
				counts, network, scaleFactor);

		if (countsConfigGroup.getDistanceFilter() != null
				&& countsConfigGroup.getDistanceFilterCenterNode() != null) {
			cca.setDistanceFilter(countsConfigGroup.getDistanceFilter(),
					countsConfigGroup.getDistanceFilterCenterNode());
		}
		cca.setCountsScaleFactor(scaleFactor);

		cca.run();

		String outputFormat = countsConfigGroup.getOutputFormat();
		if (outputFormat.contains("kml") || outputFormat.contains("all")) {
			ControlerConfigGroup ctlCG = config.controler();

			int iteration = ctlCG.getFirstIteration();
			OutputDirectoryHierarchy ctlIO =
					new OutputDirectoryHierarchy(
							ctlCG.getOutputDirectory(),
							ctlCG.getRunId(),
							OutputDirectoryHierarchy.OverwriteFileSetting.failIfDirectoryExists);

			// String filename = ctlIO.getIterationFilename(iteration, "sf"
			// + scaleFactor + "_countscompare" + ".kmz");

			String path = ctlIO.getIterationPath(iteration) + "/sf"
					+ scaleFactor + "/";
			File itDir = new File(path);
			if (!itDir.mkdirs()) {
				if (itDir.exists()) {
					log.info("Iteration directory " + path + " exists already.");
				} else {
					log.info("Could not create iteration directory " + path
							+ ".");
				}
			}
			String filename = path + "/countscompare.kmz";

			CountSimComparisonKMLWriter kmlWriter = new CountSimComparisonKMLWriter(
					cca.getComparison(), network,
					TransformationFactory.getCoordinateTransformation(config
							.global().getCoordinateSystem(),
							TransformationFactory.WGS84));
			kmlWriter.setIterationNumber(iteration);
			kmlWriter.writeFile(filename);// biasErrorGraphData.txt will
			// be
			// written here
		}

		log.info("compare with counts, scaleFactor =\t" + scaleFactor);
	}

	/**
	 * @param args
	 *            - args[0] configFilename; args[1] linkstatsFilename,
	 *            args[2...] possible values for countScaleFactor
	 */
	public static void run(String[] args) {
		double[] countScaleFactors = new double[args.length - 2];
		for (int i = 2; i < args.length; i++) {
			countScaleFactors[i - 2] = Double.parseDouble(args[i]);
		}
		CountsScaleFactorLab rccfls = new CountsScaleFactorLab(args[0], args[1],
				countScaleFactors);
		rccfls.run();

	}

	/**
	 * @param args
	 *            - args[0] configFilename; args[1] linkstatsFilename,
	 *            args[2...] possible values for countScaleFactor
	 */
	public static void main(String[] args) {
		run(args);
	}
}
