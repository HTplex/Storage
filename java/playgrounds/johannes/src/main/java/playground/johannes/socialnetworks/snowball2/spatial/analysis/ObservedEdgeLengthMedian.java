/* *********************************************************************** *
 * project: org.matsim.*
 * ObservedEdgeLengthMedian.java
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
package playground.johannes.socialnetworks.snowball2.spatial.analysis;

import gnu.trove.TObjectDoubleHashMap;

import java.util.Set;


import playground.johannes.sna.graph.Vertex;
import playground.johannes.sna.graph.spatial.SpatialVertex;
import playground.johannes.socialnetworks.graph.spatial.analysis.EdgeLengthMedian;
import playground.johannes.socialnetworks.snowball2.spatial.SpatialSampledVertexDecorator;

/**
 * @author illenberger
 *
 */
public class ObservedEdgeLengthMedian extends EdgeLengthMedian {

	@Override
	public TObjectDoubleHashMap<Vertex> values(Set<? extends Vertex> vertices) {
		@SuppressWarnings("unchecked")
		Set<SpatialSampledVertexDecorator<SpatialVertex>> spatialVertices = (Set<SpatialSampledVertexDecorator<SpatialVertex>>)vertices;
		return super.values(spatialVertices);
	}

}
