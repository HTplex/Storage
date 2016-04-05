/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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
package playground.yu.visum.filter.finalFilters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.utils.misc.Time;

import playground.yu.visum.writer.UserDefAtt;

/**
 * @author ychen
 */
public abstract class LinkAveCalA extends FinalEventFilterA {
	// -------------------------PRIVATE CLASS------------------
	/**
	 * 
	 * contains the function to compute the average traveltime or -speed.
	 */
	private static class BinAttcUnit {
		/*------------------------MEMBER VARIABLE------------------------------*/
		private int cnt;

		private double sum;

		// * -------------------------CONSTRUCTOR---------------------------
		/**
		 * @param cnt
		 *            - counter
		 * @param sum
		 *            - the summe of the travoltime or travolspeed.
		 */
		public BinAttcUnit(int cnt, double sum) {
			this.cnt = cnt;
			this.sum = sum;
		}

		// ----------------------------GETTER------------------------------
		/**
		 * @return the sum.
		 */
		protected double getSum() {
			return sum;
		}

		/**
		 * @return the cnt.
		 */
		protected int getCnt() {
			return cnt;
		}

		// * -----------------------------SETTER---------------------------
		/**
		 * 
		 * @param sum
		 *            The sum to set.
		 * @uml.property name="sum"
		 */
		protected void setSum(double sum) {
			this.sum = sum;
		}

		/**
		 * @param cnt
		 *            The cnt to set.
		 * @uml.property name="cnt"
		 */
		protected void setCnt(int cnt) {
			this.cnt = cnt;
		}

	}

	/* ------------------------MEMBER VARIABLE------------------------------ */
	/**
	 * String - AgentID LinkEnterEvent - an event of LinkEnterEvent
	 */
	private final Map<String, LinkEnterEvent> enters = new HashMap<String, LinkEnterEvent>();

	protected long timeBinMinimum = -1;

	protected long timeBinMaximum = -1;

	/**
	 * a Map < link-ID, Map < period, BinAttcUnit >>; String - the ID of a link;
	 * Long - timeBin.
	 */
	private final Map<String, Map<Long, BinAttcUnit>> attcUnits = new HashMap<String, Map<Long, BinAttcUnit>>();

	// * --------------------------CONSTRUKTOR-----------------------
	/**
	 * @param plans
	 *            - a Plans-object of the simulation
	 * @param network
	 *            - a NetworkLayer-object of the simulation
	 */
	public LinkAveCalA(Population plans, Network network) {
		super(plans, network);
	}

	/*---------------------------OVERRIDE METHODS--------------------------*/
	@Override
	public void handleEvent(Event event) {
		if (event instanceof LinkEnterEvent) { // event.getClass().equals(LinkEnterEvent.class))
													// {
			enters.put(((LinkEnterEvent) event).getPersonId().toString(),
					(LinkEnterEvent) event);
		} else if (event instanceof LinkLeaveEvent) { // event.getClass().equals(LinkLeaveEvent.class))
															// {
			String agentId = ((LinkLeaveEvent) event).getPersonId()
					.toString();
			if (enters.containsKey(agentId)) {
				if (enters
						.get(agentId)
						.getLinkId()
						.toString()
						.equals(((LinkLeaveEvent) event).getLinkId()
								.toString())) {
					count();
					count();
					compute(enters.remove(agentId), event.getTime());
					timeBinReg((long) (event.getTime() / 900));
				}
			}
		}
	}

	// ------------------------PRIVATE METHODS-------------------------
	/**
	 * @param timeBin
	 *            - f.e. when timeBin=3, it means the time between 3:00-3:59
	 */
	private void timeBinReg(long timeBin) {
		if (timeBinMinimum == -1 || timeBin < timeBinMinimum) {
			timeBinMinimum = timeBin;
		}
		if (timeBinMaximum == -1 || timeBin > timeBinMaximum) {
			timeBinMaximum = timeBin;
		}

	}

