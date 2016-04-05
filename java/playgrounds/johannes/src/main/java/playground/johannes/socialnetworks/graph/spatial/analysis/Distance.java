/* *********************************************************************** *
 * project: org.matsim.*
 * Distance.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2009 by the members listed in the COPYING,        *
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

import gnu.trove.TDoubleArrayList;
import gnu.trove.TObjectDoubleHashMap;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import playground.johannes.sna.graph.spatial.SpatialEdge;
import playground.johannes.sna.graph.spatial.SpatialVertex;
import playground.johannes.sna.math.Distribution;


/**
 * @author illenberger
 *
 */
public class Distance {

	private static Distance instance;
	
	public static Distance getInstance() {
		if(instance == null)
			instance = new Distance();
		return instance;
	}
	
	public DescriptiveStatistics statistics(Set<? extends SpatialVertex> vertices) {
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for(double val : distribution(vertices).getValues()) {
			stats.addValue(val);
		}
		return stats;
	}
	
	public Distribution distribution(Set<? extends SpatialVertex> vertices) {
		Distribution distribution = new Distribution();
		
		Set<SpatialEdge> touched = new HashSet<SpatialEdge>();
		for(SpatialVertex v : vertices) {
			for(int i = 0; i < v.getEdges().size(); i++) {
				if(touched.add(v.getEdges().get(i))) {
					double d = v.getEdges().get(i).length();
					if(!Double.isNaN(d))
						distribution.add(d);
				}
			}
		}
		
		return distribution;
	}
	
	public Distribution vertexAccumulatedDistribution(Set<? extends SpatialVertex> vertices) {
		Distribution distribution = new Distribution();
		
		for(SpatialVertex v_i : vertices) {
			double sum = 0;
			for(SpatialEdge e : v_i.getEdges()) {
				double d = e.length();
				if(!Double.isNaN(d))
					sum += e.length();
			}
			distribution.add(sum);
		}
		
		return distribution;
	}
	
	public TObjectDoubleHashMap<SpatialVertex> vertexMean(Set<? extends SpatialVertex> vertices) {
		TObjectDoubleHashMap<SpatialVertex> values = new TObjectDoubleHashMap<SpatialVertex>();
		
		for(SpatialVertex vertex : vertices) {
			double sum = 0;
			int cnt = 0;
			for(SpatialEdge e : vertex.getEdges()) {
				double d = e.length();
				if(!Double.isNaN(d)) {
					sum += e.length();
					cnt++;
				}
			}
			if(cnt > 0)
				values.put(vertex, sum/(double)cnt);
		}
		
		return values;
	}
	
	public TObjectDoubleHashMap<SpatialVertex> vertexMedian(Set<? extends SpatialVertex> vertices) {
		TObjectDoubleHashMap<SpatialVertex> values = new TObjectDoubleHashMap<SpatialVertex>();
		
		for(SpatialVertex vertex : vertices) {
			TDoubleArrayList list = new TDoubleArrayList();
			for(SpatialEdge e : vertex.getEdges()) {
				double d = e.length();
				
				if(!Double.isNaN(d)) {
					list.add(d);
				}
			}
			if(list.size() > 0)
				values.put(vertex, StatUtils.percentile(list.toNativeArray(), 50));
		}
		
		return values;
	}
	
	public Distribution vertexMeanDistribution(Set<? extends SpatialVertex> vertices) {
		TObjectDoubleHashMap<SpatialVertex> values = vertexMean(vertices);
		return new Distribution(values.getValues());
	}
	
	public Distribution vertexMedianDistribution(Set<? extends SpatialVertex> vertices) {
		TObjectDoubleHashMap<SpatialVertex> values = vertexMedian(vertices);
		return new Distribution(values.getValues());
	}
}
