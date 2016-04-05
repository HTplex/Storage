/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2014 by the members listed in the COPYING,        *
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

package playground.johannes.gsv.synPop.sim3;

import java.util.List;
import java.util.Random;

import playground.johannes.coopsim.mental.choice.ChoiceSet;
import playground.johannes.gsv.synPop.ProxyPerson;

/**
 * @author johannes
 * 
 */
public class MutatorComposite implements Mutator {

	private final ChoiceSet<Mutator> mutators;

	private Mutator active;

	public MutatorComposite(Random random) {
		mutators = new ChoiceSet<>(random);
	}

	public void addMutator(Mutator mutator) {
		mutators.addChoice(mutator);
	}
	
	@Override
	public List<ProxyPerson> select(List<ProxyPerson> persons) {
		active = mutators.randomChoice();
		return active.select(persons);
	}

	@Override
	public boolean modify(List<ProxyPerson> persons) {
		return active.modify(persons);
	}

	@Override
	public void revert(List<ProxyPerson> persons) {
		active.revert(persons);

	}

}
