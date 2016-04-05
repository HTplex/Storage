///* *********************************************************************** *
// * project: org.matsim.*
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// * copyright       : (C) 2012 by the members listed in the COPYING,        *
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
//package playground.yu.integration.cadyts.parameterCalibration.withCarCounts.pseudo;
//
//import org.matsim.core.config.Config;
//import org.matsim.core.controler.corelisteners.EventsHandling;
//import org.matsim.core.controler.corelisteners.PlansDumping;
//import org.matsim.core.controler.corelisteners.PlansReplanning;
//import org.matsim.core.replanning.StrategyManager;
//import org.matsim.core.replanning.StrategyManagerConfigLoader;
//import org.matsim.core.scoring.ScoringFunctionFactory;
//
//import playground.yu.integration.cadyts.parameterCalibration.withCarCounts.generalNormal.paramCorrection.BseParamCalibrationControler;
//import playground.yu.integration.cadyts.parameterCalibration.withCarCounts.testLeftTurn.PlansScoringWithLeftTurnPenalty4PC;
//import playground.yu.scoring.withAttrRecorder.ScorAttrReader.ScorAttrReadListener;
//import playground.yu.scoring.withAttrRecorder.leftTurn.CharyparNagelScoringFunctionFactoryWithLeftTurnPenalty;
//import playground.yu.tests.parameterCalibration.naiveWithoutUC.SimCntLogLikelihoodCtlListener;
//
///**
// * @author yu
// *
// */
//public class CtlWithLeftTurnPenaltyLs extends BseParamCalibrationControler {
//
//	public CtlWithLeftTurnPenaltyLs(Config config) {
//		super(config);
//		extension = new PCCtlListener();
//		addControlerListener(extension);
//		addControlerListener(new ScorAttrReadListener());
//		addControlerListener(new SimCntLogLikelihoodCtlListener());
//	}
//
//	/**
//	 * please check the method in super class, when the super class
//	 * {@code org.matsim.core.controler.Controler} is changed sometimes
//	 */
//	@Override
//	protected void loadCoreListeners() {
//
//		// ******DEACTIVATE SCORING & ROADPRICING IN MATSIM******
//		// the default handling of plans
//		plansScoring4PC = new PlansScoringWithLeftTurnPenalty4PC();
//		addCoreControlerListener(plansScoring4PC);
//
//		// load road pricing, if requested
//		// if (this.config.roadpricing().getTollLinksFile() != null) {
//		// this.areaToll = new RoadPricing();
//		// this.addCoreControlerListener(areaToll);
//		// }
//		// ******************************************************
//
//		addCoreControlerListener(new PlansReplanning());
//		addCoreControlerListener(new PlansDumping());
//		// EventsHanding ... very important
//		addCoreControlerListener(new EventsHandling(events));
//	}
//
//	@Override
//	protected ScoringFunctionFactory loadScoringFunctionFactory() {
//		return new CharyparNagelScoringFunctionFactoryWithLeftTurnPenalty(
//				config, network);
//	}
//
//	@Override
//	protected StrategyManager loadStrategyManager() {
//		StrategyManager manager = new PCStrMn(network, getFirstIteration(),
//				config);
//		StrategyManagerConfigLoader.load(this, manager);
//
//		return manager;
//	}
//}
