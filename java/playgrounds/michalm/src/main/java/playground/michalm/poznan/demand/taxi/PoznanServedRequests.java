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

package playground.michalm.poznan.demand.taxi;

import java.util.*;

import playground.michalm.poznan.demand.taxi.ServedRequests.WeekDay;
import playground.michalm.poznan.zone.PoznanZones;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.vividsolutions.jts.geom.MultiPolygon;


public class PoznanServedRequests
{
    //e.g. months = "234"
    public static List<ServedRequest> readRequests(int... months)
    {
        List<ServedRequest> requests = new ArrayList<>();
        String path = "d:/PP-rad/taxi/poznan-supply/zlecenia_obsluzone/Zlecenia_obsluzone_2014-0";

        for (int m : months) {
            new ServedRequestsReader(requests).readFile(path + m + ".csv");
        }

        return requests;
    }


    public static Iterable<ServedRequest> filterRequestsWithinAgglomeration(
            Iterable<ServedRequest> requests)
    {
        MultiPolygon area = PoznanZones.readAgglomerationArea();
        return Iterables.filter(requests, ServedRequests.createWithinAreaPredicate(area));
    }


    public static Iterable<ServedRequest> filterNormalPeriods(Iterable<ServedRequest> requests)
    {
        //February - 1-28 (4 full weeks)
        //March - 2-29 (4 full weeks) - exclude: 1, 30-31 (daylight saving time shift)
        //April - 1-14 + 23-29 (3 full weeks), exclude: 15-22, 30 (Easter and May's long weekend)

        @SuppressWarnings("unchecked")
        //TODO WEIRD JAVAC COMPILER PROBLEM:
        //necessary casting from Predicate<ServedRequest> to Predicate<? super ServedRequest>
        Predicate<? super ServedRequest> orPredicate = Predicates.or(
                (Predicate<? super ServedRequest>)ServedRequests.createBetweenDatesPredicate(
                        midnight("01-03"), midnight("02-03")),
                (Predicate<? super ServedRequest>)ServedRequests.createBetweenDatesPredicate(
                        midnight("30-03"), midnight("01-04")),
                (Predicate<? super ServedRequest>)ServedRequests.createBetweenDatesPredicate(
                        midnight("15-04"), midnight("23-04")),
                (Predicate<? super ServedRequest>)ServedRequests.createBetweenDatesPredicate(
                        midnight("30-04"), midnight("01-05")));

        return Iterables.filter(requests, Predicates.not(orPredicate));
    }


    private static Date midnight(String date)
    {
        //format: "dd-MM-yyyy HH:mm:ss"
        return ServedRequestsReader.parseDate(date + "-2014 00:00:00");
    }


    public static Iterable<ServedRequest> filterWorkDaysPeriods(Iterable<ServedRequest> requests)
    {
        Predicate<ServedRequest> predicate = new Predicate<ServedRequest>() {
            public boolean apply(ServedRequest request)
            {
                WeekDay wd = WeekDay.getWeekDay(request.assigned);

                switch (wd) {
                    case MON:
                        return request.assigned.getHours() >= 4;

                    case TUE:
                    case WED:
                    case THU:
                        return true;

                    case SAT:
                    case SUN:
                        return false;

                    case FRI:
                        return request.assigned.getHours() < 4;

                    default:
                        throw new IllegalArgumentException();
                }
            }
        };

        return Iterables.filter(requests, predicate);
    }


    public static Iterable<ServedRequest> filterNext24Hours(Iterable<ServedRequest> requests,
            Date fromDate)
    {
        Date toDate = new Date(fromDate.getTime() + 24 * 3600 * 1000);
        return Iterables.filter(requests,
                ServedRequests.createBetweenDatesPredicate(fromDate, toDate));
    }
}
