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

package playground.johannes.gsv.synPop.mid;

import java.util.Map;

import playground.johannes.gsv.synPop.ProxyObject;

/**
 * @author johannes
 *
 */
public class JourneyDestinationHandler implements LegAttributeHandler {

	public static final String DESTINATION = "destination";
	
	public static final String GERMANY = "de";
	/* (non-Javadoc)
	 * @see playground.johannes.gsv.synPop.mid.LegAttributeHandler#handle(playground.johannes.gsv.synPop.ProxyObject, java.util.Map)
	 */
	@Override
	public void handle(ProxyObject leg, Map<String, String> attributes) {
		String dest = attributes.get("p1012");
			
		if("innerhalb Deutschlands".equals(dest)) {
			leg.setAttribute(DESTINATION, GERMANY);
		}

	}

}
