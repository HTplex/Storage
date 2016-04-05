/* *********************************************************************** *
 * project: org.matsim.*
 * Plansgenerator.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
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
package playground.vsp.analysis.modules.ptTripAnalysis.distance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.PersonEntersVehicleEvent;
import org.matsim.api.core.v01.events.PersonLeavesVehicleEvent;
import org.matsim.api.core.v01.events.PersonStuckEvent;
import org.matsim.api.core.v01.events.TransitDriverStartsEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.events.handler.PersonEntersVehicleEventHandler;
import org.matsim.api.core.v01.events.handler.PersonLeavesVehicleEventHandler;
import org.matsim.api.core.v01.events.handler.PersonStuckEventHandler;
import org.matsim.api.core.v01.events.handler.TransitDriverStartsEventHandler;
import org.matsim.api.core.v01.network.Link;

import playground.vsp.analysis.modules.ptTripAnalysis.AbstractAnalysisTrip;
import playground.vsp.analysis.modules.ptTripAnalysis.AnalysisTripSetStorage;

import com.vividsolutions.jts.geom.Geometry;

/**
 * @author droeder
 *
 */
public class DistAnalysisHandler implements LinkEnterEventHandler, TransitDriverStartsEventHandler,
												PersonEntersVehicleEventHandler, PersonLeavesVehicleEventHandler,
												PersonArrivalEventHandler, PersonDepartureEventHandler, PersonStuckEventHandler{
	
	private static final Logger log = Logger
			.getLogger(DistAnalysisHandler.class);
	
	private Map<Id, DistAnalysisAgent> persons;
	private Map<Id, DistAnalysisPtDriver> drivers;
	private Map<Id, DistAnalysisTransitRoute> routes;
	private Map<Id, DistAnalysisVehicle> vehicles;
	private List<Id> stuckAgents;
	private Map<String, AnalysisTripSetStorage> tripSets;
	
	private Map<Id<Link>, Link> links;
	
	public DistAnalysisHandler(){
		this.persons = new HashMap<Id, DistAnalysisAgent>();
		this.drivers = new HashMap<Id, DistAnalysisPtDriver>();
		this.routes = new HashMap<Id, DistAnalysisTransitRoute>();
		this.vehicles = new HashMap<Id, DistAnalysisVehicle>();
		this.tripSets = new HashMap<String, AnalysisTripSetStorage>();
		this.tripSets.put("noZone", new AnalysisTripSetStorage(false, null, null));
		this.stuckAgents = new ArrayList<Id>();
	}
	
	public void addLinks(Map<Id<Link>, Link> map){
		this.links = map;
	}
	public void addZones(Map<String, Geometry> zones){
		this.tripSets = new HashMap<String, AnalysisTripSetStorage>();
		for(Entry<String, Geometry> e : zones.entrySet()){
			this.tripSets.put(e.getKey(), new AnalysisTripSetStorage(false, e.getValue(), null));
		}
	}
	
	public void addPerson(DistAnalysisAgent person){
		this.persons.put(person.getId(), person);
	}

	@Override
	public void reset(int iteration) {
	}
	
	@Override
	public void handleEvent(TransitDriverStartsEvent e) {
		//create a new instance if no exists
		if(!this.drivers.containsKey(e.getDriverId())){
			this.drivers.put(e.getDriverId(), new DistAnalysisPtDriver(e.getDriverId()));
		}
		if(!this.vehicles.containsKey(e.getVehicleId())){
			this.vehicles.put(e.getVehicleId(), new DistAnalysisVehicle(e.getVehicleId()));
		}
		if(!this.routes.containsKey(e.getTransitRouteId())){
			this.routes.put(e.getTransitRouteId(), new DistAnalysisTransitRoute(e.getTransitRouteId()));
		}
		
		//register route to vehicle and vehicle to driver
		this.vehicles.get(e.getVehicleId()).registerRoute(this.routes.get(e.getTransitRouteId()));
		this.drivers.get(e.getDriverId()).registerVehicle(this.vehicles.get(e.getVehicleId()));
	}

	@Override
	public void handleEvent(PersonDepartureEvent e) {
		if(this.persons.containsKey(e.getPersonId())){
			if(this.persons.get(e.getPersonId()).processAgentEvent(e)){
				//if the trip is finished, add to tripStorage
				this.addTrip2TripStorageAndRemoveFromPerson(e.getPersonId());
			}
		}
	}

	@Override
	public void handleEvent(PersonArrivalEvent e) {
		if(this.persons.containsKey(e.getPersonId())){
			if(this.persons.get(e.getPersonId()).processAgentEvent(e)){
				//if the trip is finished, add to tripStorage
				this.addTrip2TripStorageAndRemoveFromPerson(e.getPersonId());
			}
		}
	}
	
	private void addTrip2TripStorageAndRemoveFromPerson(Id id){
		AbstractAnalysisTrip t = this.persons.get(id).removeFinishedTrip();
		for(AnalysisTripSetStorage s: this.tripSets.values()){
			s.addTrip(t);
		}
	}

	@Override
	public void handleEvent(PersonLeavesVehicleEvent e) {
		if(this.persons.containsKey(e.getPersonId())){
			if(!this.vehicles.get(e.getVehicleId()).leaveVehicle(this.persons.get(e.getPersonId()))){
				log.error("agent " + e.getPersonId() + " try to leave vehicle " + e.getVehicleId() + " but isn't in there");
			}
		}
	}

	@Override
	public void handleEvent(PersonEntersVehicleEvent e) {
		// an agent should have an AgentDepartureEvent before he enters a vehicle
		if(this.persons.containsKey(e.getPersonId())){
			this.vehicles.get(e.getVehicleId()).enterVehicle(this.persons.get(e.getPersonId()));
		}
	}

	@Override
	public void handleEvent(LinkEnterEvent e) {
		//agents and drivers can process a LinkEnterEvent 
		if(this.persons.containsKey(e.getPersonId())){
			this.persons.get(e.getPersonId()).processLinkEnterEvent(this.links.get(e.getLinkId()).getLength());
		}else if(this.drivers.containsKey(e.getPersonId())){
			this.drivers.get(e.getPersonId()).processLinkEnterEvent(this.links.get(e.getLinkId()).getLength());
		}
	}
	
	
	private boolean stuck = false;
	@Override
	public void handleEvent(PersonStuckEvent e) {
		if(!stuck){
			this.stuck = true;
			log.error("Message thrown only once!!! StuckEvent for Agent: " + e.getPersonId());
		}
		this.stuckAgents.add(e.getPersonId());
		this.persons.remove(e.getPersonId());
	}

	public Map<String, AnalysisTripSetStorage> getAnalysisTripSetStorage(){
		return this.tripSets;
	}
	
	public Collection<DistAnalysisTransitRoute> getRoutes(){
		return this.routes.values();
	}
	
	public Collection<DistAnalysisVehicle> getVehicles(){
		return this.vehicles.values();
	}
	
	public List<Id> getStuckAgents(){
		return this.stuckAgents;
	}
}
