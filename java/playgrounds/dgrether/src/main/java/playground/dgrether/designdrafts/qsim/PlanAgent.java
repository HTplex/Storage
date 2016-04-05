/* *********************************************************************** *
 * project: org.matsim.*
 * PlanAgent
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
package playground.dgrether.designdrafts.qsim;

import org.matsim.api.core.v01.population.PlanElement;


/**
 * If this is the basic agent class we should think about activityEnds() and
 * legEnds() are those really needed for a basic agent? 
 * @author dgrether
 *
 */
public interface PlanAgent  extends Agent {

	public double getDepartureTime();
	
	
	public void activityEnds();
	
	public void legEnds();

	/**
	 * Irgendeine Art von Weiterschaltung ist benötigt
	 * @return
	 */
	public PlanElement getCurrentPlanElement();
	public PlanElement getNextPlanElement();
	
}
