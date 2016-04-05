/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2014 by the members listed in the COPYING,        *
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

package playground.johannes.gsv.gis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import playground.johannes.gsv.zones.Zone;
import playground.johannes.gsv.zones.ZoneCollection;
import playground.johannes.gsv.zones.io.Zone2GeoJSON;
import playground.johannes.gsv.zones.io.ZoneCollectionSHPReader;

/**
 * @author johannes
 *
 */
public class ExtractModenaZones {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ZoneCollection world = ZoneCollectionSHPReader.read("");
		ZoneCollection ger = new ZoneCollection();
		
		for(Zone zone : world.zoneSet()) {
			if("DE".equalsIgnoreCase(zone.getAttribute("NUTS0_CODE"))) {
				Zone newZone = new Zone(zone.getGeometry());
				newZone.setAttribute("nuts0_code", zone.getAttribute("NUTS0_CODE"));
				newZone.setAttribute("nuts1_code", zone.getAttribute("NUTS1_CODE"));
				newZone.setAttribute("nuts2_code", zone.getAttribute("NUTS2_CODE"));
				newZone.setAttribute("nuts3_code", zone.getAttribute("NUTS3_CODE"));
				
				newZone.setAttribute("nuts0_name", zone.getAttribute("NUTS0_NAME"));
				newZone.setAttribute("nuts1_name", zone.getAttribute("NUTS1_NAME"));
				newZone.setAttribute("nuts2_name", zone.getAttribute("NUTS2_NAME"));
				newZone.setAttribute("nuts3_name", zone.getAttribute("NUTS3_NAME"));
				
				newZone.setAttribute("gsvId", zone.getAttribute("NO"));
				newZone.setAttribute("gsvName", zone.getAttribute("NAME"));
				
				ger.add(newZone);
			}
		}
		
		String data = Zone2GeoJSON.toJson(ger.zoneSet());
		Files.write(Paths.get(""), data.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

	}

}
