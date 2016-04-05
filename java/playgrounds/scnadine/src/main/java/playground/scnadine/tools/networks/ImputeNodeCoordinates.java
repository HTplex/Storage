
/* *********************************************************************** *
 * project: posdap.*
 * ImputeNodeCoordinates.java
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
import java.util.ArrayList;

import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.utils.objectattributes.ObjectAttributes;


public class ImputeNodeCoordinates {

	/**
	 * @param args
	 */

	static double defaultValue = -999999;

	static double k = 1; // Federkonstante
	static double m = 1; // mass
	static double cMagic = 0.1; // to be determined 
	
	static int numberOfRef0Iterations = 100; // number of times the 
	static int numberOfRefLengthIterations = 10000;
	
	static ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
	static NetworkImpl network =  (NetworkImpl) scenario.getNetwork();


	static ArrayList<Node> nodesForImputation = new ArrayList<Node>();
	static ObjectAttributes nodeAttributes = new ObjectAttributes();


	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();

		File outputDir = new File("Z:\\Projekte\\RouteChoiceSetsStephane\\nodeImputation\\output\\");

		System.out.println("Read network data...");
		new MatsimNetworkReader(scenario).readFile("Z:\\Projekte\\RouteChoiceSetsStephane\\nodeImputation\\input\\chessboard_4Coords.xml");		
		System.out.println("# nodes in network before imputation: " + network.getNodes().size());
		System.out.println("# links in network before imputation: " + network.getLinks().size());
		System.out.println("-----------------------------------------");		
		System.out.println();
		
		
		NodeCoordinateImputator nodeImputation = new NodeCoordinateImputator(network, cMagic, defaultValue);

		nodeImputation.runNodeInitialisation();
		System.out.println("Nodes initialised.");


		for (int i = 0; i<100; i++) {

			nodeImputation.runCalcResultingForce("zero","posNegLin", i);

			nodeImputation.runMoveNodes();

		}
		System.out.println("RefLength = 0 done.");
		System.out.println("write RefLength = 0 network...");
		new NetworkWriter(scenario.getNetwork()).write(outputDir.toString()+"/imputedNetwork_refLength0_4Coords.xml.gz");
		System.out.println("========================================");		
		System.out.println();
		
		for (int i = 0; i<10000; i++) {

			nodeImputation.runCalcResultingForce("linkLengthDisc", "posNegLin", i);

			nodeImputation.runMoveNodes();

		}
		System.out.println("RefLength = length done.");



		System.out.println("write cleaned network...");
		new NetworkWriter(scenario.getNetwork()).write(outputDir.toString()+"/imputedNetwork_4Coords.xml.gz");
		System.out.println("========================================");		
		System.out.println();


		System.out.println("Node imputation completed in "+((System.currentTimeMillis()-startTime)/1000)+" seconds");

	}

}
