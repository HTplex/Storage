/* *********************************************************************** *
 * project: org.matsim.*
 * ObservedSocialAnalyzerTask.java
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
package playground.johannes.socialnetworks.survey.ivt2009.analysis;

import playground.johannes.sna.snowball.analysis.ObservedDegree;
import playground.johannes.socialnetworks.graph.social.analysis.AgeCorrelationTask;
import playground.johannes.socialnetworks.graph.social.analysis.AgeTask;
import playground.johannes.socialnetworks.graph.social.analysis.CentralityLingAttTask;
import playground.johannes.socialnetworks.graph.social.analysis.DegreeAgeTask;
import playground.johannes.socialnetworks.graph.social.analysis.DegreeEducationTask;
import playground.johannes.socialnetworks.graph.social.analysis.DegreeGenderTask;
import playground.johannes.socialnetworks.graph.social.analysis.EducationCategorized;
import playground.johannes.socialnetworks.graph.social.analysis.EducationTask;
import playground.johannes.socialnetworks.graph.social.analysis.Gender;
import playground.johannes.socialnetworks.graph.social.analysis.GenderTask;
import playground.johannes.socialnetworks.graph.social.analysis.SocialAnalyzerTask;
import playground.johannes.socialnetworks.snowball2.social.analysis.ObservedAge;

/**
 * @author illenberger
 *
 */
public class ObservedSocialAnalyzerTask extends SocialAnalyzerTask {

	public ObservedSocialAnalyzerTask() {
		addTask(new AgeTask(new ObservedAge()));
		addTask(new GenderTask(new ObservedGender()));
		addTask(new EducationTask(new ObservedEducationCategorized()));
		addTask(new DegreeEducationTask(ObservedDegree.getInstance(), EducationCategorized.getInstance()));
		
		addTask(new DegreeAgeTask(ObservedDegree.getInstance()));
		addTask(new DegreeGenderTask(ObservedDegree.getInstance()));
		addTask(new AgeCorrelationTask(new ObservedAge()));
		addTask(new GenderResponseRateTask());
		addTask(new EducationResponseRateTask());
		
//		addTask(new CentralityLingAttTask<ObservedGender>(new ObservedGender()));
		addTask(new CentralityLingAttTask<ObservedEducationCategorized>(new ObservedEducationCategorized()));
		addTask(new GenderEductionTask());
		addTask(new TrianglesEduTask());
//		SpatialCostFunction function = new GravityCostFunction(1.2, 0);
//		Accessibility access = new Accessibility(function);
//		
//		addTask(new AgeAccessibilityTask(access));
//		addTask(new GenderAccessibilityTask(access));
	}

}
