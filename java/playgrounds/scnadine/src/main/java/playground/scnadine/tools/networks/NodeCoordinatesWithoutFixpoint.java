
/* *********************************************************************** *
 * project: posdap.*
 * NodeCoordinatesWithoutFixpoint.java
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
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;

public class NodeCoordinatesWithoutFixpoint {

	/**
	 * @param args
	 */
	
	static double defaultValue = -999999;
	
	static long randomSeed = 4711;
	
	static double cMagic = 0.00001;
	
	static double xMax = 9000;
	static double yMax = 9000;
	
	static ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
	static NetworkImpl network =  (NetworkImpl) scenario.getNetwork();
	
	
	static NodeCoordinateImputator nodeImputation = new NodeCoordinateImputator(network, cMagic, defaultValue);
	
	public static void main(String[] args) throws Exception {

		
		long startTime = System.currentTimeMillis();
		
		File outputDir = new File("Z:\\Projekte\\RouteChoiceSetsStephane\\nodeImputation\\output\\");
		
		System.out.println("Read network data...");
//		new MatsimNetworkReader(scenario).readFile("Z:\\Projekte\\RouteChoiceSetsStephane\\nodeImputation\\input\\chessboardWithDiagonals_NoCoords.xml");
		new MatsimNetworkReader(scenario).readFile("Z:\\Projekte\\RouteChoiceSetsStephane\\nodeImputation\\input\\chessboard_4Coords.xml");		
		System.out.println("# nodes in network before imputation: " + network.getNodes().size());
		System.out.println("# links in network before imputation: " + network.getLinks().size());
		System.out.println("-----------------------------------------");		
		System.out.println();
		
		
		
		
		
		nodeInitialisation();
		
		new NetworkWriter(scenario.getNetwork()).write(outputDir.toString()+"/initilisation.xml");
		
		int numberOfIterations = 10000;
		
		for (int i = 0; i<numberOfIterations; i++) {

			expand();
			
			if (i%(numberOfIterations/10) == 0) 
				new NetworkWriter(scenario.getNetwork()).write(outputDir.toString()+"/expansion_"+i+".xml");
			
			
//			nodeImputation.runCalcResultingForce("linkLength","posNegLin", i);
			
//			nodeImputation.runCalcResultingForce("zero","posNegLin", i);
			
			nodeImputation.runCalcResultingForce("zero","quad", i);

			nodeImputation.runMoveNodes();
			
			if (i%(numberOfIterations/10) == 0) 
				new NetworkWriter(scenario.getNetwork()).write(outputDir.toString()+"/contraction_"+i+".xml");

		}
		
		
		System.out.println("write cleaned network...");
		new NetworkWriter(scenario.getNetwork()).write(outputDir.toString()+"/imputedNetwork.xml");
		System.out.println("========================================");		
		System.out.println();
		
		
		System.out.println("Node imputation completed in "+((System.currentTimeMillis()-startTime)/1000)+" seconds");
		

	}
	
	
	public static void nodeInitialisation(){
		
		MatsimRandom.getRandom().setSeed(randomSeed);
		MatsimRandom.getRandom().nextDouble();
		ArrayList<Node> nodesForImputation = new ArrayList<Node>();
		
		for (Node node : network.getNodes().values()) {
			
			double x;
			double y;
			
			double randomX = MatsimRandom.getRandom().nextDouble();			
			if (randomX <= 0.5) {
				 x = MatsimRandom.getRandom().nextDouble() * - 10;
			}
			else {
				x = MatsimRandom.getRandom().nextDouble() * 10;
			}
			
			double randomY = MatsimRandom.getRandom().nextDouble();			
			if (randomY <= 0.5) {
				 y = MatsimRandom.getRandom().nextDouble() * - 10;
			}
			else {
				y = MatsimRandom.getRandom().nextDouble() * 10;
			}
			
			node.getCoord().setX(x);
			node.getCoord().setY(y);
			nodesForImputation.add(node);
			
		}
		
		nodeImputation.setNodesForImputation(nodesForImputation);
		
	}
	
	
	public static void expand(){
		
		/*Calculate current expansion of the network*/		
		double xMinCurrent = Double.MAX_VALUE;
		double xMaxCurrent = Double.NEGATIVE_INFINITY;
		
		double yMinCurrent = Double.MAX_VALUE;
		double yMaxCurrent = Double.NEGATIVE_INFINITY;
		
		double sumX = 0;
		double sumY = 0;
		

	
		
		
		for (Node node : network.getNodes().values()) {
			
			if (node.getCoord().getX() < xMinCurrent) xMinCurrent = node.getCoord().getX();
			if (node.getCoord().getX() > xMaxCurrent) xMaxCurrent = node.getCoord().getX();
			
			if (node.getCoord().getY() < yMinCurrent) yMinCurrent = node.getCoord().getY();
			if (node.getCoord().getY() > yMaxCurrent) yMaxCurrent = node.getCoord().getY();	
			
			sumX += node.getCoord().getX();
			sumY += node.getCoord().getY();			
		}
		
		
//		System.out.println("sumX = "+sumX+", sumY = "+sumY);
//		System.out.println("xMinCurrent = "+xMinCurrent+", xMaxCurrent = "+xMaxCurrent);
//		System.out.println("yMinCurrent = "+yMinCurrent+", yMaxCurrent = "+yMaxCurrent);
		double averageXCurrent = sumX /(double) network.getNodes().size();
		double averageYCurrent = sumY /(double) network.getNodes().size();
		
//		System.out.println("Average x = "+averageXCurrent+", average y = "+averageYCurrent);
		
		
		
		/*Calculate expansion factors*/
		double expFactorX = xMax / (xMaxCurrent - xMinCurrent);
		double expFactorY = yMax / (yMaxCurrent - yMinCurrent);
		
//		System.out.println("exp factor X = "+expFactorX+", exp factor Y = "+expFactorY);
	
		
		/*Expand network by multiplying node coordinates with expansion factor*/
		
		for (Node node : network.getNodes().values()) {
			double x;
			double y;
			
			x = (node.getCoord().getX() - averageXCurrent)* expFactorX;
//			y = (node.getCoord().getY() - averageYCurrent)* expFactorY;
			y = (node.getCoord().getY() - averageYCurrent)* expFactorX;
			
		
			
			node.getCoord().setX(x);
			node.getCoord().setY(y);
		}
		
		
		
	}
	
	
	

}
