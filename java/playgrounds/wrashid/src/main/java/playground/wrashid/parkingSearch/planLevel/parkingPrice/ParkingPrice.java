package playground.wrashid.parkingSearch.planLevel.parkingPrice;

public abstract class ParkingPrice {

		
	
	/**
	 * How much must be paid for parking at the specified parking for the given
	 * startParking and endParking times.
	 * @param startParkingTime
	 * @param endParkingTime
	 * @return
	 */
	public abstract double getPrice(double startParkingTime, double endParkingTime);
	
	
	
}
