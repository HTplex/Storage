package playground.wrashid.parkingSearch.planLevel.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.matsim.api.core.v01.Id;
import org.matsim.contrib.parking.lib.GeneralLib;
import org.matsim.facilities.ActivityFacility;

import playground.wrashid.parkingSearch.planLevel.occupancy.ParkingCapacity;
import playground.wrashid.parkingSearch.planLevel.occupancy.ParkingOccupancyBins;

public class ParkingOccupancyAnalysis {

	private final HashMap<Id<ActivityFacility>, ParkingOccupancyBins> parkingOccupancyBins;
	private final ParkingCapacity parkingCapacity;
	private static LinkedList<Double> numberOfParkingViolationSlotsInIteration;

	public ParkingOccupancyAnalysis(
			HashMap<Id<ActivityFacility>, ParkingOccupancyBins> parkingOccupancyBins,
			ParkingCapacity parkingCapacity) {
		super();
		this.parkingOccupancyBins = parkingOccupancyBins;
		this.parkingCapacity = parkingCapacity;
	}

	public static void updateStatisticsForIteration(int iterationNumber,
			ParkingOccupancyAnalysis poaWriter) {
		
		if (iterationNumber==0){
			// initialize static variable
			numberOfParkingViolationSlotsInIteration = new LinkedList<Double>();
		}
		
		if (numberOfParkingViolationSlotsInIteration.size() != iterationNumber) {
			throw new Error(numberOfParkingViolationSlotsInIteration.size() + "!=" + iterationNumber);
		}

		double[][] matrix=poaWriter.getMatrix();
		
		double totalNumberOfParkingViolationSlots = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j=0;j<matrix[0].length;j++){
				if (matrix[i][j]<0){
					// note: we can iterate also over facility ids (assuming, they are positiv)
					totalNumberOfParkingViolationSlots+=matrix[i][j];
				}
			}
		}

		numberOfParkingViolationSlotsInIteration.add(totalNumberOfParkingViolationSlots);
	}

	public static void writeStatisticsGraph(String fileName){
			String xLabel = "Iteration";
			String yLabel = "numberOfParkingViolationSlots";
			String title="Number Of Slots where a Parking Violation happened.";
			int numberOfXValues = numberOfParkingViolationSlotsInIteration.size();
			int numberOfFunctions = 1;
			double[] xValues=new double[numberOfXValues];
			String[] seriesLabels=new String[numberOfFunctions];
			
			seriesLabels[0]="number of slots with parking violations";
			
			double[][] matrix=new double[numberOfXValues][numberOfFunctions];
			
			for (int i=0;i<numberOfXValues;i++){
				matrix[i][0]=numberOfParkingViolationSlotsInIteration.get(i);
				xValues[i]=i;
			}

			GeneralLib.writeGraphic(fileName, matrix, title, xLabel, yLabel, seriesLabels, xValues);
	}

	public void writeTxtFile(String filePath) {
		double[][] matrix = getMatrix();
		String headerLine = "parkingFacilityId" + "\t" + "parkingCapacity"
				+ "\t";

		for (int i = 0; i < 96; i++) {
			headerLine += i * 15 / 60.0 + "h-slot\t";
		}

		GeneralLib.writeMatrix(matrix, filePath, headerLine);
	}

	public double[][] getMatrix() {
		int numberOfParkings = parkingCapacity.getNumberOfParkings();
		double[][] matrix = new double[numberOfParkings][98];

		ArrayList<ActivityFacility> parkingFacilities = parkingCapacity
				.getParkingFacilities();

		// fill matrix data
		for (int i = 0; i < parkingFacilities.size(); i++) {
			Id parkingFacilityId = parkingFacilities.get(i).getId();
			matrix[i][0] = Integer.parseInt(parkingFacilityId.toString());
			matrix[i][1] = parkingCapacity.getParkingCapacity(parkingFacilities
					.get(i).getId());

			for (int j = 0; j < 96; j++) {
				if (parkingOccupancyBins.get(parkingFacilityId) != null) {

					// instead of just writing the occupancy, the unused
					// capacity is written out
					// therefore a minus number means a capacity violation.
					matrix[i][j + 2] = matrix[i][1]
							- parkingOccupancyBins.get(parkingFacilityId)
									.getOccupancy()[j];
				} else {
					// null means, that parking facility was not used during
					// whole simulation
					matrix[i][j + 2] = matrix[i][1];
				}
			}
		}
		return matrix;
	}

	public void writeFakeKMLFile(String filePath) {
		// docu: http://www.gpsvisualizer.com/tutorials/data.html#colorizing
		// http://www.gpsvisualizer.com/map_input?form=data

		String headerLine = "name" + "\t" + "parkingCapacity" + "\t"
				+ "occupation" + "\t" + "latitude" + "\t" + "longitude";

		ArrayList<String> fileContent = new ArrayList<String>();
		fileContent.add(headerLine);

		double[][] matrix = getMatrix();
		matrix = GeneralLib.trimMatrix(matrix, matrix.length, 5);

		double x_y_max = 10000000.0;

		ArrayList<ActivityFacility> parkingFacilities = parkingCapacity
				.getParkingFacilities();

		for (int i = 0; i < parkingFacilities.size(); i++) {
			double x = parkingFacilities.get(i).getCoord().getX();
			double y = parkingFacilities.get(i).getCoord().getY();
			double latitude = y / x_y_max * 90;
			double longitude = x / x_y_max * 180;

			matrix[i][3] = latitude;
			matrix[i][4] = longitude;

			if (matrix[i][2] < 0) {
				// only add, if negative capacity
				for (int j = 0; j < 5; j++) {
					fileContent.add(matrix[i][0] + "\t" + matrix[i][1] + "\t"
							+ matrix[i][2] + "\t" + matrix[i][3] + "\t"
							+ matrix[i][4]);
				}
			}

		}

		GeneralLib.writeList(fileContent, filePath);
		// GeneralLib.writeMatrix(matrix, filePath, headerLine);
	}

}
