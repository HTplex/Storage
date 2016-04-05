/* *********************************************************************** *
 * project: org.matsim.*
 * ConditionalDistribution.java
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

/**
 * 
 */
package playground.johannes.socialnetworks.graph.mcmc;

import playground.johannes.sna.graph.Vertex;
import playground.johannes.sna.graph.matrix.AdjacencyMatrix;

/**
 * @author illenberger
 * 
 */
public interface EnsembleProbability {

	/**
	 * Determines the ratio of the probability of a graph with <tt>y_ij = 0</tt>
	 * to the probability of a graph with <tt>y_ij = 1</tt>.
	 * 
	 * @param <V>
	 *            the vertex type
	 * @param y
	 *            an adjacency matrix
	 * @param i
	 *            a vertex index
	 * @param j
	 *            a vertex index
	 * @param y_ij
	 *            <tt>true</tt> if in the current configuration
	 *            <tt>y_ij = 1</tt>, <tt>false</tt> otherwise
	 * @return the ratio of the probability of a graph with <tt>y_ij = 0</tt> to
	 *         the probability of a graph with <tt>y_ij = 1</tt>.
	 */
	public <V extends Vertex> double ratio(AdjacencyMatrix<V> y, int i, int j, boolean y_ij);

}