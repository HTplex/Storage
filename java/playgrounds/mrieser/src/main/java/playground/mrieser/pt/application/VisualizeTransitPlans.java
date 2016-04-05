/* *********************************************************************** *
 * project: org.matsim.*
 * VisualizeTransitPlans.java
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

package playground.mrieser.pt.application;

import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.*;
import org.matsim.contrib.otfvis.OTFVis;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.ConfigWriter;
import org.matsim.core.config.groups.QSimConfigGroup.SnapshotStyle;
import org.matsim.core.config.groups.SimulationConfigGroup;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.mobsim.qsim.QSim;
import org.matsim.core.mobsim.qsim.QSimUtils;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.NetworkWriter;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.MatsimPopulationReader;
import org.matsim.core.population.PopulationFactoryImpl;
import org.matsim.core.population.routes.GenericRouteImpl;
import org.matsim.core.population.routes.LinkNetworkRouteImpl;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.pt.routes.ExperimentalTransitRoute;
import org.matsim.pt.routes.ExperimentalTransitRouteFactory;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.pt.utils.CreatePseudoNetwork;
import org.matsim.vis.otfvis.OTFClientLive;
import org.matsim.vis.otfvis.OnTheFlyServer;

import java.util.ArrayList;
import java.util.List;


public class VisualizeTransitPlans {

	private final static String NETWORK_FILE = "../thesis-data/application/network.multimodal.xml";
	private final static String TRANSIT_SCHEDULE_FILE = "../thesis-data/application/transitschedule.oevModellZH.xml";
	private final static String POPULATION_FILE = "../thesis-data/application/plans.census2000ivtch1pct.dilZh30km.pt-routedOevModell.xml.gz";

	private final ScenarioImpl realScenario;
	private final ScenarioImpl visScenario;

	public VisualizeTransitPlans() {
		this.realScenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
		this.realScenario.getConfig().scenario().setUseTransit(true);
		this.visScenario = (ScenarioImpl) ScenarioUtils.createScenario(ConfigUtils.createConfig());
	}

	private void loadRealScenario() {
		if (NETWORK_FILE != "") {
			new MatsimNetworkReader(this.realScenario).readFile(NETWORK_FILE);
		}
		new TransitScheduleReader(this.realScenario).readFile(TRANSIT_SCHEDULE_FILE);
		((PopulationFactoryImpl) this.realScenario.getPopulation().getFactory()).setRouteFactory(TransportMode.pt, new ExperimentalTransitRouteFactory());
		new MatsimPopulationReader(this.realScenario).readFile(POPULATION_FILE);
//		this.realScenario.getPopulation().printPlansCount();
	}

	private void convertData() {
		CreatePseudoNetwork pseudoNetCreator = new CreatePseudoNetwork(this.realScenario.getTransitSchedule(), this.visScenario.getNetwork(), "tr_");
		pseudoNetCreator.createNetwork();

		Population visPop = this.visScenario.getPopulation();
		PopulationFactory pb = visPop.getFactory();
		for (Person person : this.realScenario.getPopulation().getPersons().values()) {
			Person visPerson = pb.createPerson(person.getId());
			for (Plan plan : person.getPlans()) {
				Plan visPlan = pb.createPlan();
				for (PlanElement pe : plan.getPlanElements()) {
					if (pe instanceof ActivityImpl) {
						ActivityImpl act = (ActivityImpl) pe;
						ActivityImpl visAct = (ActivityImpl) pb.createActivityFromCoord(act.getType(), act.getCoord());
						visAct.setStartTime(act.getStartTime());
						visAct.setMaximumDuration((act).getMaximumDuration());
						visAct.setEndTime(act.getEndTime());
						visAct.setLinkId(NetworkUtils.getNearestLink(((NetworkImpl) this.visScenario.getNetwork()), act.getCoord()).getId());
						visPlan.addActivity(visAct);
					} else if (pe instanceof Leg) {
						Leg leg = (Leg) pe;

						Leg visLeg = pb.createLeg(leg.getMode() == TransportMode.pt ? TransportMode.car : leg.getMode());
						if (leg.getMode() == TransportMode.pt) {
							visLeg.setRoute(convertRoute((ExperimentalTransitRoute) leg.getRoute(), pseudoNetCreator));
						} else {
							visLeg.setRoute(leg.getRoute()); // reuse route
						}
						visLeg.setTravelTime(leg.getTravelTime());
						visPlan.addLeg(visLeg);
					}
				}
				// step again through the plan and set the links in the routes

				Link fromLink = null;
				Link toLink = null;
				Route route = null;
				for (PlanElement pe : visPlan.getPlanElements()) {
					if (pe instanceof Activity) {
						fromLink = toLink;
						toLink = this.visScenario.getNetwork().getLinks().get(((Activity) pe).getLinkId());
						if (route != null) {
							if (route instanceof GenericRouteImpl) {
								((GenericRouteImpl) route).setStartLinkId(fromLink.getId());
								((GenericRouteImpl) route).setEndLinkId(toLink.getId());
							} else if (route instanceof NetworkRoute) {
								((NetworkRoute) route).setStartLinkId(fromLink.getId());
								((NetworkRoute) route).setEndLinkId(toLink.getId());
							}
						}
					}
					if (pe instanceof Leg) {
						route = ((Leg) pe).getRoute();
					}
				}

				visPerson.addPlan(visPlan);
			}
			visPop.addPerson(visPerson);
		}

		new PopulationWriter(visPop, this.visScenario.getNetwork()).write("vis.plans.xml");
		new NetworkWriter(this.visScenario.getNetwork()).write("vis.network.xml");
		Config visConfig = new Config();
		visConfig.addCoreModules();
		visConfig.network().setInputFile("vis.network.xml");
		visConfig.plans().setInputFile("vis.plans.xml");
		((SimulationConfigGroup) visConfig.getModule(SimulationConfigGroup.GROUP_NAME)).setSnapshotStyle( SnapshotStyle.queue ) ;;
		new ConfigWriter(visConfig).write("vis.config.xml");

	}

	private Route convertRoute(final ExperimentalTransitRoute route, final CreatePseudoNetwork pseudoNetCreator) {
		TransitLine tLine = null;
		try {
			tLine = this.realScenario.getTransitSchedule().getTransitLines().get(route.getLineId());
		} catch (NullPointerException e) {
//			e.printStackTrace();
		}
		if (tLine == null) {
			System.err.println("could not find transit line '" + route.getLineId() + "' from route " + route.getRouteDescription());
			return null;
		}

		TransitRoute tRoute = tLine.getRoutes().get(route.getRouteId());
		if (tRoute == null) {
			System.err.println("could not find transit route '" + route.getRouteId() + "' from route " + route.getRouteDescription());
			return null;
		}

		TransitStopFacility accessStop = this.realScenario.getTransitSchedule().getFacilities().get(route.getAccessStopId());
		TransitStopFacility egressStop = this.realScenario.getTransitSchedule().getFacilities().get(route.getEgressStopId());

		NetworkRoute netRoute = new LinkNetworkRouteImpl(accessStop.getLinkId(), egressStop.getLinkId());
		List<Link> links = new ArrayList<Link>();
		boolean include = false;
		TransitStopFacility prevStop = null;
		for (TransitRouteStop stop : tRoute.getStops()) {
			if (egressStop == stop.getStopFacility()) {
				include = false;
			}
			if (include) {
				links.add(pseudoNetCreator.getLinkBetweenStops(prevStop, stop.getStopFacility()));
			}
			if (accessStop == stop.getStopFacility()) {
				include = true;
			}
			prevStop = stop.getStopFacility();
		}
		netRoute.setLinkIds(accessStop.getLinkId(), NetworkUtils.getLinkIds(links), egressStop.getLinkId());
		return netRoute;
	}

	private void visualize() {
		EventsManager events = EventsUtils.createEventsManager();
		QSim otfVisQSim = (QSim) QSimUtils.createDefaultQSim(this.visScenario, events);
		OnTheFlyServer server = OTFVis.startServerAndRegisterWithQSim(this.visScenario.getConfig(), this.visScenario, events, otfVisQSim);
		OTFClientLive.run(this.visScenario.getConfig(), server);
		otfVisQSim.run();
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		VisualizeTransitPlans app = new VisualizeTransitPlans();
		app.loadRealScenario();
		app.convertData();
		app.visualize();
	}

}
