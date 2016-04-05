/* *********************************************************************** *
 * project: org.matsim.*
 * PlanComparator.java
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

package playground.dgrether.analysis.population;

import playground.dgrether.DgPaths;
import playground.dgrether.analysis.io.DgAnalysisPopulationReader;


/**
 * This Class is able to compare two plan files of different iterations. 
 * The arguments needed to run
 * this tool will be described when called without arguments.
 *
 * @author dgrether
 *
 */
public class PlanComparator {

	public PlanComparator() {}

	private static void printHelp() {
		// String ls = System.getProperty("line.separator");
		System.out.println("This tool needs three or optional four arguments: ");
		System.out.println("1. the path to the network file (mandatory)");
		System.out
				.println("2. the path to the population of the first iteration (mandatory)");
		System.out
				.println("3. the path to the population of the second iteration (mandatory)");
		System.out.println("4. the path for the output file (optional)");
		System.out
				.println("If no output path is given, output is written to stdout");
	}

	/**
   * Should be called with 3 arguments, each of them a path to a file: 1. the
   * config file containing the world and the network 2. the first plan file 3.
   * the second plan file
   *
   * @param args
   */
	public static void main(String[] args) {
		String runNumber1 = "749";
		String runNumber2 = "869";
		String netfile = DgPaths.RUNBASE + "run" +  runNumber1.toString() + "/" + runNumber1.toString() + "." + "output_network.xml.gz";
		String plans1file = DgPaths.RUNBASE + "run" +runNumber1.toString() + "/" + runNumber1.toString()  + "." + "output_plans.xml.gz";
		String plans2file = DgPaths.RUNBASE + "run" +runNumber2.toString() + "/" + runNumber2.toString()  + "." + "output_plans.xml.gz";
		args = new String[4];
		args[0] = netfile;
		args[1] = plans1file;
		args[2] = plans2file;
		args[3] = DgPaths.RUNBASE + "run" +runNumber2.toString() + "/" + runNumber1.toString() + "vs" + runNumber2.toString()+ "plansCompare.txt";
		DgAnalysisPopulation pop = new DgAnalysisPopulation();
		DgAnalysisPopulationReader reader = new DgAnalysisPopulationReader();
		reader.readAnalysisPopulation(pop, runNumber1, netfile, args[1]);
		reader.readAnalysisPopulation(pop, runNumber2, netfile, args[2]);
		if (args.length == 3) {
			System.out.println(new PlanComparisonStringWriter(pop, runNumber1, runNumber2).getResult());
		}
		else if (args.length == 4) {
			new PlanComparisonFileWriter(pop).write(args[3], runNumber1, runNumber2);
		}
		else
			printHelp();

	}

}
