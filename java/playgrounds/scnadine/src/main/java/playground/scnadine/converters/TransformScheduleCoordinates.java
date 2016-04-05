package playground.scnadine.converters;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.transformations.GeotoolsTransformation;
import org.matsim.pt.transitSchedule.TransitScheduleReaderV1;
import org.matsim.pt.transitSchedule.api.TransitSchedule;
import org.matsim.pt.transitSchedule.api.TransitScheduleWriter;
import org.matsim.pt.transitSchedule.api.TransitStopFacility;



public class TransformScheduleCoordinates {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String sourceFile = "Z:\\Projekte\\Peacox\\networks\\osm\\ptScheduleAustria.xml.gz";
		String targetFile = "Z:\\Projekte\\Peacox\\networks\\osm\\ptScheduleAustria_transformed.xml.gz";
		String sourceCoordSystem = "WGS84";
		String targetCoordSystem = "EPSG:31253";
		
		
		
		Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
		scenario.getConfig().scenario().setUseTransit(true);
		
		TransitSchedule schedule = scenario.getTransitSchedule();				
		new TransitScheduleReaderV1(scenario).readFile(sourceFile);
		
		GeotoolsTransformation ct = new GeotoolsTransformation(sourceCoordSystem, targetCoordSystem);
		
		for (TransitStopFacility stop : schedule.getFacilities().values()) {						
			Coord coord = stop.getCoord();				
			Coord helperCoord = ct.transform(coord);
			stop.getCoord().setX(helperCoord.getX());
			stop.getCoord().setY(helperCoord.getY());
		}
		
		TransitScheduleWriter writer = new TransitScheduleWriter(schedule);
		writer.writeFile(targetFile);
		
		
		System.out.println("Public transport schedule conversion completed.");		
		System.out.println("========================================");
		
		
		
		
		
		

	}

}
