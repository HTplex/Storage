/* *********************************************************************** *
 * project: org.matsim.*
 * CostNavigationTravelTimeLogger.java
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

package playground.christoph.router;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.router.util.TravelTime;

public class CostNavigationTravelTimeLogger implements LinkEnterEventHandler, LinkLeaveEventHandler, PersonArrivalEventHandler, PersonDepartureEventHandler {

	private Population population;
	private Network network;
	private TravelTime travelTime;
	
	/*
	 * Initialize counters with 1 - as a result, the first returned
	 * trust value will be 0.5
	 */
	private int followedAndAccepted = 1;
	private int followedAndNotAccepted = 1;
	private int notFollowedAndAccepted = 1;
	private int notFollowedAndNotAccepted = 1;

	
	private Map<Id, PersonInfo> personInfos;
	private Map<Id, Boolean> followed;
	private Map<Id, Double> enterTimes;
	private Map<Id, Double> expectedTravelTimes;
	private Map<Id, Double> expectedAlternativeTravelTimes;
	
	public double toleranceSlower = 1.25;			// tau-
	public double toleranceFaster = 0.75;			// tau+
	public double toleranceAlternativeRoute = 1.10;	// tau
	
	public CostNavigationTravelTimeLogger(Population population, Network network, TravelTime travelTime) {
		this.population = population;
		this.network = network;
		this.travelTime = travelTime;

		init();
	}
	
	public CostNavigationTravelTimeLogger(Population population, Network network, TravelTime travelTime, 
			int followedAndAccepted, int followedAndNotAccepted, int notFollowedAndAccepted, int notFollowedAndNotAccepted) {
		this.population = population;
		this.network = network;
		this.travelTime = travelTime;
		
		this.followedAndAccepted = followedAndAccepted;
		this.followedAndNotAccepted = followedAndNotAccepted;
		this.notFollowedAndAccepted = notFollowedAndAccepted;
		this.notFollowedAndNotAccepted = notFollowedAndNotAccepted;
		
		init();
	}
	
	private void init() {		
		personInfos = new ConcurrentHashMap<Id, PersonInfo>();
		followed = new ConcurrentHashMap<Id, Boolean>();
		enterTimes = new ConcurrentHashMap<Id, Double>();
		expectedTravelTimes = new ConcurrentHashMap<Id, Double>();
		expectedAlternativeTravelTimes = new ConcurrentHashMap<Id, Double>();
		
		// set initial trust for the whole population
		for (Person person : this.population.getPersons().values()) {
			personInfos.put(person.getId(), new PersonInfo(followedAndAccepted, followedAndNotAccepted, notFollowedAndAccepted, notFollowedAndNotAccepted));
		}
		
		followed.clear();
		enterTimes.clear();
		expectedTravelTimes.clear();
	}
	
	@Override
	public void handleEvent(LinkEnterEvent event) {
		Id personId = event.getPersonId();
		Id linkId = event.getLinkId();
		double time = event.getTime();

		enterTimes.put(personId, time);
		expectedTravelTimes.put(personId, travelTime.getLinkTravelTime(this.network.getLinks().get(linkId), time, null, null));
	}

	@Override
	public void handleEvent(LinkLeaveEvent event) {
		Id personId = event.getPersonId();
//		Id linkId = event.getLinkId();
		double time = event.getTime();
		
		/*
		 * If the agent has just ended an activity, there is no
		 * entry in the enterTimes map. Therefore, we have nothing
		 * else to do.
		 */
		if (!enterTimes.containsKey(personId)) return;
		
		/*
		 * If the agent does not use Within-Day Replanning there will be
		 * no entry for its personId in the Map.
		 */
		if (!followed.containsKey(personId)) return;
		
		double enterTime = enterTimes.get(personId);
		double expectedTravelTime = expectedTravelTimes.get(personId);
		double travelTime = time - enterTime;
		boolean hasFollowed = followed.get(personId);
		PersonInfo personInfo = personInfos.get(personId);
		
		/*
		 * estimation > 1: slower than expected
		 * estimation < 1: faster than expected 
		 */
		double estimation = travelTime / expectedTravelTime;
		
		if (hasFollowed) {
			if (estimation > toleranceSlower) {
				personInfo.followedAndNotAccepted++;
			} else if (estimation < toleranceFaster) {
				personInfo.followedAndNotAccepted++;
			} else {
				personInfo.followedAndAccepted++;
			}
		} else {
			double expectedAlternativeTravelTime = this.expectedAlternativeTravelTimes.get(personId);
			if (expectedAlternativeTravelTime * toleranceAlternativeRoute <= travelTime) {
				personInfo.notFollowedAndAccepted++;
			} else {
				personInfo.notFollowedAndNotAccepted++;
			}	
		}		
	}

	@Override
	public void handleEvent(PersonArrivalEvent event) {
		Id personId = event.getPersonId();
		
		enterTimes.remove(personId);
		expectedTravelTimes.remove(personId);
		followed.remove(personId);
	}

	@Override
	public void handleEvent(PersonDepartureEvent event) {
		// nothing to do here...
	}

	@Override
	public void reset(int iteration) {
		init();
	}

	public double getTrust(Id personId) {
		return personInfos.get(personId).getTrust();
	}
	
	public void setFollowed(Id personId, boolean followed) {
		this.followed.put(personId, followed);
	}
	
	public void setExpectedAlternativeTravelTime(Id personId, double costs) {
		this.expectedAlternativeTravelTimes.put(personId, costs);
	}
	
	public int getFollowedAndAccepted(Id personId) {
		return personInfos.get(personId).followedAndAccepted;
	}
	
	public int getFollowedAndNotAccepted(Id personId) {
		return personInfos.get(personId).followedAndNotAccepted;
	}
	
	public int getNotFollowedAndAccepted(Id personId) {
		return personInfos.get(personId).notFollowedAndAccepted;
	}
	
	public int getNotFollowedAndNotAccepted(Id personId) {
		return personInfos.get(personId).notFollowedAndNotAccepted;
	}
		
	public double getRandomNumber(Id personId) {
		return personInfos.get(personId).getRandomNumber();
	}
		
	private class PersonInfo {

		int followedAndAccepted;
		int followedAndNotAccepted;
		int notFollowedAndAccepted;
		int notFollowedAndNotAccepted;

		public PersonInfo(int followedAndAccepted, int followedAndNotAccepted, int notFollowedAndAccepted, int notFollowedAndNotAccepted) {
			this.followedAndAccepted = followedAndAccepted;
			this.followedAndNotAccepted = followedAndNotAccepted;
			this.notFollowedAndAccepted = notFollowedAndAccepted;
			this.notFollowedAndNotAccepted = notFollowedAndNotAccepted;
		}
		
		Random random = MatsimRandom.getLocalInstance();
		
		public double getTrust() {
			double observations = followedAndAccepted + followedAndNotAccepted + notFollowedAndAccepted + notFollowedAndNotAccepted;
//			return ((double)(followedAndAccepted + notFollowedAndNotAccepted)) / observations;
			return ((double)(followedAndAccepted + notFollowedAndAccepted)) / observations;
		}
				
		public double getRandomNumber() {
			return random.nextDouble();
		}
	}
}
