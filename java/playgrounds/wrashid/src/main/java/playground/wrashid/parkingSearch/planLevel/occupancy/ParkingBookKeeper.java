package playground.wrashid.parkingSearch.planLevel.occupancy;

import java.util.HashMap;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.contrib.parking.lib.DebugLib;
import org.matsim.core.controler.Controler;

import playground.wrashid.parkingSearch.planLevel.init.ParkingRoot;

public class ParkingBookKeeper implements ActivityStartEventHandler, ActivityEndEventHandler {

	private Controler controler;
	private ParkingOccupancyMaintainer parkingOccupancyMaintainer;

	// id: personId
	// true: starts parking/arrival
	// false: end parking/departure
	HashMap<Id, Boolean> parkingMode = new HashMap<Id, Boolean>();

	
	
	
	public ParkingOccupancyMaintainer getParkingOccupancyMaintainer() {
		return parkingOccupancyMaintainer;
	}

	public ParkingBookKeeper(Controler controler) {
		this.controler = controler;
		//parkingOccupancyMaintainer=new ParkingOccupancyMaintainer(controler);
		
		//ParkingRoot.setParkingOccupancyMaintainer(parkingOccupancyMaintainer);
		
		//reset(-1);
	}

	public void handleEvent(ActivityStartEvent event) {
		Id personId = event.getPersonId();
		
		if (event.getActType().equalsIgnoreCase("parking")) {
			if (!parkingMode.containsKey(personId)) {
				// init personParkingMode (first parking action in the morning
				// is departure)
				parkingMode.put(personId, false);
			}

			if (parkingMode.get(personId)==true){
				parkingOccupancyMaintainer.logArrivalAtParking(event);
			}
		}
	}

	@Override
	public void reset(int iteration) {
		DebugLib.startDebuggingInIteration(iteration);
		
		
		parkingOccupancyMaintainer=new ParkingOccupancyMaintainer(controler);
		ParkingRoot.setParkingOccupancyMaintainer(parkingOccupancyMaintainer);
		parkingOccupancyMaintainer.performInitializationsAfterLoadingControlerData();
	}

	@Override
	public void handleEvent(ActivityEndEvent event) {
		Id personId = event.getPersonId();

		if (event.getActType().equalsIgnoreCase("parking")) {
			if (parkingMode.get(personId)==false){
				parkingOccupancyMaintainer.logDepartureFromParking(event);
				parkingMode.put(personId, true);
			} else {
				parkingMode.put(personId, false);
			}
		}
	}



	public void performScoring() {
		
		// controler.getEvents().processEvent(new AgentMoneyEventImpl(3600.0,
		// event.getPersonId(), 3.4));
		
		// code snipet for adding scores:
		// events.processEvent(new AgentMoneyEvent(3600.0, person, 3.4));
	}

}
