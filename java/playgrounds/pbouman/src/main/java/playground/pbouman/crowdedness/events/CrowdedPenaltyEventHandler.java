package playground.pbouman.crowdedness.events;

import org.matsim.core.events.handler.EventHandler;

public interface CrowdedPenaltyEventHandler extends EventHandler
{
	public void handleEvent(CrowdedPenaltyEvent event);
}
