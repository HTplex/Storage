///* *********************************************************************** *
// * project: org.matsim.*
// * BKickRouterTestOld.java
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// * copyright       : (C) 2009 by the members listed in the COPYING,        *
// *                   LICENSE and WARRANTY file.                            *
// * email           : info at matsim dot org                                *
// *                                                                         *
// * *********************************************************************** *
// *                                                                         *
// *   This program is free software; you can redistribute it and/or modify  *
// *   it under the terms of the GNU General Public License as published by  *
// *   the Free Software Foundation; either version 2 of the License, or     *
// *   (at your option) any later version.                                   *
// *   See also COPYING, LICENSE and WARRANTY file                           *
// *                                                                         *
// * *********************************************************************** */
//
//package playground.benjamin.old.income;
//
//import org.matsim.api.core.v01.Id;
//import org.matsim.core.api.experimental.events.LinkEnterEvent;
//import org.matsim.core.api.experimental.events.handler.LinkEnterEventHandler;
//import org.matsim.core.basic.v01.IdImpl;
//import org.matsim.core.config.Config;
//import org.matsim.core.controler.events.StartupEvent;
//import org.matsim.core.controler.listener.StartupListener;
//import org.matsim.testcases.MatsimTestCase;
//
//import playground.benjamin.scoring.income.old.BkControlerIncome;
//
///**
// * Tests the routing of the BkControlerIncome
// *
// * @author dgrether
// */
//public class BkRouterTestOld extends MatsimTestCase {
//
//	/*package*/ final static Id id1 = new IdImpl("1");
//	/*package*/ final static Id id2 = new IdImpl("2");
//	/*package*/ final static Id id3 = new IdImpl("3");
//	/*package*/ final static Id id8 = new IdImpl("8");
//	/*package*/ final static Id id10 = new IdImpl("10");
//
//	public void testGeneralizedCostRouting() {
//		Config config = this.loadConfig(this.getClassInputDirectory() + "configRouterTest.xml");
//		config.controler().setOutputDirectory(this.getOutputDirectory());
//		String netFileName = this.getClassInputDirectory() + "network.xml";
//		config.network().setInputFile(netFileName);
//		config.plans().setInputFile(this.getClassInputDirectory() + "plansRouterTest.xml");
//		//hh loading
//		config.scenario().setUseHouseholds(true);
//		config.households().setInputFile(this.getClassInputDirectory() + "households.xml");
//
//		BkControlerIncome controler = new BkControlerIncome(config);
//		controler.setCreateGraphs(false);
//		final EventHandler1 handler = new EventHandler1();
//
//		controler.addControlerListener(new StartupListener() {
//			public void notifyStartup(final StartupEvent event) {
//				event.getControler().getEvents().addHandler(handler);
////				event.getControler().getEvents().addHandler(new LogOutputEventHandler());
//			}
//		});
//
//		controler.run();
//		assertTrue("Person 2 should be routed on link 3", handler.link3Ok);
//		assertTrue("Person 1 should be routed on link 8", handler.link8Ok);
//		assertTrue("Person 3 should be routed on link 10", handler.link10Ok);
//	}
//
//	private static class EventHandler1 implements LinkEnterEventHandler{
//
//		boolean link3Ok = false;
//		boolean link8Ok = false;
//		boolean link10Ok = false;
//
//		//links 3, 8, 10
//		public void handleEvent(LinkEnterEvent e) {
//			if (e.getLinkId().equals(id3) && e.getPersonId().equals(id2)) {
//				link3Ok = true;
//			}
//			else if (e.getLinkId().equals(id8) && e.getPersonId().equals(id1)) {
//				link8Ok = true;
//			}
//			else if (e.getLinkId().equals(id10) && e.getPersonId().equals(id3)) {
//				link10Ok = true;
//			}
//		}
//		public void reset(int iteration) {}
//	
//	}
//
//	
//	public void testGeneralizedTollCostRouting() {
//		Config config = this.loadConfig(this.getClassInputDirectory() + "configRouterTest.xml");
//		config.controler().setOutputDirectory(this.getOutputDirectory());
//		String netFileName = this.getClassInputDirectory() + "networkForToll.xml";
//		config.network().setInputFile(netFileName);
//		config.plans().setInputFile(this.getClassInputDirectory() + "plansRouterTollTest.xml");
//		//hh loading
//		config.scenario().setUseHouseholds(true);
//		config.households().setInputFile(this.getClassInputDirectory() + "householdsForToll.xml");
//		//setting road pricing on link 8
//		config.scenario().setUseRoadpricing(true);
//		config.roadpricing().setTollLinksFile(this.getClassInputDirectory() + "tollLinksFile.xml");
//
//		BkControlerIncome controler = new BkControlerIncome(config);
//		controler.setCreateGraphs(false);
//		final EventHandler2 handler = new EventHandler2();
//
//		controler.addControlerListener(new StartupListener() {
//			public void notifyStartup(final StartupEvent event) {
//				event.getControler().getEvents().addHandler(handler);
////				event.getControler().getEvents().addHandler(new LogOutputEventHandler());
//			}
//		});
//
//		controler.run();
//		assertTrue("Person 1 should be routed on link 10", handler.link10Ok);
//	}
//
//	private static class EventHandler2 implements LinkEnterEventHandler{
//
//		boolean link10Ok = false;
//
//		//link 10
//		public void handleEvent(LinkEnterEvent e) {
//			if (e.getLinkId().equals(id10) && e.getPersonId().equals(id1)) {
//				link10Ok = true;
//			}
//		}
//		public void reset(int iteration) {}
//	
//	}
//	
//}