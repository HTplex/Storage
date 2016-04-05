package playground.scnadine.converters.osmPT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;

import playground.scnadine.converters.osmCore.OsmParser.OsmWay;

public class TestWaySorting {

	static ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
	static NetworkImpl network =  (NetworkImpl) scenario.getNetwork();
	static Map<Long, OsmWay> waysMap = new HashMap<Long, OsmWay>();
	static Map<Long, List<Id<Link>>> waysToLinks_DirA = new HashMap<Long, List<Id<Link>>>();
	static Map<Long, List<Id<Link>>> waysToLinks_DirB = new HashMap<Long, List<Id<Link>>>();

	private final static Logger log = Logger.getLogger(Osm2TransitLines.class);

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {


		String networkFile = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\chessboard10by10network.xml";
		//		String networkFile = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\chessboard10by10network.xml";

		String wayFile = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\wayDefinition.txt";
		//		String linkFile = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\wayDefinition.txt";

		String waySortingInputFile = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\waySortingInput.txt";
		//		String waySortingInputFile = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\waySortingInput.txt";


		String outputFileLinks = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\waySortingOutputLinks.txt";
		//		String outputFileLinks = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\waySortingOutputLinks.txt";

		String outputFileWays = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\waySortingOutputWays.txt";
		//		String outputFileWays = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\waySortingOutputWays.txt";

		new MatsimNetworkReader(scenario).readFile(networkFile);

		try {
			BufferedReader inWays = new BufferedReader(new InputStreamReader(new FileInputStream(new File(wayFile))));
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(waySortingInputFile))));
			BufferedWriter outWays = new BufferedWriter(new FileWriter(new File(outputFileWays)));
			BufferedWriter outLinks = new BufferedWriter(new FileWriter(new File(outputFileLinks)));



			// read way to link matching
			inWays.readLine();
			String inputLineWays = null;			
			while((inputLineWays = inWays.readLine()) != null) {
				String[] entries = inputLineWays.split("\t");

				long wayId = Long.parseLong(entries[0]);
				OsmWay way = new OsmWay(wayId);

				int i = 1;
				while (!entries[i].equals("-1")) {
					long nodeId = Long.parseLong(entries[i]);
					way.nodes.add(nodeId);
					i++;
				}

				waysMap.put(wayId, way);
				createWayLinks(way);

			}

			log.info("# ways = "+waysMap.size());

			// read relation ways to be sorted
			in.readLine();

			outLinks.write("routeId\tlinks\t-1");
			outWays.write("routeId\tways\t-1");
			outLinks.newLine();
			outWays.newLine();

			String inputLine = null;			
			while((inputLine = in.readLine()) != null) {

				String[] entries = inputLine.split("\t");
				List<Long> unsortedWays = new LinkedList<Long>();
				Map<Long, OsmWay> relationWays = new HashMap<Long, OsmWay>();

				int i = 1; 
				while (!entries[i].equals("-1")) {
					long wayId = Long.parseLong(entries[i]);
					unsortedWays.add(wayId);
					relationWays.put(wayId, waysMap.get(wayId));
					i++;
				}
				log.info("\n\n---------------------------------------");
				log.info("Sorting ways for route "+entries[0]);
				List<Long> sortedWays = new LinkedList<Long>();
				List<Id<Link>> sortedLinks = new LinkedList<Id<Link>>();

				sortWays(sortedWays, sortedLinks, unsortedWays, relationWays);

				//write output
				outWays.write(entries[0]);
				outLinks.write(entries[0]);
				for (long wayId : sortedWays) {
					outWays.write("\t"+wayId);					
				}
				for (Id<Link> linkId : sortedLinks) {
					outLinks.write("\t"+linkId);
				}

				outWays.write("\t-1");				
				outWays.newLine();
				outWays.flush();
				outLinks.write("\t-1");				
				outLinks.newLine();
				outLinks.flush();
			}

			in.close();
			outWays.close();
			outLinks.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		log.info("=======================================================================================");

	}

	private static void sortWays(List<Long> sortedWays, List<Id<Link>> sortedLinks, List<Long> unsortedWays, Map<Long, OsmWay> relationWays) {

		System.out.println("unsorted ways = "+unsortedWays);
		if (unsortedWays.size() > 1) {

			// store links in a Hashmap sorted by the nodes they are connected to
			HashMap<Long, List<Long>> unprocessedNodesAndConnectedWays = new HashMap<Long, List<Long>>();

			for (long wayId : unsortedWays) {

				OsmWay way = relationWays.get(wayId);
				long fromNodeId = way.getStartNode();
				long toNodeId = way.getEndNode();

				Id<Node> fromNode = Id.create(fromNodeId, Node.class);
				Id<Node> toNode = Id.create(toNodeId, Node.class);
				if (network.getNodes().containsKey(fromNode) && network.getNodes().containsKey(toNode)) {
					List<Long> fromNodeWays;
					if (unprocessedNodesAndConnectedWays.containsKey(fromNodeId)) {
						fromNodeWays = unprocessedNodesAndConnectedWays.get(fromNodeId);
					}
					else {
						fromNodeWays = new ArrayList<Long>();
					}			
					fromNodeWays.add(wayId);				
					unprocessedNodesAndConnectedWays.put(fromNodeId, fromNodeWays);

					if (fromNodeId != toNodeId) {
						List<Long> toNodeLinks;
						if (unprocessedNodesAndConnectedWays.containsKey(toNodeId)) {
							toNodeLinks = unprocessedNodesAndConnectedWays.get(toNodeId);
						}
						else {
							toNodeLinks = new ArrayList<Long>();
						}			
						toNodeLinks.add(wayId);				
						unprocessedNodesAndConnectedWays.put(toNodeId, toNodeLinks);
					}
				}
			}

			System.out.println("Node to link map: ");
			for (long nodeId : unprocessedNodesAndConnectedWays.keySet()) {
				System.out.print("Node "+nodeId+": ");
				for (long linkId : unprocessedNodesAndConnectedWays.get(nodeId)) {
					System.out.print(linkId+", ");
				}
				System.out.println();
			}
			System.out.println();


			List<Long> endLoopNodes = new ArrayList<Long>();
			List<Long> intermediateLoopNodes = new ArrayList<Long>();
			List<Long> endNodes = new ArrayList<Long>();
			List<Long> loopWays = new ArrayList<Long>();
			Map<Long, List<Long>> loopWayNodes	= new HashMap<Long, List<Long>>();

			for (long nodeId : unprocessedNodesAndConnectedWays.keySet()) {
				int numberOfConnectedLinks = unprocessedNodesAndConnectedWays.get(nodeId).size();				
				if (numberOfConnectedLinks == 3) {
					endLoopNodes.add(nodeId);
				}
				else if (numberOfConnectedLinks == 4) {
					intermediateLoopNodes.add(nodeId);
				}
				else if (numberOfConnectedLinks == 1) {
					endNodes.add(nodeId);
				}
			}

			for (long wayId : unsortedWays) {
				OsmWay way = waysMap.get(wayId);
				if (way.getStartNode() == way.getEndNode()) {
					loopWays.add(wayId);
					for (long nodeId : way.nodes) {
						List<Long> wayIds = loopWayNodes.get(nodeId);
						if (wayIds == null) {
							wayIds = new ArrayList<Long>(); 
						}
						if (!wayIds.contains(wayId)) {
							wayIds.add(wayId);
						}
						loopWayNodes.put(nodeId, wayIds);
					}
				}
			}

			//if there are loopways: remove any nodes that belong to a loopway from the set of end nodes
			if (!loopWayNodes.isEmpty()) {
				Iterator<Long> it = endNodes.iterator();
				while(it.hasNext()) {
					long nodeId = it.next();
					if (loopWayNodes.keySet().contains(nodeId)) {
						it.remove();
					}
				}
			}

			log.info("# end nodes = "+endNodes.size());
			if (endNodes.size() > 0) {
				log.info("End nodes: "+endNodes);
			}
			log.info("# end loops = "+endLoopNodes.size());
			if (endLoopNodes.size() > 0) {			
				log.info("End loop nodes: "+endLoopNodes);
			}
			log.info("# intermediate loops = "+intermediateLoopNodes.size());
			if (intermediateLoopNodes.size() > 0) {
				log.info("Intermediate loop nodes: "+intermediateLoopNodes);
			}			
			log.info("# loop ways = "+loopWays.size());
			if (loopWays.size() > 0) {
				log.info("Loop ways: "+loopWays);
			}


			// determine a potential start node
			// if there is at least one node with just one connected links: this has to be an end of the route
			// else start with the from node of a random link 
			long startNodeId = 0;
			if (endNodes.size() > 0) {
				startNodeId = endNodes.get(0);
			}
			else {
				startNodeId = relationWays.get(unsortedWays.get(0)).getStartNode();
			}
			developWays(sortedWays, sortedLinks, relationWays, loopWays, loopWayNodes, unprocessedNodesAndConnectedWays, startNodeId);
		}
		else {
			sortedWays.add(unsortedWays.get(0));
			List<Id<Link>> linkIds = translateWayToLinks(sortedWays.get(0), waysMap.get(sortedWays.get(0)).getStartNode()); 
			for (Id<Link> linkId : linkIds) {
				sortedLinks.add(linkId); 
			}
		}	
		System.out.println("sorted ways = "+sortedWays);
		System.out.println("sorted links = "+sortedLinks);

	}



	///////////////////////////////////////////////////////	
	// helper methods
	///////////////////////////////////////////////////////


	private static void developWays(List<Long> sortedWays, List<Id<Link>> sortedLinks, Map<Long, OsmWay> relationWays, List<Long> loopWays, Map<Long, List<Long>> loopWayNodes, HashMap<Long, List<Long>> unprocessedNodesAndConnectedWays, long startNodeId) {

		boolean developFurther = true;

		long nodeId = startNodeId;
		List<Long> loopNodes = new ArrayList<Long>();

		while (developFurther) {

			if (unprocessedNodesAndConnectedWays.isEmpty()) {
				developFurther = false;
				log.info("forward processing finished at node "+nodeId);
				System.out.println();
			}
			else {

				System.out.println("Current node = "+nodeId);

				if (!unprocessedNodesAndConnectedWays.containsKey(nodeId)) {

					if (loopWayNodes.keySet().contains(nodeId)) {

						nodeId = developLoopWay(sortedWays, sortedLinks, relationWays, loopWayNodes, unprocessedNodesAndConnectedWays, nodeId);

					}

					else if (!loopNodes.isEmpty()) {

						System.out.println("Start adding loops.\n# loop nodes = "+loopNodes.size());
						while (!loopNodes.isEmpty()) {
							long loopNodeId = loopNodes.get(0);
							System.out.println("There is a loop in the path starting at node "+loopNodeId);
							insertLoop(sortedWays, sortedLinks, relationWays, loopWayNodes, unprocessedNodesAndConnectedWays, loopNodes, loopNodeId);
							System.out.println("# loop nodes = "+loopNodes.size());

						}	
						developFurther = false;
					}				
					else {
						log.warn("WARNING: Something is odd, please check data for this route. There are links that are not connected to the rest of the route or links have to be used twice.");
						developFurther = false;						
					}
				}

				else {


					if (unprocessedNodesAndConnectedWays.get(nodeId).size() == 1 && loopWays.contains(unprocessedNodesAndConnectedWays.get(nodeId).get(0))) {

						nodeId = developLoopWay(sortedWays, sortedLinks, relationWays, loopWayNodes, unprocessedNodesAndConnectedWays, nodeId);

					}
					else{

						nodeId = developWay(sortedWays, sortedLinks, relationWays, loopWayNodes, unprocessedNodesAndConnectedWays, loopNodes, nodeId);

					}

				}

			}
		}
	}

	private static void insertLoop(List<Long> sortedWays, List<Id<Link>> sortedLinks, Map<Long, OsmWay> relationWays, Map<Long, List<Long>> loopWayNodes, HashMap<Long, List<Long>> unprocessedNodesAndConnectedLinks, List<Long> loopNodes, long nodeId) {

		System.out.println("Insert loop links at node "+nodeId+"...");
		List<Long> sortedLinksBefore = new ArrayList<Long>(sortedWays);
		sortedWays.clear();

		boolean beforeLoop = true;
		int i = 0;

		while(beforeLoop && i < sortedLinksBefore.size()) {

			OsmWay way  = relationWays.get(sortedLinksBefore.get(i));
			if (way.getStartNode() == nodeId || way.getEndNode() == nodeId) {
				beforeLoop = false;
			}
			sortedWays.add(way.id);
			i++;

		}
		long nextNodeId = developWay(sortedWays, sortedLinks, relationWays, loopWayNodes, unprocessedNodesAndConnectedLinks, loopNodes, nodeId);
		while (nextNodeId != nodeId) { 
			nextNodeId = developWay(sortedWays, sortedLinks, relationWays, loopWayNodes, unprocessedNodesAndConnectedLinks, loopNodes, nextNodeId);
		}

		while (i < sortedLinksBefore.size()) {
			sortedWays.add(sortedLinksBefore.get(i));
			i++;
		}

		loopNodes.remove(nodeId);

		System.out.println("--------------------------------------------------");
	}


	private static long developLoopWay(List<Long> sortedWays, List<Id<Link>> sortedLinks, Map<Long, OsmWay> relationWays, Map<Long, List<Long>> loopWayNodes, HashMap<Long, List<Long>> unprocessedNodesAndConnectedWays, long nodeId) {

		List<Long> connectedLoopWays = loopWayNodes.get(nodeId);
		System.out.println("connected loop ways = "+connectedLoopWays);

		//check if one of the loop ways is connected to the unprocessed nodes
		boolean foundConnection = false;
		int i = 0;
		OsmWay way = null;
		long nextNodeId = -1;

		while(!foundConnection && i < connectedLoopWays.size()) {
			way = relationWays.get(connectedLoopWays.get(i));
			for (long lwNodeId : way.nodes) {
				if (unprocessedNodesAndConnectedWays.keySet().contains(lwNodeId) && (unprocessedNodesAndConnectedWays.get(lwNodeId).size() > 1 || unprocessedNodesAndConnectedWays.get(lwNodeId).get(0) != way.id)){					
					nextNodeId = lwNodeId;
					foundConnection = true;
				}
			}
			i++;
		}

		System.out.println("selected loop way "+way.id+", nextNodeId = "+nextNodeId);
		sortedWays.add(way.id);

		long endNodeId;
		if (nextNodeId == -1) {
			endNodeId = nodeId;
		}
		else {
			endNodeId = nextNodeId;
		}

		List<Id<Link>> linkIds = translateLoopWayToLinks(way.id, nodeId, endNodeId);
		for (Id<Link> linkId : linkIds) {
			sortedLinks.add(linkId);
		}
		removeWayIdFromMap(unprocessedNodesAndConnectedWays, way.id);
		removeWayFromLoopWayMap(loopWayNodes, way.id);

		nodeId = endNodeId;

		return nodeId;
	}

	private static long developWay(List<Long> sortedWays, List<Id<Link>> sortedLinks, Map<Long, OsmWay> relationWays, Map<Long, List<Long>> loopWayNodes, HashMap<Long, List<Long>> unprocessedNodesAndConnectedWays, List<Long> loopNodes, long nodeId) {

		List<Long> connectedWays = unprocessedNodesAndConnectedWays.get(nodeId);
		System.out.println("# connected ways = "+connectedWays.size());
		OsmWay way = relationWays.get(connectedWays.get(0));
		System.out.print("connected ways: ");		
		System.out.println(connectedWays);

		List<Id<Link>> linkIds = null;

		if (connectedWays.size() == 1) {
			sortedWays.add(way.id);			
			//			System.out.println("Remove node "+nodeId);
			//			unprocessedNodesAndConnectedWays.remove(nodeId);
			loopNodes.remove(nodeId);
			removeWayIdFromMap(unprocessedNodesAndConnectedWays, way.id);
			removeWayFromLoopWayMap(loopWayNodes, way.id);

		}
		else if (connectedWays.size() == 2) {
			sortedWays.add(way.id);				
			removeWayIdFromMap(unprocessedNodesAndConnectedWays, way.id);
			removeWayFromLoopWayMap(loopWayNodes, way.id);

		}
		else if (connectedWays.size() > 2) {
			System.out.println("develop a loop node at node "+nodeId);
			loopNodes.add(nodeId);			
			sortedWays.add(way.id);
			removeWayIdFromMap(unprocessedNodesAndConnectedWays, way.id);
			removeWayFromLoopWayMap(loopWayNodes, way.id);
		}

		if (nodeId == way.getStartNode()) {
			nodeId = way.getEndNode();
			linkIds = translateWayToLinks(way.id, way.getStartNode());
		}
		else {
			nodeId = way.getStartNode();
			linkIds = translateWayToLinks(way.id, way.getEndNode());				
		}					

		for (Id<Link> linkId : linkIds) {
			sortedLinks.add(linkId);
		}

		return nodeId;
	}


	private static void removeWayIdFromMap(HashMap<Long, List<Long>> nodeWayMap, long id) {

		System.out.println("Remove way "+id+" from map.");
		Iterator<Long> itN = nodeWayMap.keySet().iterator();

		while (itN.hasNext()) {
			long nodeId = itN.next();
			List<Long> wayIds = nodeWayMap.get(nodeId);
			Iterator<Long> itW = wayIds.iterator(); 

			while (itW.hasNext()) {
				long wayId = itW.next();

				if (wayId == id) {
					itW.remove();
				}
			}
			if (nodeWayMap.get(nodeId).isEmpty()) {
				System.out.println("Remove node "+nodeId+" from map.");
				itN.remove();
			}
		}
	}

	private static void removeWayFromLoopWayMap(Map<Long, List<Long>> loopWayNodes, long wayId) {

		System.out.println("Remove way "+wayId+" from loop way nodes map.");
		Iterator<Long> itN = loopWayNodes.keySet().iterator();

		while (itN.hasNext()) {
			long nodeId = itN.next();
			List<Long> wayIds = loopWayNodes.get(nodeId);
			Iterator<Long> itW = wayIds.iterator(); 

			while (itW.hasNext()) {
				long wId = itW.next();

				if (wId == wayId) {
					itW.remove();
				}
			}
			if (loopWayNodes.get(nodeId).isEmpty()) {
				System.out.println("Remove node "+nodeId+" from loop way nodes map.");
				itN.remove();
			}
		}
	}



	private static List<Id<Link>> translateWayToLinks(long wayId, long startNodeId) {
		List<Id<Link>> linkIds = null;
		OsmWay way = waysMap.get(wayId);
		if (startNodeId == way.getStartNode()) {
			linkIds = waysToLinks_DirA.get(wayId);
		}
		else {
			linkIds = waysToLinks_DirB.get(wayId);
		}
		return linkIds;
	}

	private static List<Id<Link>> translateLoopWayToLinks(long wayId, long startNodeId, long endNodeId) {
		Id<Node> startNode = Id.create(startNodeId, Node.class);
		Id<Node> endNode = Id.create(endNodeId, Node.class);

		List<Id<Link>> linkIds = new LinkedList<Id<Link>>();
		OsmWay way = waysMap.get(wayId);
		List<Id<Link>> lwLinkIds = waysToLinks_DirA.get(way.id); 

		int i = 0;
		boolean endNodeReached = false;
		boolean afterStartNode = false;
		while (!endNodeReached) {
			Id<Link> linkId = lwLinkIds.get(i);
			Link link = network.getLinks().get(linkId);
			if (link.getFromNode().getId().equals(startNode)) {
				afterStartNode = true;
			}			
			if (afterStartNode) {
				linkIds.add(linkId);

				if (link.getToNode().getId().equals(endNode)) {
					endNodeReached = true;
				}
			}

			if (i < lwLinkIds.size()-1) {
				i++;
			}
			else {
				i = 0;
			}

		}
		return linkIds;
	}


	private static void createWayLinks(OsmWay way) {

		List<Id<Link>> links_DirA = new LinkedList<Id<Link>>();
		List<Id<Link>> links_DirB = new LinkedList<Id<Link>>();

		Stack<Id<Link>> tempBLinks = new Stack<Id<Link>>();


		// first create all links in the same direction as coded in the
		// way
		Node fromNode = network.getNodes().get(Id.create(way.nodes.get(0), Node.class));

		for (int i = 1; i < way.nodes.size(); i++) {
			Node toNode = network.getNodes().get(Id.create(way.nodes.get(i), Node.class));

			for (Link link : fromNode.getOutLinks().values()) {
				if (link.getToNode().getId().equals(toNode.getId())) {
					links_DirA.add(link.getId());
				}
			}
			for (Link link : fromNode.getInLinks().values()) {
				if (link.getFromNode().getId().equals(toNode.getId())) {
					tempBLinks.push(link.getId());
				}
			}

			fromNode = toNode;
		}

		while (!tempBLinks.isEmpty())  {
			Id<Link> linkId = tempBLinks.pop();						
			links_DirB.add(linkId);
		}

		waysToLinks_DirA.put(way.id, links_DirA);
		waysToLinks_DirB.put(way.id, links_DirB);

		//		System.out.println("DirA links for way "+way.id+": ");
		//		for (Id linkId : waysToLinks_DirA.get(way.id)) {
		//			System.out.print(linkId+", ");
		//		}
		//		System.out.println();
		//		System.out.println("DirB links for way "+way.id+": ");
		//		for (Id linkId : waysToLinks_DirB.get(way.id)) {
		//			System.out.print(linkId+", ");
		//		}
		//		System.out.println();

	}

}
