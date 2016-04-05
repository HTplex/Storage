/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
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
package playground.benjamin;


/**
 * @author dgrether
 *
 */
public interface BkPaths {
	
	
	final String DATA = "/media/data/";
	
	final String ECLIPSE = DATA + "2_Eclipse/";

	final String REPOS = ECLIPSE + "Workspace_Repos/";
	
	final String SHAREDSVN = REPOS + "shared-svn/";
  
	final String RUNSSVN = REPOS + "runs-svn/";
	
	final String STUDIESBK = SHAREDSVN + "studies/bkick/";
	
	

	final String IVTCHNET = SHAREDSVN + "studies/schweiz-ivtch/baseCase/network/ivtch-osm.xml";
//  
//	final String IVTCHROADPRICING = SHAREDSVN + "studies/schweiz-ivtch/baseCase/roadpricing/zurichCityArea/zurichCityAreaWithoutHighwaysPricingScheme.xml";
//  
//	final String IVTCHCOUNTS = SHAREDSVN + "studies/schweiz-ivtch/baseCase/counts/countsIVTCH.xml";
//
	final String IVTCHBASE = SHAREDSVN + "studies/schweiz-ivtch/";
}
