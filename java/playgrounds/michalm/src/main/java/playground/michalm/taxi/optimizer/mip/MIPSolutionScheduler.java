package playground.michalm.taxi.optimizer.mip;

import org.matsim.contrib.dvrp.data.Vehicle;
import org.matsim.contrib.dvrp.router.VrpPathWithTravelData;
import org.matsim.contrib.dvrp.util.LinkTimePair;

import playground.michalm.taxi.data.TaxiRequest;
import playground.michalm.taxi.optimizer.*;
import playground.michalm.taxi.optimizer.mip.MIPProblem.MIPSolution;
import playground.michalm.taxi.vehreqpath.VehicleRequestPath;


class MIPSolutionScheduler
{
    private final TaxiOptimizerConfiguration optimConfig;
    private final MIPRequestData rData;
    private final VehicleData vData;
    private final int m;
    private final int n;

    private MIPSolution solution;
    private Vehicle currentVeh;


    MIPSolutionScheduler(TaxiOptimizerConfiguration optimConfig, MIPRequestData rData,
            VehicleData vData)
    {
        this.optimConfig = optimConfig;
        this.rData = rData;
        this.vData = vData;
        this.m = vData.dimension;
        this.n = rData.dimension;
    }


    void updateSchedules(MIPSolution solution)
    {
        this.solution = solution;

        for (int k = 0; k < m; k++) {
            currentVeh = vData.entries.get(k).vehicle;
            appendSubsequentRequestsToCurrentVehicle(k);
        }
    }


    private void appendSubsequentRequestsToCurrentVehicle(int u)
    {
        boolean[] x_u = solution.x[u];
        for (int i = 0; i < n; i++) {
            if (x_u[m + i]) {
                appendRequestToCurrentVehicle(i);
                appendSubsequentRequestsToCurrentVehicle(m + i);
                return;
            }
        }
    }


    private void appendRequestToCurrentVehicle(int i)
    {
        LinkTimePair earliestDeparture = optimConfig.scheduler.getEarliestIdleness(currentVeh);
        TaxiRequest req = rData.requests[i];

        //use earliestDeparture.time instead of w[i]-tt (latest departure time) due to:
        // - possible inaccuracy of the optimization results
        //   (if x[.][m+i] = 0.9999 then w[i] < earliestDeparture.time may occur 
        // - we want to dispatch vehicles as soon as possible
        //   (because tt in MIP are based on the free flow speed estimates, while the actual
        //   times are usually longer hence the vehicle is likely to arrive after w[i])
        VrpPathWithTravelData path = optimConfig.calculator.calcPath(earliestDeparture.link,
                req.getFromLink(), earliestDeparture.time);

        VehicleRequestPath vrPath = new VehicleRequestPath(currentVeh, req, path);
        optimConfig.scheduler.scheduleRequest(vrPath);
    }
}
