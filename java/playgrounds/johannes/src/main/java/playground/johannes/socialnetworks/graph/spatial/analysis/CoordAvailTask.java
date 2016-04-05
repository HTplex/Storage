/* *********************************************************************** *
 * project: org.matsim.*
 * CoordAvailTask.java
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
package playground.johannes.socialnetworks.graph.spatial.analysis;

import java.util.Map;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import playground.johannes.sna.graph.Graph;
import playground.johannes.sna.graph.analysis.AnalyzerTask;
import playground.johannes.sna.graph.spatial.SpatialGraph;
import playground.johannes.sna.graph.spatial.SpatialVertex;

/**
 * @author illenberger
 *
 */
public class CoordAvailTask extends AnalyzerTask {

	@Override
	public void analyze(Graph graph, Map<String, DescriptiveStatistics> results) {
		SpatialGraph spatialGraph = (SpatialGraph)graph;

		int cnt = 0;
		for(SpatialVertex v : spatialGraph.getVertices()) {
			if(v.getPoint() != null) {
				cnt++;
			}
		}
		singleValueStats("coordAvail", cnt, results);
		
	}

}
