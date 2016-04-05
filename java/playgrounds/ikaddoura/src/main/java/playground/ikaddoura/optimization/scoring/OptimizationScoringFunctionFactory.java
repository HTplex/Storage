package playground.ikaddoura.optimization.scoring;

/* *********************************************************************** *
 * project: org.matsim.*
 * MyScoringFunctionFactory.java
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

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.matsim.core.scoring.ScoringFunction;
import org.matsim.core.scoring.ScoringFunctionAccumulator;
import org.matsim.core.scoring.ScoringFunctionFactory;
import org.matsim.core.scoring.functions.CharyparNagelLegScoring;
import org.matsim.core.scoring.functions.CharyparNagelMoneyScoring;
import org.matsim.core.scoring.functions.CharyparNagelScoringParameters;


public class OptimizationScoringFunctionFactory implements ScoringFunctionFactory {

	private final CharyparNagelScoringParameters params;
	private final double STUCKING_SCORE;
	private Network network;
	

	public OptimizationScoringFunctionFactory(final PlanCalcScoreConfigGroup planCalcScoreConfigGroup, Network network, double stuckScore) {
		this.params = CharyparNagelScoringParameters.getBuilder(planCalcScoreConfigGroup).create();
		this.network = network;
		this.STUCKING_SCORE = stuckScore;
	}

	@Override
	public ScoringFunction createNewScoringFunction(Person person) {
			
		ScoringFunctionAccumulator scoringFunctionAccumulator = new ScoringFunctionAccumulator();
		
		scoringFunctionAccumulator.addScoringFunction(new CharyparNagelLegScoring(this.params, network));
		scoringFunctionAccumulator.addScoringFunction(new CharyparNagelMoneyScoring(this.params));
		scoringFunctionAccumulator.addScoringFunction(new OptimizationAgentStuckScoringFunction(this.params, this.STUCKING_SCORE));
		scoringFunctionAccumulator.addScoringFunction(new OptimizationActivityScoringFunction(this.params));
		
		return scoringFunctionAccumulator;
	}
}
