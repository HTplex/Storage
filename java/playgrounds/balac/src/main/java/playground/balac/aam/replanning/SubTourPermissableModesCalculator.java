package playground.balac.aam.replanning;

import java.util.ArrayList;
import java.util.Collection;

import org.matsim.api.core.v01.population.Plan;
import org.matsim.core.config.Config;
import org.matsim.core.population.PersonImpl;
import org.matsim.population.algorithms.PermissibleModesCalculator;

public class SubTourPermissableModesCalculator implements PermissibleModesCalculator{

	private Config config = null;
	public SubTourPermissableModesCalculator(Config config) {
		// TODO Auto-generated constructor stub
		this.config = config;
	}

	/**
	 * @param args
	 */
	
	@Override
	public Collection<String> getPermissibleModes(Plan plan) {
		ArrayList<String> modes = new ArrayList<String>();
		PersonImpl p = (PersonImpl) plan.getPerson();
		modes.add("bike");
		modes.add("walk");
		modes.add("pt");
		if (p.getLicense().equals( "yes" ) && !p.getCarAvail().equals( "never" )) 
			modes.add("car");
		
	//	 if (p.getTravelcards() != null && Boolean.parseBoolean(config.getModule("TwoWayCarsharing").getParams().get("useTwoWayCarsharing"))
		
	//	&& (p.getLicense().equals( "yes"))  && p.getTravelcards().contains("ch-HT-mobility"))
		
	//			modes.add("twowaycarsharing");
		
		return modes;
	}

}
