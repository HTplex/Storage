package playground.balac.allcsmodestest.events;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.network.Link;

public class NoVehicleCarSharingEvent extends Event{

	public static final String EVENT_TYPE = "no carsharing vehicle";
	
	private final Id<Link> linkId;
	
	private final String carsharingType;
	
	public NoVehicleCarSharingEvent(double time, Id<Link> linkId, String carsharingType) {
		super(time);
		this.linkId = linkId;
		this.carsharingType = carsharingType;
	}

	@Override
	public String getEventType() {
		return EVENT_TYPE;
	}
	
	public Id<Link> getLinkId(){
		return this.linkId;
	}
	
	public String getCarsharingType() {
		return this.carsharingType;
	}

}
