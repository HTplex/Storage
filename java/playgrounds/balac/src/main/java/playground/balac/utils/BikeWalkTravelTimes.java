package playground.balac.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.core.utils.io.IOUtils;


public class BikeWalkTravelTimes {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		final BufferedReader readLink = IOUtils.getBufferedReader("C:/Users/balacm/Desktop/coord_new.txt");
		final BufferedWriter outLink = IOUtils.getBufferedWriter("C:/Users/balacm/Desktop/travelTimesWalkBik_new.txt");

		//final BufferedReader readLink = IOUtils.getBufferedReader("C:/Users/balacm/Desktop/coordinates.txt");
		String s = readLink.readLine();
		s = readLink.readLine();
		outLink.write("personId walkTime bikeTime");
		outLink.newLine();
		while(s != null) {
			String[] arr = s.split("\t");
			CoordImpl coordStart = new CoordImpl(arr[1], arr[2]);
			CoordImpl coordEnd = new CoordImpl(arr[3], arr[4]);
		
			outLink.write(arr[0] + " ");
			double travelTimeWalk = CoordUtils.calcDistance(coordStart, coordEnd) * 1.3 / 1.1667;
			double travelTimeBike = CoordUtils.calcDistance(coordStart, coordEnd) * 1.3 / 3.3;
			outLink.write(Double.toString(travelTimeWalk) + " " + Double.toString(travelTimeBike));
			outLink.newLine();
			s = readLink.readLine();

	}
		outLink.flush();
		outLink.close();
	}

}
