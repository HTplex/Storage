/* *********************************************************************** *
 * project: org.matsim.*
 * HwhPlansMaker.java
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

/**
 *
 */
package playground.yu.newPlans;

import java.util.Set;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.Config;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.population.MatsimPopulationReader;
import org.matsim.core.population.PopulationReader;
import org.matsim.core.population.PopulationWriter;
import org.matsim.core.scenario.ScenarioLoaderImpl;

/**
 * @author ychen
 */
public class HwhPlansMaker extends PlanSimplifyForDebug {

	protected PopulationWriter pw;
	private final Config config;

	public HwhPlansMaker( final Scenario scenario ) {
		super( scenario );
		this.config = scenario.getConfig();
		for (int i = 0; i <= 24; i++) {
			loadActType(homeActs, i);
		}
		for (int i = 25; i <= 45; i++) {
			loadActType(workActs, i);
		}
		for (int i = 46; i <= 66; i++) {
			loadActType(eduActs, i);
		}
		pw = new PopulationWriter( scenario.getPopulation(), scenario.getNetwork());
		pw.writeStartPlans(null);//config.plans().getOutputFile());
	}

	protected void loadActType(Set<String> acts, int i) {
		acts.add(config.getParam("planCalcScore", "activityType_" + i));
	}

	public void writeEndPlans() {
		pw.writeEndPlans();
	}

	@Override
	public void run(Person person) {
		super.run(person);
		if (person.getPlans().size() > 0) {
			pw.writePerson(person);
		}
	}

	public static void main(final String[] args) {
		final String netFilename = "./test/yu/ivtch/input/network.xml";
		final String plansFilename = "./test/yu/ivtch/input/allPlansZuerich.xml.gz";
		ScenarioLoaderImpl sl = ScenarioLoaderImpl.createScenarioLoaderImplAndResetRandomSeed("./test/yu/ivtch/config_for_make_hwhPlans.xml");

		Scenario scenario = sl.getScenario();
		Network network = scenario.getNetwork();
		new MatsimNetworkReader(scenario).readFile(netFilename);

		Population population = scenario.getPopulation();

		HwhPlansMaker hpm = new HwhPlansMaker( scenario );

		PopulationReader plansReader = new MatsimPopulationReader(scenario);
		plansReader.readFile(plansFilename);

		hpm.run(population);
		hpm.writeEndPlans();
	}
}
