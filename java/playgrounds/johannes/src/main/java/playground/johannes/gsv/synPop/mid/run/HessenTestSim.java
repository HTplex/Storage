/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2014 by the members listed in the COPYING,        *
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

package playground.johannes.gsv.synPop.mid.run;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;

import playground.johannes.gsv.synPop.ActivityType;
import playground.johannes.gsv.synPop.ProxyPerson;
import playground.johannes.gsv.synPop.data.DataPool;
import playground.johannes.gsv.synPop.data.FacilityDataLoader;
import playground.johannes.gsv.synPop.data.FacilityZoneValidator;
import playground.johannes.gsv.synPop.data.LandUseDataLoader;
import playground.johannes.gsv.synPop.invermo.sim.CopyHomeLocations;
import playground.johannes.gsv.synPop.io.XMLParser;
import playground.johannes.gsv.synPop.mid.PersonCloner;
import playground.johannes.gsv.synPop.mid.sim.PersonLau2Inhabitants;
import playground.johannes.gsv.synPop.sim3.ActivityLocationMutatorFactory;
import playground.johannes.gsv.synPop.sim3.AnalyzerListener;
import playground.johannes.gsv.synPop.sim3.BlockingSamplerListener;
import playground.johannes.gsv.synPop.sim3.CopyFacilityUserData;
import playground.johannes.gsv.synPop.sim3.HamiltonianComposite;
import playground.johannes.gsv.synPop.sim3.HamiltonianLogger;
import playground.johannes.gsv.synPop.sim3.InitActivitLocations;
import playground.johannes.gsv.synPop.sim3.InitHomeLocations;
import playground.johannes.gsv.synPop.sim3.MutatorCompositeFactory;
import playground.johannes.gsv.synPop.sim3.PopulationWriter;
import playground.johannes.gsv.synPop.sim3.ReplaceActTypes;
import playground.johannes.gsv.synPop.sim3.Sampler;
import playground.johannes.gsv.synPop.sim3.SamplerListenerComposite;
import playground.johannes.gsv.synPop.sim3.SamplerLogger;
import playground.johannes.gsv.synPop.sim3.SwitchHomeLocationFactory;
import playground.johannes.gsv.synPop.sim3.TargetDistanceAbsolute;
import playground.johannes.gsv.synPop.sim3.TargetDistanceHamiltonian;
import playground.johannes.socialnetworks.utils.XORShiftRandom;

/**
 * @author johannes
 * 
 */
public class HessenTestSim {

	public static final Logger logger = Logger.getLogger(HessenTestSim.class);

	private static final String MODULE_NAME = "popGenerator";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Config config = new Config();
		ConfigUtils.loadConfig(config, args[0]);

		XMLParser parser = new XMLParser();
		parser.setValidating(false);

		logger.info("Loading persons...");
		parser.parse(config.findParam(MODULE_NAME, "popInputFile"));
		Set<ProxyPerson> persons = parser.getPersons();
		logger.info(String.format("Loaded %s persons.", persons.size()));

		logger.info("Replacing activity types...");
		ProxyTaskRunner.run(new ReplaceActTypes(), persons);
		
		logger.info("Cloning persons...");
		Random random = new XORShiftRandom(Long.parseLong(config.getParam("global", "randomSeed")));
		persons = PersonCloner.weightedClones(persons, Integer.parseInt(config.getParam(MODULE_NAME, "targetSize")), random);
		logger.info(String.format("Generated %s persons.", persons.size()));

		logger.info("Loading data...");
		DataPool dataPool = new DataPool();
		dataPool.register(new FacilityDataLoader(config.getParam(MODULE_NAME, "facilities"), random), FacilityDataLoader.KEY);
		dataPool.register(new LandUseDataLoader(config.getModule(MODULE_NAME)), LandUseDataLoader.KEY);
		logger.info("Done.");

