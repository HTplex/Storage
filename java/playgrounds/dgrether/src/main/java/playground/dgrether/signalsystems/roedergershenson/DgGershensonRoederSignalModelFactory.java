/* *********************************************************************** *
 * project: org.matsim.*
 * DgGershensonRoederSignalModelFactory
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
package playground.dgrether.signalsystems.roedergershenson;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.contrib.signals.builder.SignalModelFactory;
import org.matsim.contrib.signals.data.signalgroups.v20.SignalPlanData;
import org.matsim.contrib.signals.model.SignalController;
import org.matsim.contrib.signals.model.SignalPlan;
import org.matsim.contrib.signals.model.SignalSystem;
import org.matsim.contrib.signals.model.SignalSystemsManager;


/**
 * @author dgrether
 *
 */
public final class DgGershensonRoederSignalModelFactory implements SignalModelFactory {
	
	private static final Logger log = Logger.getLogger(DgGershensonRoederSignalModelFactory.class);
	
	private SignalModelFactory delegate;

	public DgGershensonRoederSignalModelFactory(SignalModelFactory delegate){
		this.delegate = delegate;
	}
	
	@Override
	public SignalSystem createSignalSystem(Id id) {
		return this.delegate.createSignalSystem(id);
	}

	@Override
	public SignalController createSignalSystemController(String controllerIdentifier) {
		if (DgRoederGershensonController.CONTROLLER_IDENTIFIER.equals(controllerIdentifier)){
			log.info("Created controller: " + DgRoederGershensonController.CONTROLLER_IDENTIFIER);
			return new DgRoederGershensonController();
		}
		return this.delegate.createSignalSystemController(controllerIdentifier);
	}

	@Override
	public SignalSystemsManager createSignalSystemsManager() {
		return this.delegate.createSignalSystemsManager();
	}

	@Override
	public SignalPlan createSignalPlan(SignalPlanData planData) {
		return this.delegate.createSignalPlan(planData);
	}

}
