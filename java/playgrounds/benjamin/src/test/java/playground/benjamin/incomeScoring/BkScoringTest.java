///* *********************************************************************** *
// * project: org.matsim.*
// * BkScoringTest
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// * copyright       : (C) 2009 by the members listed in the COPYING,        *
// *                   LICENSE and WARRANTY file.                            *
// * email           : info at matsim dot org                                *
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// *   This program is free software; you can redistribute it and/or modify  *
// *   it under the terms of the GNU General Public License as published by  *
// *   the Free Software Foundation; either version 2 of the License, or     *
// *   (at your option) any later version.                                   *
// *   See also COPYING, LICENSE and WARRANTY file                           *
// *                                                                         *
// * *********************************************************************** */
//package playground.benjamin.incomeScoring;
//
//import org.matsim.api.core.v01.Id;
//import org.matsim.api.core.v01.Scenario;
//import org.matsim.api.core.v01.population.Population;
//import org.matsim.core.basic.v01.IdImpl;
//import org.matsim.core.config.Config;
//import org.matsim.core.controler.Controler;
//import org.matsim.core.controler.events.StartupEvent;
//import org.matsim.core.controler.listener.StartupListener;
//import org.matsim.core.router.costcalculators.TravelDisutilityFactory;
//import org.matsim.core.scenario.ScenarioImpl;
//import org.matsim.core.scenario.ScenarioLoaderImpl;
//import org.matsim.core.scoring.EventsToScore;
//import org.matsim.core.scoring.ScoringFunctionFactory;
//import org.matsim.households.PersonHouseholdMapping;
//import org.matsim.roadpricing.RoadPricingScheme;
//import org.matsim.testcases.MatsimTestCase;
//
//import playground.benjamin.scoring.income.IncomeScoringFunctionFactory;
//import playground.benjamin.scoring.income.IncomeTollTravelCostCalculatorFactory;
//import playground.benjamin.scoring.income.IncomeTravelCostCalculatorFactory;
//
//
///**
// * Tests the scoring of the BkControlerIncome
// * It should test the scoring of BkIncomeControler but there are issues with installing a scoring function after instanciating the controler...
// * @author dgrether
// *
// */
//public class BkScoringTest extends MatsimTestCase {
//
//	/*package*/ final static Id id1 = new IdImpl("1");
//	/*package*/ final static Id id2 = new IdImpl("2");
//
//	private EventsToScore planScorer;
//
//	public void testSingleIterationIncomeScoring() {
//		
//		// reading the config file:
//		Config config = this.loadConfig(this.getClassInputDirectory() + "configIncomeScoreTest.xml");
//		
//		// modifying the config:
//		String netFileName = this.getClassInputDirectory() + "network.xml";
//		config.network().setInputFile(netFileName);
//		config.plans().setInputFile(this.getClassInputDirectory() + "plansScoreTestV4.xml");
//		config.scenario().setUseHouseholds(true);
//		config.households().setInputFile(this.getClassInputDirectory() + "households.xml");
//
//		// reading the scenario (based on the config):
//		ScenarioLoaderImpl scLoader = new ScenarioLoaderImpl(config) ;
//		ScenarioImpl sc = (ScenarioImpl) scLoader.loadScenario() ;
//		
//		// creating a controler
//		final Controler controler =  new Controler(sc);
//
//		// some controler configuration:
//		controler.setCreateGraphs(false);
//		controler.setWriteEventsInterval(0);
//
//		controler.addControlerListener(new TestDataStartupListener(controler));
//
//		// installing the income-based scoring function and related:
//		final PersonHouseholdMapping personHouseholdMapping = 
//			new PersonHouseholdMapping( ((ScenarioImpl) controler.getScenario()).getHouseholds() );
//		installScoringFunctionFactory(controler, personHouseholdMapping);
//		installTravelCostCalculatorFactory(controler, personHouseholdMapping);
//		
//		// running the controler:
//		controler.run();
//
//		// should test this one:
//		//		checkThatScoresAreRight(controler.getScenario().getPopulation());
//		this.planScorer.finish();
//
//
//		//============================================
//		//this score is calculated as follows:
//		//U_total_car = 4.58*LN(120000CHF/240)   -   4.58*(((0.12/1000m)*50000m)/(120000CHF/240)))   -   (0.97/3600s)*(60min*60)   +   1.86*8*LN(1/(EXP((-10*3600s)/(8*3600s))))   +   1.86*12*LN(15/(12*EXP((-10*3600s)/(12*3600s))))
//		//            = 28.46291                 -   0.05496                                        -   0.97                      +   14.88 *LN(3.490342957)                      +   22.32  *LN(2.876218905)
//		//            =                                                                                                            +   18.599999998
//		//this calculation uses distance of street network * 1.5
//		//U_total_pt  = 4.58*LN(40000CHF/240)    -   4.58*(((0.28/1000m)*75000m)/(40000CHF/240)))                                  +   1.86*8*LN(1/(EXP((-10*3600s)/(8*3600s))))   +   1.86*12*LN(14/(12*EXP((-10*3600s)/(12*3600s))))
//		//            = 23.43126                 -   0.57708                                                                       +   14.88 *LN(3.490342957)                      +   22.32  *LN(2.684471873)
//		//            =
//		//actually it is air distance * 1.5
//		//U_total_pt  = 4.58*LN(40000CHF/240)    -   4.58*(((0.28/1000m)*48750m)/(40000CHF/240)))                                  +   1.86*8*LN(1/(EXP((-10*3600s)/(8*3600s))))   +   1.86*12*LN(14/(12*EXP((-10*3600s)/(12*3600s))))
//		//            = 23.43126                 -   0.375102                                                                       +   14.88 *LN(3.490342957)                      +   22.32  *LN(2.684471873)
//		//            =
//		System.out.println("Agent 001: " + this.planScorer.getAgentScore(id1));
//		System.out.println("Agent 002: " + this.planScorer.getAgentScore(id2));
//		// should be 69.618506624 using microsoft windows calculator but is in java 69.6185091561068 -> should be ok
//		assertEquals("Wrong score for car agent", 69.6185091561068, this.planScorer.getAgentScore(id1), EPSILON);
//		// using gnome gcalctool this should be 63.696801174 but it is in java 63.69790888638976 -> should be ok
//		assertEquals("Wrong score for pt agent", 63.69790888638976, this.planScorer.getAgentScore(id2), EPSILON);
//	}
//
//	/**
//	 * @param population
//	 */
//	private void checkThatScoresAreRight(Population population) {
//		assertEquals("Wrong score for car agent", 69.6185091561068, getAgentScore(population, id1), EPSILON);
//		// using gnome gcalctool this should be 63.696801174 but it is in java 63.69790888638976 -> should be ok
//		assertEquals("Wrong score for pt agent", 63.69790888638976, getAgentScore(population, id2), EPSILON);
//	}
//
//	/**
//	 * @param population 
//	 * @param id12
//	 * @return
//	 */
//	private double getAgentScore(Population population, Id id1) {
//		return population.getPersons().get(id1).getSelectedPlan().getScore();
//	}
//
//	private final class TestDataStartupListener implements StartupListener {
//		private final Controler controler;
//
//		private TestDataStartupListener(Controler controler) {
//			this.controler = controler;
//		}
//
//		public void notifyStartup(final StartupEvent event) {
//			//				double agent1LeaveHomeTime = controler.getPopulation().getPerson(id1).getPlans().get(0).getFirstActivity().getEndTime();
//			//				double agent2LeaveHomeTime = controler.getPopulation().getPerson(id2).getPlans().get(0).getFirstActivity().getEndTime();
//			//				controler.getEvents().addHandler(new TestSingleIterationEventHandler(agent1LeaveHomeTime, agent2LeaveHomeTime));
//			planScorer = new EventsToScore(controler.getScenario(), controler.getScoringFunctionFactory(), controler.getConfig().planCalcScore().getLearningRate());
//			controler.getEvents().addHandler(planScorer);
//		}
//	}
//
//	private void installScoringFunctionFactory(Controler controler, PersonHouseholdMapping personHouseholdMapping) {
//		Scenario scenario = controler.getScenario();
//		ScoringFunctionFactory scoringFactory = 
//			new IncomeScoringFunctionFactory(scenario.getConfig(), personHouseholdMapping, scenario.getNetwork());
//		controler.setScoringFunctionFactory(scoringFactory);
//	}
//	
//	private void installTravelCostCalculatorFactory(Controler controler, PersonHouseholdMapping personHouseholdMapping) {
//		// returns null, if there is no road pricing
//		if (controler.getConfig().scenario().isUseRoadpricing()){
//			RoadPricingScheme roadPricingScheme = (RoadPricingScheme) controler.getScenario().getScenarioElement(RoadPricingScheme.class);
//			TravelDisutilityFactory travelCostCalculatorFactory = 
//				new IncomeTollTravelCostCalculatorFactory(personHouseholdMapping, roadPricingScheme);
//			controler.setTravelDisutilityFactory(travelCostCalculatorFactory);
//		}
//		else{
//			TravelDisutilityFactory travelCostCalculatorFactory = new IncomeTravelCostCalculatorFactory(personHouseholdMapping);
//			controler.setTravelDisutilityFactory(travelCostCalculatorFactory);
//		}
//	}
//	
//}
