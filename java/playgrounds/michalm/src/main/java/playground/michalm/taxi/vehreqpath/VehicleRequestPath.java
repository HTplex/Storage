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

package playground.michalm.taxi.vehreqpath;

import org.matsim.contrib.dvrp.data.Vehicle;
import org.matsim.contrib.dvrp.router.VrpPathWithTravelData;

import playground.michalm.taxi.data.TaxiRequest;


public class VehicleRequestPath
{
    public final Vehicle vehicle;
    public final TaxiRequest request;
    public final VrpPathWithTravelData path;


    public VehicleRequestPath(Vehicle vehicle, TaxiRequest request, VrpPathWithTravelData path)
    {
        this.vehicle = vehicle;
        this.request = request;
        this.path = path;
    }
}