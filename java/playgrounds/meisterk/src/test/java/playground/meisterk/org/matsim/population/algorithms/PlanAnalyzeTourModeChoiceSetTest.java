/* *********************************************************************** *
 * project: org.matsim.*
 * PlanAnalyzeTourModeChoiceSetTest.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
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

package playground.meisterk.org.matsim.population.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.BasicLocation;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.PersonImpl;
import org.matsim.core.population.PlanImpl;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.facilities.ActivityFacilities;
import org.matsim.facilities.ActivityFacilitiesImpl;
import org.matsim.facilities.ActivityFacility;
import org.matsim.facilities.MatsimFacilitiesReader;
import org.matsim.testcases.MatsimTestCase;

import playground.meisterk.org.matsim.config.PlanomatConfigGroup;
import playground.meisterk.org.matsim.config.groups.MeisterkConfigGroup;

/**
 * Test class for {@link PlanAnalyzeTourModeChoiceSet}.
 *
 * Contains illustrative examples for analysis of feasible mode chains. See documentation <a href=http://matsim.org/node/267">here</a>.
 * @author meisterk
 *
 */
public class PlanAnalyzeTourModeChoiceSetTest extends MatsimTestCase {

	private static final String CONFIGFILE = "test/scenarios/equil/config.xml";
	private static Logger log = Logger.getLogger(PlanAnalyzeTourModeChoiceSetTest.class);

	private Config config;
	
