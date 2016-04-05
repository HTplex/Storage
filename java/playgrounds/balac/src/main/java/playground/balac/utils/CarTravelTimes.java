package playground.balac.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.LegImpl;
import org.matsim.core.population.PersonImpl;
import org.matsim.core.population.PlanImpl;
import org.matsim.core.population.PopulationImpl;
import org.matsim.core.population.PopulationWriter;
import org.matsim.core.router.PlanRouter;
import org.matsim.core.router.RoutingContextImpl;
import org.matsim.core.router.TripRouterFactoryBuilderWithDefaults;
import org.matsim.core.router.costcalculators.TravelDisutilityFactory;
import org.matsim.core.router.costcalculators.TravelTimeAndDistanceBasedTravelDisutilityFactory;
import org.matsim.core.router.util.TravelDisutility;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.trafficmonitoring.TravelTimeCalculator;
import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.core.utils.misc.ArgumentParser;

import playground.balac.carsharing.preprocess.membership.MyLinkUtils;

public class CarTravelTimes {

	Config config;
	String configfile = null;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		CarTravelTimes loadedNetworkRouter=new CarTravelTimes();
		loadedNetworkRouter.run(args);
	}

	/**
	 * Parses all arguments and sets the corresponding members.
	 *
	 * @param args
	 */
	private void parseArguments(final String[] args) {
		if (args.length == 0) {
			System.out.println("Too few arguments.");
			System.exit(1);
		}
		Iterator<String> argIter = new ArgumentParser(args).iterator();
		String arg = argIter.next();
		if (arg.equals("-h") || arg.equals("--help")) {
			System.exit(0);
		} else {
			this.configfile = arg;
			if (argIter.hasNext()) {
				System.out.println("Too many arguments.");
				System.exit(1);
			}
		}
	}

	public void run(final String[] args) throws IOException {
		
		String rootPathOut = args[2];
		//String rootPathOut = "C:/Users/balacm/Desktop/";

		String rootPath = args[2];
		//String rootPath = "C:/Users/balacm/Desktop/CarSharing/run34/ITERS/it.1/";

		String eventsFile = rootPath + "out.events_new.txt.gz";
		//String eventsFile = rootPath + "1.1.events.xml.gz";
		String outputPlansFile = rootPathOut + args[1] + "outputPlanFileX.xml";

		//parseArguments(args);
		final Config config = ConfigUtils.loadConfig(args[0]);
		
		final Scenario sc = ScenarioUtils.loadScenario(config);
		
		Network network = sc.getNetwork();
		this.config = sc.getConfig();

		
		
		final PopulationImpl plans = (PopulationImpl) sc.getPopulation();
		plans.setIsStreaming(true);
		final PopulationWriter plansWriter = new PopulationWriter(plans, network);
		plansWriter.startStreaming(outputPlansFile);
		
		// add algorithm to map coordinates to links
		plans.addAlgorithm(new org.matsim.population.algorithms.XY2Links(network));

		// add algorithm to estimate travel cost
		// and which performs routing based on that
		TravelTimeCalculator travelTimeCalculator = Events2TTCalculator.getTravelTimeCalculator(sc, eventsFile);
		TravelDisutilityFactory travelCostCalculatorFactory = new TravelTimeAndDistanceBasedTravelDisutilityFactory();
		TravelDisutility travelCostCalculator = travelCostCalculatorFactory.createTravelDisutility(travelTimeCalculator.getLinkTravelTimes(), this.config.planCalcScore());
		plans.addAlgorithm(
				new PlanRouter(
				new TripRouterFactoryBuilderWithDefaults().build(
						sc ).instantiateAndConfigureTripRouter(
								new RoutingContextImpl(
										travelCostCalculator,
										travelTimeCalculator.getLinkTravelTimes() ) ) ) );

		// add algorithm to write out the plans
		plans.addAlgorithm(plansWriter);
		final BufferedReader readLink = IOUtils.getBufferedReader(args[3]);

		//final BufferedReader readLink = IOUtils.getBufferedReader("C:/Users/balacm/Desktop/coordinates.txt");
		String s = readLink.readLine();
		s = readLink.readLine();
		while(s != null) {
			String[] arr = s.split("\\t");
			CoordImpl coordStart = new CoordImpl(arr[5], arr[6]);
			Link lStart = MyLinkUtils.getClosestLink(network, coordStart);
			CoordImpl coordEnd = new CoordImpl(arr[7], arr[8]);
			Link lEnd = MyLinkUtils.getClosestLink(network, coordEnd);
			
			PersonImpl person = new PersonImpl(Id.create(arr[0], Person.class));
			
			PlanImpl plan = (PlanImpl) sc.getPopulation().getFactory().createPlan();
			ActivityImpl act = new ActivityImpl("home", lStart.getId());
			act.setCoord(coordStart);
			//String[] arr2 = arr[4].split(":");
			//double h = Double.parseDouble(arr2[0]);
			//double m = Double.parseDouble(arr2[1]);
			//act.setEndTime(h * 3600 + m * 60);
			act.setEndTime(60.0 * Double.parseDouble(arr[3]));
			plan.addActivity(act);
			
			LegImpl leg = new LegImpl("car");
			plan.addLeg(leg);
			
			act = new ActivityImpl("leisure", lEnd.getId());
			act.setCoord(coordEnd);
			act.setEndTime(48800);
			plan.addActivity(act);
			leg = new LegImpl("car");
			plan.addLeg(leg);
			act = new ActivityImpl("home", lStart.getId());
			act.setCoord(coordStart);
			plan.addActivity(act);
			person.addPlan(plan);
			
			sc.getPopulation().addPerson(person);
			s = readLink.readLine();
			
			
		}
		
		final BufferedWriter outLink = IOUtils.getBufferedWriter(args[2] +"/travelTimes_" + args[1]+ ".txt");

		
		for(Person per: sc.getPopulation().getPersons().values()) {
			double time = 0.0;
			Plan p = per.getPlans().get(0);
			
			for(PlanElement pe: p.getPlanElements()) {
				
				if (pe instanceof Activity) {
					if (((Activity) pe).getType().equals("leisure")) {
						
						break;
					}
				}
				else if (pe instanceof Leg) {
					
					time += ((Leg) pe).getTravelTime();
					
				}
				
			}
			
			outLink.write(per.getId() + " ");
			outLink.write(Double.toString(time));
			outLink.newLine();
			
			
		}
		outLink.flush();
		outLink.close();
		plans.printPlansCount();
		plansWriter.closeStreaming();

		System.out.println("done.");
	}

}
