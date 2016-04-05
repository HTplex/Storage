/* *********************************************************************** *
 * project: org.matsim.*
 * GPSPrepareNetwork.java
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
package playground.scnadine.tools.networks;

import org.matsim.core.gbl.Gbl;
import org.matsim.core.network.NetworkImpl;

public class GPSConvertNetworkXMLToSHP {

	//////////////////////////////////////////////////////////////////////
	// exportNetwork
	//////////////////////////////////////////////////////////////////////
	
	public static void exportNetwork(String[] args){
		
		System.out.println("RUN:");

		// TODO balmermi: removed that one! Needs to be replaced!
//		Scenario.input_directory = args[0];
//		Scenario.output_directory = args[0];
//		Scenario.setUpScenarioConfig();
//		NetworkLayer network = Scenario.readNetwork();
		NetworkImpl network = null;
		
		//////////////////////////////////////////////////////////////////////
		
		
		// TODO balmermi: removed that one! Needs to be replaced!
//		NetworkWriteAsTable nwat = new NetworkWriteAsTable(Scenario.output_directory);
//		nwat.run(network);
//		nwat.close();

		//////////////////////////////////////////////////////////////////////

		System.out.println("done.");				
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////
	// main
	//////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		Gbl.startMeasurement();
		Gbl.printElapsedTime();

		exportNetwork(args);

		Gbl.printElapsedTime();
	}

}
