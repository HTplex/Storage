/* *********************************************************************** *
 * project: org.matsim.*
 * TravelStats.java
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

package playground.anhorni.locationchoice.analysis;

import org.matsim.api.core.v01.population.*;
import org.matsim.core.controler.events.IterationEndsEvent;
import org.matsim.core.controler.events.ShutdownEvent;
import org.matsim.core.controler.events.StartupEvent;
import org.matsim.core.controler.listener.IterationEndsListener;
import org.matsim.core.controler.listener.ShutdownListener;
import org.matsim.core.controler.listener.StartupListener;
import org.matsim.core.utils.geometry.CoordImpl;
import playground.anhorni.locationchoice.preprocess.plans.modifications.DistanceBins;

import java.util.List;

/**
 * @author anhorni
 */
public class TravelDistanceDistribution implements StartupListener, IterationEndsListener, ShutdownListener {

	private Population population;
	private DistanceBins shopDistanceBins;
	private DistanceBins leisureDistanceBins;

	@Override
	public void notifyStartup(final StartupEvent event) {
        this.population = event.getControler().getScenario().getPopulation();
	}

	@Override
	public void notifyIterationEnds(final IterationEndsEvent event) {

		shopDistanceBins = new DistanceBins(1000.0, 100.0 * 1000.0, "car");
		leisureDistanceBins = new DistanceBins(1000.0, 100.0 * 1000.0, "car");

		for (Person person : this.population.getPersons().values()) {
			Plan selectedPlan = person.getSelectedPlan();

			final List<?> actslegs = selectedPlan.getPlanElements();
			for (int j = 1; j < actslegs.size(); j=j+2) {
				if (actslegs.get(j) instanceof Leg) {
					Leg leg = (Leg) actslegs.get(j);
					Activity act = (Activity)actslegs.get(j+1);

					if (!leg.getMode().equals("car")) {
						continue;
					}

					double dist = ((CoordImpl)act.getCoord()).calcDistance(((Activity)actslegs.get(j-1)).getCoord()) / 1000.0;

					// act type
					String actType = act.getType();
					if (actType.startsWith("shop")) {
						shopDistanceBins.addDistance(dist);
					}
					else if (actType.startsWith("leisure")) {
						leisureDistanceBins.addDistance(dist);
					}
				}
			}
		}
		this.shopDistanceBins.plotDistribution(event.getControler().getControlerIO().getIterationFilename(event.getIteration(), "shopDistanceDistributions"), "");
		this.leisureDistanceBins.plotDistribution(event.getControler().getControlerIO().getIterationFilename(event.getIteration(), "leisureDistanceDistributions"), "");
	}

	@Override
	public void notifyShutdown(final ShutdownEvent controlerShudownEvent) {
	}
}
