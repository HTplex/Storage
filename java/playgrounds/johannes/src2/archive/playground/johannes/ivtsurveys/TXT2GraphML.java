/* *********************************************************************** *
 * project: org.matsim.*
 * TXT2GraphML.java
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
package playground.johannes.socialnetworks.survey.ivt2009;

import gnu.trove.TIntIntHashMap;
import gnu.trove.TIntIntIterator;
import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.matsim.api.core.v01.Coord;
import org.matsim.contrib.sna.gis.CRSUtils;
import org.matsim.contrib.sna.graph.Vertex;
import org.matsim.contrib.sna.graph.spatial.SpatialVertex;
import org.matsim.contrib.sna.graph.spatial.io.KMLObjectDetailComposite;
import org.matsim.contrib.sna.graph.spatial.io.SpatialGraphKMLWriter;
import org.matsim.contrib.sna.snowball.SampledVertex;
import org.matsim.contrib.sna.snowball.spatial.SampledSpatialGraphBuilder;
import org.matsim.contrib.sna.snowball.spatial.SampledSpatialSparseGraph;
import org.matsim.contrib.sna.snowball.spatial.SampledSpatialSparseVertex;
import org.matsim.contrib.sna.snowball.spatial.io.SampledSpatialGraphMLWriter;
import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.transformations.WGS84toCH1903LV03;

import playground.johannes.socialnetworks.snowball2.spatial.io.KMLTimeSpan;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 * @author illenberger
 *
 */
