/* *********************************************************************** *
 * project: org.matsim.*
 * SampledGraphMLWriter.java
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
package playground.johannes.socialnetworks.snowball2.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.matsim.core.utils.collections.Tuple;

import playground.johannes.sna.graph.Graph;
import playground.johannes.sna.graph.Vertex;
import playground.johannes.sna.graph.io.GraphMLWriter;
import playground.johannes.sna.snowball.SampledGraphProjection;
import playground.johannes.sna.snowball.SampledVertex;
import playground.johannes.sna.snowball.SampledVertexDecorator;
import playground.johannes.sna.snowball.io.SampledGraphML;


/**
 * @author illenberger
 *
 */
public class SampledGraphProjMLWriter extends GraphMLWriter {

	private GraphMLWriter delegateWriter;
	
	private String delegateFile;
	
	public SampledGraphProjMLWriter(GraphMLWriter delegateWriter) {
		super();
		this.delegateWriter = delegateWriter;
	}
	
	@Override
	public void write(Graph graph, String filename) throws IOException {
		File file = new File(filename);
		String name = file.getName();
		String parent = file.getParent();
		
		int idx = name.lastIndexOf(".");
		if(idx > -1)
			name = name.substring(0, idx);
		
		delegateFile = name + ".raw.graphml";
		delegateWriter.write(((SampledGraphProjection<?, ?, ?>)graph).getDelegate(), String.format("%1$s/%2$s", parent, delegateFile));
		
		super.write(graph, filename);
	}

	@Override
	protected List<Tuple<String, String>> getGraphAttributes() {
		List<Tuple<String, String>> attrs = super.getGraphAttributes();
		attrs.add(new Tuple<String, String>(SampledGraphProjML.DELEGATE_FILE, delegateFile));
		return attrs;
	}

	@Override
	protected List<Tuple<String, String>> getVertexAttributes(Vertex v) {
		List<Tuple<String, String>> attrs = super.getVertexAttributes(v);
		
		SampledGraphML.addSnowballAttributesData((SampledVertex) v, attrs);
		if(delegateWriter.getVertexIndices().containsKey(((SampledVertexDecorator<?>)v).getDelegate())) {
			int idx = delegateWriter.getVertexIndices().get(((SampledVertexDecorator<?>)v).getDelegate());
			attrs.add(new Tuple<String, String>(SampledGraphProjML.DELEGATE_IDX_ATTR, String.valueOf(idx)));
		} else
			throw new RuntimeException("A vertex index could not be retrieved.");
		
		return attrs;
	}

}
