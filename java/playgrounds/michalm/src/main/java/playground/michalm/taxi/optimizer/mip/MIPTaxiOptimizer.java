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

package playground.michalm.taxi.optimizer.mip;

import java.util.*;

import org.matsim.contrib.dvrp.data.Requests;
import org.matsim.contrib.dvrp.router.TimeAsTravelDisutility;
import org.matsim.contrib.dvrp.router.tree.DijkstraWithDijkstraTreeCache;
import org.matsim.contrib.dvrp.util.time.TimeDiscretizer;
import org.matsim.core.router.util.*;
import org.matsim.core.trafficmonitoring.FreeSpeedTravelTime;

import playground.michalm.taxi.data.TaxiRequest;
import playground.michalm.taxi.optimizer.*;
import playground.michalm.taxi.schedule.*;
import playground.michalm.taxi.schedule.TaxiTask.TaxiTaskType;


public class MIPTaxiOptimizer
    extends AbstractTaxiOptimizer
{
    private final PathTreeBasedTravelTimeCalculator pathTravelTimeCalc;

    private boolean wasLastPlanningHorizonFull = false;//in order to run optimization for the first request
    private boolean hasPickedUpReqsRecently = false;

    private int optimCounter = 0;


    public MIPTaxiOptimizer(TaxiOptimizerConfiguration optimConfig)
    {
        super(optimConfig, new TreeSet<TaxiRequest>(Requests.ABSOLUTE_COMPARATOR), true);

        if (!optimConfig.scheduler.getParams().destinationKnown) {
            throw new IllegalArgumentException("Destinations must be known ahead");
        }

        TravelTime travelTime = new FreeSpeedTravelTime();
        TravelDisutility travelDisutility = new TimeAsTravelDisutility(travelTime);

        pathTravelTimeCalc = new PathTreeBasedTravelTimeCalculator(
                new DijkstraWithDijkstraTreeCache(optimConfig.context.getScenario().getNetwork(),
                        travelDisutility, travelTime, TimeDiscretizer.CYCLIC_24_HOURS));
    }


    @Override
    protected void scheduleUnplannedRequests()
    {
        if (unplannedRequests.isEmpty()) {
            //nothing new to be planned and we want to avoid extra re-planning of what has been
            //already planned (high computational cost while only marginal improvement) 
            return;
        }

        if (wasLastPlanningHorizonFull && //last time we planned as many requests as possible, and...
                !hasPickedUpReqsRecently) {//...since then no empty space has appeared in the planning horizon 
            return;
        }

        MIPProblem mipProblem = new MIPProblem(optimConfig, pathTravelTimeCalc);
        mipProblem.scheduleUnplannedRequests((SortedSet<TaxiRequest>)unplannedRequests);

        optimCounter++;
        if (optimCounter % 10 == 0) {
            System.err.println(optimCounter + "; time=" + optimConfig.context.getTime());
        }

        wasLastPlanningHorizonFull = mipProblem.isPlanningHorizonFull();
        hasPickedUpReqsRecently = false;
    }


    @Override
    protected boolean doReoptimizeAfterNextTask(TaxiTask newCurrentTask)
    {
        if (newCurrentTask.getTaxiTaskType() == TaxiTaskType.PICKUP) {
            hasPickedUpReqsRecently = true;
            return true;
        }

        return false;
    }
}
