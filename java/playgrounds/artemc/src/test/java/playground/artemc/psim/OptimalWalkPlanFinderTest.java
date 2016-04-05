package playground.artemc.psim;

import org.junit.Test;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.events.*;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.api.experimental.events.TeleportationArrivalEvent;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.matsim.core.network.NetworkImpl;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.LegImpl;
import org.matsim.core.population.PersonImpl;
import org.matsim.core.population.PlanImpl;
import org.matsim.core.population.routes.GenericRouteImpl;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.scoring.EventsToScore;
import org.matsim.core.scoring.functions.CharyparNagelScoringFunctionFactory;
import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.core.utils.geometry.CoordUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by artemc on 6/5/15.
 */
public class OptimalWalkPlanFinderTest {

	private static final double EPSILON = 1e-9;

	private double calcScore(final Fixture f, Plan plan) {
		CharyparNagelScoringFunctionFactory charyparNagelScoringFunctionFactory = new CharyparNagelScoringFunctionFactory(f.config.planCalcScore(), f.scenario.getNetwork());
		EventsToScore eventsToScore = new EventsToScore(f.scenario, charyparNagelScoringFunctionFactory);
		double scoreFromEvents = calcScoreFromEvents(eventsToScore, f);
		return scoreFromEvents;
	}

	private double calcScoreFromEvents(EventsToScore eventsToScore, final Fixture f) {
		handleFirstActivity(eventsToScore, f, (Activity) f.plan.getPlanElements().get(0));
		handleLeg(eventsToScore, f, (Leg) f.plan.getPlanElements().get(1));
		handleActivity(eventsToScore, f, (Activity) f.plan.getPlanElements().get(2));
		handleLeg(eventsToScore, f, (Leg) f.plan.getPlanElements().get(3));
		handleLastActivity(eventsToScore, f, (Activity) f.plan.getPlanElements().get(4));
		eventsToScore.finish();
		return f.plan.getScore();
	}

	private void runScoreTest(Plan optimalPlan, Fixture f) {

		Double bestScore = calcScore(f, optimalPlan);

		((ActivityImpl) optimalPlan.getPlanElements().get(0)).setEndTime(8.5 * 3600 + 60);
		((LegImpl) optimalPlan.getPlanElements().get(1)).setDepartureTime(((LegImpl) optimalPlan.getPlanElements().get(1)).getDepartureTime() + 60);
		((LegImpl) optimalPlan.getPlanElements().get(1)).setArrivalTime(((LegImpl) optimalPlan.getPlanElements().get(1)).getArrivalTime() + 60);
		((ActivityImpl) optimalPlan.getPlanElements().get(2)).setStartTime(((ActivityImpl) optimalPlan.getPlanElements().get(2)).getStartTime() + 60);

		Double altScore1 =calcScore(f, optimalPlan);

		((ActivityImpl) optimalPlan.getPlanElements().get(2)).setEndTime(18 * 3600 - 60);
		((LegImpl) optimalPlan.getPlanElements().get(3)).setDepartureTime((((ActivityImpl) optimalPlan.getPlanElements().get(2))).getEndTime());
		((LegImpl) optimalPlan.getPlanElements().get(3)).setArrivalTime(((LegImpl) optimalPlan.getPlanElements().get(3)).getDepartureTime() + ((LegImpl) optimalPlan.getPlanElements().get(3)).getTravelTime());
		((ActivityImpl) optimalPlan.getPlanElements().get(4)).setStartTime(((LegImpl) optimalPlan.getPlanElements().get(3)).getArrivalTime());

		Double altScore2 = calcScore(f, optimalPlan);

		assertTrue(bestScore > altScore1);
		assertTrue(bestScore > altScore2);
	}


	private void handleFirstActivity(EventsToScore eventsToScore, Fixture f, Activity activity) {
		eventsToScore.handleEvent(new ActivityEndEvent(activity.getEndTime(), f.person.getId(), activity.getLinkId(), activity.getFacilityId(), activity.getType()));
	}

