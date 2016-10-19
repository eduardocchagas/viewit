package br.ufop.chartgenerator.data.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory.ProvidedInterface;
import br.ufop.chartgenerator.data.model.CsvSummary;
import br.ufop.harviewer.model.EntryTimings;
import br.ufop.harviewer.model.PageTimings;


public class DataTest2 {
	
	

	public static void main(String[] args) {
		ICsvPreview csvPreview = (ICsvPreview)CsvPreviewFactory
								 .createInstance(ProvidedInterface.ICSVPREVIEW);
		csvPreview.readCsvFile("ufop.csv");
		
		CsvSummary csvSummary = csvPreview.getCsvSummary();
		
		
		
		printBoxPlotChart(csvSummary, "boxPlot3");
		
		csvPreview.printTest();
	
	}
	
	public static void printBoxPlotChart(CsvSummary csvSummary, String title){
		BoxAndWhiskerCategoryDataset dataset = createDataset(csvSummary);
        JFreeChart boxPlotChart = ChartFactory.createBoxAndWhiskerChart(title,
        																"Page",
        																"Time(ms)",
        																dataset,
        																true);//legenda
        
        File fileChart = new File(title+".png");
		try {
			ChartUtilities.saveChartAsPNG(fileChart, boxPlotChart, 640, 480);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
	private static BoxAndWhiskerCategoryDataset createDataset(CsvSummary csvSummary) {
		DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		
		List<String> pages = csvSummary.getPageNames();//lista de paginas
		
		String[] timeType = {"blocking", "waiting", "receiving", "onContentLoad", "onLoad","totalOfTimings"};
		
		for(String type : timeType){
			for(String page : pages){
				List<Double> dataList = new ArrayList<Double>();
				List<EntryTimings> entrys = csvSummary.getEntryTimingsList(page);
				List<PageTimings> times = csvSummary.getPageTimingsList(page);
				for(EntryTimings entry : entrys){
					if(type.equals("blocking")){
						dataList.add(new Double(entry.getBlocking()));
					}else if(type.equals("waiting")){
						dataList.add(new Double(entry.getWaiting()));
					}else if(type.equals("receiving")){
						dataList.add(new Double(entry.getReceiving()));
					}
				}
				for(PageTimings time : times){
					if(type.equals("onContentLoad")){
						dataList.add(new Double(time.getOnContentLoad()));
					}else if(type.equals("onLoad")){
						dataList.add(new Double(time.getOnload()));
					}
				}
				if(type.equals("totalOfTimings")){
					for(EntryTimings entry : entrys)
						dataList.add(new Double(entry.getTotalOfTimings()));
				}
				dataset.add(dataList,type,page);
			}
		}
		
		
		return dataset;
	}
	
}
