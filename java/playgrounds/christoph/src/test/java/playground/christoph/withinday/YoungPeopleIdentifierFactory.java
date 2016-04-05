/* *********************************************************************** *
 * project: org.matsim.*
 * YoungPeopleIdentifierFactory.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2010 by the members listed in the COPYING,        *
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

package playground.christoph.withinday;

import org.matsim.core.mobsim.qsim.interfaces.Netsim;
import org.matsim.withinday.replanning.identifiers.interfaces.DuringLegAgentSelector;
import org.matsim.withinday.replanning.identifiers.interfaces.DuringLegIdentifierFactory;

public class YoungPeopleIdentifierFactory extends DuringLegIdentifierFactory {

	private Netsim mobsim;
	
	public YoungPeopleIdentifierFactory(Netsim mobsim) {
		this.mobsim = mobsim;
	}
	
	@Override
	public DuringLegAgentSelector createIdentifier() {
		DuringLegAgentSelector identifier = new YoungPeopleIdentifier(mobsim);
		identifier.setAgentSelectorFactory(this);
		return identifier;
	}

}
