/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2014 by the members listed in the COPYING,        *
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

package playground.johannes.gsv.synPop.analysis;

import gnu.trove.TDoubleDoubleHashMap;
import gnu.trove.TIntIntHashMap;
import gnu.trove.TObjectDoubleHashMap;
import gnu.trove.TObjectIntHashMap;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import playground.johannes.gsv.synPop.CommonKeys;
import playground.johannes.gsv.synPop.ProxyPerson;
import playground.johannes.gsv.synPop.ProxyPlan;
import playground.johannes.sna.util.TXTWriter;

/**
 * @author johannes
 *
 */
public class ActivityChainTask extends AnalyzerTask {

	public static final String KEY = "n.act";
	
	@Override
	public void analyze(Collection<ProxyPerson> persons, Map<String, DescriptiveStatistics> results) {
		TObjectDoubleHashMap<String> chains = new TObjectDoubleHashMap<String>();
		
		TObjectIntHashMap<String> typeCount = new TObjectIntHashMap<String>();
		
		TDoubleDoubleHashMap tripCounts = new TDoubleDoubleHashMap();
		
		for(ProxyPerson person : persons) {
			ProxyPlan plan = person.getPlan();
			
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < plan.getActivities().size(); i++) {
				String type = (String) plan.getActivities().get(i).getAttribute(CommonKeys.ACTIVITY_TYPE);
				
				typeCount.adjustOrPutValue(type, 1, 1);
				
				builder.append(type);
				builder.append("-");
			}
			
			String chain = builder.toString();
			chains.adjustOrPutValue(chain, 1, 1);
			
			tripCounts.adjustOrPutValue(plan.getLegs().size(), 1, 1);
		}
		
		for(Object key : typeCount.keys()) {
			DescriptiveStatistics stats = new DescriptiveStatistics();
			stats.addValue(typeCount.get((String) key));
			results.put(String.format("%s.%s", KEY, key), stats);
		}

		if (outputDirectoryNotNull()) {
			try {
				TXTWriter.writeMap(chains, "chain", "n", getOutputDirectory() + "/actchains.txt", true);
				
				TXTWriter.writeMap(tripCounts, "nTrips", "n", getOutputDirectory() + "/tripcounts.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
