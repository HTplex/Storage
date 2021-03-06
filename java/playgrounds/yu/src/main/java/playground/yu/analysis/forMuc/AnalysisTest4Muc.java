///* *********************************************************************** *
// * project: org.matsim.*
// * AvgTolledTripLengthControler.java
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// * copyright       : (C) 2007 by the members listed in the COPYING,        *
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
//package playground.yu.analysis.forMuc;
//
//import org.matsim.api.core.v01.network.Network;
//import org.matsim.api.core.v01.population.Population;
//import org.matsim.core.api.experimental.events.EventsManager;
//import org.matsim.core.config.ConfigUtils;
//import org.matsim.core.events.EventsUtils;
//import org.matsim.core.events.MatsimEventsReader;
//import org.matsim.core.network.MatsimNetworkReader;
//import org.matsim.core.population.MatsimPopulationReader;
//import org.matsim.core.scenario.ScenarioImpl;
//import org.matsim.core.scenario.ScenarioUtils;
//import org.matsim.roadpricing.RoadPricingReaderXMLv1;
//import org.matsim.roadpricing.RoadPricingSchemeImpl;
//
//import playground.yu.analysis.CalcLinksAvgSpeed;
//import playground.yu.analysis.CalcNetAvgSpeed;
//import playground.yu.analysis.CalcTrafficPerformance;
//import playground.yu.analysis.LegDistance;
//import playground.yu.analysis.MyCalcAverageTripLength;
//import playground.yu.utils.io.SimpleWriter;
//
///**
// * @author ychen
// * 
// */
//public class AnalysisTest4Muc implements Analysis4Muc {
//	private static boolean withToll = false;
//
//	private static void printUsage() {
//		System.out.println();
//		System.out.println("AnalysisTest4Muc:");
//		System.out.println("----------------");
//		System.out
//				.println("Create an additional analysis for the runs, which were done with only org.matsim.run.Controler");
//		System.out.println();
//		System.out.println("usage: AnalysisTest args");
//		System.out.println(" arg 0:\tname of scenario (for Zurich required)");
//		System.out.println(" arg 1:\trunId");
//		System.out
//				.println(" arg 2:\tname incl. path to network file (.xml[.gz])(required)");
//		System.out
//				.println(" arg 3:\tpath to events file, plans file, and analysis files, without the last \"/\"");
//		System.out.println(" args 4:\twhether there is plansfile (true/false)");
//		System.out
//				.println(" arg 5:\tsnapshot-period of OTFVis:  Specify how often a snapshot should be taken when reading the events, in seconds.");
//		System.out
//				.println(" arg 6:\tname incl. path to facilities file (.xml[.gz])(optional)");
//		System.out
//				.println(" arg 7:\tname incl. path to toll file (.xml)(optional)");
//		System.out.println("----------------");
//	}
//
//	private static void runIntern(final String[] args, final String scenario) {
//		final String netFilename = args[2];
//
//		String[] tmp = args[3]/* iterationPath */.split("it.");// ".../it.xxx"
//		String outputBase = args[3]/* iterationPath */+ "/" + args[1]/* runId */
//				+ "." + tmp[tmp.length - 1] + ".";// ".../it.xxx/rrr.xxx."
//		final String eventsFilename = outputBase + "events.txt.gz";
//
//		String plansFilename = Boolean.parseBoolean(args[4])/* hasPop? */? outputBase
//				+ "plans.xml.gz"
//				: null;
//
//		String outputBase4analysis = outputBase
//				+ "analysis"
//				+ (scenario.equals(ONLY_MUNICH) ? "." + ONLY_MUNICH + "/" : "/")
//				+ args[1]/* runId */
//				+ "." + (scenario.equals("normal") ? "" : scenario + ".");
//
//		String tollFilename = !scenario.equals(ONLY_MUNICH) ? null
//				: args[args.length - 1];// last parameter, man needs tollFile
//		// only for Munich.
//
//		ScenarioImpl sc = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
//		Network network = sc.getNetwork();
//		new MatsimNetworkReader(sc).readFile(netFilename);
//
//		// toll
//		RoadPricingSchemeImpl toll = null;
//		if (withToll) {
//			sc.getConfig().scenario().setUseRoadpricing(true);
//			toll = sc.getRoadPricingScheme();
//			new RoadPricingReaderXMLv1(toll).parse(tollFilename);
//		}
//
//		// EventsHandlers with parameter of "Population":
//		EnRouteModalSplit4Muc orms = null;
//		LegTravelTimeModalSplit4Muc lttms = null;
//		// PersonAlgorithm
//		MyCalcAverageTripLength catl = null;
//		DailyDistance4Muc dd = null;
//		DailyEnRouteTime4Muc dert = null;
//		ModeSplit4Muc ms = null;
//		LegDistance ld = null;
//		// only PersonAlgorithm begins.
//		if (plansFilename != null) {
//			Population population = sc.getPopulation();
//
//			catl = new MyCalcAverageTripLength(network);
//			ms = new ModeSplit4Muc(toll);
//			orms = new EnRouteModalSplit4Muc(scenario, population, toll);
//			lttms = new LegTravelTimeModalSplit4Muc(population, toll);
//			dd = new DailyDistance4Muc(toll, network);
//			dert = new DailyEnRouteTime4Muc(toll);
//			ld = new LegDistance(network, toll, population);
//			// in future, add some PersonAlgorithm and EventsHandler
//
//			new MatsimPopulationReader(sc).readFile(plansFilename);
//
//			catl.run(population);
//			dd.run(population);
//			dert.run(population);
//			ms.run(population);
//		} else {
//			ld = new LegDistance(network);
//		}
//		// only PersonAlgorithm ends.
//		EventsManager events = EventsUtils.createEventsManager();
//		// EventsHandlers without parameter of "Population":
//		CalcTrafficPerformance ctpf = new CalcTrafficPerformance(network, toll);
//		CalcNetAvgSpeed cas = new CalcNetAvgSpeed(network, toll);
//		CalcLinksAvgSpeed clas = null;
//		if (withToll) {
//			clas = new CalcLinksAvgSpeed(network, toll);
//		} else {
//			clas = new CalcLinksAvgSpeed(network);
//		}
//
//		events.addHandler(ctpf);
//		events.addHandler(cas);
//		events.addHandler(clas);
//		events.addHandler(ld);
//
//		if (orms != null) {
//			events.addHandler(orms);
//		}
//		if (lttms != null) {
//			events.addHandler(lttms);
//		}
//
//		new MatsimEventsReader(events).readFile(eventsFilename);
//
//		if (orms != null) {
//			orms.write(outputBase4analysis + "onRoute.txt");
//			orms.writeCharts(outputBase4analysis);
//		}
//		if (lttms != null) {
//			lttms.write(outputBase4analysis + "legtraveltimes.txt.gz");
//			lttms.writeCharts(outputBase4analysis + "legtraveltimes");
//		}
//		clas.write(outputBase4analysis + "avgSpeed.txt.gz");
//		clas.writeChart(outputBase4analysis + "avgSpeedCityArea.png");
//		ld.write(outputBase4analysis + "legDistances.txt.gz");
//		ld.writeCharts(outputBase4analysis + "legDistances");
//
//		SimpleWriter sw = new SimpleWriter(outputBase4analysis + "output.txt");
//		sw.write("netfile:\t" + netFilename + "\neventsFile:\t"
//				+ eventsFilename + "\noutputpath:\t" + outputBase4analysis
//				+ "\n");
//		if (catl != null) {
//			sw.write("avg. Trip length:\t" + catl.getAverageTripLength()
//					+ " [m]\n");
//		}
//		sw.write("traffic performance (car):\t" + ctpf.getTrafficPerformance()
//				+ " [Pkm]\n");
//		sw.write("avg. speed of the total network (car):\t"
//				+ cas.getNetAvgSpeed() + " [km/h]\n");
//		sw.close();
//
//		if (dd != null) {
//			dd.write(outputBase4analysis);
//		}
//		if (dert != null) {
//			dert.write(outputBase4analysis);
//		}
//		if (ms != null) {
//			ms.write(outputBase4analysis);
//		}
//		System.out.println("done.");
//	}
//
//	public static void run(final String[] args) {
//		runIntern(args, "normal");
//	}
//
//	public static void runScenario(final String[] args, String scenario) {
//		runIntern(args, scenario);
//	}
//
//	public static void runTollScenario(String[] args, String scenario) {
//		withToll = true;
//		runIntern(args, scenario);
//	}
//
//	/**
//	 * @param args
//	 */
//	public static void main(final String[] args) {
//		if (args.length < 3) {
//			printUsage();
//			System.exit(0);
//		} else if (args[0].equals(MUNICH)) {
//			runScenario(args, MUNICH);
//		} else if (args[0].equals(ONLY_MUNICH)) {
//			runTollScenario(args, ONLY_MUNICH);
//		} else {
//			run(args);
//		}
//	}
//}