	// -----------------------NORMAL MOTHODS--------------------------
	/**
	 * accumulates the average traveltime or travelspeed.
	 * 
	 * @param tt
	 *            - any input variable for example: average traveltime, average
	 *            travelspeed,...
	 * @param linkId
	 *            - an ID of link
	 * @param timeBin
	 *            - f.e. when timeBin=3, it means the time between 3:00-3:59
	 */
	public void computeInside(double tt, String linkId, long timeBin) {
		Map<Long, BinAttcUnit> baus;
		BinAttcUnit bau = new BinAttcUnit(1, tt);
		if (attcUnits.containsKey(linkId)) {
			baus = attcUnits.get(linkId);
			if (baus.containsKey(timeBin)) {
				bau = baus.get(timeBin);
				++bau.cnt;
				bau.sum += tt;
			}
		} else {
			baus = new TreeMap<Long, BinAttcUnit>();
		}
		baus.put(timeBin, bau);
		attcUnits.put(linkId, baus);
	}

	// *
	// ----------------------------------GETTER--------------------------------
	/**
	 * gets the result of the calculating of average traveltime or -speed.
	 * 
	 * @param linkId
	 *            - an ID of the link, whose result man wants to have
	 * @param time_s
	 *            - a timepoint with unit "second"
	 * @param info
	 *            - can be "speed" or "time", it depends on the object, who
	 *            calls the function. AveTraSpeCal - "speed"; AveTraTimeCal -
	 *            "time"
	 * @param unit
	 *            - the unit of the parameter "infor" "speed" - m/s; "time" - s.
	 * @return a value of the average traveltime or -speed on a link in certain
	 *         time (f.e. 3:00-6:00)
	 */
	public double getLinkCalResult(String linkId, int time_s, String info,
			String unit) {
		double result = 0.0;
		Long timeBin = Long.valueOf(time_s / 900);
		if (attcUnits.containsKey(linkId)) {
			Map<Long, BinAttcUnit> baus = attcUnits.get(linkId);
			if (baus.containsKey(timeBin)) {
				BinAttcUnit bau = baus.get(timeBin);
				result = bau.getSum() / bau.getCnt();
				return result;
			}
		}
		return result;
	}

	/**
	 * @return a set of key-elements of attcUnits.
	 */
	public Set<String> getLinkAveCalKeySet() {
		return attcUnits.keySet();
	}

	/**
	 * returns a protected List of UserDefAtt-objects.
	 * 
	 * @param attName
	 *            - the name of "benutzerdefiniertes Attribut" in VISUM
	 * @param attID
	 *            - the ID of "benutzerdefiniertes Attribut" in VISUM
	 * @return a protected List of UserDefAtt-objects.
	 */
	protected List<UserDefAtt> UDAexport(String attName, String attID) {
		if (udas.size() > 0) {
			return udas;
		}
		for (long i = timeBinMinimum; i < timeBinMaximum + 1; i++) {
			String an = attName + Time.writeTime(i * 900) + "-"
					+ Time.writeTime((i + 1) * 900 - 60);
			UserDefAtt uda = new UserDefAtt("LINK", i + attID, an, an,
					UserDefAtt.DatenTyp.Double, 2, UserDefAtt.SparseDocu.duenn);
			udas.add(uda);
		}
		return udas;
	}

	@Override
	public Map<String, List<Double>> UDAWexport() {
		if (udaws.size() > 0) {
			return udaws;
		}
		for (String linkID : getLinkAveCalKeySet()) {
			List<Double> udaw = new ArrayList<Double>();
			for (UserDefAtt uda : udas) {
				String[] s = uda.getATTID().split("A");
				udaw.add(atxCal(linkID, s[0]));
			}
			udaws.put(linkID, udaw);
		}
		return udaws;
	}

	// --------------------------ABSTRACT MOTHOD(S)----------------------------
	/**
	 * this abstract function does nothing. It should accumulate the average
	 * traveltime or travelspeed, if it is overridden by subclasses.
	 * 
	 * @param enter
	 *            a event called "entering a link"
	 * @param leaveTime_s
	 *            the point of time of the event called "leaving a link" with
	 *            unit second
	 */
	public abstract void compute(LinkEnterEvent enter, double leaveTime_s);

	/**
	 * This function does nothing and must be overridden by subclasses and
	 * determines the average traveltime or travelspeed.
	 * 
	 * @param linkID
	 *            - the linkID of a link for VISUM 9.3.
	 * @param timeBin
	 *            - a determined timeBin e.g. 0 for 00:00-00:14, 1 for
	 *            00:15-00:29 etc.
	 * @return the value of average traveltime or travelspeed
	 */
	protected abstract double atxCal(String linkID, String timeBin);
}
