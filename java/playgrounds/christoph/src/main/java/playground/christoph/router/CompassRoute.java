/* *********************************************************************** *
 * project: org.matsim.*
 * CompassRoute.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
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

package playground.christoph.router;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.api.core.v01.population.Person;
import org.matsim.vehicles.Vehicle;

import playground.christoph.router.util.SimpleRouter;

public class CompassRoute extends SimpleRouter {

	protected static int errorCounter = 0;
	
	protected boolean removeLoops = false;
	protected boolean tabuSearch = true;
	protected int maxLinks = 50000; // maximum number of links in a created plan
	
	private final static Logger log = Logger.getLogger(CompassRoute.class);
	
	public CompassRoute(Network network) {
		super(network);
	}
	
	@Override
	public Path calcLeastCostPath(Node fromNode, Node toNode, double startTime, final Person person, final Vehicle vehicle) {
		return findRoute(fromNode, toNode, person, vehicle);
	}
	
	private Path findRoute(Node fromNode, Node toNode, final Person person, final Vehicle vehicle) {
		Node previousNode = null;
		Node currentNode = fromNode;
		Link currentLink;
		double routeLength = 0.0;
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Link> links = new ArrayList<Link>();

		nodes.add(fromNode);
				
		while(!currentNode.equals(toNode)) {
			// stop searching if to many links in the generated Route...

			if (nodes.size() > maxLinks) {
				log.warn("Route has reached the maximum allowed length - break!");
				errorCounter++;
				break;
			}
			
			Link[] linksArray = currentNode.getOutLinks().values().toArray(new Link[currentNode.getOutLinks().size()]);
				
			// if a route should not return to the previous node from the step before
			if (tabuSearch) linksArray = tabuSelector.getLinks(linksArray, previousNode);
			
			if (linksArray.length == 0) {
				log.error("Looks like Node is a dead end. Routing could not be finished!");
				errorCounter++;
				break;
			}
			
			Link nextLink = null;
			double angle = Math.PI;	// worst possible start value
			
			// get the Link with the nearest direction to the destination node
			for(int i = 0; i < linksArray.length; i++) {
				
				if(linksArray[i] instanceof Link) {
					double newAngle = calcAngle (fromNode, toNode, linksArray[i].getToNode());
					
					//if the new direction is better than the existing one
					if (newAngle <= angle) {
						angle = newAngle;
						nextLink = linksArray[i];
					}				
				} else {
					log.error("Return object was not from type Link! Class " + linksArray[i] + " was returned!");
				}	
			}
				
			
			// make the chosen link to the current link
			if(nextLink != null) {
				currentLink = nextLink;
				previousNode = currentNode;
				currentNode = currentLink.getToNode();
				routeLength = routeLength + currentLink.getLength();
			} else {
				log.error("Number of Links " + linksArray.length);
				log.error("Return object was not from type Link! Class " + nextLink + " was returned!");
				break;
			}

			nodes.add(currentNode);
			links.add(currentLink);
		}	// while(!currentNode.equals(toNode))

		Path path = new Path(nodes, links, 0, 0);

		if (maxLinks == path.links.size()) {
//			log.info("LinkCount " + route.getLinkRoute().length + " distance " + route.getDist());
//			log.info("LinkCount " + path.links.size() + " distance " + routeLength);
		}
		
		if (removeLoops) loopRemover.removeLoops(path);
				
		return path;
	}
	
	public static int getErrorCounter() {
		return errorCounter;
	}
	
	public static void setErrorCounter(int i) {
		errorCounter = i;
	}
	
	protected double calcAngle(Node currentNode, Node toNode, Node nextLinkNode) {
		double v1x = nextLinkNode.getCoord().getX() - currentNode.getCoord().getX();
		double v1y = nextLinkNode.getCoord().getY() - currentNode.getCoord().getY();

		double v2x = toNode.getCoord().getX() - currentNode.getCoord().getX();
		double v2y = toNode.getCoord().getY() - currentNode.getCoord().getY();

		/* 
		 * If the link returns to the current Node no angle can't be calculated.
		 * choosing this link would be a bad idea, so return the worst possible angle.
		 * 
		 */
		if ((v1x == 0.0) && (v1y == 0.0)) return Math.PI;
		
		/*
		 * If the nextLinkNode is the TargetNode return 0.0 so this link is chosen.
		 */
		if (nextLinkNode.equals(toNode)) return 0.0;
		
		double cosPhi = (v1x*v2x + v1y*v2y)/(java.lang.Math.sqrt(v1x*v1x+v1y*v1y) * java.lang.Math.sqrt(v2x*v2x+v2y*v2y));
		
		double phi = java.lang.Math.acos(cosPhi);

		/* 
		 * If the angle is exactly 180° return a value that is slightly smaller.
		 * Reason: if there are only links that return to the current node and links
		 * with an angle of 180° a loop could be generated.
		 * Solution: slightly reduce angles of 180° so one of them is chosen. 
		 */
		if(phi == Math.PI) phi = Math.PI - Double.MIN_VALUE;
		
		//if (phi == Double.NaN)
		if(String.valueOf(phi).equals("NaN")) {
			log.error("v1x " + v1x);
			log.error("v1y " + v1y);
			log.error("v2x " + v2x);
			log.error("v2y " + v2y);
		}
		
		return phi;
	}

	@Override
	public SimpleRouter createInstance() {
		return new CompassRoute(network);
	}
}