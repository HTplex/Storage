
/* *********************************************************************** *
 * project: posdap.*
 * GeneratePTNetwork.java
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
import java.util.HashSet;
import java.util.Set;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;



public class GeneratePTNetwork {
	
	
	public static void main(String[] args) {

		System.out.println("Generate public transport network...");

		Gbl.startMeasurement();
		Gbl.printElapsedTime();

		final String CONFIG_MODULE = "Tools";
		final Config config = ConfigUtils.loadConfig(args[0]);
		System.out.println(config.getModule(CONFIG_MODULE));

		/*Set parameters*/
		boolean oneway = Boolean.parseBoolean(config.findParam(CONFIG_MODULE, "oneWayNetwork"));
		File targetDir = new File(config.findParam(CONFIG_MODULE,"targetdir"));

		/*Read underlying car network and public transport routes*/		
		ScenarioImpl carScenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		NetworkImpl network =  (NetworkImpl) carScenario.getNetwork();
		new MatsimNetworkReader(carScenario).readFile(config.findParam(CONFIG_MODULE,"network"));
		
		File ptLines = new File(config.findParam(CONFIG_MODULE,"sourceFile"));  

		
		System.out.println("# nodes in car network = "+network.getNodes().size());
		System.out.println("# links in car network = "+network.getLinks().size());
			
		/*Create pt scenarion and network*/
		ScenarioImpl ptScenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		NetworkImpl ptNetwork =  (NetworkImpl) ptScenario.getNetwork();
		
		/*Definde sets of modes*/
		Set<String> modes = new HashSet<String>();
		modes.add("car");
		modes.add("bus");
		
		int lineCounter = 0;
		
		try {
			BufferedReader inC = new BufferedReader(new InputStreamReader(new FileInputStream(ptLines)));
			inC.readLine();
			String inputLine = null;
		
		/*Generate nodes and links while passing through the PT lines*/
			
			while((inputLine = inC.readLine()) != null) {
				String[] entries = inputLine.split("\t");
				
				int i=2;
				while (!entries[i].equals("-1")) {
					
					Link link = network.getLinks().get(Id.create(entries[i], Link.class));
					
					Id<Node> fromNodeId = link.getFromNode().getId();
					if (!ptNetwork.getNodes().containsKey(fromNodeId)) {
						Coord coord = ptScenario.createCoord(link.getFromNode().getCoord().getX(), link.getFromNode().getCoord().getY()); 
						Node node = network.getFactory().createNode(fromNodeId, coord);
						ptNetwork.addNode(node);						
					}
					
					Id<Node> toNodeId = link.getToNode().getId();
					if (!ptNetwork.getNodes().containsKey(toNodeId)) {
						Coord coord = ptScenario.createCoord(link.getToNode().getCoord().getX(), link.getToNode().getCoord().getY()); 
						Node node = network.getFactory().createNode(toNodeId, coord);
						ptNetwork.addNode(node);
					}					
					
					Id<Link> linkId = link.getId();
					if (!ptNetwork.getLinks().containsKey(linkId)) {		
						Link ptLink = ptNetwork.getFactory().createLink(linkId, ptNetwork.getNodes().get(fromNodeId), ptNetwork.getNodes().get(toNodeId));
						ptLink.setLength(link.getLength());
						ptLink.setFreespeed(link.getFreespeed());
						ptLink.setAllowedModes(modes);
						ptNetwork.addLink(ptLink);
					}
					
					if (!oneway) {
						String linkIdString = link.getId().toString();
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
						
						Id<Link> backLinkId = Id.create(linkIdString, Link.class);
												
						if (backLinkId!= null && !ptNetwork.getLinks().containsKey(backLinkId)) {
							Link ptLink = ptNetwork.getFactory().createLink(backLinkId, ptNetwork.getNodes().get(toNodeId), ptNetwork.getNodes().get(fromNodeId));
							ptLink.setLength(link.getLength());
							ptLink.setFreespeed(link.getFreespeed());
							ptLink.setAllowedModes(modes);
							ptNetwork.addLink(ptLink);
						}
						
					}
					
					i++;
				}
				lineCounter++;

			}			
			inC.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("# public transport lines = "+lineCounter);
		System.out.println("# nodes in PT network = "+ptNetwork.getNodes().size());
		System.out.println("# links in PT network = "+ptNetwork.getLinks().size());
		

		/*Write resulting network*/
		new NetworkWriter(ptScenario.getNetwork()).write(targetDir.toString()+"/publicTransportNetwork.xml");

		System.out.println("Public transport network generation completed.");		
		Gbl.printElapsedTime();
		System.out.println("========================================");

	}

}
