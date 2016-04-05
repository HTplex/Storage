/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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

package playground.anhorni.PLOC.zh;

import java.io.File;
import java.util.Random;

import org.apache.log4j.Logger;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigWriter;
import org.matsim.core.config.ConfigReader;

import playground.anhorni.LEGO.miniscenario.create.AdaptZHScenario;
import playground.anhorni.PLOC.SingleRunControler;

public class MultipleRunsControler {
	
	private final static Logger log = Logger.getLogger(MultipleRunsControler.class);
	private int numberOfRuns = 10;
	private Random randomNumberGenerator = new Random(834273782);
	private String inPathStub;
	private String outPathStub;

	public static void main(String[] args) {
		MultipleRunsControler runControler = new MultipleRunsControler();
		runControler.init(args[0]);
    	runControler.run();
	}
	
	private void init(String createConfigFile) {
		Config createConfig = new Config();
    	ConfigReader configReader = new ConfigReader(createConfig);
    	configReader.readFile(createConfigFile);
    	
    	this.numberOfRuns = Integer.parseInt(createConfig.findParam("PLOC", "numberOfRuns"));
    	log.info("number of Runs: " + numberOfRuns);
    	
    	inPathStub = createConfig.findParam("PLOC", "inPathStub");
    	outPathStub = createConfig.findParam("PLOC", "outPathStub");
    	
    	boolean runOnly = Boolean.parseBoolean(createConfig.findParam("PLOC", "runOnly"));
    	
    	for (int i = 0; i < 100000; i++) {
    		this.randomNumberGenerator.nextLong();
    	}
    	
    	if (!runOnly) this.createPlansAndConfigs(createConfig);	
	}
	
	private void createPlansAndConfigs(Config createConfig) {
		Config runConfig = new Config();
    	ConfigReader configReader = new ConfigReader(runConfig);
    	String runConfigFile = createConfig.findParam("PLOC", "runConfig");
    	configReader.readFile(runConfigFile);
    	
    	for (int runIndex = 0; runIndex < numberOfRuns; runIndex++) {
    		
    		long seed = randomNumberGenerator.nextLong();
    		log.info("seed :" + seed);
    		createConfig.setParam("locationchoiceExperimental", "randomSeed", Long.toString(seed));
    		createConfig.setParam("controler", "outputDirectory", this.inPathStub + "/runs/run" + runIndex + "/");
    			
    		String configPath = inPathStub + "/runs/";
        	new File(configPath).mkdirs();
        	ConfigWriter configWriter = new ConfigWriter(createConfig);
        	configWriter.write(configPath + "/createConfigAdapted.xml");	
        	this.adaptPlansAndFacilities(configPath + "/createConfigAdapted.xml");
    		
    		// now write the final config  	
        	runConfig.setParam("global", "randomSeed", Long.toString(seed));
    		runConfig.setParam("locationchoiceExperimental", "randomSeed", Long.toString(seed));
        	runConfig.setParam("plans", "inputPlansFile", inPathStub + "/runs/run" + runIndex + "/plans.xml.gz");
        	runConfig.setParam("controler", "runId", Integer.toString(runIndex));
        	runConfig.setParam("controler", "outputDirectory", this.outPathStub + "/runs/run" + runIndex + "/");
        	runConfig.setParam("facilities", "inputFacilitiesFile", inPathStub + "/runs/run" + runIndex + "/facilities.xml.gz");
        	String path = inPathStub + "/runs/run" + runIndex;
        	
        	new File(path).mkdirs();
        	configWriter = new ConfigWriter(runConfig);       	        	
        	configWriter.write(path + "/config.xml");			
    	}	
	}
	
	private void adaptPlansAndFacilities(String configFile) {
		log.info("adapting the scenario .... " );
		AdaptZHScenario adapter = new AdaptZHScenario();
		adapter.run(configFile);
	}
	
	public void run() {
		for (int runIndex = 0; runIndex < numberOfRuns; runIndex++) {
			String configFile = inPathStub + "/runs/run" + runIndex + "/config.xml";
			String config = configFile;
			SingleRunControler controler;
    		controler = new SingleRunControler(config);	 
        	controler.run();
		}
	}
}
