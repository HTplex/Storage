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

package playground.johannes.gsv.synPop;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author johannes
 *
 */
public class ProxyObject {

	private Map<String, String> attributes;
	
	private Map<String, String> unmodAttribs;
	
	private Map<Object, Object> userData;
	
	public Map<String, String> getAttributes() {
		initAttriutes();
		return unmodAttribs;
	}
	
	public String getAttribute(String key) {
		initAttriutes();
		return attributes.get(key);
	}
	
	public String setAttribute(String key, String value) {
		initAttriutes();
		return attributes.put(key, value);
	}
	
	public String removeAttribute(String key) {
		if(attributes != null) {
			return attributes.remove(key);
		} else {
			return null;
		}
	}
	
	public ProxyObject clone() {
		ProxyObject clone = new ProxyObject();
		
		for(Entry<String, String> entry : attributes.entrySet()) {
			clone.setAttribute(entry.getKey(), entry.getValue());
		}
		
		return clone;
	}
	
	public Object getUserData(Object key) {
		initUserData();
		return userData.get(key);
	}
	
	public Object setUserData(Object key, Object value) {
		initUserData();
		return userData.put(key, value);
	}
	
	private void initAttriutes() {
		if(attributes == null) {
			attributes = new HashMap<String, String>(5);
			unmodAttribs = Collections.unmodifiableMap(attributes);
		}
	}
	
	private void initUserData() {
		if(userData == null) {
			userData = new HashMap<Object, Object>(5);
		}
	}
}
