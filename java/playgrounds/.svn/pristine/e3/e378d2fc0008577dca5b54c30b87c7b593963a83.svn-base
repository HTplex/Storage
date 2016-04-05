/* *********************************************************************** *
 * project: org.matsim.*
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

package playground.scnadine.tools.networks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.utils.objectattributes.ObjectAttributes;
import org.matsim.utils.objectattributes.ObjectAttributesXmlWriter;

public class NetworkConverterITNNetwork {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		Gbl.startMeasurement();
		Gbl.printElapsedTime();

		final String CONFIG_MODULE = "GPSChoiceSetGeneration";
		final Config config = ConfigUtils.loadConfig(args[0]);
		System.out.println(config.getModule(CONFIG_MODULE));


		int lineCounter = 0;
		int numberOfOneWays = 0;
		int numberOfExcludedLinks = 0;
		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		NetworkImpl network =  (NetworkImpl) scenario.getNetwork();

		File sourceFileNetworkForConversion = new File(config.findParam(CONFIG_MODULE,"sourceFileNetworkForConversion"));
		File outputDir = new File(config.findParam(CONFIG_MODULE,"outputDir"));


		ArrayList<String> linesWithProcessingErrors = new ArrayList<String>();
		ObjectAttributes linkAttributes = new ObjectAttributes();

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFileNetworkForConversion)));
		String inputLine;
		in.readLine();
		while ((inputLine = in.readLine()) != null) {			

			String[] entries = inputLine.split(","); 

			if (!entries[1].contains("Pedestrianised Street")) { 

				try {

					//first check if nodes exist, if not create them
					Id<Node> fromId = Id.create(entries[4]+"_"+entries[6], Node.class);
					Id<Node> toId = Id.create(entries[7]+"_"+entries[9], Node.class);

					
					if (fromId.toString().equals(toId.toString())) {
						System.out.println("Link "+entries[0]+" starts and ends at the same node");
						throw new Exception();
					}

					Node fromNode = network.getNodes().get(fromId);
					if (fromNode == null) {
						Coord fromCoord = scenario.createCoord(Double.parseDouble(entries[33]), Double.parseDouble(entries[34])); 
						fromNode = network.getFactory().createNode(fromId, fromCoord);
						network.addNode(fromNode);
					}
					Node toNode = network.getNodes().get(toId);
					if (toNode == null) {
						Coord toCoord = scenario.createCoord(Double.parseDouble(entries[35]), Double.parseDouble(entries[36]));
						toNode = network.getFactory().createNode(toId, toCoord);
						network.addNode(toNode);
					} 


					//determine link type and speed
					double freespeed = 14.30528;
					int linkType = 11;

					if (entries[1].contains("Motorway")) {
						if (entries[3].contains("Slip Road")) {
							freespeed = 19.66976;
							linkType = 1;
						} 
						else {
							freespeed = 24.14016;
							linkType = 2;
						}

					}
					else if (entries[1].contains("A Road")){
						if (entries[3].contains("Dual Carriageway")) {
							freespeed = 23.69312;
							linkType = 3;
						} 
						else if (entries[3].contains("Single Carriageway")) {
							freespeed = 19.66976;
							linkType = 4;
						}
						else if (entries[3].contains("Slip Road")) {
							freespeed = 19.66976;
							linkType = 5;
						}
						else {
							freespeed = 14.30528;
							linkType = 6;
						}
					}
					else if (entries[1].contains("B Road")){
						if (entries[3].contains("Dual Carriageway")) {
							freespeed = 23.69312;
							linkType = 7;
						} 
						else if (entries[3].contains("Single Carriageway")) {
							freespeed = 19.66976;
							linkType = 8;
						}
						else if (entries[3].contains("Slip Road")) {
							freespeed = 19.66976;
							linkType = 9;
						}
						else {
							freespeed = 14.30528;
							linkType = 10;
						}
					}

					//then create link(s): one link for a one-way road, two links for a two-way road
					int oneway = Integer.parseInt(entries[32]);
					if (oneway == 1) {
						
						String linkIdString = "";
						Id<Link> linkId = null;
						Link link = null;
						//include a check for direction of one-way links
						if (entries[15].contains("A")) {
							linkIdString = entries[0].concat("_TO");
							linkId = Id.create(linkIdString, Link.class);
							link = network.getFactory().createLink(linkId, fromNode, toNode);
						}
						else if (entries[15].contains("B")) {
							linkIdString = entries[0].concat("_FROM");
							linkId = Id.create(linkIdString, Link.class);
							link = network.getFactory().createLink(linkId, fromNode, toNode);
						}
						else {
							System.out.println("Direction "+entries[15]+"for link "+entries[0]+"is not defined.");
							throw new Exception();
						}
						


						//set attributes
						double length = Double.parseDouble(entries[16]);
						String roadName = entries[10];
						link.setLength(length*1000);
						link.setFreespeed(freespeed);
						linkAttributes.putAttribute(linkIdString, "type", linkType);
						linkAttributes.putAttribute(linkIdString, "roadName", roadName);
						network.addLink(link);



						numberOfOneWays++;
					}
					else {
						String linkIdString1 = entries[0].concat("_TO");
						String linkIdString2 = entries[0].concat("_FROM");
						Id<Link> linkId1 = Id.create(linkIdString1, Link.class);
						Id<Link> linkId2 = Id.create(linkIdString2, Link.class);
						Link link1 = network.getFactory().createLink(linkId1, fromNode, toNode);
						Link link2 = network.getFactory().createLink(linkId2, toNode, fromNode);

						//set attributes
						double length = Double.parseDouble(entries[16]);	
						String roadName = entries[10];
						link1.setLength(length*1000);
						link2.setLength(length*1000);
						link1.setFreespeed(freespeed);
						link2.setFreespeed(freespeed);
						linkAttributes.putAttribute(linkIdString1, "type", linkType);
						linkAttributes.putAttribute(linkIdString2, "type", linkType);
						linkAttributes.putAttribute(linkIdString1, "roadName", roadName);
						linkAttributes.putAttribute(linkIdString2, "roadName", roadName);

						network.addLink(link1);
						network.addLink(link2);
					}		
				} catch (java.lang.NumberFormatException e) {

					linesWithProcessingErrors.add(inputLine);
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					linesWithProcessingErrors.add(inputLine);
				} catch (Exception e) {
					linesWithProcessingErrors.add(inputLine);
				}
				
			}
			else{
				numberOfExcludedLinks++;
			}

			lineCounter++;
		}
		
		in.close();
		System.out.println("Network read and created");
		System.out.println("# lines read: " + lineCounter);
		System.out.println("# defect lines: " + linesWithProcessingErrors.size());
		System.out.println("# excluded links: " + numberOfExcludedLinks);
		System.out.println("# one-way links: " + numberOfOneWays);		
		System.out.println("# nodes created: " + scenario.getNetwork().getNodes().size());
		System.out.println("# links created: " + scenario.getNetwork().getLinks().size());
		System.out.println("========================================");		
		System.out.println();
		
		System.out.println("Write lines with errors...");
		if (!linesWithProcessingErrors.isEmpty()) {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File (outputDir.getPath()+System.getProperty("file.separator")+"defectLines.txt")));
			for (String line : linesWithProcessingErrors){
				out.write(line);
				out.newLine();
			}
			out.close();
		}
		System.out.println("========================================");		
		System.out.println();

		System.out.println("write cleaned network...");
		new NetworkWriter(scenario.getNetwork()).write(outputDir.toString()+"/network.xml.gz");
		System.out.println("========================================");		
		System.out.println();
		
		System.out.println("write link attributes...");
		new ObjectAttributesXmlWriter(linkAttributes).writeFile(outputDir.getPath()+System.getProperty("file.separator")+"linkAttributes.xml");
		System.out.println("========================================");		
		System.out.println();

		System.out.println("Network conversion completed.");		
		Gbl.printElapsedTime();


	}

}
