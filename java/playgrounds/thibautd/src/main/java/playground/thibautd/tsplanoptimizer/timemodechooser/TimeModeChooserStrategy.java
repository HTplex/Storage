/* *********************************************************************** *
 * project: org.matsim.*
 * TimeModeChooserStrategy.java
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
package playground.thibautd.tsplanoptimizer.timemodechooser;

import org.matsim.api.core.v01.population.HasPlansAndId;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.replanning.PlanStrategyModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.replanning.PlanStrategy;
import org.matsim.core.replanning.PlanStrategyImpl;
import org.matsim.core.replanning.ReplanningContext;
import org.matsim.core.replanning.selectors.RandomPlanSelector;

/**
 * @author thibautd
 */
public class TimeModeChooserStrategy implements PlanStrategy {
	private final PlanStrategyImpl delegate;

	public TimeModeChooserStrategy(final Controler controler) {
		delegate = new PlanStrategyImpl( new RandomPlanSelector() );
		delegate.addStrategyModule( new TimeModeChooserModule( controler ) );
	}

	public void addStrategyModule(final PlanStrategyModule module) {
		delegate.addStrategyModule( module );
	}

	public int getNumberOfStrategyModules() {
		return delegate.getNumberOfStrategyModules();
	}

	@Override
	public void run(final HasPlansAndId<Plan, Person> person) {
		delegate.run( person );
	}

	@Override
	public void init(ReplanningContext replanningContext) {
		delegate.init(replanningContext);
	}

	@Override
	public void finish() {
		delegate.finish();
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

}

