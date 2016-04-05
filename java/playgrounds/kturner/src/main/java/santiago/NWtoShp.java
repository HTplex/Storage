package santiago;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.LinkImpl;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.NodeImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.geotools.MGC;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.core.utils.gis.PointFeatureFactory;
import org.matsim.core.utils.gis.PolylineFeatureFactory;
import org.matsim.core.utils.gis.ShapeFileWriter;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import com.vividsolutions.jts.geom.Coordinate;

public class NWtoShp {

	/**
	 * @author: Kturner
	 */


	
	private static final String INPUT_DIR = "F:/Arbeit-VSP/Santiago/Kai_und_Daniel/inputFiles/";
	private static final String OUTPUT_DIR = "F:/Arbeit-VSP/Santiago/Kai_und_Daniel/Visualisierungen/";


	//Dateinamen ohne XML-Endung
	private static final String NETFILE_NAME = "santiago_tertiary" ;
	//Ende  Namesdefinition Berlin

	
	private static final String NETFILE = INPUT_DIR + NETFILE_NAME + ".xml.gz" ;

	public static void main(String[] args) {
		createDir(new File(OUTPUT_DIR));

		//Network-Stuff
		Config config = ConfigUtils.createConfig();
		config.network().setInputFile(NETFILE);
		Scenario scenario = ScenarioUtils.loadScenario(config);

//		network = convertCoordinates(network, "EPSG:", "EPSG:"); //(network, fromCS, toCS ("EPSG:...")
		convertNet2Shape(scenario.getNetwork(), OUTPUT_DIR); 
		calcMinMaxCoord(scenario.getNetwork());

		System.out.println("### ENDE ###");
	}




	private static void calcMinMaxCoord(Network network) {
		double[] box = NetworkUtils.getBoundingBox(network.getNodes().values());
		System.out.println("minX "+ box[0]);
		System.out.println("minY "+ box[1]);
		System.out.println("maxX "+ box[2]);
		System.out.println("maxY "+ box[3]);
	}




	//NW Step1: Convert Matsim-Network to Shap-File.
	private static void convertNet2Shape(Network network, String outputDir){


		CoordinateReferenceSystem crs = MGC.getCRS("EPSG:32719");  //Santiago

		
		Collection<SimpleFeature> features = new ArrayList<SimpleFeature>();
		PolylineFeatureFactory linkFactory = new PolylineFeatureFactory.Builder().
				setCrs(crs).
				setName("link").
				addAttribute("ID", String.class).
				addAttribute("fromID", String.class).
				addAttribute("toID", String.class).
				addAttribute("length", Double.class).
				addAttribute("type", String.class).
				addAttribute("capacity", Double.class).
				addAttribute("freespeed", Double.class).
				create();

		for (Link link : network.getLinks().values()) {
			Coordinate fromNodeCoordinate = new Coordinate(link.getFromNode().getCoord().getX(), link.getFromNode().getCoord().getY());
			Coordinate toNodeCoordinate = new Coordinate(link.getToNode().getCoord().getX(), link.getToNode().getCoord().getY());
			Coordinate linkCoordinate = new Coordinate(link.getCoord().getX(), link.getCoord().getY());
			SimpleFeature ft = linkFactory.createPolyline(new Coordinate [] {fromNodeCoordinate, linkCoordinate, toNodeCoordinate},
					new Object [] {link.getId().toString(), link.getFromNode().getId().toString(),link.getToNode().getId().toString(), link.getLength(), ((LinkImpl)link).getType(), link.getCapacity(), link.getFreespeed()}, null);
			features.add(ft);
		}   
		ShapeFileWriter.writeGeometries(features, outputDir + NETFILE_NAME + "_Links.shp");


		features.clear();

		PointFeatureFactory nodeFactory = new PointFeatureFactory.Builder().
				setCrs(crs).
				setName("nodes").
				addAttribute("ID", String.class).
				create();

		for (Node node : network.getNodes().values()) {
			SimpleFeature ft = nodeFactory.createPoint(node.getCoord(), new Object[] {node.getId().toString()}, null);
			features.add(ft);
		}
		ShapeFileWriter.writeGeometries(features, outputDir + NETFILE_NAME + "_Nodes.shp");
	}

	private static Network convertCoordinates(Network net, String fromCS, String toCS){

		CoordinateTransformation ct = TransformationFactory.getCoordinateTransformation(fromCS, toCS);

		for(Node node : net.getNodes().values()){
			Coord newCoord = ct.transform(node.getCoord());
			((NodeImpl)node).setCoord(newCoord);
		}

		return net;
	}


	private static void createDir(File file) {
		System.out.println("Verzeichnis " + file + " erstellt: "+ file.mkdirs());	
	}

}
