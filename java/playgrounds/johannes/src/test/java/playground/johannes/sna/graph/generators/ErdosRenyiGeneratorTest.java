/* *********************************************************************** *
 * project: org.matsim.*
 * ErdosRenyiGeneratorTest.java
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
package playground.johannes.sna.graph.generators;

import junit.framework.TestCase;

import playground.johannes.sna.graph.Graph;
import playground.johannes.sna.graph.SparseEdge;
import playground.johannes.sna.graph.SparseGraph;
import playground.johannes.sna.graph.SparseGraphBuilder;
import playground.johannes.sna.graph.SparseVertex;
import playground.johannes.sna.graph.generators.ErdosRenyiGenerator;

/**
 * @author jillenberger
 *
 */
public class ErdosRenyiGeneratorTest extends TestCase {

	public void test() {
		ErdosRenyiGenerator<SparseGraph, SparseVertex, SparseEdge> generator = new ErdosRenyiGenerator<SparseGraph, SparseVertex, SparseEdge>(new SparseGraphBuilder());
		Graph g = generator.generate(100, 0.01, 0);
		
		assertEquals(100, g.getVertices().size());
		assertEquals(56, g.getEdges().size());
	}
}
