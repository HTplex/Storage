/* *********************************************************************** *
 * project: org.matsim.*
 * LinkCostOffsets2QGIS.java
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
package playground.yu.integration.cadyts.demandCalibration.withCarCounts.utils.qgis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.utils.collections.Tuple;
import org.matsim.core.utils.geometry.geotools.MGC;
import org.matsim.core.utils.gis.PolygonFeatureFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import playground.yu.utils.qgis.MATSimNet2QGIS;
import playground.yu.utils.qgis.Network2PolygonGraph;
import playground.yu.utils.qgis.X2GraphImpl;
import utilities.misc.DynamicData;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

/**
 * @author yu
 *
 */
public class LinkCostOffsets2QGIS extends MATSimNet2QGIS {
	public static class LinkCostOffsets2PolygonGraph extends X2GraphImpl {
		private final Set<Id<Link>> linkIds;
		private PolygonFeatureFactory.Builder factoryBuilder;

		public LinkCostOffsets2PolygonGraph(final Network network,
				final CoordinateReferenceSystem crs, final Set<Id<Link>> linkIds) {
			this.network = network;
			this.crs = crs;
			this.linkIds = linkIds;
			geofac = new GeometryFactory();
			features = new ArrayList<SimpleFeature>();
			this.factoryBuilder = new PolygonFeatureFactory.Builder().
					setCrs(crs).
					setName("link").
					addAttribute("ID", String.class);
		}

		@Override
		public Collection<SimpleFeature> getFeatures() {
			for (int i = 0; i < attrTypes.size(); i++) {
				Tuple<String, Class<?>> att = attrTypes.get(i);
				factoryBuilder.addAttribute(att.getFirst(), att.getSecond());
			}
			PolygonFeatureFactory factory = factoryBuilder.create();
			for (Id<Link> linkId : linkIds) {
				Link link = network.getLinks().get(linkId);
				LinearRing lr = getLinearRing(link);
				Polygon p = new Polygon(lr, null, geofac);
				MultiPolygon mp = new MultiPolygon(new Polygon[] { p }, geofac);
				int size = 1 + parameters.size();
				Object[] o = new Object[size];
				o[0] = link.getId().toString();
				for (int i = 0; i < parameters.size(); i++) {
					o[i + 1] = parameters.get(i).get(link.getId());
				}
				SimpleFeature ft = factory.createPolygon(mp, o, null);
				features.add(ft);
			}
			return features;
		}

		@Override
		protected double getLinkWidth(final Link link) {
			Double i = (Double) parameters.get(0).get(link.getId()) * 250d;
			return Math.max(i, 50d);
		}
	}

	private int countsStartTime = 1, countsEndTime = 24;
	private List<Map<Id<Link>, Double>> linkCostOffsetsAbsoluts = null;
	private List<Map<Id<Link>, Integer>> linkCostOffsetsSigns = null;

	public LinkCostOffsets2QGIS(final int countsStartTime,
			final int countsEndTime, String netFilename, String crs) {
		super(netFilename, crs);
		this.countsStartTime = countsStartTime;
		this.countsEndTime = countsEndTime;
	}

	protected LinkCostOffsets2QGIS() {
	}

	public LinkCostOffsets2QGIS(Network network, String coordinateSystem,
			int countsStartTime, int countsEndTime) {
		((ScenarioImpl) scenario).setNetwork(network);
		crs = MGC.getCRS(coordinateSystem);
		n2g = new Network2PolygonGraph(scenario.getNetwork(), crs);
		this.countsStartTime = countsStartTime;
		this.countsEndTime = countsEndTime;
	}

	public void createLinkCostOffsets(final Collection<Link> links,
			final DynamicData<Link> linkCostOffsets) {
		linkCostOffsetsAbsoluts = new ArrayList<>(countsEndTime
				- countsStartTime + 1);
		linkCostOffsetsSigns = new ArrayList<>(countsEndTime
				- countsStartTime + 1);
		for (int i = 0; i <= countsEndTime - countsStartTime; i++) {
			linkCostOffsetsAbsoluts.add(i, null);
			linkCostOffsetsSigns.add(i, null);
		}
		for (Link link : links) {
			Id<Link> linkId = link.getId();
			for (int i = 0; i <= countsEndTime - countsStartTime; i++) {
				double costOffset = linkCostOffsets.getSum(link, (i
						+ countsStartTime - 1) * 3600,
						(i + countsStartTime) * 3600 - 1);
				Map<Id<Link>, Double> ma = linkCostOffsetsAbsoluts.get(i);
				if (ma == null) {
					ma = new HashMap<>();
					linkCostOffsetsAbsoluts.add(i, ma);
				}
				ma.put(linkId, Math.abs(costOffset)// / flowCapFactor
						);

				Map<Id<Link>, Integer> ms = linkCostOffsetsSigns.get(i);
				if (ms == null) {
					ms = new HashMap<>();
					linkCostOffsetsSigns.add(i, ms);
				}
				ms.put(linkId, (int) Math.signum(costOffset));
			}
		}
	}

	public void setLinkIds(final Set<Id<Link>> linkIds) {
		setN2g(new LinkCostOffsets2PolygonGraph(getNetwork(), crs, linkIds));
	}

	public void output(final Set<Id<Link>> linkIds, final String outputBase) {
		for (int i = 0; i <= countsEndTime - countsStartTime; i++) {
			setLinkIds(linkIds);
			addParameter("costOffset", Double.class, linkCostOffsetsAbsoluts
					.get(i));
			addParameter("sgn", Integer.class, linkCostOffsetsSigns.get(i));
			writeShapeFile(outputBase + (i + countsStartTime) + ".shp");
		}
	}
}
