/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2013 by the members listed in the COPYING,        *
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

package playground.michalm.taxi.schedule;

import org.matsim.contrib.dvrp.router.VrpPathWithTravelData;
import org.matsim.contrib.dvrp.schedule.DriveTaskImpl;

import playground.michalm.taxi.data.TaxiRequest;


public class TaxiDriveWithPassengerTask
    extends DriveTaskImpl
    implements TaxiTaskWithRequest
{
    private TaxiRequest request;//non-final due to vehicle diversion


    public TaxiDriveWithPassengerTask(VrpPathWithTravelData path, TaxiRequest request)
    {
        super(path);

        if (request.getFromLink() != path.getFromLink() && request.getToLink() != path.getToLink()) {
            throw new IllegalArgumentException();
        }

        this.request = request;
        request.setDriveWithPassengerTask(this);
    }


    @Override
    public void removeFromRequest()
    {
        request.setDriveWithPassengerTask(null);
    }


    @Override
    public TaxiTaskType getTaxiTaskType()
    {
        return TaxiTaskType.DRIVE_WITH_PASSENGER;
    }


    public TaxiRequest getRequest()
    {
        return request;
    }


    @Override
    protected String commonToString()
    {
        return "[" + getTaxiTaskType().name() + "]" + super.commonToString();
    }
}
