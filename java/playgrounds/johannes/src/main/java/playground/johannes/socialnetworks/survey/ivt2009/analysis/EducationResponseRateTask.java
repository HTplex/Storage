/* *********************************************************************** *
 * project: org.matsim.*
 * GenderResponseRateTask.java
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

import java.util.Map;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.apache.log4j.Logger;

import playground.johannes.sna.graph.Graph;
import playground.johannes.sna.graph.analysis.AnalyzerTask;
import playground.johannes.sna.snowball.SampledVertex;
import playground.johannes.socialnetworks.graph.social.SocialGraph;
import playground.johannes.socialnetworks.graph.social.SocialVertex;

/**
 * @author illenberger
 * 
 */
public class EducationResponseRateTask extends AnalyzerTask {

	private static final Logger logger = Logger.getLogger(EducationResponseRateTask.class);

	@Override
	public void analyze(Graph g, Map<String, DescriptiveStatistics> results) {
		SocialGraph graph = (SocialGraph) g;

		int academics = 0;
		int academics_egos = 0;
		int nona = 0;
		int nona_egos = 0;

		for (SocialVertex v : graph.getVertices()) {
			String edu = v.getPerson().getEducation();
			if (edu != null) {
				if (edu.equals("6") || edu.equals("7")) {
					academics++;
					if (((SampledVertex) v).isSampled()) {
						academics_egos++;
					}
				} else {
					nona++;
					if (((SampledVertex) v).isSampled()) {
						nona_egos++;
					}
				}
			}
		}

		logger.info(String.format("Academics: %1$s (%2$.4f); non-academics: %3$s (%4$.4f).", academics_egos, academics_egos
				/ (double) academics, nona_egos, nona_egos / (double) nona));
	}

}
