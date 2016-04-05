/* *********************************************************************** *
 * project: org.matsim.*
 * DgProgram
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
package playground.dgrether.koehlerstrehlersignal.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;

/**
 * @author dgrether
 * @author tthunig
 *
 */
public class DgProgram {
	
	private static final Logger log = Logger.getLogger(DgProgram.class);
	
	private Id<DgProgram> id;
	private int cycle = 0;
	private int offset = 0;
	private Map<Id<DgGreen>, DgGreen> green = new HashMap<>();
	
	
	public DgProgram(Id<DgProgram> id){
		this.id = id;
	}
	
	public void addGreen(DgGreen g){
		if (this.green.containsKey(g.getLightId())){
			log.warn("Green for light " + g.getLightId() + " already exists!");
		}
		this.green.put(g.getLightId(), g);
	}
	
	public Map<Id<DgGreen>, DgGreen> getGreensByLightId(){
		return this.green;
	}

	
	public int getCycle() {
		return cycle;
	}

	
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	
	public Id<DgProgram> getId() {
		return id;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
