/* *********************************************************************** *
 * project: org.matsim.*
 * PersonFileParser.java
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

package playground.telaviv.population;

import java.io.BufferedReader;
import java.util.Map;
import java.util.TreeMap;

import org.matsim.core.utils.io.IOUtils;
import org.matsim.core.utils.misc.Counter;

public class PersonFileParser {

	private String inFile;
	private String separator;
	private boolean skipHeader;
	
	public PersonFileParser(String inFile, String separator, boolean skipHeader) {
		this.inFile = inFile;
		this.separator = separator;
		this.skipHeader = skipHeader;
	}
	
	public Map<Integer, ParsedPerson> readFile() throws Exception {
		Map<Integer, ParsedPerson> persons = new TreeMap<Integer, ParsedPerson>();
		
	    BufferedReader br = null;

	    br = IOUtils.getBufferedReader(inFile);
			
	    // skip first Line
	    if (skipHeader) br.readLine();
	    
	    Counter counter = new Counter("# lines parsed from the population input file: ");
	    String line;
	    while((line = br.readLine()) != null) {
	    	ParsedPerson emme2Person = new ParsedPerson();
	    	
	    	String[] cols = line.split(separator);
	    	
	    	emme2Person.NEWID = parseInteger(cols[0]);
	    	emme2Person.PERSONID = parseInteger(cols[1]);
	    	emme2Person.FACTOR = parseDouble(cols[2]);
	    	emme2Person.AGE = parseInteger(cols[3]);
	    	emme2Person.GENDER = parseInteger(cols[4]);
	    	emme2Person.STUDY = parseInteger(cols[5]);
	    	emme2Person.YRSTUDY = parseInteger(cols[6]);
	    	emme2Person.ECONBRCH = parseInteger(cols[7]);
	    	emme2Person.HHID = parseInteger(cols[8]);
	    	emme2Person.NUMVEH = parseInteger(cols[9]);
	    	emme2Person.GENAGE = parseInteger(cols[10]);
	    	emme2Person.HHSIZE = parseInteger(cols[11]);
	    	emme2Person.HHWORKERS = parseInteger(cols[12]);
	    	emme2Person.MENLICENY = parseInteger(cols[13]);
	    	emme2Person.WOMLICENY = parseInteger(cols[14]);
	    	emme2Person.HHLICENSES = parseInteger(cols[15]);
	    	emme2Person.HHEDU14 = parseInteger(cols[16]);
	    	emme2Person.CODE = parseInteger(cols[17]);
	    	emme2Person.TAZH = parseInteger(cols[18]);
	    	emme2Person.WORKSTA = parseInteger(cols[19]);
	    	emme2Person.LICENSE = parseInteger(cols[20]);
	    	emme2Person.NUMCHILD = parseInteger(cols[21]);
	    	emme2Person.MAINACTPRI = parseInteger(cols[22]);
	    	emme2Person.PRIMCTOD = parseInteger(cols[23]);
	    	emme2Person.TAZDPR = parseInteger(cols[24]);
	    	emme2Person.MAINMODPR = parseInteger(cols[25]);
	    	emme2Person.INTSTOPPR = parseInteger(cols[26]);
	    	emme2Person.INTACTBP = parseInteger(cols[27]);
	    	emme2Person.INTACTAP = parseInteger(cols[28]);
	    	emme2Person.TAZBPR = parseInteger(cols[29]);
	    	emme2Person.TAZAPR = parseInteger(cols[30]);
	    	emme2Person.SWMODPR = parseInteger(cols[31]);
	    	emme2Person.MAINACTSEC = parseInteger(cols[32]);
	    	emme2Person.MAINMODSE = parseInteger(cols[33]);
	    	emme2Person.TAZDSEC = parseInteger(cols[34]);
	    	emme2Person.SECCTOD = parseInteger(cols[35]);
	    	emme2Person.INTSTOPSEC = parseInteger(cols[36]);
	    	emme2Person.TAZBSEC = parseInteger(cols[37]);
	    	emme2Person.TAZASEC = parseInteger(cols[38]);
	    	
	    	emme2Person.START_1 = parseDouble(cols[39]);
	    	emme2Person.DUR_1_BEF = parseDouble(cols[40]);
	    	emme2Person.DUR_1_MAIN = parseDouble(cols[41]);
	    	emme2Person.DUR_1_AFT = parseDouble(cols[42]);
	    	emme2Person.START_2 = parseDouble(cols[43]);
	    	emme2Person.DUR_2_BEF = parseDouble(cols[44]);
	    	emme2Person.DUR_2_MAIN = parseDouble(cols[45]);
	    	emme2Person.DUR_2_AFT = parseDouble(cols[46]);
	    	
	    	persons.put(emme2Person.PERSONID, emme2Person);
	    	counter.incCounter();
	    }
	    counter.printCounter();	    
	    br.close();
	    
		return persons;
	}
	
	private int parseInteger(String string) {
		if (string == null) return 0;
		else if (string.trim().equals("")) return 0;
		else return Integer.valueOf(string);
	}
	
	private double parseDouble(String string) {
		if (string == null) return 0.0;
		else if (string.trim().equals("")) return 0.0;
		else return Double.valueOf(string);
	}
}