/* *********************************************************************** *
 * project: org.matsim.*
 * HouseholdInfo.java
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

package playground.christoph.evacuation.withinday.replanning.utils;

import java.util.HashSet;
import java.util.Set;

import org.matsim.api.core.v01.Id;
import org.matsim.households.Household;

public class HouseholdInfo {
	private final Household household;
	private final Set<Id> membersAtMeetingPoint;
	private Id meetingPointId;	// facilityId
	private boolean homeLocationIsSecure;
	
	public HouseholdInfo(Household household) {
		this.household = household;
		this.membersAtMeetingPoint = new HashSet<Id>();
		this.homeLocationIsSecure = true;
	}
	
	public Household getHousehold() {
		return this.household;
	}
	
	public boolean isHomeLocationSecure() {
		return this.homeLocationIsSecure;
	}
	
	/*package*/ void setHomeLocationIsSecure(boolean value) {
		this.homeLocationIsSecure = value;
	}
	
	/*package*/ boolean addPersonAtMeetingpoint(Id id) {
		return membersAtMeetingPoint.add(id);
	}
	
	/*package*/ boolean removePersonAtMeetingPoint(Id id) {
		return membersAtMeetingPoint.remove(id);
	}
	
	/*package*/ void resetMembersAtMeetingPoint() {
		this.membersAtMeetingPoint.clear();
	}
	
	/*package*/ void setMeetingPointId(Id id) {
		this.meetingPointId = id;
	}
	
	public Id getMeetingPointId() {
		return this.meetingPointId;
	}
	
	public boolean allMembersAtMeetingPoint() {
		return household.getMemberIds().size() == membersAtMeetingPoint.size();
	}
}
