///* *********************************************************************** *
// * project: org.matsim.*
// * Run.java
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// * copyright       : (C) 2011 by the members listed in the COPYING,        *
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
//
///**
// *
// */
//package playground.yu.integration.cadyts.parameterCalibration.withCarCounts.testLeftTurn;
//
//import org.matsim.core.config.Config;
//import org.matsim.core.config.ConfigUtils;
//import org.matsim.core.controler.Controler;
//
//import playground.yu.analysis.RouteTravelTimeSummary;
//import playground.yu.counts.CntSimCap4Chart;
//
///**
// * @author yu
// * 
// */
//public class PC_Run {
//	/** @param args */
//	/**
//	 * @param args
//	 */
//	public static void main(final String[] args) {
//		Config config = ConfigUtils.loadConfig(args[0]);
//		Controler ctl = new PCCtlwithLeftTurnPenalty(config);
//		if (args.length > 1 && Boolean.parseBoolean(args[1])) {
//			ctl.addControlerListener(new CntSimCap4Chart());
//			ctl.addControlerListener(new RouteTravelTimeSummary());
//		}
//		// TODO set in config
//		// ctl.addControlerListener(new QVProfilControlerListener());
//
//		ctl.setCreateGraphs(false);
//		ctl.setOverwriteFiles(true);
//		ctl.run();
//	}
//
//}
