/* *********************************************************************** *
 * project: org.matsim.*
 * EdgeLengthSum.java
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

import gnu.trove.TObjectDoubleHashMap;

import java.util.Set;


import playground.johannes.sna.gis.CRSUtils;
import playground.johannes.sna.graph.Vertex;
import playground.johannes.sna.graph.spatial.SpatialVertex;
import playground.johannes.socialnetworks.gis.DistanceCalculatorFactory;

/**
 * @author illenberger
 * 
 */
public class EdgeLengthSum extends AbstractSpatialProperty {

	@Override
	public TObjectDoubleHashMap<Vertex> values(Set<? extends Vertex> vertices) {
		@SuppressWarnings("unchecked")
		Set<? extends SpatialVertex> spatialVertices = (Set<? extends SpatialVertex>) vertices;

		TObjectDoubleHashMap<Vertex> values = new TObjectDoubleHashMap<Vertex>();

		for (SpatialVertex vertex : spatialVertices) {
			if (vertex.getPoint() != null) {
				values.put(vertex, edgeLengthSum(vertex));
			}
		}
		
		return values;
	}
	
	private double edgeLengthSum(SpatialVertex vertex) {
		if (calculator == null) {
			calculator = DistanceCalculatorFactory.createDistanceCalculator(CRSUtils.getCRS(vertex.getPoint().getSRID()));
		}
		
		double sum = 0;
		
		for (SpatialVertex neighbor : vertex.getNeighbours()) {
			if (neighbor.getPoint() != null) {
				if (vertex.getPoint().getSRID() == neighbor.getPoint().getSRID()) {
					sum += calculator.distance(vertex.getPoint(), neighbor.getPoint());
				} else {
					throw new RuntimeException("Points do not share the same coordinate reference system.");
				}
			}
		}
		
		return sum;
	}

}
