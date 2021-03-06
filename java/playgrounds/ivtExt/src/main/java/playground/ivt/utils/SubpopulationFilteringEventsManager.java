/* *********************************************************************** *
 * project: org.matsim.*
 * SubpopulationFilteringEventsManager.java
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
package playground.ivt.utils;

import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.Id;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.api.experimental.events.LaneEnterEvent;
import org.matsim.core.api.experimental.events.LaneLeaveEvent;
import org.matsim.core.api.internal.HasPersonId;
import org.matsim.core.config.groups.PlansConfigGroup;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.handler.EventHandler;
import org.matsim.utils.objectattributes.ObjectAttributes;

/**
 * An events manager which (attempts to) transmit only events relevant for a given
 * subpopulation.
 * The "attempts to" in the previous sentence comes from the fact that one can
 * never be sure that an event is not specific to a person, even if it does not implement
 * {@link HasPerson}. Currently, events implementing {@link HasPerson}, as well as link
 * events, are filtered, the rest simply transmitted.
 * <br>
 * It always transmits only the events for one specific subpopulation: if non is provided,
 * it will do it for the default subpopulation (the unnamed one, normally corresponding to "normal"
 * agents).
 * <br>
 * Be aware that events of "transit driver agents" will show up in the default population,
 * but not in the others.
 *
 * @author thibautd
 */
public class SubpopulationFilteringEventsManager implements EventsManager {
	private final EventsManager delegate = EventsUtils.createEventsManager();
	private final Filter<Id> filter;

	/**
	 * Contructs an instance, using the default subpopulation and
	 * attribute name.
	 *
	 * @param personAtts the population attributes where the subpopulation information
	 * is stored
	 */
	public SubpopulationFilteringEventsManager(
			final ObjectAttributes personAtts ) {
		this( personAtts , null );
	}

	/**
	 * Contructs an instance, allowing to configure subpopulation name, using the default
	 * attribute name.
	 *
	 * @param personAtts the population attributes where the subpopulation information
	 * is stored
	 * @param subpop the subpopulation to analyse. <tt>null</tt> corresponds
	 * to the "default" (unnamed) subpopulation.
	 */
	public SubpopulationFilteringEventsManager(
			final ObjectAttributes personAtts,
			final String subpop ) {
		this( personAtts,
				// default
				new PlansConfigGroup().getSubpopulationAttributeName(),
				subpop );
	}

	/**
	 * Contructs an instance, allowing to configure attribute name and subpopulation name.
	 *
	 * @param personAtts the population attributes where the subpopulation information
	 * is stored
	 * @param attName the name of the subpopulation attribute in the ObjectAttributes
	 * @param subpop the subpopulation to analyse. <tt>null</tt> corresponds
	 * to the "default" (unnamed) subpopulation.
	 */
	public SubpopulationFilteringEventsManager(
			final ObjectAttributes personAtts,
			final String attName,
			final String subpop ) {
		this( new SubpopulationFilter( personAtts , attName , subpop ) );
	}

	public SubpopulationFilteringEventsManager(
			final Filter<Id> filter) {
		this.filter = filter;
	}

	@Override
	public void processEvent(Event event) {
		if ( event instanceof HasPersonId ) {
			final Id id = ((HasPersonId) event).getPersonId();
			if ( !filter.accept( id ) ) return;
		}

		// Those "core" events have a person but do not implement
		// HasPersonId: handle them specially
		if ( event instanceof LaneEnterEvent ) {
			final Id id = ((LaneEnterEvent) event).getPersonId();
			if ( !filter.accept( id ) ) return;
		}
		
		if ( event instanceof LaneLeaveEvent ) {
			final Id id = ((LaneLeaveEvent) event).getPersonId();
			if ( !filter.accept( id ) ) return;
		}
		
		if ( event instanceof LinkEnterEvent ) {
			final Id id = ((LinkEnterEvent) event).getPersonId();
			if ( !filter.accept( id ) ) return;
		}
		
		if ( event instanceof LinkLeaveEvent ) {
			final Id id = ((LinkLeaveEvent) event).getPersonId();
			if ( !filter.accept( id ) ) return;
		}
		
		delegate.processEvent(event);
	}

	@Override
	public void addHandler(EventHandler handler) {
		delegate.addHandler(handler);
	}

	@Override
	public void removeHandler(EventHandler handler) {
		delegate.removeHandler(handler);
	}

	@Override
	public void resetHandlers(int iteration) {
		delegate.resetHandlers(iteration);
	}

	@Override
	public void initProcessing() {
		delegate.initProcessing();
	}

	@Override
	public void afterSimStep(double time) {
		delegate.afterSimStep(time);
	}

	@Override
	public void finishProcessing() {
		delegate.finishProcessing();
	}
}