	private void handleLastActivity(EventsToScore eventsToScore, Fixture f, Activity activity) {
		eventsToScore.handleEvent(new ActivityStartEvent(activity.getStartTime(), f.person.getId(), activity.getLinkId(), activity.getFacilityId(), activity.getType()));
	}

	private void handleLeg(EventsToScore eventsToScore, Fixture f, Leg leg) {
		eventsToScore.handleEvent(new PersonDepartureEvent(leg.getDepartureTime(), f.person.getId(), leg.getRoute().getStartLinkId(), leg.getMode()));
		if (leg.getRoute() instanceof NetworkRoute) {
			NetworkRoute networkRoute = (NetworkRoute) leg.getRoute();
			eventsToScore.handleEvent(new LinkLeaveEvent(leg.getDepartureTime(), f.person.getId(), leg.getRoute().getStartLinkId(), networkRoute.getVehicleId()));
			for (Id<Link> linkId : networkRoute.getLinkIds()) {
				eventsToScore.handleEvent(new LinkEnterEvent(leg.getDepartureTime(), f.person.getId(), linkId, networkRoute.getVehicleId()));
				eventsToScore.handleEvent(new LinkLeaveEvent(leg.getDepartureTime(), f.person.getId(), linkId, networkRoute.getVehicleId()));
			}
			eventsToScore.handleEvent(new LinkEnterEvent(leg.getDepartureTime() + leg.getTravelTime(), f.person.getId(), leg.getRoute().getEndLinkId(), null));
		} else {
			eventsToScore.handleEvent(new TeleportationArrivalEvent(leg.getDepartureTime() + leg.getTravelTime(), f.person.getId(), leg.getRoute().getDistance()));
		}
		eventsToScore.handleEvent(new PersonArrivalEvent(leg.getDepartureTime() + leg.getTravelTime(), f.person.getId(), leg.getRoute().getEndLinkId(), leg.getMode()));
	}

	private void handleActivity(EventsToScore eventsToScore, Fixture f, Activity activity) {
		eventsToScore.handleEvent(new ActivityStartEvent(activity.getStartTime(), f.person.getId(), activity.getLinkId(), activity.getFacilityId(), activity.getType()));
		eventsToScore.handleEvent(new ActivityEndEvent(activity.getEndTime(), f.person.getId(), activity.getLinkId(), activity.getFacilityId(), activity.getType()));
	}

	@Test
	public void testTypicalActivityDuration() throws Exception {

		Fixture f = new Fixture();
		PlanCalcScoreConfigGroup scoring = f.config.planCalcScore();

		// setup activity types h and w for scoring
		PlanCalcScoreConfigGroup.ActivityParams params = new PlanCalcScoreConfigGroup.ActivityParams("home");
		params.setTypicalDuration(14 * 3600);
		scoring.addActivityParams(params);

		params = new PlanCalcScoreConfigGroup.ActivityParams("work");
		params.setTypicalDuration(9 * 3600);
		params.setOpeningTime(9 * 3600);
		params.setClosingTime(18 * 3600);
		scoring.addActivityParams(params);

		OptimalWalkPlanFinder optimalWalkPlanFinder = new OptimalWalkPlanFinder(f.scenario.getConfig());
		Plan optimalPlan = optimalWalkPlanFinder.findOptimalWalkPlan(f.plan);

		assertEquals(8.5 * 3600, ((ActivityImpl) optimalPlan.getPlanElements().get(0)).getEndTime(), EPSILON);
		assertEquals(18.0 * 3600, ((ActivityImpl) optimalPlan.getPlanElements().get(2)).getEndTime(), EPSILON);

		runScoreTest(optimalPlan, f);
	}


