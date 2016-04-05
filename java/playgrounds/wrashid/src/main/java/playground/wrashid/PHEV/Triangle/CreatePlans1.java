//package playground.wrashid.PHEV.Triangle;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import org.matsim.api.core.v01.TransportMode;
//import org.matsim.api.core.v01.population.Population;
//import org.matsim.core.api.experimental.facilities.ActivityFacilities;
//import org.matsim.core.api.experimental.facilities.ActivityFacility;
//import org.matsim.core.basic.v01.IdImpl;
//import org.matsim.core.config.Config;
//import org.matsim.core.config.ConfigUtils;
//import org.matsim.core.facilities.ActivityOption;
//import org.matsim.core.facilities.ActivityOptionImpl;
//import org.matsim.core.facilities.MatsimFacilitiesReader;
//import org.matsim.core.population.ActivityImpl;
//import org.matsim.core.population.LegImpl;
//import org.matsim.core.population.PersonImpl;
//import org.matsim.core.population.PlanImpl;
//import org.matsim.core.scenario.ScenarioImpl;
//import org.matsim.core.scenario.ScenarioUtils;
//
//public class CreatePlans1 {
//
//	/**
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception {
//		args=new String[1];
//		args[0]="C:/data/SandboxCVS/ivt/studies/triangle/config/config.xml";
//		Config config = ConfigUtils.loadConfig(args[0]);
////		config.plans().setOutputFile("C:/data/SandboxCVS/ivt/studies/triangle/plans/100Kplans/plans_hwsh.xml");
//
//		ScenarioImpl scenario = (ScenarioImpl) ScenarioUtils.createScenario(config);
//		Population plans = scenario.getPopulation();
//        Knowledges result7;
//        throw new RuntimeException("Knowledges are no more.");
//
//        Knowledges knowledges = result7;
//
//		// read facilities
//		ActivityFacilities facilities = scenario.getActivityFacilities();
//		new MatsimFacilitiesReader(scenario).readFile("C:/data/SandboxCVS/ivt/studies/triangle/facilities/facilities.xml");
//
//
//		// get home and work activity
//		ActivityOption home=null;
//		ActivityOption work=null;
//		ActivityOption shop=null;
//		for (ActivityFacility f : facilities.getFacilities().values()) {
//			Iterator<ActivityOption> a_it = f.getActivityOptions().values().iterator();
//			while (a_it.hasNext()) {
//				ActivityOption a = a_it.next();
//				//System.out.println(a.getType());
//				if (a.getType().equals("home")) {
//					home=a;
//				} else if (a.getType().equals("work")){
//					work=a;
//				} else if (a.getType().equals("shop")){
//					shop=a;
//				}
//			}
//		}
//
//
//
//
//
//
//		// create persons
//		for (int i=0;i<100000;i++){
//			PersonImpl person = new PersonImpl(new IdImpl(i));
//			plans.addPerson(person);
//
//
//			Object k = knowledges.getFactory().createKnowledge(person.getId(), "");
//            boolean result2;
//            throw new RuntimeException("Knowledges are no more.");
//
//            boolean result1;
//            throw new RuntimeException("Knowledges are no more.");
//
//            boolean result;
//            throw new RuntimeException("Knowledges are no more.");
//
//            PlanImpl plan = person.createAndAddPlan(true);
//            ArrayList<ActivityOptionImpl> result6;
//            throw new RuntimeException("Knowledges are no more.");
//
//            ActivityFacility home_facility = result6.get(0).getFacility();
//            ArrayList<ActivityOptionImpl> result5;
//            throw new RuntimeException("Knowledges are no more.");
//
//            ActivityFacility work_facility = result5.get(0).getFacility();
//            ArrayList<ActivityOptionImpl> result4;
//            throw new RuntimeException("Knowledges are no more.");
//
//            ActivityFacility shop_facility = result4.get(0).getFacility();
//            ArrayList<ActivityOptionImpl> result3;
//            throw new RuntimeException("Knowledges are no more.");
//
//            ArrayList<? extends ActivityOption> acts = result3;
//
//			double depTimeHome=3600*8;
//			double depTimeWork=3600*16;
//			double depTimeShop=3600*17.5;
//			double mitterNacht=3600*24;
//			double duration=3600*8;
//
//
//			// home: 0:00-8:00
//			// work: 8-16
//			// shop: 16-17.30
//			// home: 17.30-0:00
//
//			ActivityImpl a = plan.createAndAddActivity("home",home_facility.getCoord());
//			a.setLinkId(home_facility.getLinkId());
//			a.setEndTime(depTimeHome);
//			LegImpl l = plan.createAndAddLeg(TransportMode.car);
//			l.setArrivalTime(depTimeHome);
//			l.setTravelTime(0.0);
//			l.setDepartureTime(depTimeHome);
//			a = plan.createAndAddActivity("work",work_facility.getCoord());
//			a.setLinkId(work_facility.getLinkId());
//			a.setStartTime(depTimeHome);
//			a.setEndTime(depTimeWork);
//			a.setMaximumDuration(depTimeWork-depTimeHome);
//			l = plan.createAndAddLeg(TransportMode.car);
//			l.setArrivalTime(depTimeWork);
//			l.setTravelTime(0.0);
//			l.setDepartureTime(depTimeWork);
//			a = plan.createAndAddActivity("shop",shop_facility.getCoord());
//			a.setLinkId(shop_facility.getLinkId());
//			a.setStartTime(depTimeWork);
//			a.setEndTime(depTimeShop);
//			a.setMaximumDuration(depTimeShop-depTimeWork);
//			l = plan.createAndAddLeg(TransportMode.car);
//			l.setArrivalTime(depTimeShop);
//			l.setTravelTime(0.0);
//			l.setDepartureTime(depTimeShop);
//			a = plan.createAndAddActivity("home",home_facility.getCoord());
//			a.setLinkId(home_facility.getLinkId());
//			// assign home-work-home activities to each person
//
//
////			Leg l=null;
//
//		}
//
//
//
//		// new PopulationWriter(plans, scenario.getNetwork()).write(null);//config.plans().getOutputFile());
//	}
//
//}
