/* *********************************************************************** *
 * project: org.matsim.*
 * NewAgentPtPlan.java
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

package playground.yu.newPlans;

import java.util.List;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.population.MatsimPopulationReader;
import org.matsim.core.population.PopulationReader;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * writes a new Plansfile, in which every person will only have ONE {@code Plan}
 * , that also is the selected {@code Plan} of the old {@code Population}, and
 * the transport mode of this Plan will be changed to "car".
 *
 * @author ychen
 *
 */
public class SelectedCaredPlans extends NewPopulation {
	/**
	 * Constructor, writes file-head
	 *
	 * @param plans
	 *            - a Plans Object, which derives from MATSim plansfile
	 */
	public SelectedCaredPlans(final Network network, Population plans,
			String filename) {
		super(network, plans, filename);
	}

	@Override
	public void run(Person person) {
		Plan selectedPlan = person.getSelectedPlan();

		changedMode(selectedPlan);

		person.getPlans().clear();
		person.addPlan(selectedPlan);
		pw.writePerson(person);
	}

	private void changedMode(Plan plan) {
		List<PlanElement> pes = plan.getPlanElements();
		for (PlanElement pe : pes) {
			if (pe instanceof Leg) {
				Leg leg = (Leg) pe;
				if (!leg.getMode().equals(TransportMode.car)) {
					leg.setMode(TransportMode.car);
				}
			}
		}
	}

	public static void main(final String[] args) {

		String netFilename, populationFilename, outputPopulationFilename;
		if (args.length == 3) {
			netFilename = args[0];
			populationFilename = args[1];
			outputPopulationFilename = args[2];
		} else {
			netFilename = "test/input/network.xml";
			// populationFilename = "test/input/200.plans.xml.gz";
			populationFilename = "test/output/normalOhneJam/ITERS/it.200/200.plans.xml.gz";
			outputPopulationFilename = "test/input/it200ohneJamBasePop.xml.gz";
		}

		Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
		Network network = scenario.getNetwork();
		new MatsimNetworkReader(scenario).readFile(netFilename);

		Population population = scenario.getPopulation();
		PopulationReader plansReader = new MatsimPopulationReader(scenario);
		plansReader.readFile(populationFilename);

		SelectedCaredPlans sp = new SelectedCaredPlans(network, population,
				outputPopulationFilename);
		sp.run(population);
		sp.writeEndPlans();
	}
}
