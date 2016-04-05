package playground.wrashid.parkingSearch.planLevel.scenario;

import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.population.MatsimPopulationReader;
import org.matsim.core.population.PopulationReader;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.facilities.MatsimFacilitiesReader;

public class BaseNonControlerScenario {

	/**
	 * If you just want to work on population, network or facilities without the controler for tests, use this scenario.
	 * @param sc
	 * @return
	 */
	public static Network loadNetwork(ScenarioImpl sc) {
		String facilitiesPath = "test/input/playground/wrashid/parkingSearch/planLevel/chessFacilities.xml";
		String networkFile = "test/input/playground/wrashid/parkingSearch/planLevel/network.xml";
		String inputPlansFile = "test/input/playground/wrashid/parkingSearch/planLevel/chessPlans2.xml";
	
		new MatsimFacilitiesReader(sc).readFile(facilitiesPath);
	
		PopulationReader popReader = new MatsimPopulationReader(sc);
		popReader.readFile(inputPlansFile);
	
		new MatsimNetworkReader(sc).readFile(networkFile);
	
		return sc.getNetwork();
	}

}
