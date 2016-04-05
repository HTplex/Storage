/* *********************************************************************** *
 * project: org.matsim.*
 * TripsAnalyzer.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2013 by the members listed in the COPYING,        *
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

package playground.balac.analysis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.events.IterationEndsEvent;
import org.matsim.core.controler.events.ShutdownEvent;
import org.matsim.core.controler.events.StartupEvent;
import org.matsim.core.controler.listener.IterationEndsListener;
import org.matsim.core.controler.listener.ShutdownListener;
import org.matsim.core.controler.listener.StartupListener;
import org.matsim.core.utils.charts.XYLineChart;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.core.utils.io.UncheckedIOException;

/**
 * Calculates:
 * - average leg travel time per mode
 * - average leg travel time over all modes
 * - number of trips per mode
 * - number of trips over all modes
 * - average leg travel distance per mode
 * - average leg travel distance over all modes
 * 
 * @author cdobler
 */
public class TripsAnalyzer implements PersonDepartureEventHandler, PersonArrivalEventHandler,
		StartupListener, IterationEndsListener, ShutdownListener {

	public static String defaultTripsFileName = "tripCounts";
	public static String defaultDurationsFileName = "tripDurations";

	private final boolean autoConfig;
	
	private final Set<String> sortedModes = new TreeSet<String>();
	private final Set<Id> observedAgents;
	private final Map<Id, Double> departureTimes = new HashMap<Id, Double>();
	private final Map<String, List<Double>> legTravelTimes = new HashMap<String, List<Double>>();
	
	private final Map<Id, Id> departurePositions = new HashMap<Id, Id>();
	private final Map<String, List<Double>> legTravelDistances = new HashMap<String, List<Double>>();
	
	
	private String tripsFileName;
	private String durationsFileName;
	private String distancesFileName;
	private boolean createGraphs;
	
	private BufferedWriter tripsWriter;
	private BufferedWriter durationWriter;
	private BufferedWriter distanceWriter;
	
	private double[][] tripsHistory;
	private double[][] durationHistory;
	private double[][] distanceHistory;
	private int minIteration;
	
	private Network network;
	
	/**
	 * This is how most people will probably will use this class.
	 * It has to be created an registered as ControlerListener.
	 * Then, it auto-configures itself (register as events handler,
	 * get paths to output files, ...).
	 */
	public TripsAnalyzer() {
		
		this.autoConfig = true;

		this.createGraphs = true;
		
		// modes which are analyzed by default
		this.sortedModes.add(TransportMode.bike);
		this.sortedModes.add(TransportMode.car);
		this.sortedModes.add(TransportMode.pt);
		this.sortedModes.add(TransportMode.ride);
		this.sortedModes.add(TransportMode.walk);
		
		this.observedAgents = null;
	}
	
	public TripsAnalyzer(String tripsFileName, String durationsFileName, String distancesFileName, Set<String> modes, boolean createGraphs, Network network) {
		this(tripsFileName, durationsFileName, distancesFileName, modes, null, createGraphs, network);
	}
	
	public TripsAnalyzer(String tripsFileName, String durationsFileName, String distancesFileName, Set<String> modes,
			Set<Id> observedAgents, boolean createGraphs, Network network) {

		this.autoConfig = false;
		this.network = network;
		this.tripsFileName = tripsFileName;
		this.durationsFileName = durationsFileName;
		this.distancesFileName = distancesFileName;
		this.sortedModes.addAll(modes);
		if (observedAgents != null) {
			// make a copy to prevent people changing the set over the iterations
			this.observedAgents = new HashSet<Id>(observedAgents);			
		} else this.observedAgents = null;
		this.createGraphs = createGraphs;
	}
	
	public void setCreateGraphs(boolean createGraphs) {
		this.createGraphs = createGraphs;
	}
	
	public Set<String> getModes() {
		return this.sortedModes;
	}
	
	@Override
	public void reset(int iteration) {
		for(List<Double> modeTravelTime : legTravelTimes.values()) {
			modeTravelTime.clear();
		}
		for(List<Double> modeTravelDistance : legTravelDistances.values()) {
			modeTravelDistance.clear();
		}
	}

	@Override
	public void handleEvent(PersonArrivalEvent event) {
		
		if (observedAgents != null && !observedAgents.contains(event.getPersonId())) return;
		
		Double departureTime = departureTimes.remove(event.getPersonId());
		if (departureTime == null) throw new RuntimeException("No departure time for agent " + event.getPersonId() + " was found!");
		
		double travelTime = event.getTime() - departureTime;
		String mode = event.getLegMode();
		List<Double> modeTravelTimes = legTravelTimes.get(mode);
		if (modeTravelTimes != null) modeTravelTimes.add(travelTime);
		
		Id startPosition = departurePositions.remove(event.getPersonId());
		if (startPosition == null) throw new RuntimeException("No departure position for agent " + event.getPersonId() + " was found!");

		double distance = CoordUtils.calcDistance(this.network.getLinks().get(startPosition).getCoord(),
				this.network.getLinks().get(event.getLinkId()).getCoord());
		
		List<Double> modeTravelDistances = legTravelDistances.get(mode);
		if (modeTravelDistances != null) modeTravelDistances.add(distance);

		
	}

	@Override
	public void handleEvent(PersonDepartureEvent event) {
		if (observedAgents != null && !observedAgents.contains(event.getPersonId())) return;
		departureTimes.put(event.getPersonId(), event.getTime());
		departurePositions.put(event.getPersonId(), event.getLinkId());
		
	}

	@Override
	public void notifyStartup(final StartupEvent event) {

		Controler controler = event.getControler();	
		this.minIteration = controler.getConfig().controler().getFirstIteration();
		int maxIter = controler.getConfig().controler().getLastIteration();
		int iterations = maxIter - this.minIteration;
		this.tripsHistory = new double[this.sortedModes.size() + 1][iterations + 1];
		this.durationHistory = new double[this.sortedModes.size() + 1][iterations + 1];	
		this.distanceHistory = new double[this.sortedModes.size() + 1][iterations + 1];	
		
		if (autoConfig) {
			this.tripsFileName = event.getControler().getControlerIO().getOutputFilename(defaultTripsFileName);
			this.durationsFileName = event.getControler().getControlerIO().getOutputFilename(defaultDurationsFileName);
			
					}
		controler.getEvents().addHandler(this);


		this.tripsWriter = IOUtils.getBufferedWriter(tripsFileName + ".txt");
		this.durationWriter = IOUtils.getBufferedWriter(durationsFileName + ".txt");
		this.distanceWriter = IOUtils.getBufferedWriter(distancesFileName + ".txt");

		try {
			this.tripsWriter.write("ITERATION");
			this.durationWriter.write("ITERATION");
			this.distanceWriter.write("ITERATION");
			for (String mode : sortedModes) {
				this.tripsWriter.write("\t");
				this.tripsWriter.write(mode.toUpperCase());
				this.durationWriter.write("\t");
				this.durationWriter.write(mode.toUpperCase());
				this.distanceWriter.write("\t");
				this.distanceWriter.write(mode.toUpperCase());
				
				this.legTravelTimes.put(mode, new LinkedList<Double>());
				this.legTravelDistances.put(mode, new LinkedList<Double>());
			}
			this.tripsWriter.write("\t");
			this.tripsWriter.write("OVERALL");
			this.tripsWriter.write("\n");
			this.durationWriter.write("\t");
			this.durationWriter.write("OVERALL");
			this.durationWriter.write("\n");
			this.distanceWriter.write("\t");
			this.distanceWriter.write("OVERALL");
			this.distanceWriter.write("\n");
			
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	@Override
	public void notifyIterationEnds(IterationEndsEvent event) {
		try {
			int iteration = event.getIteration();
			this.tripsWriter.write(String.valueOf(iteration));
			this.durationWriter.write(String.valueOf(iteration));
			this.distanceWriter.write(String.valueOf(iteration));
			int index = iteration - this.minIteration;
			
			int i = 0;
			int overallTrips = 0;
			double overallTravelTime = 0.0;
			double overallTravelDistance = 0.0;
			for (String mode : sortedModes) {
				List<Double> modeTravelTimes = legTravelTimes.get(mode);
				List<Double> modeTravelDistances = legTravelDistances.get(mode);

				
				double sumTravelTimes = 0.0;
				for (double travelTime : modeTravelTimes) sumTravelTimes += travelTime;
				
				double sumTravelDistances = 0.0;
				for (double distance : modeTravelDistances) sumTravelDistances += distance;
				
				
				int modeTrips = modeTravelTimes.size();
				overallTrips += modeTrips;
				overallTravelTime += sumTravelTimes;

				double averageTravelTime = sumTravelTimes / modeTrips;
				double averageTravelDistance = sumTravelDistances / modeTrips;
				this.tripsHistory[i][index] = modeTrips;
				this.durationHistory[i][index] = averageTravelTime;
				this.distanceHistory[i][index] = averageTravelDistance;
				
				this.tripsWriter.write("\t");
				this.tripsWriter.write(String.valueOf(modeTrips));
				this.durationWriter.write("\t");
				this.durationWriter.write(String.valueOf(averageTravelTime));
				this.distanceWriter.write("\t|");
				this.distanceWriter.write(String.valueOf(averageTravelDistance));

				
				i++;
			}
			
			double averageTravelTime = overallTravelTime / overallTrips;
			double averageTravelDistance = overallTravelDistance / overallTrips;
			this.tripsHistory[sortedModes.size()][index] = overallTrips;
			this.durationHistory[sortedModes.size()][index] = averageTravelTime;
			this.distanceHistory[sortedModes.size()][index] = averageTravelDistance;
			
			this.tripsWriter.write("\t");
			this.tripsWriter.write(String.valueOf(overallTrips));
			this.tripsWriter.write("\n");
			this.durationWriter.write("\t");
			this.durationWriter.write(String.valueOf(averageTravelTime));
			this.durationWriter.write("\n");
			this.distanceWriter.write("\t");
			this.distanceWriter.write(String.valueOf(averageTravelDistance));
			this.distanceWriter.write("\n");
//			this.tripsWriter.flush();
//			this.durationWriter.flush();
//			this.tripsWriter.close();
//			this.durationWriter.close();
//			this.tripsWriter = null;
//			this.durationWriter = null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		if (this.createGraphs && event.getIteration() != this.minIteration) {
			int index = event.getIteration() - this.minIteration;

			// create chart when data of more than one iteration is available.
			XYLineChart chart;
			
			double[] iterations = new double[index + 1];
			for (int i = 0; i <= index; i++) {
				iterations[i] = i + this.minIteration;
			}
			double[] values = new double[index + 1];
			
			int i;
			
			/*
			 * average leg duration
			 */
			chart = new XYLineChart("Average Leg Travel Times Statistics", "iteration", "time");
			
			i = 0;
			for (String mode : sortedModes) {
				System.arraycopy(this.durationHistory[i], 0, values, 0, index + 1);
				chart.addSeries(mode, iterations, values);
				i++;
			}
			System.arraycopy(this.durationHistory[i], 0, values, 0, index + 1);
			chart.addSeries("overall", iterations, values);
			
			chart.addMatsimLogo();
			chart.saveAsPng(this.durationsFileName + ".png", 800, 600);
			
			
			/*
			 * average leg distance
			 */
			chart = new XYLineChart("Average Leg Travel Distances Statistics", "iteration", "distance");
			
			i = 0;
			for (String mode : sortedModes) {
				System.arraycopy(this.distanceHistory[i], 0, values, 0, index + 1);
				chart.addSeries(mode, iterations, values);
				i++;
			}
			System.arraycopy(this.distanceHistory[i], 0, values, 0, index + 1);
			chart.addSeries("overall", iterations, values);
			
			chart.addMatsimLogo();
			chart.saveAsPng(this.distancesFileName + ".png", 800, 600);
			
			
			/*
			 * number of trips
			 */
			chart = new XYLineChart("Number of Trips per Mode Statistics", "iteration", "number of trips");
			
			i = 0;
			for (String mode : sortedModes) {
				System.arraycopy(this.tripsHistory[i], 0, values, 0, index + 1);
				chart.addSeries(mode, iterations, values);
				i++;
			}
			System.arraycopy(this.tripsHistory[i], 0, values, 0, index + 1);
			chart.addSeries("overall", iterations, values);
			
			chart.addMatsimLogo();
			chart.saveAsPng(this.tripsFileName + ".png", 800, 600);
		}
	}

	@Override
	public void notifyShutdown(ShutdownEvent event) {
		try {
			if (this.tripsWriter != null) {
				this.tripsWriter.flush();
				this.tripsWriter.close();				
			}
			if (this.durationWriter != null) {
				this.durationWriter.flush();
				this.durationWriter.close();
			}
			if (this.distanceWriter != null) {
				this.distanceWriter.flush();
				this.distanceWriter.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
