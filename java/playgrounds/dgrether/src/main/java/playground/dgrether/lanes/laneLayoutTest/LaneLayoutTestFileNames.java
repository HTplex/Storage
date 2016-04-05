/* *********************************************************************** *
 * project: org.matsim.*
 * LaneLayoutTestFileNames
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
package playground.dgrether.lanes.laneLayoutTest;


/**
 * @author dgrether
 *
 */
public interface LaneLayoutTestFileNames {

	public final static String BASEDIR = "./src/main/resources/laneLayoutTest/";
	
	public final static String NETWORK = BASEDIR + "network.xml";
	
	public final static String LANEDEFINITIONS = BASEDIR + "lanedefinitions.xml";

	public final static String LANEDEFINITIONSV2 = BASEDIR + "lanedefinitionsV2.0.xml";

	public final static String CONFIG = BASEDIR + "laneLayoutTestConfig.xml";
	
	public final static String POPULATION = BASEDIR + "laneLayoutTestPopulation.xml";
}
