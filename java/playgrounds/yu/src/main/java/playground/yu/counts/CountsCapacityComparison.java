/* *********************************************************************** *
 * project: org.matsim.*
 * CountsCapacityComparison.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
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
package playground.yu.counts;

import java.io.IOException;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.counts.Counts;
import org.matsim.counts.MatsimCountsReader;

import playground.yu.utils.io.SimpleWriter;

/**
 * @author yu
 * 
 */
public class CountsCapacityComparison {
	public static boolean isInRange(final Link link,
			Coord distanceFilterCenter, double filterRadius) {
		return CoordUtils.calcDistance(link.getCoord(), distanceFilterCenter) < filterRadius;
	}

	/**
	 * compare link capacity with counts-value, in order to check problematical
	 * link capacites
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Gbl.startMeasurement();

		final String netFilename = "../schweiz-ivtch-SVN/baseCase/network/ivtch-osm.xml";
		final String countsFilename = "../schweiz-ivtch-SVN/baseCase/counts/countsIVTCH.xml";
		final String outputFilename = "../matsimTests/countsCapacityComparison/output_zurich.txt";

		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils
				.createScenario(ConfigUtils.createConfig());
		Network network = scenario.getNetwork();
		new MatsimNetworkReader(scenario).readFile(netFilename);

		Counts counts = new Counts();
		new MatsimCountsReader(counts).readFile(countsFilename);

		SimpleWriter sw = new SimpleWriter(outputFilename);
		sw.writeln("linkId\tx\ty\tCapacity [veh/h]\tmax Value of Counts");

		Coord center = network.getNodes().get(Id.create("2531", Node.class)).getCoord();
		double capPeriod = network.getCapacityPeriod() / 3600.0 * 0.97;
		int n_countStations = 0;

		for (Id<Link> linkId : counts.getCounts().keySet()) {

			Link link = network.getLinks().get(linkId);
			if (link != null) {
				if (isInRange(link, center, 30000.0)) {
					n_countStations++;
					double capacity = link.getCapacity() / capPeriod;
					double maxCountsValue = counts.getCount(linkId)
							.getMaxVolume().getValue();
					if (capacity <= maxCountsValue) {
						sw.writeln(linkId.toString() + "\t"
								+ link.getCoord().getX() + "\t"
								+ link.getCoord().getY() + "\t" + capacity
								+ "\t" + maxCountsValue);
						// TODO what about the upward and downward links??
						// upward
						Link upLink = link;
						int smallUpwardLinks = 1;
						while (smallUpwardLinks == 1) {
							// sw.writeln("upward");
							double capSum = 0.0;
							smallUpwardLinks = 0;
							for (Link inLink : upLink.getFromNode()
									.getInLinks().values()) {
								capSum += inLink.getCapacity() / capPeriod;
								smallUpwardLinks++;
							}
							if (capSum <= maxCountsValue) {
								for (Link inLink : upLink.getFromNode()
										.getInLinks().values()) {
									sw.writeln(inLink.getId().toString() + "\t"
											+ inLink.getCoord().getX() + "\t"
											+ inLink.getCoord().getY() + "\t"
											+ inLink.getCapacity() / capPeriod);
								}
							}
							upLink = upLink.getFromNode().getInLinks().values()
									.iterator().next();
						}
						// downward
						Link downLink = link;
						int smallDownwardLinks = 1;
						while (smallDownwardLinks == 1) {
							// sw.writeln("downward");
							// System.out.println("downLink Id:"
							// + downLink.getId().toString());
							double capSum = 0.0;
							smallDownwardLinks = 0;
							for (Link outLink : downLink.getToNode()
									.getOutLinks().values()) {
								capSum += outLink.getCapacity() / capPeriod;
								smallDownwardLinks++;
							}
							if (capSum <= maxCountsValue) {
								for (Link outLink : downLink.getToNode()
										.getOutLinks().values()) {
									sw.writeln(outLink.getId().toString()
											+ "\t" + outLink.getCoord().getX()
											+ "\t" + outLink.getCoord().getY()
											+ "\t" + outLink.getCapacity()
											/ capPeriod);
								}
							}
							downLink = downLink.getToNode().getOutLinks()
									.values().iterator().next();
							// System.out.println("(new) downLink Id:"
							// + downLink.getId().toString());
						}

						sw.flush();
					}
				}
			}
		}
		sw.close();
		System.out.println("n_countStations=" + n_countStations);
	}
}