		logger.info("Validation data...");
		FacilityZoneValidator.validate(dataPool, ActivityType.HOME, 3);
		FacilityZoneValidator.validate(dataPool, ActivityType.HOME, 1);
		logger.info("Done.");

		
		logger.info("Setting up sampler...");
		/*
		 * Distribute population according to zone values.
		 */
		logger.info("Initializing home locations...");
		new InitHomeLocations(dataPool, random).apply(persons);
		/*
		 * Assign random facilities to acts
		 */
		logger.info("Initializing activity locations...");
		int numThreads = Integer.parseInt(config.findParam(MODULE_NAME, "numThreads"));
//		ConcurrentProxyTaskRunner.run(new InitHomeBasedActLocsFactory(dataPool, random), persons, numThreads);
		ProxyTaskRunner.run(new InitActivitLocations(dataPool), persons);
		/*
		 * Build a hamiltonian to evaluate the target LAU2 zone
		 */
		HamiltonianComposite H = new HamiltonianComposite();
		PersonLau2Inhabitants personLau2 = new PersonLau2Inhabitants(dataPool);
		H.addComponent(personLau2, Double.parseDouble(config.getParam(MODULE_NAME, "thetaPersonLau2")));
		/*
		 * Build a hamiltonian to evaluate the target NUTS1 zone;
		 */
//		PersonNuts1Name personNuts1 = new PersonNuts1Name(dataPool);
//		H.addComponent(personNuts1, 5);
		/*
		 * Build a hamiltonian to evaluate the target distance
		 */
		TargetDistanceHamiltonian distance = new TargetDistanceHamiltonian();
		H.addComponent(distance, Double.parseDouble(config.getParam(MODULE_NAME, "thetaTargetDistance")));
		/*
		 * Build the move set and sampler
		 */
		MutatorCompositeFactory factory = new MutatorCompositeFactory(random);
		factory.addFactory(new SwitchHomeLocationFactory(random));
		factory.addFactory(new ActivityLocationMutatorFactory(dataPool, ActivityType.HOME, random));

		Sampler sampler = new Sampler(persons, H, factory, random);
		/*
		 * Build the listener
		 */
//		SamplerListenerComposite lComposite = new SamplerListenerComposite();
		/*
		 * need to copy home location user key to activity attributes
		 */
		long dumpInterval = (long) Double.parseDouble(config.getParam(MODULE_NAME, "dumpInterval"));
		long logInterval = (long) Double.parseDouble(config.getParam(MODULE_NAME, "logInterval"));
		String outputDir = config.getParam(MODULE_NAME, "outputDir");

		if(dumpInterval % logInterval != 0) {
			throw new RuntimeException("The dump intervall needs to be a multiple of the log intervall.");
		}
		
		SamplerListenerComposite listener = new SamplerListenerComposite();
		listener.addComponent(new HamiltonianLogger(personLau2, logInterval, outputDir));
		listener.addComponent(new HamiltonianLogger(distance, logInterval, outputDir));
		listener.addComponent(new HamiltonianLogger(new TargetDistanceAbsolute(), logInterval, outputDir));
		listener.addComponent(new CopyHomeLocations(dumpInterval));
		listener.addComponent(new CopyFacilityUserData(dumpInterval));
		listener.addComponent(new PopulationWriter(outputDir, dumpInterval));
		listener.addComponent(new AnalyzerListener(dataPool, outputDir, dumpInterval));

//		lComposite.addComponent(new BlockingSamplerListener(listener, dumpInterval, numThreads));
		/*
		 * add loggers
		 */
//		lComposite.addComponent(new BlockingSamplerListener(new HamiltonianLogger(personLau2, logInterval, outputDir), logInterval, numThreads));
////		lComposite.addComponent(new BlockingSamplerListener(new HamiltonianLogger(personNuts1, logInterval, outputDir), logInterval, numThreads));
//		lComposite.addComponent(new BlockingSamplerListener(new HamiltonianLogger(distance, logInterval, outputDir), logInterval, numThreads));
//		lComposite.addComponent(new BlockingSamplerListener(new HamiltonianLogger(new TargetDistanceAbsolute(), logInterval, outputDir), logInterval,
//				numThreads));
		
		SamplerLogger slogger = new SamplerLogger();
		listener.addComponent(slogger);

		sampler.setSamplerListener(new BlockingSamplerListener(listener, logInterval, numThreads));

		logger.info("Running sampler...");
		long iters = (long) Double.parseDouble(config.getParam(MODULE_NAME, "iterations"));

		sampler.run(iters, numThreads);
		slogger.stop();
		logger.info("Done.");
	}

}
