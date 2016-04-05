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

package playground.johannes.gsv.synPop;

import org.matsim.api.core.v01.TransportMode;

/**
 * @author johannes
 *
 */
public class ConvertRide2Car implements ProxyPlanTask {

	@Override
	public void apply(ProxyPlan plan) {
		for(ProxyObject leg : plan.getLegs()) {
			if(TransportMode.ride.equalsIgnoreCase(leg.getAttribute(CommonKeys.LEG_MODE))) {
				leg.setAttribute(CommonKeys.LEG_MODE, TransportMode.car);
			}
		}

	}

}
