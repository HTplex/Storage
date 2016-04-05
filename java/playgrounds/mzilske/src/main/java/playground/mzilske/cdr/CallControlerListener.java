/*
 *  *********************************************************************** *
 *  * project: org.matsim.*
 *  * CallControlerListener.java
 *  *                                                                         *
 *  * *********************************************************************** *
 *  *                                                                         *
 *  * copyright       : (C) 2014 by the members listed in the COPYING, *
 *  *                   LICENSE and WARRANTY file.                            *
 *  * email           : info at matsim dot org                                *
 *  *                                                                         *
 *  * *********************************************************************** *
 *  *                                                                         *
 *  *   This program is free software; you can redistribute it and/or modify  *
 *  *   it under the terms of the GNU General Public License as published by  *
 *  *   the Free Software Foundation; either version 2 of the License, or     *
 *  *   (at your option) any later version.                                   *
 *  *   See also COPYING, LICENSE and WARRANTY file                           *
 *  *                                                                         *
 *  * ***********************************************************************
 */

package playground.mzilske.cdr;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.controler.events.ShutdownEvent;
import org.matsim.core.controler.listener.ShutdownListener;

import javax.inject.Inject;

class CallControlerListener implements ShutdownListener {

    @Inject
    Scenario scenario;

    @Inject
    Sightings sightings;

    @Inject
    CallProcessTicker ticker;

    @Inject
    CallProcess callProcess;

    @Override
    public void notifyShutdown(ShutdownEvent event) {
        ticker.finish();
        callProcess.finish();
        scenario.addScenarioElement("sightings", sightings);
    }

}
