/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2013 by the members listed in the COPYING,        *
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

package playground.scnadine.converters.osmPT;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.pt.transitSchedule.api.TransitScheduleWriter;

/**
 * @author mrieser / Senozon AG
 */
public class Main {

	public static void main(String[] args) {
		Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
		scenario.getConfig().scenario().setUseTransit(true);
				

		Osm2TransitLines osm2pt = new Osm2TransitLines(scenario.getTransitSchedule(), scenario.getNetwork());
//		
//		osm2pt.convert("C:\\Data\\Projekte\\Peacox\\networks\\osm\\switzerland.osm");
		osm2pt.convert("C:\\Data\\Projekte\\Peacox\\networks\\osm\\zurich.osm.gz");
//		osm2pt.convert("C:\\Data\\Projekte\\Peacox\\networks\\osm\\zueri_old.osm.gz");
//		osm2pt.convert("D:\\Arbeit\\Projekte\\Peacox\\Networks\\switzerland.osm");
//		osm2pt.convert("D:\\Arbeit\\Projekte\\Peacox\\Networks\\zueri.osm.gz");
//		osm2pt.convert("C:\\Data\\Projekte\\Peacox\\networks\\osm\\austria.osm");
		
		
//		osm2pt.convert("C:\\Data\\Projekte\\Peacox\\networks\\fieldTrial\\austria-latest.osm");
//		osm2pt.convert("C:\\Data\\Projekte\\Peacox\\networks\\fieldTrial\\ireland-and-northern-ireland-latest.osm");
		
//		new NetworkWriter(scenario.getNetwork()).write("C:\\Data\\Projekte\\Peacox\\networks\\osm\\ptNetworkSwitzerland_newTypes.xml.gz");
//		new NetworkWriter(scenario.getNetwork()).write("C:\\Data\\Projekte\\Peacox\\networks\\osm\\ptNetworkZueri.xml.gz");
//		new NetworkWriter(scenario.getNetwork()).write("D:\\Arbeit\\Projekte\\Peacox\\Networks\\ptNetworkSwitzerland.xml.gz");
		new NetworkWriter(scenario.getNetwork()).write("D:\\Arbeit\\Projekte\\Peacox\\Networks\\ptNetworkZueri.xml.gz");
//		new NetworkWriter(scenario.getNetwork()).write("C:\\Data\\Projekte\\Peacox\\Networks\\osm\\ptNetworkZueri_noRail.xml.gz");
//		new NetworkWriter(scenario.getNetwork()).write("C:\\Data\\Projekte\\Peacox\\networks\\osm\\ptNetworkAustria.xml.gz");
		
//		new NetworkWriter(scenario.getNetwork()).write("C:\\Data\\Projekte\\Peacox\\networks\\fieldTrial\\ptNetworkAustria.xml.gz");
//		new NetworkWriter(scenario.getNetwork()).write("C:\\Data\\Projekte\\Peacox\\networks\\fieldTrial\\ptNetworkIreland.xml.gz");
		
//		new TransitScheduleWriter(scenario.getTransitSchedule()).writeFile("D:\\Arbeit\\Projekte\\Peacox\\Networks\\ptScheduleSwitzerland.xml.gz");
//		new TransitScheduleWriter(scenario.getTransitSchedule()).writeFile("D:\\Arbeit\\Projekte\\Peacox\\Networks\\ptScheduleZueri.xml");
//		new TransitScheduleWriter(scenario.getTransitSchedule()).writeFile("C:\\Data\\Projekte\\Peacox\\networks\\osm\\ptScheduleZueri.xml");
//		new TransitScheduleWriter(scenario.getTransitSchedule()).writeFile("C:\\Data\\Projekte\\Peacox\\networks\\osm\\ptScheduleAustria.xml.gz");
		
//		new TransitScheduleWriter(scenario.getTransitSchedule()).writeFile("C:\\Data\\Projekte\\Peacox\\networks\\fieldTrial\\ptScheduleAustria.xml.gz");
//		new TransitScheduleWriter(scenario.getTransitSchedule()).writeFile("C:\\Data\\Projekte\\Peacox\\networks\\fieldTrial\\ptScheduleIreland.xml.gz");
	}
}
