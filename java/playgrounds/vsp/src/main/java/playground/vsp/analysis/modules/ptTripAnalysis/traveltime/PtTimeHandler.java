/* *********************************************************************** *
 * project: org.matsim.*
 * Plansgenerator.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
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
package playground.vsp.analysis.modules.ptTripAnalysis.traveltime;

import java.util.ArrayList;
import java.util.List;

import org.matsim.api.core.v01.events.Event;


/**
 * @author droeder
 *
 */
public class PtTimeHandler {
	private List<SomePtTime> times;
	private Double first = null;
	private Double last = 0.0;
	
	public PtTimeHandler(){
		this.times = new ArrayList<SomePtTime>();
		
		//here it would be possible to register other instances of SomePtTime
		times.add(new AccesWait());
		times.add(new AccesWalk());
		times.add(new LineTT());
		times.add(new SwitchWait());
		times.add(new SwitchWalk());
	}
	
	public void handleEvent(Event e){
		if(first == null){
			first = e.getTime();
		}else{
			last = e.getTime();
		}
		//handle event for all instances of SomePtTime
		for(SomePtTime t: this.times){
			t.handleEvent(e);
		}
	}
	
	/**
	 * updates all values of the given trip
	 * @param trip
	 */
	public void finish(TTAnalysisTrip trip){
		trip.tripTTime = last - first;
		for(SomePtTime t: this.times){
			if ( t instanceof AccesWait){
				trip.accesWaitCnt = t.getCount();
				trip.accesWaitTime = t.getTime();
			}else if(t instanceof AccesWalk){
				trip.accesWalkCnt = t.getCount();
				trip.accesWalkTTime = t.getTime();
			}else if(t instanceof LineTT){
				trip.lineCnt = t.getCount();
				trip.lineTTime = t.getTime();
			}else if(t instanceof SwitchWait){
				trip.switchWaitCnt = t.getCount();
				trip.switchWaitTime = t.getTime();
			}else if(t instanceof SwitchWalk){
				trip.switchWalkCnt = t.getCount();
				trip.switchWalkTTime = t.getTime();
				trip.egressWalkCnt = 1;
				trip.egressWalkTTime = ((SwitchWalk) t).getEgressWalkTime();
			}
		}
	}
}
