
/* *********************************************************************** *
 * project: posdap.*
 * GeneratePTSchedule.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,     *
 *                   LICENSE and WARRANTY file.                            *
 * email           : posdap at ivt dot baug dot ethz dot ch                *
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

package playground.scnadine.tools.networks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.population.routes.ModeRouteFactory;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.pt.transitSchedule.TransitScheduleFactoryImpl;
import org.matsim.pt.transitSchedule.api.TransitLine;
import org.matsim.pt.transitSchedule.api.TransitRoute;
import org.matsim.pt.transitSchedule.api.TransitRouteStop;
import org.matsim.pt.transitSchedule.api.TransitSchedule;
import org.matsim.pt.transitSchedule.api.TransitScheduleFactory;
import org.matsim.pt.transitSchedule.api.TransitScheduleWriter;
import org.matsim.pt.transitSchedule.api.TransitStopFacility;


public class GeneratePTSchedule {


	public static void main(String[] args) {

		System.out.println("Generate public transport schedule without departure times...");

		Gbl.startMeasurement();
		Gbl.printElapsedTime();

		final String CONFIG_MODULE = "Tools";
		final Config config = ConfigUtils.loadConfig(args[0]);
		System.out.println(config.getModule(CONFIG_MODULE));

		boolean oneway = Boolean.parseBoolean(config.findParam(CONFIG_MODULE, "oneWayNetwork"));
		File targetDir = new File(config.findParam(CONFIG_MODULE,"targetdir"));

		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		NetworkImpl network =  (NetworkImpl) scenario.getNetwork();
		new MatsimNetworkReader(scenario).readFile(config.findParam(CONFIG_MODULE,"network"));

		File ptLines = new File(config.findParam(CONFIG_MODULE,"sourceFile")); 


		TransitScheduleFactory builder = new TransitScheduleFactoryImpl();
		 ModeRouteFactory routeFactory = new ModeRouteFactory();
		TransitSchedule schedule = builder.createTransitSchedule();


		int lineCounter = 0;

		try {
			BufferedReader inC = new BufferedReader(new InputStreamReader(new FileInputStream(ptLines)));
			inC.readLine();
			String inputLine = null;



			while((inputLine = inC.readLine()) != null) {

				String[] entries = inputLine.split("\t");

				System.out.println("Processing line number "+entries[0]);

				LinkedList<Id<Link>> routeLinkIds = new LinkedList<Id<Link>>();
				List<Id<Link>> stopLinkIds = new ArrayList<Id<Link>>();
				List<Id<Link>> stopLinkIdsBack = new ArrayList<Id<Link>>();

				/*read links of the line route*/

				int i=2;
				while (!entries[i].equals("-1")) {					
					Id<Link> linkId = Id.create(entries[i], Link.class); 
					routeLinkIds.add(linkId);				
					i++;
				}
				System.out.println("# links in line read = "+routeLinkIds.size());

				/*read stops of the line route*/
				i++;
				while (!entries[i].equals("-1")) {				
					Id<Link> linkId = Id.create(entries[i], Link.class); 
					stopLinkIds.add(linkId);
					
					if (!oneway) {
						String idString = null;
						if (entries[i].contains("_E")) {
							idString = entries[i].replace("_E", "_W");
						}
						if (entries[i].contains("_W")) {
							idString = entries[i].replace("_W", "_E");
						}
						if (entries[i].contains("_N")) {
							idString = entries[i].replace("_N", "_S");
						}
						if (entries[i].contains("_S")) {
							idString = entries[i].replace("_S", "_N");
						}												
						Id<Link> linkId2 = Id.create(idString, Link.class); 
						stopLinkIdsBack.add(linkId2);						
					}			
					
					i++;
				}
				System.out.println("# stops in line = "+stopLinkIds.size());

				
				

				/*create stop facilities and store stop route*/
				List<TransitRouteStop> stops = new ArrayList<TransitRouteStop>();
				List<TransitRouteStop> stopsBack = new ArrayList<TransitRouteStop>();
				int numberOfStops = 0;
				for (Id<Link> linkId : stopLinkIds) {

					TransitStopFacility stop = null;
					
					if (!schedule.getFacilities().containsKey(linkId)) {
						Link link = network.getLinks().get(linkId);				
						Coord coord = scenario.createCoord((link.getFromNode().getCoord().getX()+link.getToNode().getCoord().getX())/2, (link.getFromNode().getCoord().getY()+link.getToNode().getCoord().getY())/2); 
						stop = builder.createTransitStopFacility(Id.create(linkId, TransitStopFacility.class), coord,false);
						schedule.addStopFacility(stop);											
					}
					else {
						stop = schedule.getFacilities().get(linkId);
					}
					TransitRouteStop routeStop = builder.createTransitRouteStop(stop, 300 * numberOfStops, 300 * numberOfStops + 30);
					stops.add(routeStop);
					
					numberOfStops++;
				}
				
				if (!oneway) {
					TransitStopFacility stop = null;
					
					numberOfStops = 0;
					for (Id<Link> linkId : stopLinkIdsBack) {

						if (!schedule.getFacilities().containsKey(linkId)) {
							Link link = network.getLinks().get(linkId);				
							Coord coord = scenario.createCoord((link.getFromNode().getCoord().getX()+link.getToNode().getCoord().getX())/2, (link.getFromNode().getCoord().getY()+link.getToNode().getCoord().getY())/2); 
							stop = builder.createTransitStopFacility(Id.create(linkId, TransitStopFacility.class), coord,false);
							schedule.addStopFacility(stop);
							
						}
						else {
							stop = schedule.getFacilities().get(linkId);							
						}
						
						TransitRouteStop routeStop = builder.createTransitRouteStop(stop, 300 * numberOfStops, 300 * numberOfStops + 30);
						stopsBack.add(routeStop);
						
						numberOfStops++;
					}					
				
				}
				System.out.println("# stops in schedule = "+schedule.getFacilities().size());
				
				
				/*Create transit route*/
				
				Id<TransitLine> lineId = Id.create(entries[0]+"_To", TransitLine.class);
				NetworkRoute route = (NetworkRoute) routeFactory.createRoute(TransportMode.car, routeLinkIds.get(0), routeLinkIds.get(routeLinkIds.size()-1));
				route.setLinkIds(routeLinkIds.get(0), routeLinkIds.subList(1, routeLinkIds.size()-1), routeLinkIds.get(routeLinkIds.size()-1));
				
				TransitRoute transitRoute = builder.createTransitRoute(Id.create("0", TransitRoute.class), route, stops, entries[1]);
				TransitLine line = builder.createTransitLine(lineId);
				line.addRoute(transitRoute);
				schedule.addTransitLine(line);
				
				if (!oneway) {
					
					List<Id<Link>> routeLinkIdsBack = new ArrayList<Id<Link>>();
					ListIterator<Id<Link>> it = routeLinkIds.listIterator(routeLinkIds.size());
					while (it.hasPrevious()) {
						String linkIdString = it.previous().toString();
							if (linkIdString.contains("_E")) {
								linkIdString = linkIdString.replace("_E", "_W");
							}
							else if (linkIdString.contains("_W")) {
								linkIdString = linkIdString.replace("_W", "_E");
							}
							else if (linkIdString.contains("_S")) {
								linkIdString = linkIdString.replace("_S", "_N");
							}
							else if (linkIdString.contains("_N")) {
								linkIdString = linkIdString.replace("_N", "_S");
							}						
							Id<Link> linkId2 = Id.create(linkIdString, Link.class);						
						routeLinkIdsBack.add(linkId2);
					}
					
					Id<Link> lineIdBack = Id.create(entries[0]+"_Back", Link.class);
					NetworkRoute routeBack = (NetworkRoute) routeFactory.createRoute(TransportMode.car, routeLinkIdsBack.get(0), routeLinkIdsBack.get(routeLinkIdsBack.size()-1));
					routeBack.setLinkIds(routeLinkIdsBack.get(0), routeLinkIdsBack.subList(1, routeLinkIdsBack.size()-1), routeLinkIdsBack.get(routeLinkIds.size()-1));
					TransitRoute transitRouteBack = builder.createTransitRoute(Id.create("0", TransitRoute.class), routeBack, stopsBack, entries[1]);
					TransitLine lineBack = builder.createTransitLine(Id.create(lineIdBack, TransitLine.class));
					lineBack.addRoute(transitRouteBack);
					schedule.addTransitLine(lineBack);
					
					
				}
				
				

				lineCounter++;
			}
			inC.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		/*Write transit schedule*/
		TransitScheduleWriter writer = new TransitScheduleWriter(schedule);
		writer.writeFile(targetDir.toString()+"/publicTransportSchedule.xml");
		
		System.out.println("# public transport lines = "+lineCounter);
		System.out.println("# one-directional public transport lines = "+schedule.getTransitLines().size());
		
		
		System.out.println("Public transport schedule generation completed.");		
		Gbl.printElapsedTime();
		System.out.println("========================================");
	}

}
