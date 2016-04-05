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

package playground.johannes.gsv.zones.io;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

import playground.johannes.gsv.zones.Zone;
import playground.johannes.gsv.zones.ZoneCollection;
import playground.johannes.socialnetworks.gis.io.FeatureSHP;

import com.vividsolutions.jts.geom.Geometry;

/**
 * @author johannes
 *
 */
public class ZoneCollectionSHPReader {

	public static ZoneCollection read(String filename) {
		ZoneCollection zones = new ZoneCollection();
		Set<Zone> zoneSet = new HashSet<>();
		
		try {
			for(SimpleFeature feature : FeatureSHP.readFeatures(filename)) {
				Zone zone = new Zone((Geometry) feature.getDefaultGeometry());
		
				for(Property prop : feature.getProperties()) {
					zone.setAttribute(prop.getName().getLocalPart(), prop.getValue().toString());
					
				}
				
				zoneSet.add(zone);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		zones.addAll(zoneSet);
		
		return zones;
	}
}