public class TXT2GraphML {
	
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TXT2GraphML.class);

	private static final String TAB = "\t";
	
	private static CoordinateTransformation transform = new WGS84toCH1903LV03();
	
	private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 21781);
	
	public static void main(String[] args) throws IOException {
		SampledSpatialGraphBuilder builder = new SampledSpatialGraphBuilder(CRSUtils.getCRS(21781));
		SampledSpatialSparseGraph graph = builder.createGraph();
		/*
		 * read ego table
		 */
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		
		String line = reader.readLine();
		String[] tokens = line.split(TAB);
		int idIdx = getIndex("Laufnr.", tokens);
		int statusIdx = getIndex("Tats�chlicher Teilnahmestatus FB", tokens);
		int longIdx = getIndex("long", tokens);
		int latIdx = getIndex("lat", tokens);
		
		if(idIdx < 0 || longIdx < 0 || latIdx < 0 || statusIdx < 0)
			throw new IllegalArgumentException("Header not found!");
		
		TIntObjectHashMap<SampledSpatialSparseVertex> egoIds = new TIntObjectHashMap<SampledSpatialSparseVertex>();
		
		Map<Vertex, String> timeStamps = new HashMap<Vertex, String>();
		Map<String, Map<String, String>> surveyData = new CSVReader().readSnowballData("/Users/jillenberger/Work/work/socialnets/data/ivt2009/raw/tbl_surveydata.csv");
		
		int egocount = 0;
		while((line = reader.readLine()) != null) {
			tokens = line.split(TAB);
			String statusStr = tokens[statusIdx];
			if (!statusStr.equalsIgnoreCase("#NV")) {
				int status = Integer.parseInt(statusStr);
				/*
				 * check if ego is sampled
				 */
				if (status == 1) {
					int id = Integer.parseInt(tokens[idIdx]);

					Coord c = getCoord(tokens, longIdx, latIdx);
					if (c != null) {
						SampledSpatialSparseVertex v = builder.addVertex(graph, geometryFactory.createPoint(new Coordinate(c.getX(), c.getY())));
						int it = 0;
						if(id >= 1000 && id < 10000)
							it = 1;
						else if(id >= 10000)
							it = 2;
						if(it < 0)
							System.err.println("Iteration less than 0");
						v.sample(it);
						v.detect(Math.max(0, it - 1));
						if(egoIds.put(id, v) != null)
							System.err.println("Overwriting ego with id " + id);
						if(id == 10064)
							System.err.println("alalla");
						egocount++;
						
						Map<String, String> egoData = surveyData.get(tokens[2]);
						String time = egoData.get("timeStamp");
						timeStamps.put(v, time);
					}
				}
			}
		}
		logger.info(String.format("Built %1$s egos.", egocount));
		/*
		 * read alter table
		 */
		reader = new BufferedReader(new FileReader(args[1]));
		line = reader.readLine();
		tokens = line.split(TAB);
		idIdx = getIndex("AlterLaufnummer", tokens);
		int egoIdIdx = getIndex("Excel", tokens);
		longIdx = getIndex("long", tokens);
		latIdx = getIndex("lat", tokens);
		
		int altercount = 0;
		int alterDoubled = 0;
		int doubleedges = 0;
//		int alterdropped = 0;
		int nocoord = 0;
		int alterIdCounter = 1000000;
		int egoNotFound = 0;
		
		TIntIntHashMap egoDegree = new TIntIntHashMap();
		TIntObjectIterator<SampledSpatialSparseVertex> it = egoIds.iterator();
		for(int i = 0; i< egoIds.size(); i++) {
			it.advance();
			egoDegree.put(it.key(), 0);
		}
		
//		TIntObjectHashMap<SampledSpatialSparseVertex> alterIds = new TIntObjectHashMap<SampledSpatialSparseVertex>();
		while((line = reader.readLine()) != null) {
			tokens = line.split(TAB);
			
			String egoIdStr = tokens[egoIdIdx];
			if(!egoIdStr.equalsIgnoreCase("Testzugang")) {
			String[] tokens2 = egoIdStr.split(" ");
//			try {
			int egoId = Integer.parseInt(tokens2[tokens2.length - 1]);
			SampledSpatialSparseVertex ego = egoIds.get(egoId);
			if(ego == null) {
				logger.warn(String.format("Ego with id %1$s not found!", egoId));
				egoNotFound++;
			} else {
				if(!ego.isSampled())
					System.err.println("ego not sampled! id =" + egoId);
				else{
				String idStr = tokens[idIdx];
				if (!idStr.equalsIgnoreCase("#NV")) {// && !idStr.equalsIgnoreCase("")) {
					int id;
					if(idStr.equalsIgnoreCase("") || idStr.equalsIgnoreCase("NICHT ANSCHREIBEN"))
						id = ++alterIdCounter;
					else
						id = Integer.parseInt(idStr);

					if(id == 10064)
						System.err.println();
					
					SampledSpatialSparseVertex alter = egoIds.get(id);
					if (alter == null) {
						Coord c = getCoord(tokens, longIdx, latIdx);
						if (c != null) {
							alter = builder.addVertex(graph, geometryFactory.createPoint(new Coordinate(c.getX(), c.getY())));
							alter.detect(Math.max(0, ego.getIterationSampled()));
							if(ego.getIterationSampled() < 0)
								System.err.println("Iteration less than 0");
							timeStamps.put(alter, timeStamps.get(ego));
							if(egoIds.put(id, alter) != null)
								System.err.println("Overwriting alter with id " + id);
							altercount++;
						} else {
							nocoord++;
						}
					} else {
						alterDoubled++;
					}
					if (alter != null) {
						if(builder.addEdge(graph, ego, alter) == null)
							doubleedges++;
						else
							egoDegree.adjustOrPutValue(egoId, 1, 1);
					}
//					} else {
//						alterdropped++;
//					}
				}
			}
//			} catch (NumberFormatException e) {
//				alterdropped++;
//			}
			}
			}
		}
		
		TIntIntIterator it2 = egoDegree.iterator();
		for(int i = 0; i < egoDegree.size(); i++) {
			it2.advance();
			if(it2.value() == 0) {
				logger.warn(String.format("Ego with id %1$s named no contacts. Marking as not sampled...", it2.key()));
				SampledSpatialSparseVertex v = egoIds.get(it2.key());
				v.sample(-1);
				builder.removeVertex(graph, v);
			}
		}
		
		logger.info(String.format("Built %1$s alters. %2$s alters named at least twice. %3$s doubled edges.", altercount, alterDoubled, doubleedges));
		logger.info(String.format("Dropped %1$s alters because ego not found.", egoNotFound));
		logger.info(String.format("Dropped %1$s alters becuase no coordinates are avaiable.", nocoord));
		/*
		 * Dump graph.
		 */
		SampledSpatialGraphMLWriter writer = new SampledSpatialGraphMLWriter();
		writer.write(graph, args[2]);
		
		SpatialGraphKMLWriter kmlwriter = new SpatialGraphKMLWriter();
//		kmlwriter.setCoordinateTransformation(new CH1903LV03toWGS84());
		kmlwriter.setDrawEdges(false);
//		kmlwriter.setVertexStyle(new KMLSnowballVertexStyle(kmlwriter.getVertexIconLink()));
//		writer.setVertexStyle(new KMLDegreeStyle(writer.getVertexIconLink()));
//		writer.setVertexDescriptor(new KMLSnowballDescriptor());
		KMLObjectDetailComposite<SpatialVertex> composite = new KMLObjectDetailComposite<SpatialVertex>();
		composite.addObjectDetail(new org.matsim.contrib.sna.graph.spatial.io.KMLVertexDescriptor(graph));
		composite.addObjectDetail(new KMLTimeSpan(timeStamps));
//		kmlwriter.setVertexDescriptor(new KMLVertexDescriptor(graph));
//		kmlwriter.setPlacemarkCustomizable(new KMLTimeSpan(timeStamps));
		kmlwriter.setKmlVertexDetail(composite);
		kmlwriter.write(graph, args[2] + ".kmz");
	}
	
	private static int getIndex(String header, String[] tokens) {
		for(int i = 0; i < tokens.length; i++) {
			if(tokens[i].equalsIgnoreCase(header))
				return i;
		}
		
		return -1;
	}
	
	private static Coord getCoord(String[] tokens, int longIdx, int latIdx) {
		try {
		String latStr = tokens[latIdx];
		String longStr = tokens[longIdx];
		if(latStr.length() > 0 && longStr.length() > 0) {
			double latitude = Double.parseDouble(latStr);
			double longitude = Double.parseDouble(longStr);
			
			return transform.transform(new CoordImpl(longitude, latitude));
		} else {
			logger.warn("No coordinates available!");
			return new CoordImpl(0, 0);
		}
		} catch (ArrayIndexOutOfBoundsException e) {
			return new CoordImpl(0, 0);
		}
	}
}
