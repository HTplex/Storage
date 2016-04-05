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

package playground.boescpa.lib.obj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * A hashmap that stores more than one value under the same key.
 *  
 * @author pboesch
 *
 * @param <K>	key
 * @param <V>	value
 */
public final class BoxedHashMap<K, V> {
	
	private HashMap<K, ArrayList<V>> boxedHashMap;
	
	public BoxedHashMap() {
		boxedHashMap = new HashMap<K, ArrayList<V>>();
	}
	
	public int size() {
		return boxedHashMap.size();
	}
	
	public boolean isEmpty() {
		return boxedHashMap.isEmpty();
	}
	
	/**
	 * Returns all values to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     */
	public ArrayList<V> getValues(K k) {
		if (boxedHashMap.containsKey(k)) {
			return boxedHashMap.get(k);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the first value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
	 */
	public V getValue(K k) {
		if (boxedHashMap.containsKey(k)) {
			return boxedHashMap.get(k).get(0);
		} else {
			return null;
		}
	}
	
	public boolean containsKey(K k) {
		return boxedHashMap.containsKey(k);
	}
	
	/**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the value
     * is added to the key's list of values.
     */
	public void put(K k, V v) {
		if (containsKey(k)) {
			getValues(k).add(v);
		} else {
			boxedHashMap.put(k, new ArrayList<V>());
			put(k,v);
		}
	}
	
	/**
	 * Removes from this map the specified key and with it all values to 
	 * which the is mapped.
     */
	public void remove(K k) {
		boxedHashMap.remove(k);
	}
	
	/**
	 * Removes from this map the last value mapped to this key.
     */
	public void removeLast(K k) {
		ArrayList<V> l = boxedHashMap.get(k);  
		l.remove(l.size()-1);
	}
	
	public void clear() {
		boxedHashMap.clear();
	}
	
	/**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.
     */
	public boolean containsValue(V v) {
		for (ArrayList<V> l:boxedHashMap.values()) {
			if (l.contains(v)) {
				return true;
			}
		}
		return false;
	}
	
	public Set<K> keySet() {
		return boxedHashMap.keySet();
	}
	
	public Collection<ArrayList<V>> values() {
		return boxedHashMap.values();
	}
}
