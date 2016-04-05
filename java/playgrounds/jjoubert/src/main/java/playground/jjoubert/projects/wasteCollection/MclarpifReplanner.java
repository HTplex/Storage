/* *********************************************************************** *
 * project: org.matsim.*
 * ConvertOsmToMatsim.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2009 by the members listed in the COPYING,        *
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

/**
 * 
 */
package playground.jjoubert.projects.wasteCollection;

import org.apache.log4j.Logger;
import org.matsim.core.controler.events.ReplanningEvent;
import org.matsim.core.controler.listener.ReplanningListener;

import javax.inject.Singleton;

/**
 *
 * @author jwjoubert
 */
@Singleton
public class MclarpifReplanner implements ReplanningListener {
	final private Logger log = Logger.getLogger(MclarpifReplanner.class);
	@Override
	public void notifyReplanning(ReplanningEvent event) {
		// TODO Auto-generated method stub
		log.warn(" ==> Do your replanning magic, MCLARPIF!");
	}
}
