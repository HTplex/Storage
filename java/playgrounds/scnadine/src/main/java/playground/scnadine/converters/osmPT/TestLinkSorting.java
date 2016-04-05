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

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;

public class TestLinkSorting {

	/**
	 * @param args
	 */

	static ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
	static NetworkImpl network =  (NetworkImpl) scenario.getNetwork();

	private final static Logger log = Logger.getLogger(Osm2TransitLines.class);

	public static void main(String[] args) {

		String networkFile = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\chessboard10by10network.xml";
		//		String networkFile = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\chessboard10by10network.xml";

		String linkFile = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\linkSortingInput.txt";
		//		String linkFile = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\linkSortingInput.txt";

		String outputFile = "Z:\\sandbox02\\studies\\GPSProcessing\\TestScenario\\linkSortingOutput.txt";
		//		String outputFile = "C:\\Users\\rinadine\\sandbox\\studies\\GPSProcessing\\TestScenario\\linkSortingOutput.txt";

		new MatsimNetworkReader(scenario).readFile(networkFile);

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(linkFile))));
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(outputFile)));

			String headerline = in.readLine();
			out.write(headerline);
			out.newLine();

			String inputLine = null;			
			while((inputLine = in.readLine()) != null) {

				String[] entries = inputLine.split("\t");
				List<Id<Link>> unsortedLinks = new LinkedList<Id<Link>>();

				int i = 1; 
				while (!entries[i].equals("-1")) {
					unsortedLinks.add(Id.create(entries[i], Link.class));					
					i++;
				}
				log.info("\n\n---------------------------------------");
				log.info("Sorting links for route "+entries[0]);
				List<Id<Link>> sortedLinks = sortLinks(unsortedLinks);

				out.write(entries[0]);
				for (Id<Link> linkId : sortedLinks) {
					out.write("\t"+linkId);					
				}
				out.write("\t-1");				
				out.newLine();
				out.flush();
				log.info("");
			}

			in.close();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}


	private static List<Id<Link>> sortLinks(List<Id<Link>> unsortedLinks) {

		List<Id<Link>> sortedLinks = new LinkedList<Id<Link>>();

		if (unsortedLinks.size() > 1) {

			// store links in a Hashmap sorted by the nodes they are connected to
			HashMap<Id<Node>, List<Id<Link>>> unprocessedNodesAndConnectedLinks = new HashMap<Id<Node>, List<Id<Link>>>();

			for (Id<Link> linkId : unsortedLinks) {

				Link link = network.getLinks().get(linkId);
				Id<Node> fromNodeId = link.getFromNode().getId();
				Id<Node> toNodeId = link.getToNode().getId();


				List<Id<Link>> fromNodeLinks;
				if (unprocessedNodesAndConnectedLinks.containsKey(fromNodeId)) {
					fromNodeLinks = unprocessedNodesAndConnectedLinks.get(fromNodeId);
				}
				else {
					fromNodeLinks = new ArrayList<Id<Link>>();
				}			
				fromNodeLinks.add(linkId);				
				unprocessedNodesAndConnectedLinks.put(fromNodeId, fromNodeLinks);

				List<Id<Link>> toNodeLinks;
				if (unprocessedNodesAndConnectedLinks.containsKey(toNodeId)) {
					toNodeLinks = unprocessedNodesAndConnectedLinks.get(toNodeId);
				}
				else {
					toNodeLinks = new ArrayList<Id<Link>>();
				}			
				toNodeLinks.add(linkId);				
				unprocessedNodesAndConnectedLinks.put(toNodeId, toNodeLinks);
			}

			System.out.println("Node to link map: ");
			for (Id<Node> nodeId : unprocessedNodesAndConnectedLinks.keySet()) {
				System.out.print("Node "+nodeId+": ");
				for (Id<Link> linkId : unprocessedNodesAndConnectedLinks.get(nodeId)) {
					System.out.print(linkId+", ");
				}
				System.out.println();
			}
			System.out.println();



			//determine the number of intermediate and end loops and the number of end nodes
			List<Id<Node>> endLoopNodes = new ArrayList<Id<Node>>();
			List<Id<Node>> intermediateLoopNodes = new ArrayList<Id<Node>>();
			List<Id<Node>> endNodes = new ArrayList<Id<Node>>();

			System.out.println("Number of connected links per node: ");
			for (Id<Node> nodeId : unprocessedNodesAndConnectedLinks.keySet()) {
				int numberOfConnectedLinks = unprocessedNodesAndConnectedLinks.get(nodeId).size();				
				System.out.println("node "+nodeId+": "+numberOfConnectedLinks);
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

			log.info("Number of end loops = "+endLoopNodes.size());
			if (endLoopNodes.size() > 0) {
				String endLoopIds = "End loop nodes: ";				
				for (Id<Node> nodeId : endLoopNodes) {
					endLoopIds = endLoopIds.concat(nodeId+", ");
				}
				log.info(endLoopIds);
			}
			log.info("Number of intermediate loops = "+intermediateLoopNodes.size());
			if (intermediateLoopNodes.size() > 0) {
				String intLoopIds = "Intermediate loop nodes: ";
				for (Id<Node> nodeId : intermediateLoopNodes) {
					intLoopIds= intLoopIds.concat(nodeId+", ");
				}
				log.info(intLoopIds);
			}
			log.info("Number of end nodes = "+endNodes.size());
			if (endNodes.size() > 0) {
				String endNodeIds = "End nodes: ";
				for (Id<Node> nodeId : endNodes) {
					endNodeIds = endNodeIds.concat(nodeId+", ");
				}
				log.info(endNodeIds);
			}


			// determine a potential start node
			// if there is at least one node with just one connected links: this has to be an end of the route
			// else start with the from node of a random link 
			Id<Node> startNodeId = null;
			if (endNodes.size() > 0) {
				startNodeId = endNodes.get(0);
			}
			else {
				startNodeId = network.getLinks().get(unsortedLinks.get(0)).getFromNode().getId();
			}
			developLinks(sortedLinks, unprocessedNodesAndConnectedLinks, startNodeId);

		}
		else {
			sortedLinks = unsortedLinks;
		}		

		return sortedLinks;

	}



	private static void developLinks(List<Id<Link>> sortedLinks, HashMap<Id<Node>, List<Id<Link>>> unprocessedNodesAndConnectedLinks, Id<Node> startNodeId) {

		boolean developFurther = true;

		Id<Node> nodeId = startNodeId;
		List<Id<Node>> loopNodes = new ArrayList<Id<Node>>();

		while (developFurther) {

			nodeId = developLink(sortedLinks, unprocessedNodesAndConnectedLinks, loopNodes, nodeId);


			if (unprocessedNodesAndConnectedLinks.get(nodeId).isEmpty()) {

				unprocessedNodesAndConnectedLinks.remove(nodeId);

				System.out.println("# unprocessed nodes = "+unprocessedNodesAndConnectedLinks.size());

				if (unprocessedNodesAndConnectedLinks.isEmpty()) {
					developFurther = false;
					log.info("forward processing finished at node "+nodeId);
					System.out.println();
				}
				else {
					if (!loopNodes.isEmpty()) {
 
						System.out.println("Start adding loops.\n# loop nodes = "+loopNodes.size());
						while (!loopNodes.isEmpty()) {
							Id<Node> loopNodeId = loopNodes.get(0);
							System.out.println("There is a loop in the path starting at node "+loopNodeId);
							insertLoop(sortedLinks, unprocessedNodesAndConnectedLinks, loopNodes, loopNodeId);
							System.out.println("# loop nodes = "+loopNodes.size());

						}	
						developFurther = false;
					}				
					else {
						log.warn("WARNING: Something is odd, please check data for this route. There are links that are not connected to the rest of the route or links have to be used twice.");
						developFurther = false;						
					}
				}


			}
		}
	}

	private static Id<Node> developLink(List<Id<Link>> sortedLinks, HashMap<Id<Node>, List<Id<Link>>> unprocessedNodesAndConnectedLinks, List<Id<Node>> loopNodes, Id<Node> nodeId) {

		List<Id<Link>> connectedLinks = unprocessedNodesAndConnectedLinks.get(nodeId);
		System.out.println("# connected links "+connectedLinks.size());
		for (Id<Link> id : connectedLinks) System.out.print(id+" ");
		System.out.println();
		if (connectedLinks.size() == 1) {
			Link link = network.getLinks().get(connectedLinks.get(0));				
			sortedLinks.add(link.getId());
			for (Id<Link> id : sortedLinks) System.out.print(id+", ");
			System.out.println();
			System.out.println("Remove node "+nodeId);
			unprocessedNodesAndConnectedLinks.remove(nodeId);
			loopNodes.remove(nodeId);
			removeLinkIdFromMap(unprocessedNodesAndConnectedLinks, link.getId());
			if (nodeId.equals(link.getFromNode().getId())) {
				nodeId = link.getToNode().getId();
			}
			else {
				nodeId = link.getFromNode().getId();
			}					
			System.out.println("current node = "+nodeId+" with "+unprocessedNodesAndConnectedLinks.get(nodeId).size()+" connected links.");

		}
		else if (connectedLinks.size() == 2) {
			Link link = network.getLinks().get(connectedLinks.get(0));
			sortedLinks.add(link.getId());
			for (Id<Link> id : sortedLinks) System.out.print(id+", ");
			System.out.println();
			removeLinkIdFromMap(unprocessedNodesAndConnectedLinks, link.getId());
			if (nodeId.equals(link.getFromNode().getId())) {
				nodeId = link.getToNode().getId();
			}
			else {
				nodeId = link.getFromNode().getId();
			}	
			System.out.println("current node = "+nodeId+" with "+unprocessedNodesAndConnectedLinks.get(nodeId).size()+" connected links.");					
		}
		else if (connectedLinks.size() > 2) {
			System.out.println("develop a loop node");
			loopNodes.add(nodeId);

			Link link = network.getLinks().get(connectedLinks.get(0));
			System.out.println("walk along link "+link.getId());
			sortedLinks.add(link.getId());
			for (Id<Link> id : sortedLinks) System.out.print(id+", ");
			System.out.println();
			removeLinkIdFromMap(unprocessedNodesAndConnectedLinks, link.getId());
			if (nodeId.equals(link.getFromNode().getId())) {
				nodeId = link.getToNode().getId();
			}
			else {
				nodeId = link.getFromNode().getId();
			}	
			System.out.println("current node = "+nodeId+" with "+unprocessedNodesAndConnectedLinks.get(nodeId).size()+" connected links.");

		}

		return nodeId;
	}

	private static void insertLoop(List<Id<Link>> sortedLinks, HashMap<Id<Node>, List<Id<Link>>> unprocessedNodesAndConnectedLinks, List<Id<Node>> loopNodes, Id<Node> nodeId) {

		System.out.println("--------------------------------------------------");
		System.out.println("Insert loop links...");
		List<Id<Link>> sortedLinksBefore = new ArrayList<Id<Link>>(sortedLinks);
		sortedLinks.clear();

		boolean beforeLoop = true;
		int i = 0;

		System.out.println("Add all links before the loop...");		
		while(beforeLoop && i < sortedLinksBefore.size()) {

			Link link  = network.getLinks().get(sortedLinksBefore.get(i));
			if (link.getToNode().getId().equals(nodeId) || link.getFromNode().getId().equals(nodeId)) {
				beforeLoop = false;
			}
			sortedLinks.add(link.getId());
			i++;

		}
		for (Id<Link> linkId : sortedLinks) {
			System.out.print(linkId+", ");
		}
		System.out.println();
		
		System.out.println("\nDevelop loop links...");
		Id<Node> nextNodeId = developLink(sortedLinks, unprocessedNodesAndConnectedLinks, loopNodes, nodeId);
		while (!nextNodeId.equals(nodeId)) { 
			nextNodeId = developLink(sortedLinks, unprocessedNodesAndConnectedLinks, loopNodes, nextNodeId);
		}

		System.out.println("\nAdd all the links after the loop");

		while (i < sortedLinksBefore.size()) {
			sortedLinks.add(sortedLinksBefore.get(i));
			i++;
		}
		for (Id<Link> linkId : sortedLinks) {
			System.out.print(linkId+", ");
		}
		System.out.println();
		
		loopNodes.remove(nodeId);

		System.out.println("--------------------------------------------------");
	}





	private static void removeLinkIdFromMap(HashMap<Id<Node>, List<Id<Link>>> nodeLinkMap, Id<Link> id) {

		for (Id<Node> nodeId : nodeLinkMap.keySet()) {

			List<Id<Link>> linkIds = nodeLinkMap.get(nodeId);
			Iterator<Id<Link>> it = linkIds.iterator(); 

			while (it.hasNext()) {
				Id<Link> linkId = it.next();

				if (linkId.equals(id)) {
					it.remove();
				}
			}
		}

	}

}
