/* *********************************************************************** *
 * project: org.matsim.*
 * GetDateString.java
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

package playground.jjoubert.Utilities;

import java.util.GregorianCalendar;

public class DateString extends GregorianCalendar{

	/**
	 * A small utility class that extends the <code>GregorianCalendar</code> and overwrites
	 * the <code>toString</code> method to return the current time, in the form 
	 * <code>YYYYMMDDHHMMSSsss</code> as a <code>String</code>.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns the date of the class in the form YYYYMMDDHHMMSSsss.
	 */
	@Override
	public String toString(){
		String result = null;
		int year = this.get(YEAR);
		int month = this.get(MONTH)+1; // Seems to be a java thing that month is started at 0... 
		int day = this.get(DAY_OF_MONTH);
		int hour = this.get(HOUR_OF_DAY);
		int minute = this.get(MINUTE);
		int second = this.get(SECOND);
		int millisecond = this.get(MILLISECOND);
			
		result = String.format("%04d%02d%02d%02d%02d%02d%03d", 
				year, month, day,
				hour, minute, second, millisecond);
		return result;
	}
}
