/* *********************************************************************** *
 * project: org.matsim.*
 * GirvanNewmanAlgorithm.java
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
package playground.johannes.socialnetworks.graph.analysis;

import gnu.trove.TObjectDoubleIterator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import playground.johannes.sna.graph.Edge;
import playground.johannes.sna.graph.EdgeDecorator;
import playground.johannes.sna.graph.Graph;
import playground.johannes.sna.graph.GraphProjection;
import playground.johannes.sna.graph.SparseGraphProjectionBuilder;
import playground.johannes.sna.graph.Vertex;
import playground.johannes.sna.graph.VertexDecorator;
import playground.johannes.sna.graph.analysis.Components;
import playground.johannes.sna.graph.spatial.SpatialSparseGraph;
import playground.johannes.sna.graph.spatial.io.SpatialGraphKMLWriter;
import playground.johannes.sna.graph.spatial.io.SpatialGraphMLReader;
import playground.johannes.socialnetworks.graph.io.PajekCommunityColorizer;
import playground.johannes.socialnetworks.graph.spatial.io.SpatialPajekWriter;

/**
 * @author illenberger
 *
 */
public class GirvanNewmanAlgorithm {

	private static final Logger logger = Logger.getLogger(GirvanNewmanAlgorithm.class);

	private String outputDir;

	public <V extends Vertex> List<Set<Set<V>>> dendogram(Graph graph, int maxIterations, Handler handler) {
		List<Set<Set<V>>> dendogram = new ArrayList<Set<Set<V>>>();
		SparseGraphProjectionBuilder<Graph, V, Edge> builder = new SparseGraphProjectionBuilder<Graph, V, Edge>();
		GraphProjection<Graph, V, Edge> projection = builder.decorateGraph(graph);
//		projection.decorate();

		int iteration = 0;
		int lastSize = Integer.MAX_VALUE;
		while(projection.getEdges().size() > 0 && iteration < maxIterations) {
			logger.info(String.format("Calculating edge betweenness at level %1$s...", iteration));
			Centrality c = new Centrality();
			c.init(projection);



			double maxBC = 0;
			Edge maxBCEdge = null;
			TObjectDoubleIterator<Edge> it = c.edgeBetweenness().iterator();
			for(int i = 0; i < c.edgeBetweenness().size(); i++) {
				it.advance();
				if(it.value() > maxBC) {
					maxBC = it.value();
					maxBCEdge = it.key();
				}
			}

			builder.removeEdge(projection, (EdgeDecorator<Edge>) maxBCEdge);
			Collection<Set<V>> list = new Components().components(projection);
			Set<Set<V>> partition = new HashSet<Set<V>>(list);

			logger.info(String.format("Done - disconnected components... %1$s", partition.size()));

			if(partition.size() != lastSize) {
				dendogram.add(partition);
				lastSize = partition.size();
				if(handler != null)
					handler.handlePartition(projection, partition, iteration, dendogram);
			}

			iteration++;
		}

		return dendogram;
	}

	public static <V extends Vertex> void writeDendogram(List<Set<Set<V>>> dendogram, String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

		for(Set<Set<V>> level : dendogram) {
			for(Set<?> cluster : level) {
				writer.write(String.valueOf(cluster.size()));
				writer.write("\t");
			}
			writer.newLine();
		}
		writer.close();
	}

	public interface Handler {

		public <V extends Vertex> void handlePartition(GraphProjection projection, Set<Set<V>> components, int iteration, List<Set<Set<V>>> dendogram);

	}

	public class DumpHandler implements Handler {

		public <V extends Vertex> void handlePartition(GraphProjection projection, Set<Set<V>> partition, int level, List<Set<Set<V>>> dendogram) {
			//************************************************
			SortedSet<Set<V>> components = new TreeSet<Set<V>>(new Comparator<Collection<?>>() {
				public int compare(Collection<?> o1, Collection<?> o2) {
					int result = o2.size() - o1.size();
					if(result == 0) {
						if(o1 == o2)
							return 0;
						else
							/*
							 * Does not work for empty collections, but is
							 * ok for the purpose here.
							 */
							return o2.hashCode() - o1.hashCode();
					} else
						return result;
				}
			});
			for(Set<V> cluster : partition) {
				Set<V> newCluster = new HashSet<V>();
				for(Vertex v : cluster)
					newCluster.add(((VertexDecorator<V>)v).getDelegate());
				components.add(newCluster);
			}

			//************************************************
			SpatialGraphKMLWriter writer = new SpatialGraphKMLWriter();
//			writer.setCoordinateTransformation(new CH1903LV03toWGS84());
			writer.setDrawEdges(false);
//			writer.setDrawNames(false);
//			writer.setVertexDescriptor(new KMLVertexDescriptor((SpatialSparseGraph) projection.getDelegate()));
//			writer.setVertexStyle(new KMLCommunityStlyle(writer.getVertexIconLink(), components));
//			try {
				writer.write((SpatialSparseGraph) projection.getDelegate(), String.format("%1$s/%2$s.communityGraph.kmz", outputDir, level));
//			} catch (IOException e) {
//				 TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//************************************************
			try {
				writeDendogram(dendogram, String.format("%1$s/%2$s.dendogram.txt", outputDir, level));

				SpatialPajekWriter pwriter = new SpatialPajekWriter();
				pwriter.write((SpatialSparseGraph) projection.getDelegate(), new PajekCommunityColorizer(components), String.format("%1$s/%2$s.graph.net", outputDir, level));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String args[]) throws IOException {
		SpatialGraphMLReader reader = new SpatialGraphMLReader();
		Graph graph = reader.readGraph(args[0]);
//		ErdosRenyiGenerator<SparseGraph, SparseVertex, SparseEdge> generator = new ErdosRenyiGenerator<SparseGraph, SparseVertex, SparseEdge>(new SparseGraphFactory());
//		System.out.println("Generating graph...");
//		SparseGraph graph = generator.generate(200, 0.02, 4711);
//		GMLReader reader = new GMLReader();
//		Graph graph = reader.read("/Users/fearonni/Downloads/karate/karate.gml", new SparseGraphFactory());



		GirvanNewmanAlgorithm algo = new GirvanNewmanAlgorithm();
		algo.outputDir = args[1];

		writeDendogram(algo.dendogram(graph, Integer.parseInt(args[2]), algo.new DumpHandler()), String.format("%1$s/dendogram.txt", algo.outputDir));


	}
}
