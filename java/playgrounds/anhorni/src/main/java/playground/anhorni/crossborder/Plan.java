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

package playground.anhorni.crossborder;

import org.matsim.api.core.v01.Id;

public class Plan {
	
	private Id startLink;
	private Id endLink;
	
	private Id id;
	private String activityType;
	private String tempHomeType;
	private int startTime;
	
	public Plan(Id startLink, Id endLink) {
		this.startLink=startLink;
		this.endLink=endLink;
	}
	
	public Id getStartLink() {
		return startLink;
	}
	public void setStartLink(Id startLink) {
		this.startLink = startLink;
	}
	public Id getEndLink() {
		return endLink;
	}
	public void setEndLink(Id endLink) {
		this.endLink = endLink;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getTempHomeType() {
		return tempHomeType;
	}
	public void setTempHomeType(String tempHomeType) {
		this.tempHomeType = tempHomeType;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}
}
