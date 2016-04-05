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

package playground.johannes.gsv.matrices.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.wololo.geojson.Feature;
import org.wololo.geojson.GeoJSON;
import org.wololo.geojson.GeoJSONFactory;
import org.wololo.geojson.Geometry;
import org.wololo.jts2geojson.GeoJSONWriter;

import playground.johannes.gsv.sim.cadyts.ODUtils;
import playground.johannes.gsv.zones.KeyMatrix;
import playground.johannes.gsv.zones.MatrixOperations;
import playground.johannes.gsv.zones.Zone;
import playground.johannes.gsv.zones.ZoneCollection;
import playground.johannes.gsv.zones.io.KeyMatrixXMLReader;
import playground.johannes.socialnetworks.gis.WGS84DistanceCalculator;
import playground.johannes.socialnetworks.utils.CollectionUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

/**
 * @author johannes
 * 
 */
public class OriginDiffPlot {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		KeyMatrixXMLReader reader = new KeyMatrixXMLReader();
		reader.setValidating(false);
		reader.parse("/home/johannes/sge/prj/matsim/run/874/output/nuts3/miv.sym.xml");
		KeyMatrix simMatrix = reader.getMatrix();

		reader.parse("/home/johannes/gsv/miv-matrix/refmatrices/tomtom.de.xml");
		KeyMatrix refMatrix = reader.getMatrix();

		ZoneCollection zones = ZoneCollection.readFromGeoJSON("/home/johannes/gsv/gis/nuts/ger/geojson/de.nuts3.json", "gsvId");

		ODUtils.cleanDistances(refMatrix, zones, 100000, WGS84DistanceCalculator.getInstance());
		// ODUtils.cleanDistances(simMatrix, zones, 100000,
		// WGS84DistanceCalculator.getInstance());
		double c = ODUtils.calcNormalization(refMatrix, simMatrix);
		MatrixOperations.applyFactor(refMatrix, c);

//		String originId = "6412";
		String originId = "2000";

		Map<String, Double> errors = new HashMap<>();
		Map<String, Double> volumes = new HashMap<>();

		Set<String> keys = simMatrix.keys();
		for (String key : keys) {
			Double refVol = refMatrix.get(originId, key);
			if (refVol == null)
				refVol = 0.0;

			Double simVol = simMatrix.get(originId, key);
			if (simVol == null)
				simVol = 0.0;

			double err = Math.abs((simVol - refVol) / refVol);
			if (!Double.isNaN(err) && !Double.isInfinite(err)) {
				errors.put(key, err);
				volumes.put(key, simVol);
			}
		}

		errors = CollectionUtils.sortByValue(errors);
		volumes = CollectionUtils.sortByValue(volumes, true);
		
		GeometryFactory factory = JTSFactoryFinder.getGeometryFactory();
		GeoJSONWriter jsonWriter = new GeoJSONWriter();
		List<Feature> features = new ArrayList<>();
		int maxRelations = 50;
		int cnt = 0;
		Zone zi = zones.get(originId);
		
//		for (Map.Entry<String, Double> entry : errors.entrySet()) {
		for (Map.Entry<String, Double> entry : volumes.entrySet()) {
		
			Zone zj = zones.get(entry.getKey());
			if (zj != null) {

				Coordinate[] coords = new Coordinate[2];
				coords[0] = zi.getGeometry().getCentroid().getCoordinate();
				coords[1] = zj.getGeometry().getCentroid().getCoordinate();

				LineString line = factory.createLineString(coords);
				GeoJSON json = jsonWriter.write(line);
				Geometry geom = (Geometry) GeoJSONFactory.create(json.toString());

				Map<String, Object> atts = new HashMap<>();
//				atts.put("error", entry.getValue());
				atts.put("error", errors.get(entry.getKey()));
				atts.put("volume", volumes.get(entry.getKey()));

				features.add(new Feature(geom, atts));

				cnt++;
				if (cnt >= maxRelations) {
					break;
				}
			}
		}

		Files.write(Paths.get("/home/johannes/gsv/miv-matrix/doc/final/odplot-2000.json"), jsonWriter.write(features).toString().getBytes(),
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

}
