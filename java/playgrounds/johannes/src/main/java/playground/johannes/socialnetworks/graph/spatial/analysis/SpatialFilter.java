/* *********************************************************************** *
 * project: org.matsim.*
 * GraphClippingFilter.java
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
package playground.johannes.socialnetworks.graph.spatial.analysis;

import java.util.HashSet;
import java.util.Set;

import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import playground.johannes.sna.gis.CRSUtils;
import playground.johannes.sna.graph.GraphBuilder;
import playground.johannes.sna.graph.spatial.SpatialEdge;
import playground.johannes.sna.graph.spatial.SpatialGraph;
import playground.johannes.sna.graph.spatial.SpatialVertex;
import playground.johannes.socialnetworks.graph.analysis.GraphFilter;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

/**
 * @author illenberger
 * 
 */
public class SpatialFilter implements GraphFilter<SpatialGraph> {

	private GraphBuilder<SpatialGraph, SpatialVertex, SpatialEdge> builder;

	private Geometry geometry;

	private boolean edgeMode;

	@SuppressWarnings("unchecked")
	public SpatialFilter(GraphBuilder<? extends SpatialGraph, ? extends SpatialVertex, ? extends SpatialEdge> builder,
			Geometry geometry) {
		this.builder = (GraphBuilder<SpatialGraph, SpatialVertex, SpatialEdge>) builder;
		this.geometry = geometry;
	}

	public SpatialFilter(GraphBuilder<? extends SpatialGraph, ? extends SpatialVertex, ? extends SpatialEdge> builder,
			Geometry geometry, boolean edgeMode) {
		this(builder, geometry);
		this.edgeMode = edgeMode;
	}
	
	@Override
	public SpatialGraph apply(SpatialGraph graph) {
		/*
		 * create copy of graph
		 */
		SpatialGraph copy = builder.copyGraph(graph);
		/*
		 * delete vertices and edges outside geometry
		 */
		Set<SpatialEdge> edges = new HashSet<SpatialEdge>();
		Set<SpatialVertex> vertices = new HashSet<SpatialVertex>();
		findElements(copy, vertices, edges);

		for (SpatialEdge edge : edges)
			builder.removeEdge(copy, edge);

		for (SpatialVertex vertex : vertices)
			builder.removeVertex(copy, vertex);

		return copy;

	}

	public void setEdgeMode(boolean flag) {
		this.edgeMode = flag;
	}
	
	protected void findElements(SpatialGraph graph, Set<SpatialVertex> vertices, Set<SpatialEdge> edges) {
		try {
			CoordinateReferenceSystem sourceCRS = graph.getCoordinateReferenceSysten();
			CoordinateReferenceSystem targetCRS = CRSUtils.getCRS(geometry.getSRID());

			MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);

			if (edgeMode) {
				for(SpatialEdge edge : graph.getEdges()) {
					Point p1 = edge.getVertices().getFirst().getPoint();
					Point p2 = edge.getVertices().getSecond().getPoint();
					
					if(p1 == null) {
						edges.add(edge);
						vertices.add(edge.getVertices().getFirst());
					}
					if(p2 == null) {
						edges.add(edge);
						vertices.add(edge.getVertices().getSecond());
					}
					
					if(p1 != null && p2 != null) {
						p1 = CRSUtils.transformPoint(p1, transform);
						p2 = CRSUtils.transformPoint(p2, transform);
						
						if (!geometry.contains(p1) && !geometry.contains(p2)) {
							edges.add(edge);
							vertices.add(edge.getVertices().getFirst());
							vertices.add(edge.getVertices().getSecond());
						}
					}
				}
			} else {
				for (SpatialVertex vertex : graph.getVertices()) {
					boolean remove = false;

					if (vertex.getPoint() == null)
						remove = true;
					else {
						Point p = CRSUtils.transformPoint(vertex.getPoint(), transform);
						if (!geometry.contains(p)) {
							remove = true;
						}
					}

					if (remove) {
						vertices.add(vertex);
						edges.addAll(vertex.getEdges());
					}

				}
			}
		} catch (FactoryException e) {
			e.printStackTrace();
		}

	}
}
