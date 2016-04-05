package patryk.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonStuckEvent;
import org.matsim.api.core.v01.events.Wait2LinkEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonStuckEventHandler;
import org.matsim.api.core.v01.events.handler.Wait2LinkEventHandler;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;



public class StuckAgents implements PersonStuckEventHandler {
	ArrayList<Id<Link>> linkIDs = new ArrayList<>();
	ArrayList<Id<Person>> personIDs = new ArrayList<>();

	@Override
	public void reset(int iteration) {
		linkIDs.clear();
		personIDs.clear();
	}

	@Override
	public void handleEvent(PersonStuckEvent event) {
		linkIDs.add(event.getLinkId());
		personIDs.add(event.getPersonId());
	}
	
	public ArrayList<Id<Link>> getPersonStuck() {
		return linkIDs;
	}
	
	public ArrayList<Id<Person>> getPersonStuckIDs() {
		return personIDs;
	}

}
