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

package playground.anhorni.locationchoice.preprocess.plans.modifications;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;


public class AssignLeisureTripDistances {

	private Population plans = ((ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig())).getPopulation();
	private final static Logger log = Logger.getLogger(AssignLeisureTripDistances.class);

	// from Microcensus for all modes
	private double groceryShare = 0.66;
	private int numberOfShopActs;

	public AssignLeisureTripDistances(Population plans) {
		this.plans = plans;
	}

	public void run() {
		this.init();
		this.assignGrocery();
	}

	private void init() {
		this.numberOfShopActs = this.getNumberOfShopActs();
	}

	private int getNumberOfShopActs() {
		int numberOfShopActs = 0;

		Iterator<? extends Person> person_it = this.plans.getPersons().values().iterator();
		while (person_it.hasNext()) {
			Person person = person_it.next();

			// intitially only one plan is available
			if (person.getPlans().size() > 1) {
				log.error("More than one plan for person: " + person.getId());
			}
			Plan selectedPlan = person.getSelectedPlan();

			final List<? extends PlanElement> actslegs = selectedPlan.getPlanElements();
			for (int j = 0; j < actslegs.size(); j=j+2) {
				final ActivityImpl act = (ActivityImpl)actslegs.get(j);
				if (act.getType().startsWith("shop")) {
					numberOfShopActs++;
				}
			}
		}
		return numberOfShopActs;
	}

	private void assignGrocery() {

		int assignedNumberOf_GroceryActs = 0;
		int assignedNumberOf_NonGroceryActs = 0;

		for (Person person : this.plans.getPersons().values()) {

			// intitially only one plan is available
			if (person.getPlans().size() > 1) {
				log.error("More than one plan for person: " + person.getId());
			}
			Plan selectedPlan = person.getSelectedPlan();

			final List<? extends PlanElement> actslegs = selectedPlan.getPlanElements();
			for (int j = 0; j < actslegs.size(); j=j+2) {
				final ActivityImpl act = (ActivityImpl)actslegs.get(j);
				if (act.getType().startsWith("shop")) {
					double random = MatsimRandom.getRandom().nextDouble();
					if (random <= 0.66) {
						if (assignedNumberOf_GroceryActs < groceryShare * numberOfShopActs) {
							act.setType("shop_grocery");
							assignedNumberOf_GroceryActs++;
						}
						else {
							act.setType("shop_nongrocery");
							assignedNumberOf_NonGroceryActs++;
						}
					}
					else {
						if (assignedNumberOf_NonGroceryActs < (1.0-groceryShare) * numberOfShopActs) {
							act.setType("shop_nongrocery");
							assignedNumberOf_NonGroceryActs++;
						}
						else {
							act.setType("shop_grocery");
							assignedNumberOf_GroceryActs++;
						}
					}
				}
			}
		}
		log.info("Number of shopping activities:\t" + this.numberOfShopActs);
		log.info("Number of grocery shopping acts:\t" + assignedNumberOf_GroceryActs);
		log.info("Number of non-grocery acts:\t" + assignedNumberOf_NonGroceryActs);
		log.info("Share:\t"+ (100.0* assignedNumberOf_GroceryActs / this.numberOfShopActs));
	}

	public Population getPlans() {
		return plans;
	}

	public void setPlans(Population plans) {
		this.plans = plans;
	}
}
