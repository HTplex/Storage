/* *********************************************************************** *
 * project: kai
 * SimpleAdaptiveSignalEngine.java
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

package playground.kai.usecases.simpleadaptivesignalengine;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;
import org.matsim.api.core.v01.network.Link;
import org.matsim.core.controler.Controler;
import org.matsim.core.mobsim.framework.events.MobsimBeforeSimStepEvent;
import org.matsim.core.mobsim.framework.listeners.MobsimBeforeSimStepListener;
import org.matsim.core.mobsim.qsim.interfaces.Netsim;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.signals.mobsim.SignalizeableItem;
import org.matsim.core.mobsim.qsim.qnetsimengine.SignalGroupState;

/**
 * @author nagel
 *
 */
class SimpleAdaptiveSignal implements MobsimBeforeSimStepListener, LinkEnterEventHandler, LinkLeaveEventHandler {

	private Queue<Double> vehicleExitTimesOnLink5 = new LinkedList<Double>() ;
	private long cnt4 = 0 ;
	private long cnt5 = 0 ;
	private Controler controler;
	private Writer out ;
	
	class Result {
		int iteration ;
		double shareUp ;
		double shareDown ;
	} ;
	
	private List<Result> results = new ArrayList<Result>() ;
	
	public SimpleAdaptiveSignal(Controler controler) {
		this.controler = controler ;
	}

	@Override
	public void notifyMobsimBeforeSimStep(MobsimBeforeSimStepEvent e) {
		Netsim mobsim = (Netsim) e.getQueueSimulation() ;
		double now = mobsim.getSimTimer().getTimeOfDay() ;

		SignalizeableItem link4 = (SignalizeableItem) mobsim.getNetsimNetwork().getNetsimLink(Id.create("4", Link.class)) ;
		SignalizeableItem link5 = (SignalizeableItem) mobsim.getNetsimNetwork().getNetsimLink(Id.create("5", Link.class)) ;
		final Double dpTime = this.vehicleExitTimesOnLink5.peek();
		if ( dpTime !=null && dpTime < now && (long)now%4 < 4  ) {
			link4.setSignalStateAllTurningMoves(SignalGroupState.RED) ;
			link5.setSignalStateAllTurningMoves(SignalGroupState.GREEN) ;
		} else {
			link4.setSignalStateAllTurningMoves(SignalGroupState.GREEN) ;
			link5.setSignalStateAllTurningMoves(SignalGroupState.RED) ;
		}

//		if ( time<20.*60 && (long)time%2==0 ) { 
//			link4.setSignalStateAllTurningMoves(SignalGroupState.RED) ;
//		} else {
//			link4.setSignalStateAllTurningMoves(SignalGroupState.GREEN) ;
//		}
	}

	@Override
	public void handleEvent(LinkEnterEvent event) {
		if ( event.getLinkId().equals(Id.create("5", Link.class)) ) {
			this.vehicleExitTimesOnLink5.add( event.getTime() + 100. ) ;
			// yy replace "100" by freeSpeedTravelTime
			// jedes Fahrzeug, welches die Kante betritt, erhöht den Zähler um 1:
			cnt5++ ;
		} 
		if ( event.getLinkId().equals(Id.create("4", Link.class)) ) {
			cnt4++ ;
		}
	}
	@Override
	public void handleEvent(LinkLeaveEvent event) {
		if ( event.getLinkId().equals(Id.create("5", Link.class)) ) {
			// jedes Fahrzeug, welches die Kante verlässg, erniedrigt den Zähler um 1:
			this.vehicleExitTimesOnLink5.remove() ;
		}
	}

	@Override
	public void reset(int iteration) {
		double sum = cnt4 + cnt5 ;
		Logger.getLogger(this.getClass()).warn("iteration: " + iteration + " cnt4: " + (cnt4/sum) + " cnt5: " + (cnt5/sum) ) ; 
		
		Result result = new Result() ;
		result.iteration = iteration ;
		result.shareUp = cnt5/sum ;
		result.shareDown = cnt4/sum ;
		results.add(result) ;
		
		cnt4 = 0 ;
		cnt5 = 0 ;

		if ( out==null ) {
			out = IOUtils.getBufferedWriter(controler.getControlerIO().getOutputFilename("split.txt")) ;
		}

		
		try {
			out.write( result.iteration + "\t" + result.shareUp + "\t" + result.shareDown + "\n" ) ;
			out.flush() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	static public void main( String[] args ) {
		Main.main( args ) ;
	}

}
