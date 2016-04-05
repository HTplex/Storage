/* *********************************************************************** *
 * project: org.matsim.*
 * ActivityStartTimes.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2011 by the members listed in the COPYING,        *
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
package playground.johannes.coopsim.analysis;

import gnu.trove.TObjectDoubleHashMap;

import java.util.Set;

import org.matsim.api.core.v01.population.Activity;

import playground.johannes.coopsim.pysical.Trajectory;

/**
 * @author illenberger
 *
 */
public class ArrivalTime extends AbstractTrajectoryProperty {

	private final String purpose;
	
	public ArrivalTime(String purpose) {
		this.purpose = purpose;
	}
	
	@Override
	public TObjectDoubleHashMap<Trajectory> values(Set<? extends Trajectory> trajectories) {
		TObjectDoubleHashMap<Trajectory> values = new TObjectDoubleHashMap<Trajectory>(trajectories.size());
		
		for(Trajectory trajectory : trajectories) {
			for(int i = 0; i < trajectory.getElements().size(); i += 2) {
				Activity act = (Activity)trajectory.getElements().get(i);
				
				if(purpose == null || act.getType().equals(purpose)) {
					if(values.containsKey(trajectory)) {
						if(Math.random() > 0.5) // this is a hack to deal with multiple activities of the same type
							values.put(trajectory, trajectory.getTransitions().get(i));
					} else {
						values.put(trajectory, trajectory.getTransitions().get(i));
					}
						
				}
			}
		}
		
		return values;
	}
}
