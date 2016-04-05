/* *********************************************************************** *
 * project: org.matsim.*
 * ChartWriter
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2009 by the members listed in the COPYING,        *
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
package playground.dgrether.analysis.charts.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.matsim.core.utils.io.IOUtils;


/**
 * @author dgrether
 *
 */
public class DgChartWriter {
	
	private static final Logger log = Logger.getLogger(DgChartWriter.class);

	
	public static void writeChart(String filename, JFreeChart jchart){
		writeChartDataToFile(filename, jchart);
		writeToPng(filename, jchart);
	}
	
	public static void writeToPng(String filename, JFreeChart jchart) {
		filename += ".png";
		try {
			ChartUtilities.saveChartAsPNG(new File(filename), jchart, 1200, 800, null, true, 9);
			log.info("Chart written to : " +filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void writeChartDataToFile(String filename, JFreeChart chart) {
		filename += ".txt";
		try {
			BufferedWriter writer = IOUtils.getBufferedWriter(filename);
			try{ /*read "try" as if (plot instanceof XYPlot)*/
				XYPlot xy = chart.getXYPlot();
				String yAxisLabel = xy.getRangeAxis().getLabel();
				
				String xAxisLabel = "";
				if (xy.getDomainAxis() != null){
					xAxisLabel = xy.getDomainAxis().getLabel();
				}
				String header = "#" + xAxisLabel + "\t " + yAxisLabel;
				writer.write(header);
				writer.newLine();
			//write the header
				writer.write("#");
				for (int i = 0; i < xy.getDatasetCount(); i++){
					XYDataset xyds = xy.getDataset(i);
					int seriesIndex = 0;
					int maxItems = 0;
					int seriesCount = xyds.getSeriesCount();
					while (seriesIndex <  seriesCount){
						writer.write("Series " + xyds.getSeriesKey(seriesIndex).toString());
						if (seriesIndex < seriesCount - 1){
							writer.write("\t \t");
						}
						if (xyds.getItemCount(seriesIndex) > maxItems){
							maxItems = xyds.getItemCount(seriesIndex);
						}
						seriesIndex++;
					}
					writer.newLine();
					
					//write the data
					Number xValue, yValue = null;
					for (int itemsIndex = 0; itemsIndex < maxItems; itemsIndex++){
						for (int seriesIdx = 0; seriesIdx < seriesCount; seriesIdx ++) {
							if (seriesIdx < xyds.getSeriesCount() && itemsIndex < xyds.getItemCount(seriesIdx)){
								xValue = xyds.getX(seriesIdx, itemsIndex);
								yValue = xyds.getY(seriesIdx, itemsIndex);
								if (xValue != null && yValue != null){
									writer.write(xValue.toString());
									writer.write("\t");
									writer.write(yValue.toString());
									if (seriesIdx < seriesCount - 1){
										writer.write("\t");
									}
								}
							}
						}
						writer.newLine();
					}
				}
			} catch(ClassCastException e){ //else instanceof CategoryPlot
				log.info("Due to a caught class cast exception, it should be a CategoryPlot");
				CategoryPlot cp = chart.getCategoryPlot();
				String header = "# CategoryRowKey \t CategoryColumnKey \t CategoryRowIndex \t CategoryColumnIndex \t Value";
				writer.write(header);
				writer.newLine();
				for (int i = 0; i < cp.getDatasetCount(); i++) {
					CategoryDataset cpds = cp.getDataset(i);
					for (int rowIndex = 0; rowIndex < cpds.getRowCount(); rowIndex++){
						for (int columnIndex = 0; columnIndex < cpds.getColumnCount(); columnIndex ++) {
							Number value = cpds.getValue(rowIndex, columnIndex);
							writer.write(cpds.getRowKey(rowIndex).toString());
							writer.write("\t");
							writer.write(cpds.getColumnKey(columnIndex).toString());
							writer.write("\t");
							writer.write(Integer.toString(rowIndex));
							writer.write("\t");
							writer.write(Integer.toString(columnIndex));
							writer.write("\t");
							writer.write(value.toString());
							writer.newLine();
						}
					}
				}
				
				
			}
		  writer.close();
		  log.info("Chart data written to: " + filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
		
	}
	
}
