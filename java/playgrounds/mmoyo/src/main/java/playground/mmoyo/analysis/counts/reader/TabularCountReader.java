/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2010 by the members listed in the COPYING,        *
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

package playground.mmoyo.analysis.counts.reader;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.core.utils.io.tabularFileParser.TabularFileHandler;
import org.matsim.core.utils.io.tabularFileParser.TabularFileParser;
import org.matsim.core.utils.io.tabularFileParser.TabularFileParserConfig;
import org.matsim.counts.Count;
import org.matsim.counts.Counts;
import org.matsim.counts.CountsWriter;
import org.matsim.pt.transitSchedule.api.TransitSchedule;
import org.matsim.pt.transitSchedule.api.TransitStopFacility;

import playground.mmoyo.utils.DataLoader;

/**Reads a tabular text with pt counts saved as ANSI. (See the Header structure)**/
public class TabularCountReader implements TabularFileHandler {
	private static final Logger log = Logger.getLogger(TabularCountReader.class);
	private static final String[] HEADER = {"id", "Haltestelle", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
	private final TabularFileParserConfig tabFileParserConfig;
	private boolean isFirstLine = true;
	private int rowNum;
	private Counts counts = new Counts();

	final String EMPTY = "--";
	final String ZERO = "0.0";
	final String POINT = ".";

	private final TransitSchedule transitSchedule;

	public TabularCountReader(final String countName, final TransitSchedule transitSchedule) {
		this.tabFileParserConfig = new TabularFileParserConfig();
		this.tabFileParserConfig.setDelimiterTags(new String[] {"\t"});
		this.transitSchedule = transitSchedule;
		counts.setName(countName);
		counts.setDescription("counts values from BVG 09.2009");
		counts.setYear(2009);
	}

	@Override
	public void startRow(final String[] row) throws IllegalArgumentException {
		if (this.isFirstLine) {
			boolean equalsHeader = true;
			int i = 0;
			for (String s : row) {
				if (!s.equalsIgnoreCase(HEADER[i])){
					equalsHeader = false;
					break;
				}
				i++;
			}
			if (!equalsHeader) {
				log.warn("the structure does not match. The header should be:  ");
				for (String g : HEADER) {
					System.out.print(g + " ");
				}
				System.out.println();
			}
			this.isFirstLine = false;
		}else{
			//for each station
			Id<Link> id =  Id.create(row[0], Link.class);
			counts.createAndAddCount(id, row[1]); //id of station  with index 0     ,   //name of station with index 1
			Count count = counts.getCount(id);
			count.setCoord(this.transitSchedule.getFacilities().get(Id.create(row[0], TransitStopFacility.class)).getCoord());  // look up the coordinate of the stop facility and set it to the count

			int col= 0;
			for (String s : row) {
				if (col>1){
					if (s.equals(EMPTY)){s= ZERO;}
					count.createVolume(col-1, Double.parseDouble(s));
				}
				col++;
			}
			rowNum++;
		}
	}

	public void writeCounts (final String outputFile){
		new CountsWriter(this.counts).write(outputFile);
	}

	public void readFile(final String filename) throws IOException {
		this.tabFileParserConfig.setFileName(filename);
		new TabularFileParser().parse(this.tabFileParserConfig, this);
	}

	public static void main(String[] args) throws IOException {
		String tabularFile = "../playgrounds/mmoyo/output/@counts/1.txt";           //<-change
		TransitSchedule transitSchedule = new DataLoader().readTransitSchedule("../shared-svn/studies/countries/de/berlin-bvg09/pt/nullfall_berlin_brandenburg/input/pt_transitSchedule.xml.gz");
		TabularCountReader countReader = new TabularCountReader("occupancy counts", transitSchedule) ;   //<-set name
		countReader.readFile(tabularFile);
		countReader.writeCounts("../playgrounds/mmoyo/output/@counts/counts.xml");   //<-output file name
		System.out.println("done.");
	}
}