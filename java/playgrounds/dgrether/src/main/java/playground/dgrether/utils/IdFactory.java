/* *********************************************************************** *
 * project: org.matsim.*
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
package playground.dgrether.utils;

import java.util.List;

import org.matsim.api.core.v01.Id;

/**
 * @author dgrether
 *
 */
public class IdFactory {

	public static <T> void generateIds(int number, List<Id<T>> idList, Class<T> type) {
		for (int i = 1; i <= number; i++) {
			idList.add(Id.create(i, type));
		}
	}
	
}
