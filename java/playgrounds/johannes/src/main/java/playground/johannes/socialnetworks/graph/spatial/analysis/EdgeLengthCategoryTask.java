/* *********************************************************************** *
 * project: org.matsim.*
 * AcceptancePropaCategoryTask.java
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

import gnu.trove.TDoubleDoubleHashMap;
import gnu.trove.TDoubleObjectHashMap;
import gnu.trove.TDoubleObjectIterator;
import gnu.trove.TObjectDoubleHashMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import playground.johannes.sna.graph.Graph;
import playground.johannes.sna.graph.Vertex;
import playground.johannes.sna.graph.analysis.ModuleAnalyzerTask;
import playground.johannes.sna.graph.spatial.SpatialEdge;
import playground.johannes.sna.graph.spatial.SpatialVertex;
import playground.johannes.sna.math.FixedSampleSizeDiscretizer;
import playground.johannes.sna.math.Histogram;
import playground.johannes.sna.math.LinLogDiscretizer;
import playground.johannes.sna.util.TXTWriter;
import playground.johannes.socialnetworks.graph.analysis.AttributePartition;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

/**
 * @author illenberger
 * 
 */
public class EdgeLengthCategoryTask extends ModuleAnalyzerTask<Accessibility> {

//	private Set<Point> destinations;

	private Geometry boundary;

	public EdgeLengthCategoryTask(Accessibility module) {
		this.setModule(module);
	}
	
//	public void setDestinations(Set<Point> destinations) {
//		this.destinations = destinations;
//	}

	public void setBoundary(Geometry boundary) {
		this.boundary = boundary;
	}

	@Override
	public void analyze(Graph graph, Map<String, DescriptiveStatistics> statsMap) {
		Accessibility access = module;
//		access.setTargets(destinations);

//		destinations = new HashSet<Point>();
		Set<Vertex> vertices = new HashSet<Vertex>();
		for (Vertex v : graph.getVertices()) {
			Point p = ((SpatialVertex) v).getPoint();
			if (p != null) {
//				if (boundary.contains(p)) {
					vertices.add(v);
//					destinations.add(((SpatialVertex) v).getPoint());
//				}
			}
		}
//		access.setTargets(destinations);
		
		TObjectDoubleHashMap<Vertex> normValues = access.values(vertices);
//		TObjectDoubleHashMap<Vertex> normValues = ObservedDegree.getInstance().values(vertices);
//		TObjectDoubleHashMap<Vertex> normValues = ObservedAge.getInstance().values(vertices);
		
//		AttributePartition partitioner = new AttributePartition(new FixedBordersDiscretizer(normValues.getValues(), new double[]{21.0}));
		AttributePartition partitioner = new AttributePartition(FixedSampleSizeDiscretizer.create(normValues.getValues(), 1, 2));
		TDoubleObjectHashMap<?> partitions = partitioner.partition(normValues);
		TDoubleObjectIterator<?> it = partitions.iterator();

//		AcceptanceProbability propa = new ObservedAcceptanceProbability();
//		AcceptanceProbability propa = new AcceptanceProbability();
		EdgeLength propa = new EdgeLength();

		Map<String, TDoubleDoubleHashMap> histograms = new HashMap<String, TDoubleDoubleHashMap>();
		Map<String, DescriptiveStatistics> distributions = new HashMap<String, DescriptiveStatistics>();
		double sum = 0;
		
		for (int i = 0; i < partitions.size(); i++) {
			it.advance();
			double key = it.key();
			Set<SpatialVertex> partition = (Set<SpatialVertex>) it.value();
			System.out.println("Partition size = " + partition.size() + "; key = " + key);
//			DescriptiveStatistics distr = propa.distribution(partition, destinations);
			Set<SpatialEdge> edges = new HashSet<SpatialEdge>();
			for(SpatialVertex v : partition) {
				edges.addAll(v.getEdges());
			}
			DescriptiveStatistics distr = propa.statistics(edges);
			try {
				double[] values = distr.getValues();
				System.out.println("Num samples = " + values.length);
				if(values.length > 0) {
					TDoubleDoubleHashMap hist = Histogram.createHistogram(distr, FixedSampleSizeDiscretizer.create(values, 1, 50), true);
					sum += Histogram.sum(hist);
					histograms.put(String.format("d-cat%1$.4f", key), hist);
					distributions.put(String.format("d-cat%1$.4f", key), distr);
				}
				writeHistograms(distr, new LinLogDiscretizer(1000.0, 2), String.format("d-cat%1$.4f.log", key), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(Entry<String, TDoubleDoubleHashMap> entry : histograms.entrySet()) {
			String key = entry.getKey();
			TDoubleDoubleHashMap histogram = entry.getValue();
			Histogram.normalize(histogram, sum);
			try {
				TXTWriter.writeMap(histogram, "d", "p", String.format("%1$s/%2$s.txt", getOutputDirectory(), key));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			histogram = Histogram.createCumulativeHistogram(histogram);
			Histogram.complementary(histogram);
			try {
				TXTWriter.writeMap(histogram, "d", "p", String.format("%1$s/%2$s.cum.txt", getOutputDirectory(), key));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			DescriptiveStatistics stats = distributions.get(key);
			writeRawData(stats, key);
		}
	}

}