	@Test
	public void testNoTimePressurelActivityDuration() throws Exception {

		Fixture f = new Fixture();
		PlanCalcScoreConfigGroup scoring = f.config.planCalcScore();

		// setup activity types h and w for scoring
		PlanCalcScoreConfigGroup.ActivityParams params = new PlanCalcScoreConfigGroup.ActivityParams("home");
		params.setTypicalDuration(12 * 3600);
		scoring.addActivityParams(params);

		params = new PlanCalcScoreConfigGroup.ActivityParams("work");
		params.setTypicalDuration(9 * 3600);
		params.setOpeningTime(9*3600);
		params.setClosingTime(18*3600);
		scoring.addActivityParams(params);

		OptimalWalkPlanFinder optimalWalkPlanFinder = new OptimalWalkPlanFinder(f.scenario.getConfig());
		Plan optimalPlan = optimalWalkPlanFinder.findOptimalWalkPlan(f.plan);

		assertEquals(8.5*3600,((ActivityImpl) optimalPlan.getPlanElements().get(0)).getEndTime(),EPSILON);
		assertEquals(18.0*3600,((ActivityImpl) optimalPlan.getPlanElements().get(2)).getEndTime(),EPSILON);

		runScoreTest(optimalPlan, f);
	}

	@Test
	public void testNoTimePressureAndSmallTypicalDurationlActivityDuration() throws Exception {

		Fixture f = new Fixture();
		PlanCalcScoreConfigGroup scoring = f.config.planCalcScore();

		// setup activity types h and w for scoring
		PlanCalcScoreConfigGroup.ActivityParams params = new PlanCalcScoreConfigGroup.ActivityParams("home");
		params.setTypicalDuration(13 * 3600);
		scoring.addActivityParams(params);

		params = new PlanCalcScoreConfigGroup.ActivityParams("work");
		params.setTypicalDuration(9 * 3600);
		params.setOpeningTime(9 * 3600);
		params.setClosingTime(18*3600);
		scoring.addActivityParams(params);

		OptimalWalkPlanFinder optimalWalkPlanFinder = new OptimalWalkPlanFinder(f.scenario.getConfig());
		Plan optimalPlan = optimalWalkPlanFinder.findOptimalWalkPlan(f.plan);

		assertEquals(8.5*3600,((ActivityImpl) optimalPlan.getPlanElements().get(0)).getEndTime(),EPSILON);
		assertEquals((9*3600+9*(23/22)*3600),((ActivityImpl) optimalPlan.getPlanElements().get(2)).getEndTime(),EPSILON);

		runScoreTest(optimalPlan, f);
	}


	@Test
	public void testTimePressure() throws Exception {

		Fixture f = new Fixture();
		PlanCalcScoreConfigGroup scoring = f.config.planCalcScore();

		// setup activity types h and w for scoring
		PlanCalcScoreConfigGroup.ActivityParams params = new PlanCalcScoreConfigGroup.ActivityParams("home");
		params.setTypicalDuration(24 * 3600);
		scoring.addActivityParams(params);

		params = new PlanCalcScoreConfigGroup.ActivityParams("work");
		params.setTypicalDuration(9 * 3600);
		params.setOpeningTime(9 * 3600);
		params.setLatestStartTime(9 * 3600);
		params.setEarliestEndTime(17 * 3600);
		params.setClosingTime(18*3600);
		scoring.addActivityParams(params);

		OptimalWalkPlanFinder optimalWalkPlanFinder = new OptimalWalkPlanFinder(f.scenario.getConfig());
		Plan optimalPlan = optimalWalkPlanFinder.findOptimalWalkPlan(f.plan);

		assertEquals(8.5*3600,((ActivityImpl) optimalPlan.getPlanElements().get(0)).getEndTime(),EPSILON);
		assertEquals(17*3600,((ActivityImpl) optimalPlan.getPlanElements().get(2)).getEndTime(),EPSILON);

		runScoreTest(optimalPlan, f);
	}


