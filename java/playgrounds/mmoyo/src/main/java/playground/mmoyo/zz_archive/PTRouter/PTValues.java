/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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

package playground.mmoyo.zz_archive.PTRouter;

/**
 * This class contains common constant values for routing
 */

@Deprecated
public abstract class PTValues {
	
	final static byte ACCESS_BYT 	= 1;
	final static byte STANDARD_BYT 	= 2;
	final static byte TRANSFER_BYT 	= 3;
	public final static byte DETTRANSFER_BYT= 4;
	public final static byte EGRESS_BYT 	= 5;
	
	public final static String ACCESS_STR 		= "Access";
	public final static String STANDARD_STR 	= "Standard";
	public final static String TRANSFER_STR 	= "Transfer";
	public final static String DETTRANSFER_STR	= "DetTransfer";
	public final static String EGRESS_STR 		= "Egress";
	
	public final static double AV_WALKING_SPEED = 1/1.34;  //1.34 [Weidmann93], [Antonini2004].  0.836 by [Al-Azzawi 07]  
	public final static double DETTRANSFER_RANGE = 300;	//300 original distance to search station to build det transfer links
	public final static double FIRST_WALKRANGE = 600;  	//initial distance for station search 
	public final static double WALKRANGE_EXT = 300;   		//progressive extension distance of the station search
	
	public final static int INI_STATIONS_NUM = 2;			//number of stations to find in order to start the route search

	public static byte routerCalculator = 1;
	public static String scenarioName ;
	public static double distanceCoefficient =0.15;
	public static double timeCoefficient = 0.85;
	public static double transferPenalty = 60.0;
	public static double walkCoefficient = 1.0;
	
	public static boolean fragmentPlans = false;
	public static boolean noCarPlans = false;
	public static boolean compressPlan = false;
	public static boolean allowDirectWalks = false;
}


/*
public double Person_WalkSpeed(final byte age, final double time, final double length){

	//-> complete values according to Weidmann 1993 and set arrays as final
	byte[] arrAges= [0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80];   
	double[] ageSpeed= [x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x];
	
	//-> validate results ou of range
	return ageSpeed[Arrays.binarySearch(arrAges, age)];
}

	public double firstWalkRange(){
	//public int distToWalk(final int personAge){
	//-> complete personalized values.
	return 600;
}*/
