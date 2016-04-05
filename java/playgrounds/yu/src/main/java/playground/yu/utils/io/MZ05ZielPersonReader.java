/* *********************************************************************** *
 * project: org.matsim.*
 * MZ05ZielPersonReader.java
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
package playground.yu.utils.io;

import org.matsim.core.utils.io.tabularFileParser.TabularFileHandler;
import org.matsim.core.utils.io.tabularFileParser.TabularFileParser;
import org.matsim.core.utils.io.tabularFileParser.TabularFileParserConfig;

/**
 * @author yu
 * 
 */
public class MZ05ZielPersonReader implements TabularFileHandler {
	private int zielPersonCnt = 0, zPKantonZurichCnt = 0;
	private double AW_Distanz = 0.0, KantonZurichAW_Distanz = 0.0;
	private SimpleWriter sw = null;

	public MZ05ZielPersonReader(String outputFilename) {
		sw = new SimpleWriter(outputFilename);
		sw.writeln("linearDistance\tlinearDistance [m]\tlinearDistance [km]");
	}

	@Override
	public void startRow(String[] row) {
		String AW_Distanz = row[37];
		if (AW_Distanz != null) {
			double w2hDist = Double.parseDouble(AW_Distanz);
			if (w2hDist > 0) {
				zielPersonCnt++;
				this.AW_Distanz += w2hDist;
				if (row[23] != null) {
					if (row[23].equals("1")) {
						zPKantonZurichCnt++;
						KantonZurichAW_Distanz += w2hDist;
					}
				}
			}
		}
	}

	public void write() {
		double avgAW_Distanz = AW_Distanz / zielPersonCnt;
		double avgKantonZurichAW_Distanz = KantonZurichAW_Distanz
				/ zPKantonZurichCnt;
		sw.writeln("avg. AW_Distanz\t" + avgAW_Distanz + "\t" + avgAW_Distanz
				/ 1000.0);
		sw.writeln("avg. KantonZurichAW_Distanz\t" + avgKantonZurichAW_Distanz
				+ "\t" + avgKantonZurichAW_Distanz / 1000.0);
		sw.writeln("\npersons (work2home legs) :\t" + zielPersonCnt);
		sw.writeln("persons KantonZurich (work2home legs) :\t"
				+ zPKantonZurichCnt);
		sw.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String zielPersonFilename = "D:/fromNB04/Archieve/MikroZensus2005/4_DB_ASCII(Sep_TAB)/Zielpersonen.dat";
		String outputFilename = "../matsimTests/LinearDistance/MZ05linearDistance.txt";

		TabularFileParserConfig tfpc = new TabularFileParserConfig();
		tfpc.setCommentTags(new String[] { "HHNR" });
		tfpc.setDelimiterRegex("\t");
		tfpc.setFileName(zielPersonFilename);

		MZ05ZielPersonReader mz05zpr = new MZ05ZielPersonReader(outputFilename);

		new TabularFileParser().parse(tfpc, mz05zpr);

		mz05zpr.write();
	}

}
