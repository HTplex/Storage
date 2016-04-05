/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2010 by the members listed in the COPYING,        *
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

package playground.anhorni.LEGO.miniscenario.create;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.network.NetworkFactoryImpl;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.facilities.ActivityFacility;
import org.matsim.facilities.ActivityFacilityImpl;
import org.matsim.facilities.ActivityOptionImpl;
import org.matsim.facilities.FacilitiesWriter;
import org.matsim.facilities.OpeningTimeImpl;

public class CreateNetwork {
	private ScenarioImpl scenario = null;	
	private Config config = null;
	
	private final static Logger log = Logger.getLogger(CreateNetwork.class);
	private final String LCEXP = "locationchoiceExperimental";
			
	
	public void createNetwork(ScenarioImpl scenario, Config config) {
		this.scenario = scenario;
		this.config = config;
		NetworkFactoryImpl networkFactory = new NetworkFactoryImpl(this.scenario.getNetwork());
		
		this.addNodes(networkFactory);
		this.addLinks(networkFactory);	
	}
			
	private void addLinks(NetworkFactoryImpl networkFactory) {
		
		int linkCnt = 0;
		int facilityCnt = 0;
		double freeSpeed = 35.0 / 3.6;
		
		double sideLength = Double.parseDouble(config.findParam(LCEXP, "sideLength"));
		double spacing = Double.parseDouble(config.findParam(LCEXP, "spacing"));
		double linkCapacity = Double.parseDouble(config.findParam(LCEXP, "linkCapacity"));
		
		int stepsPerSide = (int)(sideLength / spacing);
		
		for (int i = 0; i <= stepsPerSide ; i++) {
			for (int j = 0; j <= stepsPerSide; j++) {
				Id<Node> fromNodeId = Id.create(Integer.toString(i * (stepsPerSide + 1) + j), Node.class);
							
				if (j > 0) {
					// create backward link
					Id<Node> toNodeId = Id.create(Integer.toString(i * (stepsPerSide + 1) + j - 1), Node.class);
					
					Link l0 = networkFactory.createLink(Id.create(linkCnt, Link.class), fromNodeId, toNodeId);
					l0.setCapacity(linkCapacity);
					l0.setFreespeed(freeSpeed);				
					l0.setLength(((CoordImpl)scenario.getNetwork().getNodes().get(fromNodeId).getCoord()).calcDistance(
							scenario.getNetwork().getNodes().get(toNodeId).getCoord()));
					this.scenario.getNetwork().addLink(l0);
					linkCnt++;
					
					this.addFacility(l0, facilityCnt);
					facilityCnt++;						
					
					Link l1 = networkFactory.createLink(Id.create(linkCnt, Link.class), toNodeId, fromNodeId);
					l1.setCapacity(linkCapacity);
					l1.setFreespeed(freeSpeed);
					l1.setLength(((CoordImpl)scenario.getNetwork().getNodes().get(toNodeId).getCoord()).calcDistance(
							scenario.getNetwork().getNodes().get(fromNodeId).getCoord()));
					this.scenario.getNetwork().addLink(l1);
					linkCnt++;
				}				
				
				if (i > 0) {
					// create downward link
					Id<Node> toNodeId = Id.create(Integer.toString((i - 1) * (stepsPerSide + 1) + j), Node.class);
					
					Link l0 = networkFactory.createLink(Id.create(Integer.toString(linkCnt), Link.class), fromNodeId, toNodeId);
					l0.setCapacity(linkCapacity);
					l0.setFreespeed(freeSpeed);
					l0.setLength(((CoordImpl)scenario.getNetwork().getNodes().get(fromNodeId).getCoord()).calcDistance(
							scenario.getNetwork().getNodes().get(toNodeId).getCoord()));
					this.scenario.getNetwork().addLink(l0);
					linkCnt++;
					
					this.addFacility(l0, facilityCnt);						
					facilityCnt++;
					
					Link l1 = networkFactory.createLink(Id.create(linkCnt, Link.class), toNodeId, fromNodeId);
					l1.setCapacity(linkCapacity);
					l1.setFreespeed(freeSpeed);
					l1.setLength(((CoordImpl)scenario.getNetwork().getNodes().get(fromNodeId).getCoord()).calcDistance(
							scenario.getNetwork().getNodes().get(toNodeId).getCoord()));
					this.scenario.getNetwork().addLink(l1);
					linkCnt++;
				}
				
			}
		}
		log.info("Created " + linkCnt + " links");
		log.info("Created " + facilityCnt + " facilities");
	}
	
	private void addFacility(Link l, int facilityId) {
		int personsPerLocation = Integer.parseInt(config.findParam(LCEXP, "personsPerLoc"));
				
		Id<ActivityFacility> id = Id.create(Integer.toString(facilityId), ActivityFacility.class);
		
		this.scenario.getActivityFacilities().addActivityFacility(
				this.scenario.getActivityFacilities().getFactory().createActivityFacility(id, l.getCoord()));
		
		ActivityFacilityImpl facility = (ActivityFacilityImpl)(this.scenario.getActivityFacilities().getFacilities().get(id));
		facility.createActivityOption("shop");
		facility.createActivityOption("home");
		facility.getActivityOptions().get("shop").setCapacity(personsPerLocation * 0.5);
				
		ActivityOptionImpl actOptionShop = (ActivityOptionImpl)facility.getActivityOptions().get("shop");
		OpeningTimeImpl opentimeShop = new OpeningTimeImpl(9.5 * 3600.0, 14.5 * 3600);
		actOptionShop.addOpeningTime(opentimeShop);
		
		ActivityOptionImpl actOptionHome = (ActivityOptionImpl)facility.getActivityOptions().get("home");
		OpeningTimeImpl opentimeHome = new OpeningTimeImpl(0 * 3600.0, 24.0 * 3600);
		actOptionHome.addOpeningTime(opentimeHome);
	}
			
	private void addNodes(NetworkFactoryImpl networkFactory) {
		
		double sideLength = Double.parseDouble(config.findParam(LCEXP, "sideLength"));
		double spacing = Double.parseDouble(config.findParam(LCEXP, "spacing"));
		
		int nodeCnt = 0;
		int stepsPerSide = (int)(sideLength/ spacing);
		for (int i = 0; i <= stepsPerSide ; i++) {
			for (int j = 0; j <= stepsPerSide; j++) {
				Node n = networkFactory.createNode(Id.create(Integer.toString(nodeCnt), Node.class), new CoordImpl(i * spacing, j * spacing));
				this.scenario.getNetwork().addNode(n);
				nodeCnt++;
			}
		}
		log.info("Created " + nodeCnt + " nodes");
	}
	
	public void write(String path) {
		this.writeNetwork(path);
		this.writeFacilities(path);
	}
			
	private void writeNetwork(String path) {
		new NetworkWriter(this.scenario.getNetwork()).write(path + "network.xml");
	}
	
	private void writeFacilities(String path) {
		new FacilitiesWriter(this.scenario.getActivityFacilities()).write(path + "facilities.xml");
	}
}
