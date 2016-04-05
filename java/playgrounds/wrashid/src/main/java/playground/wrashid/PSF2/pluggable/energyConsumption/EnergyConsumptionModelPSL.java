/* *********************************************************************** *
 * project: org.matsim.*
 * EnergyConsumptionModelPSL.java
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

package playground.wrashid.PSF2.pluggable.energyConsumption;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.contrib.parking.lib.DebugLib;
import org.matsim.vehicles.VehicleType;

import playground.wrashid.PSF.data.energyConsumption.AverageEnergyConsumptionGalus;
import playground.wrashid.PSF2.vehicle.vehicleFleet.Vehicle;

public class EnergyConsumptionModelPSL implements EnergyConsumptionModel {

	AverageEnergyConsumptionGalus phevEnergyConsumptionModel;
	private double maxAllowedSpeedInNetworkInKmPerHour;

	public EnergyConsumptionModelPSL(double maxAllowedSpeedInNetworkInKmPerHour) {
		phevEnergyConsumptionModel = new AverageEnergyConsumptionGalus();
		this.maxAllowedSpeedInNetworkInKmPerHour = maxAllowedSpeedInNetworkInKmPerHour;
	}

	@Override
	public double getEnergyConsumptionForLinkInJoule(Vehicle vehicle,
			double timeSpentOnLink, Link link) {
		double speedOfVehicleOnLinkInKmPerHour = Vehicle
				.getAverageSpeedOfVehicleOnLinkInMetersPerSecond(
						timeSpentOnLink, link) / 1000 * 3600;

		if (speedOfVehicleOnLinkInKmPerHour > maxAllowedSpeedInNetworkInKmPerHour) {
			return 0;
		}

		if (vehicle.getVehicleClassId().equals(Id.create(1, VehicleType.class))) {
			// NOTE: phevs must have class Id one in this case
			return phevEnergyConsumptionModel.getEnergyConsumption(
					Vehicle.getAverageSpeedOfVehicleOnLinkInMetersPerSecond(timeSpentOnLink, link), link.getLength());
		} else if (vehicle.getVehicleClassId().equals(Id.create(2, VehicleType.class))) {
			return 0;
		} else {
			DebugLib
					.stopSystemAndReportInconsistency("must implement energy consumption of this vehicle class, before using this model: vehicleClassId="
							+ vehicle.getVehicleClassId());
			return 0;
		}
	}

}
