/* *********************************************************************** *
 * project: org.matsim.*
 * PHEV.java
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

package playground.wrashid.PSF2.vehicle.vehicleFleet;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.parking.lib.DebugLib;
import org.matsim.contrib.parking.lib.GeneralLib;
import org.matsim.vehicles.VehicleType;

import playground.wrashid.PSF.energy.charging.ChargeLog;
import playground.wrashid.PSF.energy.charging.ChargingTimes;
import playground.wrashid.PSF2.ParametersPSF2;
import playground.wrashid.PSF2.vehicle.energyStateMaintainance.EnergyStateMaintainer;
import playground.wrashid.lib.MathLib;

public class PlugInHybridElectricVehicle extends Vehicle {

	private double batterySizeInJoule;
	private double batteryMinThresholdInJoule;
	private double currentBatteryChargeInJoule;

	protected double electricEnergyUseInJouleDuringDayForDriving;

	public PlugInHybridElectricVehicle(EnergyStateMaintainer energyStateMaintainer, Id<VehicleType> vehicleClassId) {
		super(energyStateMaintainer, vehicleClassId);
	}

	public PlugInHybridElectricVehicle(Id<VehicleType> vehicleClassId) {
		super(vehicleClassId);
	}

	@Override
	public void updateEnergyState(double energyConsumptionOnLinkInJoule) {
		logEnergyConsumption(energyConsumptionOnLinkInJoule);

		if (getAvailbleBatteryCharge() >= energyConsumptionOnLinkInJoule) {
			processElectricityUsage(energyConsumptionOnLinkInJoule);
		} else if (getAvailbleBatteryCharge() > 0) {
			processElectricityUsage(getAvailbleBatteryCharge());
		}
	}

	private void processElectricityUsage(double energyConsumptionInJoule) {
		electricEnergyUseInJouleDuringDayForDriving += energyConsumptionInJoule;
		setCurrentBatteryChargeInJoule(getCurrentBatteryChargeInJoule() - energyConsumptionInJoule);

		if (getCurrentBatteryChargeInJoule() < 0) {
			DebugLib.stopSystemAndReportInconsistency();
		}
	}

	private double getAvailbleBatteryCharge() {
		return getCurrentBatteryChargeInJoule() - getBatteryMinThresholdInJoule();
	}

	public double getRequiredBatteryCharge() {
		return getBatterySizeInJoule() - getCurrentBatteryChargeInJoule();
	}

	private void chargeVehicle(double energyConsumptionInJoule) {
		setCurrentBatteryChargeInJoule(getCurrentBatteryChargeInJoule() + energyConsumptionInJoule);

		if (getCurrentBatteryChargeInJoule() > getBatterySizeInJoule()
				&& !MathLib.equals(getCurrentBatteryChargeInJoule(), getBatterySizeInJoule(), 0.1)) {
			DebugLib.stopSystemAndReportInconsistency();
		}
	}

	public void centralizedCharging(double arrivalTime, double chargingDuration, double plugSizeInWatt, Id<Link> linkId, Id facilityId) {
		double chargeInJoule = chargingDuration * plugSizeInWatt;

		logChargingTime(arrivalTime, chargingDuration, chargeInJoule, linkId);

		chargeVehicle(chargeInJoule);
	}

	private void logChargingTime(double arrivalTime, double chargingDuration, double chargeInJoule, Id<Link> linkId) {
		Id<Person> personId = ParametersPSF2.vehicles.getKey(this);
		double endChargingTime = GeneralLib.projectTimeWithin24Hours(arrivalTime + chargingDuration);
		

		if (ParametersPSF2.chargingTimes.get(personId) == null) {
			ParametersPSF2.chargingTimes.put(personId, new ChargingTimes());
		}

		ParametersPSF2.chargingTimes.get(personId)
				.addChargeLog(
						new ChargeLog(linkId, GeneralLib.projectTimeWithin24Hours(arrivalTime), endChargingTime,
								getCurrentBatteryChargeInJoule(), getCurrentBatteryChargeInJoule() + chargeInJoule, linkId));
	}

	public void setBatterySizeInJoule(double batterySizeInJoule) {
		this.batterySizeInJoule = batterySizeInJoule;
	}

	public double getBatterySizeInJoule() {
		return batterySizeInJoule;
	}

	public void setBatteryMinThresholdInJoule(double batteryMinThresholdInJoule) {
		this.batteryMinThresholdInJoule = batteryMinThresholdInJoule;
	}

	public double getBatteryMinThresholdInJoule() {
		return batteryMinThresholdInJoule;
	}

	public void setCurrentBatteryChargeInJoule(double currentBatteryChargeInJoule) {
		this.currentBatteryChargeInJoule = currentBatteryChargeInJoule;
	}

	public double getCurrentBatteryChargeInJoule() {
		return currentBatteryChargeInJoule;
	}

}
