/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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

package playground.anhorni.locationchoice.analysis.facilities;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.facilities.ActivityFacilities;
import org.matsim.facilities.ActivityFacility;
import org.matsim.facilities.FacilitiesReaderMatsimV1;

import playground.anhorni.choiceSetGeneration.filters.AreaReader;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class SpatialAnalysisFacilities {

	private final static Logger log = Logger.getLogger(SpatialAnalysisFacilities.class);
	private List<Polygon> area;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpatialAnalysisFacilities analyzer = new SpatialAnalysisFacilities();
		analyzer.run(args[0], args[1]);
	}

	public void run(String facilitiesFile, String areaShapeFile) {

		TreeMap<Id,ActivityFacility> shop_facilities = this.readAndFilterFacilities(facilitiesFile);

		// Read area
		AreaReader areaReader = new AreaReader();
		areaReader.readShapeFile(areaShapeFile);
		this.area = areaReader.getAreaPolygons();

		List<ActivityFacility> zhShopFacilities = this.filterStores(shop_facilities);
		this.print(zhShopFacilities);
	}

	private void print(List<ActivityFacility> facilities) {
		log.info(facilities.size());
		FacilitiesWriter writer = new FacilitiesWriter();
		int [] numberOfFacilities = writer.write(facilities);

		log.info("Number of shop_retail_gt2500sqm: " + numberOfFacilities[0]);
		log.info("Number of shop_retail_get1000sqm: " + numberOfFacilities[1]);
		log.info("Number of shop_retail_get400sqm: " + numberOfFacilities[2]);
		log.info("Number of shop_retail_get100sqm: " + numberOfFacilities[3]);
		log.info("Number of shop_retail_lt100sqm: " + numberOfFacilities[4]);
		log.info("Number of shop_other: " + numberOfFacilities[5]);
	}

	private TreeMap<Id,ActivityFacility> readAndFilterFacilities(String facilitiesFile) {
		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		ActivityFacilities facilities  = scenario.getActivityFacilities();
		new FacilitiesReaderMatsimV1(scenario).readFile(facilitiesFile);

		TreeMap<Id,ActivityFacility> shop_facilities = new TreeMap<Id,ActivityFacility>();
		shop_facilities.putAll(facilities.getFacilitiesForActivityType("shop_retail_gt2500sqm"));
		shop_facilities.putAll(facilities.getFacilitiesForActivityType("shop_retail_get1000sqm"));
		shop_facilities.putAll(facilities.getFacilitiesForActivityType("shop_retail_get400sqm"));
		shop_facilities.putAll(facilities.getFacilitiesForActivityType("shop_retail_get100sqm"));
		shop_facilities.putAll(facilities.getFacilitiesForActivityType("shop_retail_lt100sqm"));
		shop_facilities.putAll(facilities.getFacilitiesForActivityType("shop_other"));

		return shop_facilities;
	}

	private List<ActivityFacility> filterStores(TreeMap<Id,ActivityFacility> shop_facilities) {

		List<ActivityFacility> zhShopFacilities = new Vector<ActivityFacility>();

		Iterator<ActivityFacility> shop_iter = shop_facilities.values().iterator();
		while (shop_iter.hasNext()) {
			ActivityFacility facility = shop_iter.next();

			if (this.insideArea(facility.getCoord())) {
				zhShopFacilities.add(facility);
			}
		}
		return zhShopFacilities;
	}

	private boolean insideArea(Coord coordIn) {
		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate coord = new Coordinate(coordIn.getX(), coordIn.getY());
		Point point = geometryFactory.createPoint(coord);

		Iterator<Polygon> polygon_it = this.area.iterator();
		while (polygon_it.hasNext()) {
			Polygon polygon = polygon_it.next();
			if (polygon.contains(point)) {
				return true;
			}
		}
		return false;
	}
}
