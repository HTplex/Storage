/* *********************************************************************** *
 * project: org.matsim.*
 * MyCalcAverageTripLength.java
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

/**
 * 
 */
package playground.yu.analysis;

import org.matsim.analysis.CalcAverageTripLength;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.api.core.v01.population.Route;


/**
 * It's a subclass of {@code CalcAverageTripLength} and can also calculate trip
 * length of pseudo-pt leg.
 * 
 * @author yu
 * 
 */
public class MyCalcAverageTripLength extends CalcAverageTripLength {
	private double sumLength = 0.0;
	private int cntTrips = 0;
	private final Network network;

	/**
	 * @param network
	 */
	public MyCalcAverageTripLength(Network network) {
		super(network);
		this.network = network;
	}

	@Override
	public void run(Plan plan) {
		for (PlanElement pe : plan.getPlanElements()) {
			if (pe instanceof Leg) {
				Route route = ((Leg) pe).getRoute();
				if (route != null) {
					this.sumLength += CalcRouteDistance.getRouteDistance(route,
							network);
					this.cntTrips++;
				}
			}
		}
	}

	public double getAverageTripLength() {
		if (this.cntTrips == 0) {
			return 0;
		}
		return (this.sumLength / this.cntTrips);
	}
}