	private PlanomatConfigGroup planomatConfigGroup = new PlanomatConfigGroup();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.config = super.loadConfig(PlanAnalyzeTourModeChoiceSetTest.CONFIGFILE);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.config = null;
	}

	public void testFacilitiesBased() {

		// load data
		log.info("Reading facilities xml file...");
		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(this.config);
		ActivityFacilities facilities = scenario.getActivityFacilities();
		new MatsimFacilitiesReader(scenario).readFile(this.config.facilities().getInputFile());
		log.info("Reading facilities xml file...done.");

		// run
		this.runDemo((ActivityFacilitiesImpl) facilities);

	}

	public void testNetworkBased() {

		// load data
		log.info("Reading network xml file...");
		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		Network network = scenario.getNetwork();
		new MatsimNetworkReader(scenario).readFile(this.config.network().getInputFile());
		log.info("Reading network xml file...done.");

		// config
		this.planomatConfigGroup.setTripStructureAnalysisLayer(PlanomatConfigGroup.TripStructureAnalysisLayerOption.link);

		// run
		this.runDemo((NetworkImpl) network);

	}

	private Map<String, ArrayList<String[]>> getTestCases() {
		HashMap<String, ArrayList<String[]>> testCases = new HashMap<String, ArrayList<String[]>>();

		////////////////////////////////////////////////////////////////
		//
		// 1 2 1
		//
		////////////////////////////////////////////////////////////////
		String testedActChainLocations = "1 2 1";
		ArrayList<String[]> expectedTourModeOptions = new ArrayList<String[]>();
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk});
		testCases.put(testedActChainLocations, expectedTourModeOptions);

		////////////////////////////////////////////////////////////////
		//
		// 1 2 20 1
		//
		////////////////////////////////////////////////////////////////
		testedActChainLocations = "1 2 20 1";
		expectedTourModeOptions = new ArrayList<String[]>();
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.walk});
		testCases.put(testedActChainLocations, expectedTourModeOptions);

		////////////////////////////////////////////////////////////////
		//
		// 1 2 1 2 1
		//
		////////////////////////////////////////////////////////////////

		testedActChainLocations = "1 2 1 2 1";
		expectedTourModeOptions = new ArrayList<String[]>();
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.pt, TransportMode.pt, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.pt, TransportMode.walk, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.walk, TransportMode.pt, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.walk, TransportMode.walk, TransportMode.car});

		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.walk, TransportMode.walk});

		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.pt, TransportMode.pt, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.pt, TransportMode.walk, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.walk, TransportMode.pt, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.walk, TransportMode.walk, TransportMode.bike});

		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.walk, TransportMode.walk});
		testCases.put(testedActChainLocations, expectedTourModeOptions);

		////////////////////////////////////////////////////////////////
		//
		// 1 2 1 3 1
		//
		////////////////////////////////////////////////////////////////
		testedActChainLocations = "1 2 1 3 1";
		expectedTourModeOptions = new ArrayList<String[]>();
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.walk, TransportMode.walk});

		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.walk, TransportMode.walk});

		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.walk, TransportMode.walk});

		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.walk, TransportMode.walk});
		testCases.put(testedActChainLocations, expectedTourModeOptions);

		////////////////////////////////////////////////////////////////
		//
		// 1 2 3 4
		//
		////////////////////////////////////////////////////////////////
		testedActChainLocations = "1 2 3 4";
		expectedTourModeOptions = new ArrayList<String[]>();
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.walk});
		testCases.put(testedActChainLocations, expectedTourModeOptions);

		////////////////////////////////////////////////////////////////
		//
		// 1 2 2 1
		//
		////////////////////////////////////////////////////////////////
		testedActChainLocations = "1 2 2 1";
		expectedTourModeOptions = new ArrayList<String[]>();

		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.pt, TransportMode.car});
		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.walk, TransportMode.car});

		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.pt, TransportMode.walk, TransportMode.walk});

		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.pt, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.walk, TransportMode.bike});

		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.pt, TransportMode.walk});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.pt});
		expectedTourModeOptions.add(new String[]{TransportMode.walk, TransportMode.walk, TransportMode.walk});

		testCases.put(testedActChainLocations, expectedTourModeOptions);

		////////////////////////////////////////////////////////////////
		//
		// 1 2 3 4 3 2 1
		//
		////////////////////////////////////////////////////////////////
		testedActChainLocations = "1 2 3 4 3 2 1";
		expectedTourModeOptions = new ArrayList<String[]>();

		expectedTourModeOptions.add(new String[]{TransportMode.car, TransportMode.car, TransportMode.car, TransportMode.car, TransportMode.car, TransportMode.car});

		int variableLegs = 2;
		for (int ii = 0; ii < (int) Math.pow(2, variableLegs); ii++) {
			String[] combination = new String[testedActChainLocations.split(" ").length - 1];
			combination[0] = combination[1] = combination[4] = combination[5] = TransportMode.car;
			for (int jj = 0; jj < variableLegs ; jj++) {
				combination[jj + 2] = (((ii & ((int) Math.pow(2, variableLegs - (jj + 1)))) == 0)) ? TransportMode.pt : TransportMode.walk;
			}
			expectedTourModeOptions.add(combination);
		}

		variableLegs = 4;
		for (int ii = 0; ii < (int) Math.pow(2, variableLegs); ii++) {
			String[] combination = new String[testedActChainLocations.split(" ").length - 1];
			combination[0] = combination[5] = TransportMode.car;
			for (int jj = 0; jj < variableLegs ; jj++) {
				combination[jj + 1] = (((ii & ((int) Math.pow(2, variableLegs - (jj + 1)))) == 0)) ? TransportMode.pt : TransportMode.walk;
			}
			expectedTourModeOptions.add(combination);
		}

		variableLegs = 5;
		for (int ii = 0; ii < (int) Math.pow(2, variableLegs); ii++) {
			String[] combination = new String[testedActChainLocations.split(" ").length - 1];
			combination[0] = TransportMode.pt;
			for (int jj = 0; jj < variableLegs; jj++) {
				combination[jj + 1] = (((ii & ((int) Math.pow(2, variableLegs - (jj + 1)))) == 0)) ? TransportMode.pt : TransportMode.walk;
			}
			expectedTourModeOptions.add(combination);
		}

		variableLegs = 3;
		for (int ii = 0; ii < (int) Math.pow(2, variableLegs); ii++) {
			String[] combination = new String[testedActChainLocations.split(" ").length - 1];
			combination[0] = combination[5] = TransportMode.bike;
			combination[1] = TransportMode.pt;
			for (int jj = 0; jj < variableLegs; jj++) {
				combination[jj + 2] = (((ii & ((int) Math.pow(2, variableLegs - (jj + 1)))) == 0)) ? TransportMode.pt : TransportMode.walk;
			}
			expectedTourModeOptions.add(combination);
		}

		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.pt, TransportMode.pt, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.pt, TransportMode.walk, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.bike, TransportMode.bike, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.walk, TransportMode.pt, TransportMode.bike, TransportMode.bike});
		expectedTourModeOptions.add(new String[]{TransportMode.bike, TransportMode.bike, TransportMode.walk, TransportMode.walk, TransportMode.bike, TransportMode.bike});

		variableLegs = 3;
		for (int ii = 0; ii < (int) Math.pow(2, variableLegs); ii++) {
			String[] combination = new String[testedActChainLocations.split(" ").length - 1];
			combination[0] = combination[5] = TransportMode.bike;
			combination[1] = TransportMode.walk;
			for (int jj = 0; jj < variableLegs; jj++) {
				combination[jj + 2] = (((ii & ((int) Math.pow(2, variableLegs - (jj + 1)))) == 0)) ? TransportMode.pt : TransportMode.walk;
			}
			expectedTourModeOptions.add(combination);
		}

		variableLegs = 5;
		for (int ii = 0; ii < (int) Math.pow(2, variableLegs); ii++) {
			String[] combination = new String[testedActChainLocations.split(" ").length - 1];
			combination[0] = TransportMode.walk;
			for (int jj = 0; jj < variableLegs; jj++) {
				combination[jj + 1] = (((ii & ((int) Math.pow(2, variableLegs - (jj + 1)))) == 0)) ? TransportMode.pt : TransportMode.walk;
			}
			expectedTourModeOptions.add(combination);
		}

		testCases.put(testedActChainLocations, expectedTourModeOptions);
		
		return testCases;
	}
	
	protected void runDemo(NetworkImpl network) {
		Map<String, ArrayList<String[]>> testCases = getTestCases();

		PlanAnalyzeTourModeChoiceSet testee = new PlanAnalyzeTourModeChoiceSet(new MeisterkConfigGroup().getChainBasedModes(), this.planomatConfigGroup.getTripStructureAnalysisLayer(), null, network);
		Set<String> possibleModes = new LinkedHashSet<String>();
		possibleModes.add(TransportMode.car);
		possibleModes.add(TransportMode.pt);
		possibleModes.add(TransportMode.bike);
		possibleModes.add(TransportMode.walk);
		testee.setModeSet(possibleModes);

		for (Entry<String, ArrayList<String[]>> entry : testCases.entrySet()) {

			String facString  = entry.getKey();
			log.info("Testing location sequence: " + facString);

			PlanImpl plan = this.generateTestPlan(facString, network);

			testee.run(plan);

			ArrayList<String[]> actual = testee.getChoiceSet();
			assertEquals(entry.getValue().size(), actual.size());
			for (int i = 0; i < entry.getValue().size(); i++) {
				String[] a = actual.get(i);
				String[] e = entry.getValue().get(i);
				assertTrue(Arrays.deepEquals(e, a));
			}
		}
	}
	
	protected void runDemo(ActivityFacilitiesImpl facilities) {
		Map<String, ArrayList<String[]>> testCases = getTestCases();
		
		PlanAnalyzeTourModeChoiceSet testee = new PlanAnalyzeTourModeChoiceSet(new MeisterkConfigGroup().getChainBasedModes(), this.planomatConfigGroup.getTripStructureAnalysisLayer(), facilities, null);
		Set<String> possibleModes = new LinkedHashSet<String>();
		possibleModes.add(TransportMode.car);
		possibleModes.add(TransportMode.pt);
		possibleModes.add(TransportMode.bike);
		possibleModes.add(TransportMode.walk);
		testee.setModeSet(possibleModes);
		
		for (Entry<String, ArrayList<String[]>> entry : testCases.entrySet()) {
			
			String facString  = entry.getKey();
			log.info("Testing location sequence: " + facString);
			
			PlanImpl plan = this.generateTestPlan(facString, facilities);
			
			testee.run(plan);
			
			ArrayList<String[]> actual = testee.getChoiceSet();
			assertEquals(entry.getValue().size(), actual.size());
			for (int i = 0; i < entry.getValue().size(); i++) {
				String[] a = actual.get(i);
				String[] e = entry.getValue().get(i);
				assertTrue(Arrays.deepEquals(e, a));
			}
		}
	}

	public void testIsModeChainFeasible(ActivityFacilitiesImpl facilities) {

		// load data
		log.info("Reading network xml file...");
		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		Network network = scenario.getNetwork();
		new MatsimNetworkReader(scenario).readFile(this.config.network().getInputFile());
		log.info("Reading network xml file...done.");

		// config
		this.planomatConfigGroup.setTripStructureAnalysisLayer(PlanomatConfigGroup.TripStructureAnalysisLayerOption.link);

		String facString = "1 2 3 4 3 2 1";
		PlanImpl testPlan = this.generateTestPlan(facString, facilities);
		Set<String> chainBasedModes = new HashSet<String>();
		chainBasedModes.add(TransportMode.bike);
		chainBasedModes.add(TransportMode.car);

		HashMap<String[], Boolean> candidates = new HashMap<String[], Boolean>();
		candidates.put(
				new String[]{
						TransportMode.car,
						TransportMode.car,
						TransportMode.car,
						TransportMode.car,
						TransportMode.car,
						TransportMode.car},
				true);
		candidates.put(
				new String[]{
						TransportMode.car,
						TransportMode.pt,
						TransportMode.car,
						TransportMode.car,
						TransportMode.pt,
						TransportMode.car},
				false);
		candidates.put(
				new String[]{
						TransportMode.car,
						TransportMode.pt,
						TransportMode.walk,
						TransportMode.walk,
						TransportMode.pt,
						TransportMode.car},
				true);
		candidates.put(
				new String[]{
						TransportMode.car,
						TransportMode.pt,
						TransportMode.walk,
						TransportMode.walk,
						TransportMode.pt,
						TransportMode.walk},
				false);

		for (String[] candidate : candidates.keySet()) {
			assertEquals(
					Boolean.valueOf(PlanAnalyzeTourModeChoiceSet.isModeChainFeasible(testPlan, candidate, chainBasedModes, this.planomatConfigGroup.getTripStructureAnalysisLayer(), facilities, network)),
					candidates.get(candidate));
		}

	}

	private PlanImpl generateTestPlan(String facString, NetworkImpl network) {
		PersonImpl person = new PersonImpl(Id.create("1000", Person.class));
		PlanomatConfigGroup.TripStructureAnalysisLayerOption tripStructureAnalysisLayer = this.planomatConfigGroup.getTripStructureAnalysisLayer();
		BasicLocation location = null;
		ActivityImpl act = null;

		PlanImpl plan = new org.matsim.core.population.PlanImpl(person);

		String[] locationIdSequence = facString.split(" ");
		for (int aa=0; aa < locationIdSequence.length; aa++) {
			location = network.getLinks().get(Id.create(locationIdSequence[aa], Link.class));
			if (PlanomatConfigGroup.TripStructureAnalysisLayerOption.facility.equals(tripStructureAnalysisLayer)) {
				act = plan.createAndAddActivity("actAtFacility" + locationIdSequence[aa]);
				act.setFacilityId(location.getId());
			} else if (PlanomatConfigGroup.TripStructureAnalysisLayerOption.link.equals(tripStructureAnalysisLayer)) {
				act = plan.createAndAddActivity("actOnLink" + locationIdSequence[aa], location.getId());
			}
			act.setEndTime(10*3600);
			if (aa != (locationIdSequence.length - 1)) {
				plan.createAndAddLeg("undefined");
			}
		}
		return plan;
	}

	private PlanImpl generateTestPlan(String facString, ActivityFacilitiesImpl facilities) {
		PersonImpl person = new PersonImpl(Id.create("1000", Person.class));
		PlanomatConfigGroup.TripStructureAnalysisLayerOption tripStructureAnalysisLayer = this.planomatConfigGroup.getTripStructureAnalysisLayer();
		BasicLocation location = null;
		ActivityImpl act = null;
		
		PlanImpl plan = new org.matsim.core.population.PlanImpl(person);
		
		String[] locationIdSequence = facString.split(" ");
		for (int aa=0; aa < locationIdSequence.length; aa++) {
			location = facilities.getFacilities().get(Id.create(locationIdSequence[aa], ActivityFacility.class));
			if (PlanomatConfigGroup.TripStructureAnalysisLayerOption.facility.equals(tripStructureAnalysisLayer)) {
				act = plan.createAndAddActivity("actAtFacility" + locationIdSequence[aa]);
				act.setFacilityId(location.getId());
			} else if (PlanomatConfigGroup.TripStructureAnalysisLayerOption.link.equals(tripStructureAnalysisLayer)) {
				act = plan.createAndAddActivity("actOnLink" + locationIdSequence[aa], Id.create(location.getId(), Link.class));
			}
			act.setEndTime(10*3600);
			if (aa != (locationIdSequence.length - 1)) {
				plan.createAndAddLeg("undefined");
			}
		}
		return plan;
	}

}
