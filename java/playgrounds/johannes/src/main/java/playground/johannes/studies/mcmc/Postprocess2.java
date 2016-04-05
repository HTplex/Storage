/* *********************************************************************** *
 * project: org.matsim.*
 * Postprocess.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2011 by the members listed in the COPYING,        *
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
package playground.johannes.studies.mcmc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigReader;

/**
 * @author illenberger
 *
 */
public class Postprocess2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String rootDir = "/Volumes/cluster.math.tu-berlin.de/net/ils/jillenberger/socialnets/mcmc/runs/run334/";
		String statsPath = "20000000000/topo/statistics.txt";
		String statsPath2 = "20000000000/topo/statistics.txt";
//		String key = "r_age";
		String key1 = "c_local";
//		String key1 = "r_age";
//		String key1 = "r_k";
		String key2 = "n_vertex";
		
//		String thetaKey = "theta_gender";
		String thetaKey = "theta_age";
//		String thetaKey = "theta_distance";
		
//		TDoubleObjectHashMap<TDoubleDoubleHashMap> table = new TDoubleObjectHashMap<TDoubleDoubleHashMap>();
//		TDoubleDoubleHashMap values = new TDoubleDoubleHashMap();
		KeyMatrix<Double> matrix = new KeyMatrix<Double>();
		
		Config config = new Config();
		ConfigReader configReader = new ConfigReader(config);
		
		File root = new File(rootDir + "/output/");
		for(String runDir : root.list()) {
			String statsFileName = String.format("%1$s/output/%2$s/%3$s", rootDir, runDir, statsPath);
			String statsFileName2 = String.format("%1$s/output/%2$s/%3$s", rootDir, runDir, statsPath2);
			File statsFile = new File(statsFileName);
			if(statsFile.exists()) {
				/*
				 * read value
				 */
				BufferedReader reader = new BufferedReader(new FileReader(statsFile));
				double val1 = Double.NaN;
				double val2 = Double.NaN;
				String line = null;
				while((line = reader.readLine()) != null) {
					String tokens[] = line.split("\t");
					if(tokens[0].equals(key1)) {
						val1 = Double.parseDouble(tokens[1]);
					}// else if(tokens[0].equals(key2)) {
//						val2 = Double.parseDouble(tokens[1]);
//					}
				}
				/*
				 * 
				 */
				reader = new BufferedReader(new FileReader(statsFileName2));
				while((line = reader.readLine()) != null) {
					String tokens[] = line.split("\t");
					if(tokens[0].equals(key2)) {
						val2 = Double.parseDouble(tokens[1]);
					}
				}
				/*
				 * get theta param
				 */		
				configReader.readFile(String.format("%1$s/output/%2$s/config.xml", rootDir, runDir));
				double theta = Double.parseDouble(config.getParam("ergm", thetaKey));
				/*
				 * 
				 */
				matrix.putValue(val1, -theta, val2);
			} else {
				System.err.println(String.format("No stats file: %1$s", statsFileName));
			}
		}
		
		matrix.write(String.format("%1$s/analysis/c_local.txt", rootDir));
	}

}
