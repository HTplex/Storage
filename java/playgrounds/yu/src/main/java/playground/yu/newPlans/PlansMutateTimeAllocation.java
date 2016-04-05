/* *********************************************************************** *
 * project: org.matsim.*
 * PlansMutateTimeAllocation.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2009 by the members listed in the COPYING,        *
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

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.population.MatsimPopulationReader;
import org.matsim.core.population.PopulationWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.population.algorithms.PlanMutateTimeAllocation;

/**
 * @author yu
 *
 */
public class PlansMutateTimeAllocation {

	public static void main(String[] args) {

		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		Network network = scenario.getNetwork();
		new MatsimNetworkReader(scenario)
				.readFile("D:/fromNB04/wm/Toronto/toronto/networks/changedNetworkWithManeuvers/network.xml");

		Population population = scenario.getPopulation();
		new MatsimPopulationReader(scenario)
				.readFile("D:/fromNB04/wm/Toronto/toronto/plans/xy/plans.xml.gz");

		PlanMutateTimeAllocation pmta = new PlanMutateTimeAllocation(1800,
				MatsimRandom.getLocalInstance());

		for (Person person : population.getPersons().values())
			for (Plan plan : person.getPlans())
				pmta.run(plan);

		new PopulationWriter(population, network).write("D:/fromNB04/wm/Toronto/toronto/plans/xy/plansBlurred.xml.gz");

		System.out.println("done.");
	}
}
