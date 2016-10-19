package br.ufop.chartgenerator.data.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory.ProvidedInterface;
import br.ufop.chartgenerator.data.model.CsvSummary;

public class DataTest4 {
	
	public static void main(String[] args) {
		ICsvPreview csvPreview = (ICsvPreview)CsvPreviewFactory
				 .createInstance(ProvidedInterface.ICSVPREVIEW);
		csvPreview.readCsvFile("ufop.csv");
		
		CsvSummary csvSummary = csvPreview.getCsvSummary();
		printLineChart(csvSummary, "LineChart1","");
	}
	
	
	public static void printLineChart(CsvSummary csvSummary, String title, String chartFolder){
		CategoryDataset dataset = createLineDataset(csvSummary);
		JFreeChart lineChart = ChartFactory.createLineChart(title,
														"Date - Hour",
														"Time(ms)",
														dataset,
														PlotOrientation.VERTICAL,
														true,
														true,
														false);
		CategoryPlot plot = (CategoryPlot)lineChart.getPlot();
        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
 
        lineChart.setBackgroundPaint(ChartColor.WHITE);
		File fileChart = new File(title+".png");
		try {
			ChartUtilities.saveChartAsPNG(fileChart, lineChart, 640, 480);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static CategoryDataset createLineDataset(CsvSummary csvSummary) {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		List<String> pages = csvSummary.getPageNames();
		List<String> harFileNames = csvSummary.getHarNamesList(pages.get(0));
		List<String> categoryHarName = new ArrayList<String>();
		
		for(String harFileName : harFileNames){
			String partes[] = harFileName.split("\\+");
			String date[] = partes[1].split("-");
			String hour[] = partes[2].split("-");
			categoryHarName.add(date[2]+"/"+date[1]+"/"+date[0]+"-"+hour[0]+":"+hour[1]);
			
		}
		
		for(String page : pages){
			for(int i = 0; i < categoryHarName.size(); i++){
				result.addValue(new Double(csvSummary.getEntryTimingsList(page).get(i).getTotalOfTimings() + 
										   csvSummary.getPageTimingsList(page).get(i).getOnContentLoad()), 
									page, categoryHarName.get(i));
			}
		}
		
		return result;
	}
}
