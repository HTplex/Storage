/* *********************************************************************** *
 * project: org.matsim.*
 * TopoAnalyzer.java
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
package playground.johannes.studies.netanalysis;

import java.io.IOException;


import playground.johannes.sna.graph.SparseGraph;
import playground.johannes.sna.graph.analysis.GraphAnalyzer;
import playground.johannes.sna.graph.io.SparseGraphMLReader;
import playground.johannes.sna.util.MultiThreading;
import playground.johannes.socialnetworks.graph.analysis.AnalyzerTaskComposite;
import playground.johannes.socialnetworks.graph.analysis.ExtendedTopologyAnalyzerTask;
import playground.johannes.socialnetworks.graph.analysis.TopologyAnalyzerTask;
import playground.johannes.socialnetworks.graph.spatial.analysis.EdgeLengthDegreeTask;

/**
 * @author illenberger
 *
 */
public class TopoAnalyzer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SparseGraphMLReader reader = new SparseGraphMLReader();
		SparseGraph graph = reader.readGraph(args[0]);
		
		String output = null;
		if(args.length > 1)
			output = args[1];

		AnalyzerTaskComposite composite = new AnalyzerTaskComposite();
		composite.addTask(new TopologyAnalyzerTask());
//		composite.addTask(new ExtendedTopologyAnalyzerTask());
		
		if(output != null)
			composite.setOutputDirectoy(output);
		
		if(args.length > 2)			
			MultiThreading.setNumAllowedThreads(Integer.parseInt(args[2]));
		
		GraphAnalyzer.analyze(graph, composite, output);
	}

}
