/* *********************************************************************** *
 * project: org.matsim.*
 * Mode_choice_main.java
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

package playground.ciarif.modechoice_old;

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.algorithms.NetworkCalcTopoType;
import org.matsim.core.network.algorithms.NetworkSummary;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.facilities.ActivityFacilities;

import playground.balmermi.world.MatsimWorldReader;
import playground.balmermi.world.World;

import java.io.IOException;

public class Mode_choice_main {

	//////////////////////////////////////////////////////////////////////
	// test run 01
	//////////////////////////////////////////////////////////////////////

	public static void testRun01(Config config) {

		System.out.println("TEST RUN 01:");

		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		World world = null;
		final ActivityFacilities facilities = scenario.getActivityFacilities();
		System.out.println("  reading world xml file... ");
		final MatsimWorldReader worldReader = new MatsimWorldReader(scenario, world);
		worldReader.readFile(null);
		System.out.println("  done.");

		System.out.println("  creating network layer... ");
		Network network = scenario.getNetwork();
		System.out.println("  done.");

		System.out.println("  reading network xml file... ");
		new MatsimNetworkReader(scenario).readFile(config.network().getInputFile());
		System.out.println("  done.");

		System.out.println();
		System.out.println("1. VALIDATE AND COMPLETE THE WORLD");
		System.out.println();

		System.out.println("  running world algorithms... ");
	//	new WorldConnectLocations(config).run(world);
		System.out.println("  done.");

		System.out.println();
		System.out.println("2. SUMMARY INFORMATION OF THE NETWORK");
		System.out.println();

		System.out.println("  running network algorithms... ");
		NetworkSummary ns_algo = new NetworkSummary();
		ns_algo.run(network);
		new NetworkCalcTopoType().run(network);
		System.out.println("  done.");

		System.out.println();
		System.out.println("3. CREATING A POPULATION BASED ON THE NETWORK");
		System.out.println();

		System.out.println("  creating plans object... ");
		Population plans = scenario.getPopulation();

		System.out.println("  running plans algorithms... ");
		new PlansCreateFromNetwork(ns_algo,0).run(plans);
		System.out.println("  done.");

		System.out.println();
		System.out.println("4. AGGREGATION OF THE FACILITIES TO THE NETWORK LEVEL");
		System.out.println();

		System.out.println("  reading facilities xml file... ");
		//FacilitiesParser facilities_parser = new FacilitiesParser(facilities);
		//facilities_parser.parse();
		System.out.println("  done.");

		System.out.println("  running facilities algorithms... ");
		//facilities.addAlgorithm(new FacilitiesAggregation(network));
		//facilities.runAlgorithms();
		System.out.println("  done.");

		System.out.println();
		System.out.println("5. DEFINE CAPACITIES AND OPENTIMES FOR THE FACILITIES BASED ON THE POPULATION");
		System.out.println();

		System.out.println("  running facilities algorithms... ");
		//facilities.clearAlgorithms();
		//facilities.addAlgorithm(new FacilitiesDefineCapAndOpentime(plans.getPersons().size()));
		//facilities.runAlgorithms();
		System.out.println("  done.");

		System.out.println();
		System.out.println("6. DEFINE SOME KNOWLEDGE FOR THE POPULATION");
		System.out.println();

		System.out.println();
		System.out.println("7. CREATE AN INITIAL DAYPLAN FOR EACH PERSON ACCORDING TO THEIR KNOWLEDGE");
		System.out.println();

		System.out.println("  running plans algorithms... ");
		// new PersonCreatePlanFromKnowledge(knowledges).run(plans);
		//new FrancescoAlgo().run(plans);

        new TicketAlgo ().run(plans);

		new ModeChoiceAlgorithm().run(plans);
		//new ModeAlgo().run(plans);

		System.out.println("  done.");

		System.out.println();
		System.out.println("8. WRITING DOWN ALL DATA");
		System.out.println();

		System.out.println("  writing facilities xml file... ");
		//FacilitiesWriter facilities_writer = new FacilitiesWriter(Facilities.getSingleton());
		//facilities_writer.write();
		System.out.println("  done.");

		System.out.println("  writing plans xml file... ");
	//	new PopulationWriter(plans, network).write(config.plans().getOutputFile());
		System.out.println("  done.");

//		System.out.println("  writing network xml file... ");
//		NetworkWriter network_writer = new NetworkWriter(network);
	//	network_writer.write(config.network().getOutputFile());
//		System.out.println("  done.");

//		System.out.println("  writing world xml file... ");
//		WorldWriter world_writer = new WorldWriter(world);
	//	world_writer.write(config.world().getOutputFile());
//		System.out.println("  done.");

		System.out.println("  writing config xml file... ");
	//ssss	new ConfigWriter(config).write(config.config().getOutputFile());
		System.out.println("  done.");
		System.out.println("TEST SUCCEEDED.");
		System.out.println();
        throw new RuntimeException("Knowledges are no more.");

    }

	//////////////////////////////////////////////////////////////////////
	// main
	//////////////////////////////////////////////////////////////////////

	public static void main(final String[] args) throws IOException {
		Gbl.startMeasurement();
		Config config = ConfigUtils.loadConfig(args[0]);

		testRun01(config);
		Gbl.printElapsedTime();
	}

}