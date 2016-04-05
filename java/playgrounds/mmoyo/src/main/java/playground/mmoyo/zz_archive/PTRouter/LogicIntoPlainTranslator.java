/* *********************************************************************** *
 * project: org.matsim.*
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

package playground.mmoyo.zz_archive.PTRouter;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.LegImpl;
import org.matsim.core.population.routes.LinkNetworkRouteImpl;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.population.routes.RouteUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Translates logic nodes and links into plain nodes and links.
 */
public class LogicIntoPlainTranslator {
	private final NetworkImpl plainNet;
	//private Map<Id,Node> logicToPlainStopMap;
	//private Map<Id,LinkImpl> logicToPlainLinkMap;
	//private Map<Id,LinkImpl> lastLinkMap;


	/**the constructor creates the joiningLinkMap that stores the relation between logic and plain Nodes*/
	/*
	public LogicIntoPlainTranslator(final NetworkLayer plainNetwork,  final Map<Id,Node> logicToPlanStopMap, Map<Id,LinkImpl> logicToPlanLinkMap, Map<Id,LinkImpl> lastLinkMap) {
		this.plainNet= plainNetwork;
		//this.logicToPlainStopMap = logicToPlanStopMap;
		//this.logicToPlainLinkMap = logicToPlanLinkMap;
		//this.lastLinkMap = lastLinkMap;
	}
	*/

	public LogicIntoPlainTranslator(final NetworkImpl plainNetwork){
		this.plainNet= plainNetwork;
	}

	/*
	public Node convertToPlain(Id logicNodeId){
		return logicToPlainStopMap.get(logicNodeId);
	}
	*/

	public List<Node> convertNodesToPlain(List<Node> logicNodes){
		List<Node> plainNodes  = new ArrayList<Node>();
		for (Node logicNode: logicNodes){
			plainNodes.add(((Station)logicNode).getPlainNode());
		}
		return plainNodes;
	}

	/*
	private Link convertToPlain(final Link logicLink){
		Link logicAliasLink = logicLink;
		if (((LinkImpl)logicLink).getType().equals("Transfer") || ((LinkImpl)logicLink).getType().equals("DetTransfer"))
			logicAliasLink= lastLinkMap.get(logicLink.getFromNode().getId());
		return logicToPlainLinkMap.get(logicAliasLink.getId());
	}
	*/

	public List<Link> convertToPlain(List<Link> logicLinks){
		List<Link> plainLinks  = new ArrayList<Link>();
		for (Link logicLink: logicLinks){
			plainLinks.add(((PTLink)logicLink).getPlainLink());
		}
		return plainLinks;
	}

	/**translates the plans of a whole population*/
	public void convertToPlain(Population population){
		for (Person person: population.getPersons().values()) {
			Plan plan = person.getPlans().get(0);
			for (PlanElement pe : plan.getPlanElements()) {
				if (pe instanceof ActivityImpl) {
					ActivityImpl act =  (ActivityImpl) pe;
					Link plainLink= NetworkUtils.getNearestLink(plainNet, act.getCoord());
					act.setLinkId(plainLink.getId());
				}else{
					LegImpl leg = (LegImpl)pe;
					NetworkRoute logicRoute = (NetworkRoute)leg.getRoute();
					List<Node> plainNodes = convertNodesToPlain(RouteUtils.getNodes(logicRoute, this.plainNet));
					logicRoute.setLinkIds(null, NetworkUtils.getLinkIds(RouteUtils.getLinksFromNodes(plainNodes)), null);
				}
			}
		}
	}

	public List<Leg> convertToPlainLeg (List<Leg> logicLegList){
		List<Leg> plainLegList = new ArrayList<Leg>();
		for(Leg logicLeg : logicLegList){
			NetworkRoute logicRoute= (NetworkRoute)logicLeg.getRoute();
			List<Link> plainLinks = convertToPlain(NetworkUtils.getLinks(this.plainNet, logicRoute.getLinkIds()));
			//if(plainLinks.size()>0){
				NetworkRoute plainRoute = new LinkNetworkRouteImpl(null, null);
				plainRoute.setLinkIds(null, NetworkUtils.getLinkIds(plainLinks), null);
				Leg plainLeg = new LegImpl(logicLeg.getMode());
				plainLeg = logicLeg;
				plainLeg.setRoute(plainRoute);
				plainLegList.add(plainLeg);
				logicLeg.setRoute(plainRoute);
			//}
		}

		logicLegList = null;
		return plainLegList;
	}

	public List<LegImpl> convertToPlainLegORIGINAL (List<LegImpl> logicLegList){
		List<LegImpl> plainLegList = new ArrayList<LegImpl>();
		for(LegImpl logicLeg : logicLegList){
			NetworkRoute logicNetworkRoute= (NetworkRoute)logicLeg.getRoute();
			List<Id<Link>> plainLinkList = new ArrayList<Id<Link>>();

			for (Link link: NetworkUtils.getLinks(this.plainNet, logicNetworkRoute.getLinkIds())) {
				//if (link.getType().equals(ptValues.STANDARD))
					plainLinkList.add(link.getId());
			}
			if(plainLinkList.size()>0){
				NetworkRoute plainRoute = new LinkNetworkRouteImpl(null, null);
				plainRoute.setLinkIds(null, plainLinkList, null);

				LegImpl plainLeg = new LegImpl(TransportMode.pt);
				plainLeg = logicLeg;
				plainLeg.setRoute(plainRoute);
				plainLegList.add(plainLeg);

				logicLeg.setRoute(plainRoute);
			}
		}
		logicLegList = null;
		return plainLegList;
	}

}
