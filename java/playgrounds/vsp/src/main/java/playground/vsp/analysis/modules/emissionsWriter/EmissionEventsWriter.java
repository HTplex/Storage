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

/**
 * 
 * @author ikaddoura
 * 
 */
package playground.vsp.analysis.modules.emissionsWriter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.matsim.contrib.emissions.ColdEmissionHandler;
import org.matsim.contrib.emissions.EmissionModule;
import org.matsim.contrib.emissions.WarmEmissionHandler;
import org.matsim.core.events.algorithms.EventWriterXML;
import org.matsim.core.events.handler.EventHandler;
import org.matsim.core.scenario.ScenarioImpl;

import playground.vsp.analysis.modules.AbstractAnalysisModule;

/**
 * This module requires a scenario with emission specific informations set in the VspExperimentalConfigGroup.
 * Emission events are written based on a standard events file.
 * 
 * @author ikaddoura, benjamin
 *
 */
public class EmissionEventsWriter extends AbstractAnalysisModule{
	private final static Logger log = Logger.getLogger(EmissionEventsWriter.class);
	private ScenarioImpl scenario;
	private EmissionModule emissionModule;
	private WarmEmissionHandler wEmiHandler;
	private ColdEmissionHandler cEmiHandler;
	private EventWriterXML emissionEventWriter;
	private String outputPath;
	private String filename;
	
	public EmissionEventsWriter(String outputFolder) {
		super(EmissionEventsWriter.class.getSimpleName());
		this.outputPath = outputFolder + this.getName() + "/";
	}
	
	public void init(ScenarioImpl scenario) {
		this.scenario = scenario;
		this.emissionModule = new EmissionModule(scenario);
		this.emissionModule.createLookupTables();
		this.emissionModule.createEmissionHandler();
		this.wEmiHandler = emissionModule.getWarmEmissionHandler();
		this.cEmiHandler = emissionModule.getColdEmissionHandler();
		this.filename = "emission.events.xml.gz";
	}
	
	@Override
	public List<EventHandler> getEventHandler() {
		List<EventHandler> handler = new LinkedList<EventHandler>();
		
		handler.add(this.wEmiHandler);
		handler.add(this.cEmiHandler);
		
		new File(this.outputPath).mkdirs();
		this.emissionEventWriter = new EventWriterXML(this.outputPath + this.filename);
		this.emissionModule.getEmissionEventsManager().addHandler(this.emissionEventWriter);
		
		return handler;
	}

	@Override
	public void preProcessData() {
		// nothing to do
	}

	@Override
	public void postProcessData() {
		// nothing to do
	}

	@Override
	public void writeResults(String outputFolder) {
		// outputFolder is required earlier and therefore not used here, move in abstract class to constructor? ik
		this.emissionEventWriter.closeFile();
		this.emissionModule.writeEmissionInformation(this.outputPath + this.filename);
	}
	
}
