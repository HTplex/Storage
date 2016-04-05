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

import java.util.Vector;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.locationchoice.utils.RandomFromVarDistr;
import org.matsim.core.config.Config;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.LegImpl;
import org.matsim.core.population.PersonImpl;
import org.matsim.core.population.PlanImpl;
import org.matsim.core.population.PopulationWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.facilities.ActivityFacility;


public class CreatePopulation {
	private ScenarioImpl scenario = null;	
	private Config config;
	private final static Logger log = Logger.getLogger(CreatePopulation.class);	
	private final String LCEXP = "locationchoiceExperimental";
			
	public void createPopulation(ScenarioImpl scenario, Config config, RandomFromVarDistr rnd) {		
		this.scenario = scenario;
		this.config = config;		
		this.addPersons();
		
		log.info("Finishing plans ...");
		this.finishPlans();
		this.removeNonAnalysisPersons();
	}
						
	private void addPersons() {
		
		double sideLength = Double.parseDouble(config.findParam(LCEXP, "sideLength"));
		int personsPerLocation = Integer.parseInt(config.findParam(LCEXP, "personsPerLoc"));
		
		int personCnt = 0;	
		int analysisPopulationCnt = 0;
		for (ActivityFacility facility : this.scenario.getActivityFacilities().getFacilitiesForActivityType("home").values()) {
						
			// check if person is in the analysis population
			int offset = Integer.parseInt(config.findParam(LCEXP, "analysisPopulationOffset"));
			if ((facility.getCoord().getX() >= (sideLength / 2.0) - 500.0
					&& facility.getCoord().getX() <= (sideLength / 2.0) + 500.0) && 
					(facility.getCoord().getY() >= (sideLength / 2.0) - 500.0
					&& facility.getCoord().getY() <= (sideLength / 2.0) + 500.0)) {
				offset = 0;
				analysisPopulationCnt++;
			}
						
			for (int j = 0; j < personsPerLocation; j++) {
				PersonImpl p = new PersonImpl(Id.create(personCnt + offset, Person.class));
				personCnt++;
				p.createAndAddPlan(true);
				ActivityImpl act = new ActivityImpl("home", facility.getCoord());
				act.setFacilityId(facility.getId());
				act.setEndTime(11.0 * 3600.0);
				p.getSelectedPlan().addActivity(act);
				p.getSelectedPlan().addLeg(new LegImpl("car"));
				
				p.createDesires("");				
				this.scenario.getPopulation().addPerson(p);
			}
		}
		log.info("Created " + personCnt + " persons including " + 
				analysisPopulationCnt * personsPerLocation + " analysis persons");
	}
		
	private void finishPlans() {
		//RandomFromVarDistr rnd = new RandomFromVarDistr();
		//double maxWorkDistance = 500.0;
		//double maxShopDistance = 2500.0;
		//Bins workBins = new Bins(configReader.getSpacing() * 2, maxWorkDistance, "work distance");
		//Bins shopBins = new Bins(configReader.getSpacing() * 2, maxShopDistance, "shop distance");
		
		int counter = 0;
		int nextMsg = 1;
		for (Person p : this.scenario.getPopulation().getPersons().values()) {
			
			// show counter
			counter++;
			if (counter % nextMsg == 0) {
				nextMsg *= 2;
				log.info(" person # " + counter);
			}
			PlanImpl plan = (PlanImpl)p.getSelectedPlan(); 
			
			//double workDistance = 0.0; //rnd.getNegLinear(maxWorkDistance);
			//ActivityFacility workFacility = this.assignLocation("work", workDistance, plan.getFirstActivity().getCoord());
			// if person is not in the analysis population
			//if (Integer.parseInt(p.getId().toString()) > configReader.getAnalysisPopulationOffset()) {
			//	workBins.addVal(((CoordImpl)workFacility.getCoord()).calcDistance(plan.getFirstActivity().getCoord()), 1.0);
			//}
			
			//ActivityImpl workAct = new ActivityImpl("work", workFacility.getCoord());
			//workAct.setEndTime(17.0 * 3600);
			//workAct.setFacilityId(workFacility.getId());
			//p.getSelectedPlan().addActivity(workAct);
			//p.getSelectedPlan().addLeg(new LegImpl("car"));
			
			//double shopDistance = rnd.getUniform(maxShopDistance);
			//ActivityFacility shopFacility = this.assignLocation("shop", shopDistance, workFacility.getCoord());
			
			// if person is not in the analysis population
			//if (Integer.parseInt(p.getId().toString()) > configReader.getAnalysisPopulationOffset()) {
			//	shopBins.addVal(((CoordImpl)shopFacility.getCoord()).calcDistance(workFacility.getCoord()), 1.0);
			//}
			ActivityImpl shopAct = new ActivityImpl("shop", plan.getFirstActivity().getCoord());
			shopAct.setFacilityId(plan.getFirstActivity().getFacilityId());
			shopAct.setEndTime(12.0 * 3600.0);
			p.getSelectedPlan().addActivity(shopAct);
			p.getSelectedPlan().addLeg(new LegImpl("car"));
			
			ActivityImpl homeAct = new ActivityImpl("home", plan.getFirstActivity().getCoord());
			homeAct.setFacilityId(plan.getFirstActivity().getFacilityId());
			p.getSelectedPlan().addActivity(homeAct);		
		}
		//workBins.plotBinnedDistribution(configReader.getPath() + "input/workDistances", "#", "m");
		//shopBins.plotBinnedDistribution(configReader.getPath() + "input/shopDistances", "#", "m");
	}
	
//	private ActivityFacility assignLocation(String type, double distance, Coord startCoordinates) {
//		int distanceIndex = (int)(distance /configReader.getSpacing());
//		Vector<ActivityFacility> facilities = new Vector<ActivityFacility>();
//		for (ActivityFacility facility : this.scenario.getActivityFacilities().getFacilitiesForActivityType(type).values()) {
//			double distanceTmp = ((CoordImpl)startCoordinates).calcDistance(facility.getCoord());
//			if (distanceTmp <= distance && distanceTmp >= distanceIndex * configReader.getSpacing()) {
//				facilities.add(facility);
//			}
//		}
//		Collections.shuffle(facilities);
//		return facilities.get(0);
//	}
	
				
	public void write(String path) {
		new PopulationWriter(scenario.getPopulation(), scenario.getNetwork()).write(path + "plans.xml");
	}
	
	// better quality of assignment of tastes!
	private void removeNonAnalysisPersons() {
		Vector<Id> ids2remove = new Vector<Id>();
		for (Person p : this.scenario.getPopulation().getPersons().values()) {
			// if person is not in the analysis population
			if (Integer.parseInt(p.getId().toString()) >= Integer.parseInt(config.findParam(LCEXP, "analysisPopulationOffset"))) {
				ids2remove.add(p.getId()); 
			}
		}
		for (Id id : ids2remove) {
			this.scenario.getPopulation().getPersons().remove(id);
		}
		log.info("Removed " + ids2remove.size() + " non-analysis persons ...");
	}
}
