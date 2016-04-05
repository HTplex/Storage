///* *********************************************************************** *
// * project: org.matsim.*
// * PersonLicenseModel.java
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// * copyright       : (C) 2007 by the members listed in the COPYING,        *
// *                   LICENSE and WARRANTY file.                            *
// * email           : info at matsim dot org                                *
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// *   This program is free software; you can redistribute it and/or modify  *
// *   it under the terms of the GNU General Public License as published by  *
// *   the Free Software Foundation; either version 2 of the License, or     *
// *   (at your option) any later version.                                   *
// *   See also COPYING, LICENSE and WARRANTY file                           *
// *                                                                         *
// * *********************************************************************** */
//
//package playground.balmermi.census2000v2.modules;
//
//import java.util.ArrayList;
//
//import org.apache.log4j.Logger;
//import org.matsim.api.core.v01.population.Person;
//import org.matsim.api.core.v01.population.Plan;
//import org.matsim.core.api.experimental.facilities.ActivityFacilities;
//import org.matsim.core.facilities.ActivityOption;
//import org.matsim.core.facilities.ActivityOptionImpl;
//import org.matsim.core.population.ActivityImpl;
//import org.matsim.population.algorithms.AbstractPersonAlgorithm;
//import org.matsim.population.algorithms.PlanAlgorithm;
//
//public class PersonAssignPrimaryActivities extends AbstractPersonAlgorithm implements PlanAlgorithm {
//
//	//////////////////////////////////////////////////////////////////////
//	// member variables
//	//////////////////////////////////////////////////////////////////////
//
//	private final static Logger log = Logger.getLogger(PersonAssignPrimaryActivities.class);
//	private final Knowledges knowledges;
//	private final ActivityFacilities facilities;
//
//	//////////////////////////////////////////////////////////////////////
//	// constructors
//	//////////////////////////////////////////////////////////////////////
//
//	public PersonAssignPrimaryActivities(Knowledges knowledges, ActivityFacilities facilities) {
//		log.info("    init " + this.getClass().getName() + " module...");
//		this.knowledges = knowledges;
//		this.facilities = facilities;
//		log.info("    done.");
//	}
//
//	//////////////////////////////////////////////////////////////////////
//	// run methods
//	//////////////////////////////////////////////////////////////////////
//
//	@Override
//	public void run(Person person) {
//		this.run(person.getSelectedPlan());
//	}
//
//	public void run(Plan plan) {
//		Object k = this.knowledges.getKnowledgesByPersonId().get(plan.getPerson().getId());
//		if (k == null) { throw new RuntimeException("pid="+plan.getPerson().getId()+": no knowledge defined!"); }
//        boolean result2;
//        throw new RuntimeException("Knowledges are no more.");
//
//        if (!result2) { throw new RuntimeException("pid="+plan.getPerson().getId()+": no activities defined!"); }
//        ArrayList<ActivityOptionImpl> result1;
//        throw new RuntimeException("Knowledges are no more.");
//
//        ArrayList<ActivityOptionImpl> prim_acts = result1;
//		for (int i=0; i<plan.getPlanElements().size(); i=i+2) {
//			ActivityImpl act = (ActivityImpl)plan.getPlanElements().get(i);
//			String curr_type = act.getType();
//			ActivityOption a = this.facilities.getFacilities().get(act.getFacilityId()).getActivityOptions().get(curr_type);
//			if (a == null) { throw new RuntimeException("pid="+plan.getPerson().getId()+": Inconsistency with f_id="+act.getFacilityId()+"!"); }
//			if (!prim_acts.contains(a)) {
//                boolean result;
//                throw new RuntimeException("Knowledges are no more.");
//
//            }
//		}
//	}
//}
