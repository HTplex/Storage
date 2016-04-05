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

package playground.anhorni.choiceSetGeneration.helper;

import org.matsim.core.population.ActivityImpl;

public class Trip {
	
	int tripNr;
	ActivityImpl beforeShoppingAct = null;
	// TODO: handle multiple shopping acts
	ActivityImpl shoppingAct = null;
	ActivityImpl afterShoppingAct = null;
	
	public Trip(int tripNr, ActivityImpl beforeShoppingAct, ActivityImpl shoppingAct,
			ActivityImpl afterShoppingAct) {
		super();
		this.tripNr = tripNr;
		this.beforeShoppingAct = beforeShoppingAct;
		this.shoppingAct = shoppingAct;
		this.afterShoppingAct = afterShoppingAct;
	}
		
	// --------------------------------------------------------
	public ActivityImpl getBeforeShoppingAct() {
		return beforeShoppingAct;
	}
	public void setBeforeShoppingAct(ActivityImpl beforeShoppingAct) {
		this.beforeShoppingAct = beforeShoppingAct;
	}
	public ActivityImpl getShoppingAct() {
		return shoppingAct;
	}
	public void setShoppingAct(ActivityImpl shoppingAct) {
		this.shoppingAct = shoppingAct;
	}
	public ActivityImpl getAfterShoppingAct() {
		return afterShoppingAct;
	}
	public void setAfterShoppingAct(ActivityImpl afterShoppingAct) {
		this.afterShoppingAct = afterShoppingAct;
	}
	public int getTripNr() {
		return tripNr;
	}
	public void setTripNr(int tripNr) {
		this.tripNr = tripNr;
	}

}
