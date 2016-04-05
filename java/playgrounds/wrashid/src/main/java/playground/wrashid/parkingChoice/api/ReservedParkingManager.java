package playground.wrashid.parkingChoice.api;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;

import playground.wrashid.parkingChoice.infrastructure.ActInfo;
import playground.wrashid.parkingChoice.infrastructure.ReservedParking;

/**
 * 
 * @author wrashid
 *
 */
public interface ReservedParkingManager {

	boolean considerForChoiceSet(ReservedParking reservedParking, Id<Person> personId, double OPTIONALtimeOfDayInSeconds, ActInfo targetActInfo);
	
}
