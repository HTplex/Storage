/* *********************************************************************** *
 * project: org.matsim.*
 * ExtractHousholdInfo.java
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
package playground.thibautd.householdsfromcensus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;

import org.matsim.contrib.socnetsim.framework.cliques.Clique;

/**
 * @author thibautd
 */
public class ExtractHousholdInfo {
	private final Map<Id<Clique>, List<Id<Person>>> cliques;
	private static final String FIRST_LINE = "hh_id\tz_id\tf_id\thhtpw\thhtpz\tp_w_list\tp_z_list";
	private static final String SPLIT_EXPR = "\t";
	private static final String LIST_SPLIT_EXPR = ";";
	private static final int HH_LOC  = 0;
	private static final int MEMB_LOC  = 5;

	public ExtractHousholdInfo(String filePath) {
		try {
			//File file = new File(filePath);
			//FileInputStream fis = new FileInputStream(file);
			//BufferedInputStream bis = new BufferedInputStream(fis);
			//DataInputStream dis = new DataInputStream(bis);
			FileReader file = new FileReader(filePath);
			BufferedReader buf = new BufferedReader(file);

			cliques = parse(buf);

			buf.close();
			file.close();
			//dis.close();
			//bis.close();
			//fis.close();
		} catch (Exception e) {
			throw new RuntimeException( e );
		}
	}

	private static Map<Id<Clique>, List<Id<Person>>> parse(BufferedReader buf) {

		String[] currentLine;
		Id<Clique> currentId;
		String[] currentMembers;
		List<Id<Person>> membersList;

		Map<Id<Clique>, List<Id<Person>>> map = new HashMap<Id<Clique>, List<Id<Person>>>();

		try {
			if (!buf.readLine().equals(FIRST_LINE)) {
				throw new IllegalArgumentException("bad file format");
			} else {
					// extract info from files
					while (true) {
						try {
							currentLine = buf.readLine().split(SPLIT_EXPR);
						} catch (NullPointerException e) {
							break;
						}
						currentId = Id.create(currentLine[HH_LOC], Clique.class);
						currentMembers = currentLine[MEMB_LOC].split(LIST_SPLIT_EXPR);
			
						// put info in the specified format
						membersList = new ArrayList<>();
						for (int i=0; i < currentMembers.length; i++) {
							membersList.add(Id.create(currentMembers[i], Person.class));
						}
						map.put(currentId, membersList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public Map<Id<Clique>, List<Id<Person>>> getCliques() {
		return this.cliques;
	}
}

