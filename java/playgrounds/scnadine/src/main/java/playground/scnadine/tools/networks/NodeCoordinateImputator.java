
/* *********************************************************************** *
 * project: posdap.*
 * NodeCoordinateImputator.java
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

import java.util.ArrayList;

import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.network.NetworkImpl;
import org.matsim.utils.objectattributes.ObjectAttributes;

public class NodeCoordinateImputator {


	//////////////////////////////////////////////////////////////////////
	// member variables
	//////////////////////////////////////////////////////////////////////


	private double defaultValue = -999999;

	private long randomSeed = 4711;


	private double k = 1; // Federkonstante
	private double m = 1; // mass
	private double cMagic = 0.1; // to be determined 

	private NetworkImpl network;

	private ArrayList<Node> nodesForImputation = new ArrayList<Node>();
	private ObjectAttributes nodeAttributes = new ObjectAttributes();


	//////////////////////////////////////////////////////////////////////
	// constructors
	//////////////////////////////////////////////////////////////////////

	public NodeCoordinateImputator(NetworkImpl network, double cMagic, double defaultValue) {
		this.cMagic = cMagic;
		this.network = network;
		this.defaultValue = defaultValue;
	}





	//////////////////////////////////////////////////////////////////////
	// run methods
	//////////////////////////////////////////////////////////////////////

	public void runNodeInitialisation() {

		MatsimRandom.getRandom().setSeed(randomSeed);
		MatsimRandom.getRandom().nextDouble();


		double sumX = 0;
		double sumY = 0;
		int nofFixedNodes = 0;

		for (Node node : this.network.getNodes().values()) {			
			if (node.getCoord().getX() == this.defaultValue || node.getCoord().getY() == this.defaultValue) {
				this.nodesForImputation.add(node);				
			}
			else {
				sumX += node.getCoord().getX();
				sumY += node.getCoord().getY();
				nofFixedNodes++;
			}
		}

		//determine average x and y for new defaults

		double averageX = sumX / nofFixedNodes;
		double averageY = sumY / nofFixedNodes;

		for (Node node : this.nodesForImputation) {
			node.getCoord().setX(averageX + MatsimRandom.getRandom().nextDouble());
			node.getCoord().setY(averageY + MatsimRandom.getRandom().nextDouble());
		}

		System.out.println("# nodes for imputation = "+nodesForImputation.size());

	}


	/**
	 * @param refLengthCalculation
	 *            Configures the way refLength is calculated, 3 Options implemented:
	 *            zero: refLength = 0, 
	 *            linkLength: refLength = link.getLength(), 
	 *            linkLengthDisc: refLength = link.getLength() but with decreasing discount for the first 100 Iterations        
	 * 
	 * @param forceCalculation
	 *            Configures the way the forces are calculated
	 *            posLin: only linear positive forces, posNegLin: linear positive and negative forces, posLinNegQuad: positive forces linear, negative forces quadratic
	 *             				
	 * @param i
	 *            Current iteration number
	 * @throws Exception 
	 */

	public void runCalcResultingForce(String refLengthCalculation, String forceCalculation, int i) throws Exception {

		for (Node node : this.nodesForImputation) {

			double forceX = 0;
			double forceY = 0;

			for (Link link : node.getInLinks().values()) {
				Node fromNode = link.getFromNode();


				/*Calculation of the reference length*/				

				double refLength;
				if (refLengthCalculation.equals("zero")) {
					refLength = 0;				
				}
				else if (refLengthCalculation.equals("linkLength")) {
					refLength = link.getLength();
				}
				else if (refLengthCalculation.equals("linkLengthDisc")) {
					if (i < 100) {
						refLength = i / 100.0 * link.getLength();
					}
					else {
						refLength = link.getLength();
					}
				}
				else {
					throw new Exception("Value "+refLengthCalculation+" is not defined for variable refLengthCalculation.");
				}




				/*Calculation of the force produced by the link*/
				double deltaX = fromNode.getCoord().getX() - node.getCoord().getX();
				double deltaY = fromNode.getCoord().getY() - node.getCoord().getY();
				double currentDist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (forceCalculation.equals("posLin")) {

					if (currentDist > 0.01 && currentDist > refLength) {
						double elongation = currentDist - refLength;
						double forceStrength = elongation * this.k;
						forceX += deltaX / currentDist * forceStrength;
						forceY += deltaY / currentDist * forceStrength;		
					}

				}
				else if (forceCalculation.equals("posNegLin")) {

					if (currentDist > 0.01) {
						double elongation = currentDist - refLength;
						double forceStrength = elongation * this.k;
						forceX += deltaX / currentDist * forceStrength;
						forceY += deltaY / currentDist * forceStrength;		
					}

				}
				else if (forceCalculation.equals("posLinNegQuad")) {

					if (currentDist >= refLength) {
						double elongation = currentDist - refLength;
						double forceStrength = elongation * this.k;
						forceX += deltaX / currentDist * forceStrength;
						forceY += deltaY / currentDist * forceStrength;					
					}
					else {
						double tempFact = (currentDist * currentDist) / (refLength * refLength) - currentDist / refLength;
						forceX += tempFact * deltaX; 
						forceY += tempFact * deltaY; 
					}

				}
				else if (forceCalculation.equals("quad")) {


					double elongation = (currentDist - refLength) * (currentDist - refLength);
					double forceStrength = elongation * this.k;
					forceX += deltaX / currentDist * forceStrength;
					forceY += deltaY / currentDist * forceStrength;

				}
				else {
					throw new Exception("Value "+forceCalculation+" is not defined for variable forceCalculation.");
				}

			}




			for (Link link : node.getOutLinks().values()) {
				Node toNode = link.getToNode();


				/*Calculation of the reference length*/				

				double refLength;
				if (refLengthCalculation.equals("zero")) {
					refLength = 0;				
				}
				else if (refLengthCalculation.equals("linkLength")) {
					refLength = link.getLength();
				}
				else if (refLengthCalculation.equals("linkLengthDisc")) {
					if (i < 100) {
						refLength = i / 100.0 * link.getLength();
					}
					else {
						refLength = link.getLength();
					}
				}
				else {
					throw new Exception("Value "+refLengthCalculation+" is not defined for variable refLengthCalculation.");
				}


				/*Calculation of the force produced by the link*/
				double deltaX = toNode.getCoord().getX() - node.getCoord().getX();
				double deltaY = toNode.getCoord().getY() - node.getCoord().getY();
				double currentDist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (forceCalculation.equals("posLin")) {

					if (currentDist > 0.01 && currentDist > refLength) {
						double elongation = currentDist - refLength;
						double forceStrength = elongation * this.k;
						forceX += deltaX / currentDist * forceStrength;
						forceY += deltaY / currentDist * forceStrength;		
					}

				}
				else if (forceCalculation.equals("posNegLin")) {

					if (currentDist > 0.01) {
						double elongation = currentDist - refLength;
						double forceStrength = elongation * this.k;
						forceX += deltaX / currentDist * forceStrength;
						forceY += deltaY / currentDist * forceStrength;		
					}

				}
				else if (forceCalculation.equals("posLinNegQuad")) {

					if (currentDist >= refLength) {
						double elongation = currentDist - refLength;
						double forceStrength = elongation * this.k;
						forceX += deltaX / currentDist * forceStrength;
						forceY += deltaY / currentDist * forceStrength;					
					}
					else {
						double tempFact = (currentDist * currentDist) / (refLength * refLength) - currentDist / refLength;
						forceX += tempFact * deltaX; 
						forceY += tempFact * deltaY; 
					}

				}
				else if (forceCalculation.equals("quad")) {

					if (currentDist > 0.01) {
						double elongation = (currentDist - refLength) * (currentDist - refLength);
						double forceStrength = elongation * this.k;
						forceX += deltaX / currentDist * forceStrength;
						forceY += deltaY / currentDist * forceStrength;
					}

				}
				else {
					throw new Exception("Value "+forceCalculation+" is not defined for variable forceCalculation.");
				}



			}


			this.nodeAttributes.putAttribute(node.getId().toString(), "forceX", new Double(forceX));
			this.nodeAttributes.putAttribute(node.getId().toString(), "forceY", new Double(forceY));

		}

	}



	public void runMoveNodes() {		

		for (Node node : this.nodesForImputation) {

			double speedX = ((Double) this.nodeAttributes.getAttribute(node.getId().toString(), "forceX")) / this.m * this.cMagic;			
			double newX = node.getCoord().getX() + speedX;
			node.getCoord().setX(newX);

			double speedY = ((Double) this.nodeAttributes.getAttribute(node.getId().toString(), "forceY")) / this.m * this.cMagic;			
			double newY = node.getCoord().getY() + speedY;
			node.getCoord().setY(newY);

		}

	}


	public void setNodesForImputation(ArrayList<Node> nodesForImputation) {
		this.nodesForImputation = nodesForImputation;

	}



}
