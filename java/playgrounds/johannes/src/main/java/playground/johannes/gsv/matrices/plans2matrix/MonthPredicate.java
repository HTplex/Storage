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

package playground.johannes.gsv.matrices.plans2matrix;

import playground.johannes.gsv.synPop.ProxyObject;
import playground.johannes.gsv.synPop.ProxyPerson;
import playground.johannes.gsv.synPop.mid.MIDKeys;

/**
 * @author johannes
 * 
 */
public class MonthPredicate implements Predicate {

	private final String month;

	public MonthPredicate(String month) {
		this.month = month;
	}

	@Override
	public boolean test(ProxyPerson person, ProxyObject leg, ProxyObject prev, ProxyObject next) {
		if (month == null || month.equalsIgnoreCase(person.getAttribute(MIDKeys.PERSON_MONTH))) {
			return true;
		} else {
			return false;
		}
	}

}
