package playground.wrashid.parkingSearch.planLevel.strc2010;

import playground.wrashid.lib.RunLib;

/**
 * 
 * @author wrashid
 * 
 */
public class Run12 {
	public static void main(String[] args) {
		int runNumber=RunLib.getRunNumber(new Object() { }.getClass().getEnclosingClass());
		RunSeries.getControler(runNumber).run();
	}

}
