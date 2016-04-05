package playground.scnadine.converters;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.transformations.GeotoolsTransformation;

public class TransformNetworkCoordinates {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sourceFile = "Z:\\Projekte\\Peacox\\networks\\osm\\ptNetworkZueri.xml.gz";
		String targetFile = "Z:\\Projekte\\Peacox\\networks\\osm\\ptNetworkZueri_transformed.xml.gz";
		String sourceCoordSystem = "WGS84";
		String targetCoordSystem = "EPSG:31253";
		
		
		
		Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());		
		Network network = scenario.getNetwork();
		
		new MatsimNetworkReader(scenario).readFile(sourceFile);	
		
		GeotoolsTransformation ct = new GeotoolsTransformation(sourceCoordSystem, targetCoordSystem);
		
		for (Node node : network.getNodes().values()) {						
			Coord coord = node.getCoord();				
			Coord helperCoord = ct.transform(coord);
			node.getCoord().setX(helperCoord.getX());
			node.getCoord().setY(helperCoord.getY());
		}
		
		new NetworkWriter(scenario.getNetwork()).write(targetFile);
		
		
		System.out.println("Network node conversion completed.");		
		System.out.println("========================================");
		

	}

}
