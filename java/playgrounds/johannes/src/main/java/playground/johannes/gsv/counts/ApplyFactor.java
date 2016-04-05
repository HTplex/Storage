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

package playground.johannes.gsv.counts;

import org.matsim.counts.Count;
import org.matsim.counts.Counts;
import org.matsim.counts.CountsReaderMatsimV1;
import org.matsim.counts.CountsWriter;

/**
 * @author johannes
 * 
 */
public class ApplyFactor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Counts counts = new Counts();
		CountsReaderMatsimV1 reader = new CountsReaderMatsimV1(counts);
		reader.parse("/home/johannes/gsv/counts/counts.2013.net20140909.5.xml");

		Counts newCounts = new Counts();
		newCounts.setDescription(counts.getDescription());
		newCounts.setName(counts.getName());
		newCounts.setYear(counts.getYear());

		for (Count count : counts.getCounts().values()) {
			if (count.getVolume(1).getValue() != 0) {
				Count newCount = newCounts.createAndAddCount(count.getLocId(), count.getCsId());
				for (int i = 1; i < 25; i++) {
					newCount.createVolume(i, count.getVolume(i).getValue() / 24.0);
				}
				newCount.setCoord(count.getCoord());
			}
		}

		CountsWriter writer = new CountsWriter(newCounts);
		writer.write("/home/johannes/gsv/counts/counts.2013.net20140909.5.24h.xml");
	}

}