	private static class Fixture {
		protected Config config = null;
		private PersonImpl person = null;
		private PlanImpl plan = null;
		private Scenario scenario;
		private NetworkImpl network;
		private int firstLegStartTime;
		private int firstLegTravelTime;
		private int secondLegTravelTime;
		private int secondLegStartTime;


		public Fixture() {
			firstLegStartTime = 8 * 3600 + 1800;
			firstLegTravelTime = 30 * 60;
			secondLegStartTime = 18 * 3600;
			secondLegTravelTime = 30 * 60;

			// home act end 8am
			// work 7:30 to 10:00

			// home 15:15 to ...

			this.config = ConfigUtils.createConfig();

			PlanCalcScoreConfigGroup scoring = this.config.planCalcScore();
			scoring.setBrainExpBeta(1.0);

			scoring.setConstantWalk(0.0);

			scoring.setEarlyDeparture_utils_hr(-3.0);
			scoring.setLateArrival_utils_hr(-3.0);
			scoring.setMarginalUtlOfWaiting_utils_hr(0.0);
			scoring.setPerforming_utils_hr(1.0);
			scoring.setTravelingWalk_utils_hr(-1.0);

			scoring.setMarginalUtilityOfMoney(1.);


			// setup activity types h and w for scoring
			PlanCalcScoreConfigGroup.ActivityParams params = new PlanCalcScoreConfigGroup.ActivityParams("home");
			params.setTypicalDuration(14 * 3600);
			scoring.addActivityParams(params);


			params = new PlanCalcScoreConfigGroup.ActivityParams("work");
			params.setTypicalDuration(9 * 3600);
			scoring.addActivityParams(params);


			this.scenario = ScenarioUtils.createScenario(config);
			this.network = (NetworkImpl) this.scenario.getNetwork();
			Node node1 = this.network.createAndAddNode(Id.create("1", Node.class), new CoordImpl(0.0, 0.0));
			Node node2 = this.network.createAndAddNode(Id.create("2", Node.class), new CoordImpl(1000.0, 0.0));

			Link link1 = this.network.createAndAddLink(Id.create("1", Link.class), node1, node2, 1000, 25, 3600, 1);

			this.person = new PersonImpl(Id.create("1", Person.class));
			this.plan = this.person.createAndAddPlan(true);

			CoordImpl homeLocation = new CoordImpl(0.0,1.0);
			CoordImpl workLocation = new CoordImpl(1153.8461538461538,1.0);

			ActivityImpl firstActivity = this.plan.createAndAddActivity("home", homeLocation);
			firstActivity.setEndTime(firstLegStartTime);

			Leg leg = this.plan.createAndAddLeg(TransportMode.walk);
			leg.setDepartureTime(firstLegStartTime);
			leg.setTravelTime(firstLegTravelTime);
			Route route1 = new GenericRouteImpl(link1.getId(), link1.getId());
			route1.setTravelTime(firstLegTravelTime);
			route1.setDistance(CoordUtils.calcDistance(homeLocation, workLocation));
			leg.setRoute(route1);

			ActivityImpl secondActivity = this.plan.createAndAddActivity("work", workLocation);
			secondActivity.setStartTime(firstLegStartTime + firstLegTravelTime);
			secondActivity.setEndTime(secondLegStartTime);

			leg = this.plan.createAndAddLeg(TransportMode.walk);
			leg.setDepartureTime(secondLegStartTime);
			leg.setTravelTime(secondLegTravelTime);
			Route route2 = new GenericRouteImpl(link1.getId(), link1.getId());
			route2.setTravelTime(secondLegTravelTime);
			route2.setDistance(CoordUtils.calcDistance(homeLocation, workLocation));
			leg.setRoute(route2);

			ActivityImpl lastActivity = this.plan.createAndAddActivity("home", homeLocation);
			lastActivity.setStartTime(secondLegStartTime + secondLegTravelTime);

			this.scenario.getPopulation().addPerson(this.person);
		}
	}
}