
/* *********************************************************************** *
 * project: posdap.*
 * GenerateChessboardNetwork.java
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

import java.io.File;

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


public class GenerateChessboardNetwork {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Generate chessboard network...");

		Gbl.startMeasurement();
		Gbl.printElapsedTime();

		final String CONFIG_MODULE = "Tools";
		final Config config = ConfigUtils.loadConfig(args[0]);
		System.out.println(config.getModule(CONFIG_MODULE));

		/*Set parameters*/
		int gridSize = Integer.parseInt(config.findParam(CONFIG_MODULE, "gridSize"));
		boolean oneway = Boolean.parseBoolean(config.findParam(CONFIG_MODULE, "oneWayNetwork"));
		File targetDir = new File(config.findParam(CONFIG_MODULE,"targetdir"));
		System.out.println("Grid size = "+gridSize);


		/*Generate network*/		
		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		NetworkImpl network =  (NetworkImpl) scenario.getNetwork();


		/*Generate nodes*/		
		for (int i = 0; i < gridSize; i++) {			
			for (int j= 0; j < gridSize; j++) {				
				Id<Node> id = Id.create(i*gridSize+j+"", Node.class);		
				Coord coord = scenario.createCoord(i * 1000, j * 1000); 
				Node node = network.getFactory().createNode(id, coord);
				network.addNode(node);				
			}					
		}

		System.out.println("Number of nodes = "+network.getNodes().keySet().size());
		/*Generate links
		 * - at the moment only for two way networks
		 * TODO: generation of one-way networks*/

		if (!oneway) {

			int linkIdInt = 0;

			/*Generate northward and southward links*/
			System.out.println("Generate northward and southward links");
			for (int i = 0; i < gridSize; i++) {			
				for (int j= 0; j < gridSize-1; j++) {	
					Node fromNode = network.getNodes().get(Id.create(i*gridSize+j+"", Node.class));	
					Node toNode = network.getNodes().get(Id.create(i*gridSize+j+1+"", Node.class));
					Id<Link> linkIdN = Id.create(linkIdInt+"_N", Link.class);
					Id<Link> linkIdS = Id.create(linkIdInt+"_S", Link.class);
					Link linkN = network.getFactory().createLink(linkIdN, fromNode, toNode);
					Link linkS = network.getFactory().createLink(linkIdS, toNode, fromNode);					
					linkN.setLength(1000);
					linkN.setFreespeed(15);
					linkS.setLength(1000);
					linkS.setFreespeed(15);					
					network.addLink(linkN);
					network.addLink(linkS);
					linkIdInt++;
					
				}
				
			}


			/*Generate eastward and westward links*/
			System.out.println("Generate eastward and westward links");
			for (int i = 0; i < gridSize; i++) {	
				for (int j= 0; j < gridSize-1; j++) {
					Node fromNode = network.getNodes().get(Id.create(i+j*gridSize+"", Node.class));	
					Node toNode = network.getNodes().get(Id.create(i+j*gridSize+gridSize+"", Node.class));
					Id<Link> linkIdE = Id.create(linkIdInt+"_E", Link.class);
					Id<Link> linkIdW = Id.create(linkIdInt+"_W", Link.class);
					Link linkE = network.getFactory().createLink(linkIdE, fromNode, toNode);
					Link linkW = network.getFactory().createLink(linkIdW, toNode, fromNode);
					linkE.setLength(1000);
					linkE.setFreespeed(15);
					linkW.setLength(1000);
					linkW.setFreespeed(15);
					network.addLink(linkE);
					network.addLink(linkW);
					linkIdInt++;
				}
			}



		}
		else {
			System.out.println("This function is not supported yet");
		}
		System.out.println("Number of links = "+network.getLinks().keySet().size());
		System.out.println();


		/*Write resulting network*/
		new NetworkWriter(scenario.getNetwork()).write(targetDir.toString()+"/chessboard"+gridSize+"by"+gridSize+"network.xml");

		System.out.println("Network generation completed.");		
		Gbl.printElapsedTime();
		System.out.println("========================================");

	}

}
