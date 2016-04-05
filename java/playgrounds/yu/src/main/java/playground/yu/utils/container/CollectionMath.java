/* *********************************************************************** *
 * project: org.matsim.*
 * CollectionSum.java
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

/**
 *
 */
package playground.yu.utils.container;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yu
 *
 */
public class CollectionMath {
	public static double getSum(Collection<? extends Number> clt) {
		double sum = 0.0;
		int idx = 0;
		for (Iterator<Number> ir = (Iterator<Number>) clt.iterator(); ir
				.hasNext();) {
			Number n = ir.next();
			if (n == null) {
				throw new RuntimeException("The\t" + idx
						+ ". element is a NULL element!!");
			}
			sum += n.doubleValue();
			idx++;
		}
		return sum;
	}

	// public static double getSum(Collection<Integer> clt) {
	// double sum = 0.0;
	// for (Iterator<Integer> ir = clt.iterator(); ir.hasNext();)
	// sum += ir.next();
	// return sum;
	// }

	public static double getSum(final double[] array) {
		if (array == null || array.length == 0) {
			throw new RuntimeException(
					"WARNING: This is an EMPTY or a NULL array");
			// return -1;
		}
		double sum = 0;
		for (double element : array) {
			sum += element;
		}
		return sum;
	}

	public static double getSum(final int[] array) {
		if (array == null || array.length == 0) {
			throw new RuntimeException(
					"WARNING: This is an EMPTY or a NULL array");
		}
		double sum = 0;
		for (double element : array) {
			sum += element;
		}
		return sum;
	}

	public static double getAvg(Collection<? extends Number> clt) {
		return getSum(clt) / clt.size();
	}

	public static double getAvg(final double[] array) {
		return getSum(array) / array.length;
	}

	public static double getAvg(final int[] array) {
		return getSum(array) / array.length;
	}

	public static void main(String[] args) {
		Set<Double> dbs = new HashSet<Double>();
		for (int i = 0; i < 10; i++) {
			dbs.add(Math.random());
		}
		System.out.println("sum=" + getSum(dbs));
	}
}
