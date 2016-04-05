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

package playground.johannes.gsv.synPop.mid.run;

import java.util.Collection;
import java.util.List;

import playground.johannes.gsv.synPop.ProxyPerson;
import playground.johannes.gsv.synPop.ProxyPlan;
import playground.johannes.gsv.synPop.ProxyPlanTask;
import playground.johannes.gsv.synPop.ProxyPlanTaskFactory;
import playground.johannes.sna.util.ProgressLogger;
import playground.johannes.socialnetworks.utils.CollectionUtils;

/**
 * @author johannes
 *
 */
public class ConcurrentProxyTaskRunner {

	public static void run(ProxyPlanTaskFactory factory, Collection<ProxyPerson> persons, int numThreads) {
		/*
		 * split collection in approx even segments
		 */
		int n = Math.min(persons.size(), numThreads);
		final List<ProxyPerson>[] segments = CollectionUtils.split(persons, n);
		/*
		 * create threads
		 */
		ProgressLogger.init(persons.size(), 1, 10);
		Thread[] threads = new Thread[numThreads];
		for(int i = 0; i < numThreads; i++) {
			final ProxyPlanTask task = factory.getInstance();
			final List<ProxyPerson> subPersons = segments[i];
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(ProxyPerson p : subPersons) {
						for(ProxyPlan plan : p.getPlans())
							task.apply(plan);
						
						ProgressLogger.step();
					}
					
				}
			});
			
			threads[i].start();
		}
		/*
		 * wait for threads
		 */
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ProgressLogger.termiante();
	}
}
