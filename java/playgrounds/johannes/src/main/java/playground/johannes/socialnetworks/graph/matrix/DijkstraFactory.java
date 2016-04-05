/* *********************************************************************** *
 * project: org.matsim.*
 * DijkstraFactory.java
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
package playground.johannes.socialnetworks.graph.matrix;

import playground.johannes.sna.graph.matrix.AdjacencyMatrix;
import playground.johannes.sna.graph.matrix.Dijkstra;

/**
 * @author illenberger
 *
 */
public class DijkstraFactory {

	public Dijkstra newDijkstra(AdjacencyMatrix<?> y) {
		return new Dijkstra(y);
	}
}
