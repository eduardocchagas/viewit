package br.ufop.chartgenerator.data.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory.ProvidedInterface;
import br.ufop.chartgenerator.data.model.CsvSummary;

public class DataTest3 {

	public static void main(String[] args) {
		ICsvPreview csvPreview = (ICsvPreview)CsvPreviewFactory
				 .createInstance(ProvidedInterface.ICSVPREVIEW);
		csvPreview.readCsvFile("ufop.csv");
		
		CsvSummary csvSummary = csvPreview.getCsvSummary();
		printBarChart(csvSummary, "BarChart1");
	}
	
	public static void printBarChart(CsvSummary csvSummary, String title){
		CategoryDataset dataset = createDataset2(csvSummary);
		JFreeChart barChart = ChartFactory.createStackedBarChart(
	        	title,
	            "Pages",                  // domain axis label
	            "Time (ms)",                     // range axis label
	            dataset,                     // data
	            PlotOrientation.HORIZONTAL,  // the plot orientation
	            true,                        // include legend
	            true,                        // tooltips
	            false                        // urls
	        );
		File fileChart = new File(title+".png");
		try {
			ChartUtilities.saveChartAsPNG(fileChart, barChart, 640, 480);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static CategoryDataset createDataset2(CsvSummary csvSummary){
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		
		List<String> pages = csvSummary.getPageNames();//lista de paginas
		
		List<String> timeType = new ArrayList<String>();
		timeType.add("blocking");
		timeType.add("waiting");
		timeType.add("receiving");
		timeType.add("onContentLoad");
		timeType.add("onLoad");
		
		for(String type : timeType){
			for(String page : pages){
				if(type.equals("blocking")){
					result.addValue(new Double(csvSummary.getEntryTimingsList(page).get(0).getBlocking()),
										type, page);
				}else if(type.equals("waiting")){
					result.addValue(new Double(csvSummary.getEntryTimingsList(page).get(0).getWaiting()), 
										type, page);
				}else if(type.equals("receiving")){
					result.addValue(new Double(csvSummary.getEntryTimingsList(page).get(0).getReceiving()),
										type, page);
				}else if(type.equals("onContentLoad")){
					result.addValue(new Double(csvSummary.getPageTimingsList(page).get(0).getOnContentLoad()), 
										type, page);
				}else if(type.equals("onLoad")){
					result.addValue(new Double(csvSummary.getPageTimingsList(page).get(0).getOnload()), 
										type, page);
				}
			}
		}
		
		return result;
	}

}
