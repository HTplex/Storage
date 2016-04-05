/* *********************************************************************** *
 * project: org.matsim.*
 * DeriveListOfFilesToBeFiltered.java
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
package playground.scnadine.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;

public class DeriveListOfFilesToBeFiltered {

	static File [] allFiles;

	public static void main(String[] args) throws IOException {

		Config config = ConfigUtils.loadConfig(args[0]);
		final String CONFIG_MODULE = "Tools";
		System.out.println(config.getModule(CONFIG_MODULE));

		File sourcedir = new File(config.findParam(CONFIG_MODULE,"sourcedir"));
		allFiles = sourcedir.listFiles();

		File targetFile = new File(config.findParam(CONFIG_MODULE,"targetFile"));

		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(targetFile));
			for (File file : allFiles){
				String[] fileName = file.getName().split("_");
				String personId = fileName[1];
				out.write(personId+"\n");
			}
			out.close();
		} catch (IOException e) {
			System.out.println("Error while writing PersonsToBeFiltered.");
		}
		System.out.println("Done writing file filter.");
	}

}
