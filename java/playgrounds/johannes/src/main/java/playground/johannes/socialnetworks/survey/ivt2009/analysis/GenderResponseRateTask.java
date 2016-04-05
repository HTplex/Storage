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
public class GenderResponseRateTask extends AnalyzerTask {

	private static final Logger logger = Logger.getLogger(GenderResponseRateTask.class);

	@Override
	public void analyze(Graph g, Map<String, DescriptiveStatistics> results) {
		SocialGraph graph = (SocialGraph) g;

		int males = 0;
		int males_egos = 0;
		int females = 0;
		int females_egos = 0;

		for (SocialVertex v : graph.getVertices()) {
			String sex = v.getPerson().getPerson().getSex();
			if (sex != null) {
				if (sex.equals("m")) {
					males++;
					if (((SampledVertex) v).isSampled()) {
						males_egos++;
					}
				} else {
					females++;
					if (((SampledVertex) v).isSampled()) {
						females_egos++;
					}
				}
			}
		}

		logger.info(String.format("Males: %1$s (%2$.4f); females: %3$s (%4$.4f).", males_egos, males_egos
				/ (double) males, females_egos, females_egos / (double) females));
	}

}
