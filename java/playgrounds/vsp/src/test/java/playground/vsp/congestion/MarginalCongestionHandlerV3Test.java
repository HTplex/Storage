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
package playground.vsp.congestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.testcases.MatsimTestUtils;

import playground.vsp.congestion.events.CongestionEvent;
import playground.vsp.congestion.handlers.CongestionEventHandler;
import playground.vsp.congestion.handlers.CongestionHandlerImplV3;

/**
 * @author ikaddoura
 *
 */

public class MarginalCongestionHandlerV3Test {
	
	@Rule
	public MatsimTestUtils testUtils = new MatsimTestUtils();
			
	@Test
	public final void testCongestionExample(){
		
		String configFile = testUtils.getPackageInputDirectory()+"MarginalCongestionHandlerV3Test/config.xml";

		Controler controler = new Controler(configFile);
		
		EventsManager events = controler.getEvents();
		events.addHandler(new CongestionHandlerImplV3(events, (ScenarioImpl) controler.getScenario()));
				
		final List<CongestionEvent> congestionEvents = new ArrayList<CongestionEvent>();
		
		events.addHandler( new CongestionEventHandler() {

			@Override
			public void reset(int iteration) {				
			}

			@Override
			public void handleEvent(CongestionEvent event) {
				congestionEvents.add(event);
			}	
		});

		controler.getConfig().controler().setOverwriteFileSetting(
				OutputDirectoryHierarchy.OverwriteFileSetting.overwriteExistingFiles );
		controler.run();
		
		// process
		Map<Id<Person>, Double> personId2causedDelay = new HashMap<Id<Person>, Double>();
		Map<Id<Person>, Double> personId2affectedDelay = new HashMap<Id<Person>, Double>();
		List<Id<Person>> persons = new ArrayList<Id<Person>>();
		double totalDelay = 0.;
		
		for (CongestionEvent event : congestionEvents) {
			
			if (!persons.contains(event.getCausingAgentId())) {
				persons.add(event.getCausingAgentId());
			}
			
			if (!persons.contains(event.getAffectedAgentId())) {
				persons.add(event.getAffectedAgentId());
			}
			
			totalDelay = totalDelay + event.getDelay();
			
			if (personId2causedDelay.containsKey(event.getCausingAgentId())){
				double causedSoFar = personId2causedDelay.get(event.getCausingAgentId());
				double causedNewValue = causedSoFar + event.getDelay();
				personId2causedDelay.put(event.getCausingAgentId(), causedNewValue);
			} else {
				personId2causedDelay.put(event.getCausingAgentId(), event.getDelay());
			}
			
			if (personId2affectedDelay.containsKey(event.getAffectedAgentId())){
				double affectedSoFar = personId2affectedDelay.get(event.getAffectedAgentId());
				double affectedNewValue = affectedSoFar + event.getDelay();
				personId2affectedDelay.put(event.getAffectedAgentId(), affectedNewValue);
			} else {
				personId2affectedDelay.put(event.getAffectedAgentId(), event.getDelay());
			}
		}
		
		// print out
		for (Id<Person> personId : persons) {
			System.out.println("Person: " + personId + " // total caused delay: " + personId2causedDelay.get(personId) + " // total affected delay: " + personId2affectedDelay.get(personId));		
		}
		
		// assert
		Assert.assertEquals("wrong values for testAgent7", 38.0, personId2causedDelay.get(Id.create("testAgent7", Person.class)), MatsimTestUtils.EPSILON);
		Assert.assertEquals("wrong values for testAgent7", 12.0, personId2affectedDelay.get(Id.create("testAgent7", Person.class)), MatsimTestUtils.EPSILON);
		// ...
	 }
}
