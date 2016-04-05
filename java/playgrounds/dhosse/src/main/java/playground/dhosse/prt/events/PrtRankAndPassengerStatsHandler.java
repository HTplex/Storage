package playground.dhosse.prt.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.PersonEntersVehicleEvent;
import org.matsim.api.core.v01.events.PersonLeavesVehicleEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.events.handler.PersonEntersVehicleEventHandler;
import org.matsim.api.core.v01.events.handler.PersonLeavesVehicleEventHandler;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.dvrp.MatsimVrpContextImpl;
import org.matsim.contrib.dvrp.data.Vehicle;
import org.matsim.contrib.minibus.genericUtils.RecursiveStatsContainer;
import org.matsim.core.controler.events.IterationEndsEvent;

import playground.dhosse.prt.data.PrtData;
import playground.michalm.taxi.data.TaxiRank;

public class PrtRankAndPassengerStatsHandler implements ActivityStartEventHandler, ActivityEndEventHandler,
	PersonEntersVehicleEventHandler, PersonLeavesVehicleEventHandler {

	protected Map<Id<TaxiRank>, Integer> rankIds2NumberOfBoardingPassengers = new HashMap<Id<TaxiRank>, Integer>();
	protected Map<Id<TaxiRank>, Integer> rankIds2NumberOfAlightingPassengers = new HashMap<Id<TaxiRank>, Integer>();
	protected Map<Id<TaxiRank>, RecursiveStatsContainer> rankIds2PassengerWaitingTimes = new HashMap<Id<TaxiRank>, RecursiveStatsContainer>();
	
	protected Map<Id<TaxiRank>,Double> rankIds2FirstRequestTimes = new HashMap<Id<TaxiRank>, Double>();
	
	protected Map<Id<Person>,Double> personId2WaitingTime = new HashMap<Id<Person>,Double>();
	
	//temp
	protected Map<Id<Person>,Double> personId2BeginWaitingTime = new HashMap<Id<Person>, Double>();
	protected Map<Id<TaxiRank>,List<Id<Person>>> rankId2WaitingPassengersList = new HashMap<Id<TaxiRank>, List<Id<Person>>>();
	
	protected MatsimVrpContextImpl context;
	protected PrtData data;
	
	public PrtRankAndPassengerStatsHandler(MatsimVrpContextImpl context, PrtData data){
		this.context = context;
		this.data = data;
	}
	
	@Override
	public void reset(int iteration) {
		
		this.personId2BeginWaitingTime.clear();
		this.personId2WaitingTime.clear();
		this.rankIds2NumberOfAlightingPassengers.clear();
		this.rankIds2NumberOfBoardingPassengers.clear();
		this.rankIds2PassengerWaitingTimes.clear();
		this.rankId2WaitingPassengersList.clear();
		
	}

	@Override
	public void handleEvent(PersonEntersVehicleEvent event) {
		
		Id<Vehicle> vehicleId = Id.create(event.getVehicleId().toString(), Vehicle.class);
		
		if(!vehicleId.toString().equals(event.getPersonId().toString())){
			//it's not the driver
			int indexOfVehicle = 0;
			for(Vehicle v : this.context.getVrpData().getVehicles()){
				if(v.getId().equals(vehicleId)){
					indexOfVehicle = this.context.getVrpData().getVehicles().indexOf(v);
					break;
				}
			}
			
			Link link = this.context.getScenario().getNetwork().getLinks().get(this.context.getVrpData().getVehicles().get(indexOfVehicle).getAgentLogic().getDynAgent().getCurrentLinkId());
			TaxiRank rank = this.data.getNearestRank(link);
			
			if(!this.rankIds2NumberOfBoardingPassengers.containsKey(rank.getId())){
				
				this.rankIds2NumberOfBoardingPassengers.put(rank.getId(), 0);
				
			}
			
			int nPassengers = this.rankIds2NumberOfBoardingPassengers.get(rank.getId()) + 1;
			
			this.rankIds2NumberOfBoardingPassengers.put(rank.getId(), nPassengers);
			
			
			if(this.personId2BeginWaitingTime.get(event.getPersonId()) != null){
				
				double personWaitingTime = event.getTime() - this.personId2BeginWaitingTime.get(event.getPersonId());
				
				if(!this.rankIds2PassengerWaitingTimes.containsKey(rank.getId())){
					this.rankIds2PassengerWaitingTimes.put(rank.getId(), new RecursiveStatsContainer());
				}
				
				if(!this.personId2WaitingTime.containsKey(event.getPersonId())){
					
					this.personId2WaitingTime.put(event.getPersonId(), 0.);
					
				}
					
				double wtime = this.personId2WaitingTime.get(event.getPersonId()) + personWaitingTime;
				this.personId2WaitingTime.put(event.getPersonId(), wtime);
					
				this.rankIds2PassengerWaitingTimes.get(rank.getId()).handleNewEntry(personWaitingTime);
				
				this.rankId2WaitingPassengersList.get(rank.getId()).remove(event.getPersonId());
				this.personId2BeginWaitingTime.remove(event.getPersonId());
				
			}

		}
		
	}

	@Override
	public void handleEvent(PersonLeavesVehicleEvent event) {
		
		Id<Vehicle> vehicleId = Id.create(event.getVehicleId().toString(), Vehicle.class);
		
		if(!vehicleId.toString().equals(event.getPersonId().toString())){
			//it's not the driver
			int indexOfVehicle = 0;
			for(Vehicle v : this.context.getVrpData().getVehicles()){
				if(v.getId().equals(vehicleId)){
					indexOfVehicle = this.context.getVrpData().getVehicles().indexOf(v);
					break;
				}
			}
			
			Link link = this.context.getScenario().getNetwork().getLinks().get(this.context.getVrpData().getVehicles().get(indexOfVehicle).getAgentLogic().getDynAgent().getCurrentLinkId());
			TaxiRank rank = this.data.getNearestRank(link);
			
			if(!this.rankIds2NumberOfAlightingPassengers.containsKey(rank.getId())){
				
				this.rankIds2NumberOfAlightingPassengers.put(rank.getId(), 0);
				
			}
			
			int nPassengers = this.rankIds2NumberOfAlightingPassengers.get(rank.getId()) + 1;
			
			this.rankIds2NumberOfAlightingPassengers.put(rank.getId(), nPassengers);
			
		}
		
	}

	@Override
	public void handleEvent(ActivityEndEvent event) {
		
		if(event.getActType().equals("pt interaction")){
			
			Link link = this.context.getScenario().getNetwork().getLinks().get(event.getLinkId());
			TaxiRank rank = this.data.getNearestRank(link);
			
			this.personId2BeginWaitingTime.put(event.getPersonId(), event.getTime());
			
			if(!this.rankId2WaitingPassengersList.containsKey(rank.getId())){
			
				this.rankId2WaitingPassengersList.put(rank.getId(), new ArrayList<Id<Person>>());
				
			}
			
			if(!this.rankIds2FirstRequestTimes.containsKey(rank.getId())){
				this.rankIds2FirstRequestTimes.put(rank.getId(), event.getTime());
			}
			
			this.rankId2WaitingPassengersList.get(rank.getId()).add(event.getPersonId());
			
		}
		
	}

	@Override
	public void handleEvent(ActivityStartEvent event) {
	
		if(event.getActType().equals("home")||event.getActType().equals("work")){
			
			this.personId2BeginWaitingTime.remove(event.getPersonId());
			
		}
		
	}
	
	public Map<Id<TaxiRank>,Double> getTimesOfFirstRequestByRankId(){
		return this.rankIds2FirstRequestTimes;
	}
	
	public void finalize(IterationEndsEvent event){
		
		//finishes unprocessed waiting time entries and set waiting time to simEndTime - beginWaitingTime
		double endTime = event.getControler().getConfig().qsim().getEndTime();

		for(Entry<Id<TaxiRank>, List<Id<Person>>> entry : this.rankId2WaitingPassengersList.entrySet()){
			
			for(Id<Person> personId : entry.getValue()){
				
				if(this.personId2BeginWaitingTime.containsKey(personId)){

					double waitingTime = endTime - this.personId2BeginWaitingTime.get(personId);
					
					if(!this.rankIds2PassengerWaitingTimes.containsKey(entry.getKey())){
						
						this.rankIds2PassengerWaitingTimes.put(entry.getKey(), new RecursiveStatsContainer());
						
					}
					
					this.rankIds2PassengerWaitingTimes.get(entry.getKey()).handleNewEntry(waitingTime);
					
					if(!this.personId2WaitingTime.containsKey(personId)){
						this.personId2WaitingTime.put(personId, 0.);
					}
					
					double wtime = this.personId2WaitingTime.get(personId) + waitingTime;
					this.personId2WaitingTime.put(personId, wtime);
					
				}
				
				this.personId2BeginWaitingTime.remove(personId);
				
			}
			
		}
	}

}
