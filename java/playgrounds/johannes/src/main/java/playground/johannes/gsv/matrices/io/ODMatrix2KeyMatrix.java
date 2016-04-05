/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2015 by the members listed in the COPYING,        *
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

package playground.johannes.gsv.matrices.io;

import playground.johannes.gsv.zones.KeyMatrix;
import playground.johannes.gsv.zones.io.KeyMatrixXMLWriter;
import playground.johannes.gsv.zones.io.ODMatrixXMLReader;

/**
 * @author johannes
 *
 */
public class ODMatrix2KeyMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ODMatrixXMLReader reader = new ODMatrixXMLReader();
		reader.setValidating(false);
		reader.parse("/home/johannes/gsv/matrices/refmatrices/itp.xml");
		KeyMatrix m1 = reader.getMatrix().toKeyMatrix("gsvId");
		
		KeyMatrixXMLWriter writer = new KeyMatrixXMLWriter();
		writer.write(m1, "/home/johannes/gsv/matrices/refmatrices/itp.xml");

	}
	
}
