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

package playground.fhuelsmann.emission.analysis.mobility;


import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;
import org.matsim.core.population.PopulationUtils;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioLoaderImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.gis.ShapeFileReader;
import org.opengis.feature.simple.SimpleFeature;
import playground.benjamin.scenarios.munich.analysis.filter.LocationFilter;
import playground.benjamin.scenarios.munich.analysis.filter.PersonFilter;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class Mobility {
	
	private static String runDirectory = "../../run980/";
	private static String eventsFile = runDirectory + "ITERS/it.1000/980.1000.events.xml.gz";
	private static String netFile = runDirectory + "980.output_network.xml.gz";
	private static String plansFile = runDirectory + "980.output_plans.xml.gz";

	private static String shapeFile = "../../detailedEval/Net/shapeFromVISUM/Verkehrszellen_MUC_Netz.shp";
	
	
	private final Scenario scenario;
	
	//private Set<Feature> zones;
	//private Map<Integer, Feature> zonesMap;

	public static void main (String[] args)throws Exception {
		Mobility mob = new Mobility();
		mob.run(args);
		
	}
	public Mobility(){
		Config config = ConfigUtils.createConfig();
		this.scenario = ScenarioUtils.createScenario(config);
		}

	private void run(String[] args) {
		loadScenario();
		EventsManager eventsManager = EventsUtils.createEventsManager();
	
		// An example of an events handler which takes
		// AgenArrivalEvent and AgentDepartureEvent to calculate travelTime per person 
		CalcAvgTripDurations actd = new CalcAvgTripDurations();
		
		eventsManager.addHandler(actd);
		MatsimEventsReader reader = new MatsimEventsReader(eventsManager);
		reader.readFile(eventsFile);
		
		Collection<SimpleFeature> zoneShape = readShape(shapeFile);

		Population population = scenario.getPopulation();
		Population pop = getRelevantPopulation(population,zoneShape);
		
		Map<Id, Double> zoneId2mobility =actd.getAvgTripDuration(pop);
		
		

		writeInformation(pop, zoneId2mobility);
	}


	private void writeInformation(Population population, Map<Id, Double> zoneId2mobility) {

		System.out.println("##################################################################");
		for(Entry<Id, Double> entry : zoneId2mobility.entrySet()){
			System.out.println("zoneId: " + entry.getKey() + "\t" + "mobility: " + entry.getValue() + " %");
		}
		System.out.println("##################################################################" + "\n");
	}
	
	
/**
 * method 1 - to get polygones from a multiPolygon and the geometry
 **/
/* public void getZonesFromShapeFile(Set<Feature> featuresInShape){
	FeatureCollection featureCollection = (FeatureCollection) featuresInShape.get(key);
		Iterator it = featureCollection.iterator();
		while (it.hasNext()) {
			Feature feaNext = (Feature) it.next();
			Object geometry = feaNext.getDefaultGeometry();
			Geometry geom = (Geometry) geometry;
			MultiPolygon multiPolygon = (MultiPolygon) geom;
			
			for (int j = 0; j < multiPolygon.getNumGeometries(); j++) {
				Geometry geomMulti = multiPolygon.getGeometryN(j);
				CoordinateList coordinateList = new CoordinateList();
				Vector<Geometry> vectorGeometry = new Vector<Geometry>();
				for (int i = 0; i < geomMulti.getCoordinates().length; i++) {
					if (coordinateList.size() > 0
							&& coordinateList.contains(geomMulti.getCoordinates()[i])) {
						coordinateList.add(geomMulti.getCoordinates()[i], true);
						vectorGeometry.add(gf.createLineString(coordinateList.toCoordinateArray()));
						coordinateList = new CoordinateList();
						} 
					else {
						coordinateList.add(geomMulti.getCoordinates()[i], true);
                    	}
					}
				}
			}
		}
}*/

	/**
	 * method 2 - to make zones out of a shapefile adapted from playground.christoph.netherlands.zones.CreateZoneConnectors.java;
	 **/
	/*public void createMapping(CoordinateTransformation coordinateTransformation) throws Exception {
		network = scenario.getNetwork();

		log.info("Loading Network ... done");
		log.info("Nodes: " + network.getNodes().size());
		log.info("Links: " + network.getLinks().size());
		
		
		 * read zones shape file
		 
		zones = new HashSet<Feature>();

		FeatureSource featureSource = ShapeFileReader.readDataFile(shapeFile);
		for (Object o : featureSource.getFeatures()) {
			zones.add((Feature) o);
		}
	
		zonesMap = new TreeMap<Integer, Feature>();
		for (Feature zone : zones) {
//			int id = Integer.valueOf(zone.getID().replace("postcode4.", ""));	// Object Id
			int id = ((Long)zone.getAttribute(1)).intValue();	// Zone Id
//			int id = ((Long)zone.getAttribute(3)).intValue();	// PostCode
			zonesMap.put(id, zone);
		}
	}*/

	public Population getRelevantPopulation(Population population, Collection<SimpleFeature> featuresInShape) {
		ScenarioImpl emptyScenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
        Population filteredPopulation = PopulationUtils.createPopulation(emptyScenario.getConfig(), emptyScenario.getNetwork());
		for(Person person : population.getPersons().values()){
			PersonFilter personFilter = new PersonFilter();
			boolean isPersonFreight = personFilter.isPersonFreight(person.getId());
			if(!isPersonFreight){
				LocationFilter homeLocationFilter = new LocationFilter();
				boolean isPersonsHomeInShape = homeLocationFilter.isPersonsHomeInShape(person, featuresInShape);
				if(isPersonsHomeInShape){
					filteredPopulation.addPerson(person);
				}
			}
		}
		return filteredPopulation;
	}
	
	Collection<SimpleFeature> readShape(String shapeFile) {
		final Collection<SimpleFeature> featuresInShape;
		featuresInShape = new ShapeFileReader().readFileAndInitialize(shapeFile);
		return featuresInShape;
	}
	
	
	private void loadScenario() {
		Config config = scenario.getConfig();
		config.network().setInputFile(netFile);
		config.plans().setInputFile(plansFile);
		ScenarioLoaderImpl scenarioLoader = new ScenarioLoaderImpl(scenario);
		scenarioLoader.loadScenario();
	}
	
}
