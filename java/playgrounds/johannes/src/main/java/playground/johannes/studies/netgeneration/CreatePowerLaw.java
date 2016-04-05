/* *********************************************************************** *
 * project: org.matsim.*
 * CreatePowerLaw.java
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
package playground.johannes.studies.netgeneration;

import java.io.IOException;

import gnu.trove.TDoubleDoubleHashMap;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import playground.johannes.sna.math.Discretizer;
import playground.johannes.sna.math.FixedSampleSizeDiscretizer;
import playground.johannes.sna.math.Histogram;
import playground.johannes.sna.util.TXTWriter;

/**
 * @author illenberger
 *
 */
public class CreatePowerLaw {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DescriptiveStatistics stats = new DescriptiveStatistics();
	
		TDoubleDoubleHashMap hist = new TDoubleDoubleHashMap();
		for(int i = 1; i < 1000; i++)
//			stats.addValue(Math.pow(i, -2));
			hist.put(i, Math.pow(i, -2));

//		Discretizer d = FixedSampleSizeDiscretizer.create(stats.getValues(), 10);
//		TDoubleDoubleHashMap hist = Histogram.createHistogram(stats, d, true);
		
		TXTWriter.writeMap(hist, "x", "p", "/Users/jillenberger/Desktop/powerlaw/distr.txt");
		
		hist = Histogram.createCumulativeHistogram(hist);
		
		TXTWriter.writeMap(hist, "x", "p", "/Users/jillenberger/Desktop/powerlaw/distr.cum.txt");
	}

}
