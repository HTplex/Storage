/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2013 by the members listed in the COPYING,        *
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

/**
 * 
 */
package playground.ikaddoura.noise2.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.pt.PtConstants;

import playground.ikaddoura.noise2.data.NoiseContext;
import playground.ikaddoura.noise2.data.PersonActivityInfo;
import playground.ikaddoura.noise2.data.ReceiverPoint;

/**
 * 
 * A handler which keeps track of each agent's activities throughout the day if the activity is of a certain predefined activity type (considered activity type).
 * This handler is required for the calculation of noise damages.
 * 
 * @author ikaddoura
 *
 */

public class PersonActivityTracker implements ActivityEndEventHandler , ActivityStartEventHandler {

	private static final Logger log = Logger.getLogger(PersonActivityTracker.class);
	
	private final NoiseContext noiseContext;
	private final List<String> consideredActivityTypes = new ArrayList<String>();
	
	private Map<Id<Person>, Integer> personId2currentActNr = new HashMap<Id<Person>, Integer>();
		
	public PersonActivityTracker(NoiseContext noiseContext) {
		this.noiseContext = noiseContext;
		
		String[] consideredActTypesArray = noiseContext.getGrid().getGridParams().getConsideredActivitiesForDamages();
		for (int i = 0; i < consideredActTypesArray.length; i++) {
			this.consideredActivityTypes.add(consideredActTypesArray[i]);
		}
		
		if (this.consideredActivityTypes.size() == 0) {
			log.warn("Not considering any activity type for the noise damage computation. This event handler can be disabled.");
		}	
		
		setFirstActivities();
		
	}

	@Override
	public void reset(int iteration) {
		
		this.personId2currentActNr.clear();
		setFirstActivities();
	}
	
	private void setFirstActivities() {
		
		log.info("Receiving first activities from the selected plans...");
		for (Person person : this.noiseContext.getScenario().getPopulation().getPersons().values()) {
			Plan plan = person.getSelectedPlan();

			if (!plan.getPlanElements().isEmpty() && plan.getPlanElements().get(0) instanceof Activity) {
				Activity firstActivity = (Activity) plan.getPlanElements().get(0);

				if (this.consideredActivityTypes.contains(firstActivity.getType())) {
					
					Id<ReceiverPoint> rpId = noiseContext.getGrid().getActivityCoord2receiverPointId().get(noiseContext.getGrid().getPersonId2listOfConsideredActivityCoords().get(person.getId()).get(0));
					this.personId2currentActNr.put(person.getId(), 0);
					
					PersonActivityInfo actInfo = new PersonActivityInfo();
					actInfo.setStartTime(0.);
					actInfo.setEndTime(30 * 3600.);
					actInfo.setActivityType(firstActivity.getType());
					
					if (rpId == null) {
						throw new RuntimeException("No receiver point for " + person.getId() + ". Aborting...");
					}
					
					if (this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().containsKey(person.getId())) {
						this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().get(person.getId()).add(actInfo);
					} else {
						ArrayList<PersonActivityInfo> personActivityInfos = new ArrayList<PersonActivityInfo>();
						personActivityInfos.add(actInfo);
						this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().put(person.getId(), personActivityInfos);
					}
				}
			}
		}
		log.info("Receiving first activities from the selected plans... Done.");
	}
	
	public void handleEvent(ActivityStartEvent event) {
						
		if (!(this.noiseContext.getScenario().getPopulation().getPersons().containsKey(event.getPersonId()))) {
		} else {
		
			if (!event.getActType().toString().equals(PtConstants.TRANSIT_ACTIVITY_TYPE)) {
				
				if (this.consideredActivityTypes.contains(event.getActType())) {
										
					int newActNr = this.personId2currentActNr.get(event.getPersonId()) + 1;
					this.personId2currentActNr.put(event.getPersonId(), newActNr);
										
					Coord coord = noiseContext.getGrid().getPersonId2listOfConsideredActivityCoords().get(event.getPersonId()).get(this.personId2currentActNr.get(event.getPersonId()));
					Id<ReceiverPoint> rpId = noiseContext.getGrid().getActivityCoord2receiverPointId().get(coord);
					
					PersonActivityInfo actInfo = new PersonActivityInfo();
					actInfo.setStartTime(event.getTime());
					actInfo.setEndTime(30 * 3600.); // assuming this activity to be the last one in the agents' plan, will be overwritten if it is not the last activity
					actInfo.setActivityType(event.getActType());
					
					if (this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().containsKey(event.getPersonId())) {
						this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().get(event.getPersonId()).add(actInfo);
					} else {
						ArrayList<PersonActivityInfo> personActivityInfos = new ArrayList<PersonActivityInfo>();
						personActivityInfos.add(actInfo);
						this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().put(event.getPersonId(), personActivityInfos);
					}
				}
			}
		}
	}

	@Override
	public void handleEvent(ActivityEndEvent event) {
				
		if (!(this.noiseContext.getScenario().getPopulation().getPersons().containsKey(event.getPersonId()))) {
		} else {
			
			if (!event.getActType().toString().equals(PtConstants.TRANSIT_ACTIVITY_TYPE)) {
				
				if (this.consideredActivityTypes.contains(event.getActType())) {
										
					Coord coord = noiseContext.getGrid().getPersonId2listOfConsideredActivityCoords().get(event.getPersonId()).get(this.personId2currentActNr.get(event.getPersonId()));
					Id<ReceiverPoint> rpId = noiseContext.getGrid().getActivityCoord2receiverPointId().get(coord);

					if (this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().containsKey(event.getPersonId())) {
						for (PersonActivityInfo actInfo : this.noiseContext.getReceiverPoints().get(rpId).getPersonId2actInfos().get(event.getPersonId())) {
							if (actInfo.getEndTime() == 30 * 3600.) {
								actInfo.setEndTime(event.getTime());
							}
						}	
					}
				}
			} 
		}		
	}
}
