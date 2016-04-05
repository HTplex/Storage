/* *********************************************************************** *
 * project: org.matsim.*
 * BKickLegScoring
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2009 by the members listed in the COPYING,        *
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
package playground.benjamin.scoring.income.old;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.Route;
import org.matsim.core.scoring.functions.CharyparNagelScoringParameters;
import org.matsim.core.scoring.functions.CharyparNagelLegScoring;
import org.matsim.households.Income;
import org.matsim.households.PersonHouseholdMapping;
import org.matsim.households.Income.IncomePeriod;

/**
 * @author dgrether
 *
 */
public class BKickLegScoring extends CharyparNagelLegScoring {

	private static final Logger log = Logger.getLogger(BKickLegScoring.class);

	private static double betaIncomeCar = 1.31;

	private static double betaIncomePt = 1.31;

	private double incomePerTrip;

	private Plan plan;

	public BKickLegScoring(final Plan plan, final CharyparNagelScoringParameters params, PersonHouseholdMapping hhdb, Network network) {
		super(params, network);
		this.plan = plan;
		Income income = hhdb.getHousehold(plan.getPerson().getId()).getIncome();
		this.incomePerTrip = this.calculateIncomePerTrip(income);

//		log.info("Using BKickLegScoring...");
	}

	@Override
	protected double calcLegScore(final double departureTime, final double arrivalTime, final Leg leg) {
		double tmpScore = 0.0;
		double travelTime = arrivalTime - departureTime; // traveltime in
		// seconds
		double dist = 0.0; // distance in meters

		if (TransportMode.car.equals(leg.getMode())) {
			Route route = leg.getRoute();
			dist = route.getDistance();
			if (Double.isNaN(dist)){
				throw new IllegalStateException("Route distance is NaN for person: " + this.plan.getPerson().getId());
			}

			tmpScore += travelTime * this.params.modeParams.get(TransportMode.car).marginalUtilityOfTraveling_s + this.params.modeParams.get(TransportMode.car).marginalUtilityOfDistance_m * dist
					* betaIncomeCar / this.incomePerTrip + betaIncomeCar * Math.log(this.incomePerTrip);
		}
		else if (TransportMode.pt.equals(leg.getMode())) {
				dist = leg.getRoute().getDistance();
//				log.error("dist: " + dist);
				if (Double.isNaN(dist)){
					throw new IllegalStateException("Route distance is NaN for person: " + this.plan.getPerson().getId());
				}
				tmpScore += travelTime * this.params.modeParams.get(TransportMode.pt).marginalUtilityOfTraveling_s + this.params.modeParams.get(TransportMode.pt).marginalUtilityOfDistance_m
						* dist * betaIncomePt / this.incomePerTrip + betaIncomePt * Math.log(this.incomePerTrip);
			}
			else {
				throw new IllegalStateException("Scoring funtion not defined for other modes than pt and car!");
			}

		if (Double.isNaN(tmpScore)){
			throw new IllegalStateException("Leg score is NaN for person: " + this.plan.getPerson().getId());
		}
		return tmpScore;
	}

	private double calculateIncomePerTrip(Income income) {
		double ipt = Double.NaN;
		if (income.getIncomePeriod().equals(IncomePeriod.year)) {
			ipt = income.getIncome() / (240 * 3.5);
//			log.debug("income: " + ipt);
			if (Double.isNaN(ipt)){
				throw new IllegalStateException("cannot calculate income for person: " + this.plan.getPerson().getId());
			}
		}
		else {
			throw new UnsupportedOperationException("Can't calculate income per trip");
		}
		return ipt;
	}

}
