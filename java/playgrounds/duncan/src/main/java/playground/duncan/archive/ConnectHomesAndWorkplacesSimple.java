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

package playground.duncan.archive;

import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.MatsimPopulationReader;
import org.matsim.core.population.PopulationWriter;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.facilities.MatsimFacilitiesReader;

public class ConnectHomesAndWorkplacesSimple {

	public void run(final String[] args) {

		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());

//		ActivityFacilitiesImpl facilities = scenario.getActivityFacilities() ;
		MatsimFacilitiesReader fr = new MatsimFacilitiesReader(scenario) ;
		fr.readFile( "lsfd" ) ;

		Population population = scenario.getPopulation() ;
		MatsimPopulationReader pr = new MatsimPopulationReader ( scenario ) ;
		pr.readFile( "lsdkjf" ) ;

		// program locachoice here

		new PopulationWriter(population, scenario.getNetwork()).write("newfilename");

	}

	public static void main(final String[] args) {
		ConnectHomesAndWorkplacesSimple app = new ConnectHomesAndWorkplacesSimple();
		app.run(args);
	}

}
