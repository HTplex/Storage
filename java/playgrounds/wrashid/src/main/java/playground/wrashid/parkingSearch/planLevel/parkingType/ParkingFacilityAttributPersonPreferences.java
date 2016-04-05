package playground.wrashid.parkingSearch.planLevel.parkingType;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.population.ActivityImpl;

public interface ParkingFacilityAttributPersonPreferences {

	public ParkingAttribute getParkingFacilityAttributPreferencesOfPersonForActivity(Id<Person> personId, ActivityImpl activity);
	
}
