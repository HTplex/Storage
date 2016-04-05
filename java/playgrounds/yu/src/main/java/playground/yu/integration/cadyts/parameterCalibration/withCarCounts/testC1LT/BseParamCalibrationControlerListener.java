/* *********************************************************************** *
 * project: org.matsim.*
 * BsePCControlerListenerImpl.java
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

/**
 *
 */
package playground.yu.integration.cadyts.parameterCalibration.withCarCounts.testC1LT;

import org.matsim.analysis.VolumesAnalyzer;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.controler.listener.ControlerListener;
import org.matsim.core.network.LinkImpl;

import playground.yu.integration.cadyts.CalibrationConfig;
import playground.yu.integration.cadyts.parameterCalibration.withCarCounts.mnlValidation.CadytsChoice;
import cadyts.measurements.SingleLinkMeasurement.TYPE;
import cadyts.supply.SimResults;

/**
 * @author yu
 * 
 */
public abstract class BseParamCalibrationControlerListener implements
		ControlerListener, CalibrationConfig {
	protected class SimResultsContainerImpl implements SimResults<Link> {
		/***/
		private static final long serialVersionUID = 1L;

		public SimResultsContainerImpl() {
		}

		@Override
		public double getSimValue(final Link link, final int startTime_s,
				final int endTime_s, final TYPE type) {
			int hour = startTime_s / 3600;
			int[] values = volumes.getVolumesForLink(link.getId());
			if (values == null) {
				return 0;
			}
			return values[hour] * countsScaleFactor;
		}
	}

	// protected boolean watching = false;
	protected VolumesAnalyzer volumes = null;
	protected double countsScaleFactor = 1d, distanceFilter = 0d;
	protected Coord distanceFilterCenterNodeCoord = null;
	protected CPC1<Link> calibrator = null;
	protected SimResults<Link> resultsContainer = null;

	protected CadytsChoice chooser;

	protected int writeLinkUtilOffsetsInterval = 100;

	public boolean isInRange(final Id linkid, final Network net) {
		if (distanceFilterCenterNodeCoord == null) {
			return true;
		}

		Link l = net.getLinks().get(linkid);
		if (l == null) {
			System.out.println("Cannot find requested link: "
					+ linkid.toString());
			return false;
		}
		return ((LinkImpl) l).calcDistance(distanceFilterCenterNodeCoord) < distanceFilter;
	}
}
