package br.ufop.chartgenerator.data.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory.ProvidedInterface;
import br.ufop.chartgenerator.data.model.CsvSummary;
import br.ufop.harviewer.model.ContentInfo;


public class DataTest {
	
	

	public static void main(String[] args) {
		ICsvPreview csvPreview = (ICsvPreview)CsvPreviewFactory
								 .createInstance(ProvidedInterface.ICSVPREVIEW);
		csvPreview.readCsvFile("ufop.csv");
		
		CsvSummary csvSummary = csvPreview.getCsvSummary();
		
		List<String> pages = csvSummary.getPageNames();
		
		for(String name : pages){
			ContentInfo contentInfo = csvSummary.getContentInfoByPage(name);
			
			printChartTotalResources(name, contentInfo);
			printChartResources(name, contentInfo);
		}
		
		csvPreview.printTest();
	
	}
	public static void printChartTotalResources(String pageName, ContentInfo contentInfo){
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		if(contentInfo.getTotalHtml() > 0)
			pieDataset.setValue("HTML", contentInfo.getTotalHtml());
		if(contentInfo.getTotalCss() > 0)
			pieDataset.setValue("CSS", contentInfo.getTotalCss());
		if(contentInfo.getTotalJavaScript() > 0)
			pieDataset.setValue("JAVASCRIPT", contentInfo.getTotalJavaScript());
		if(contentInfo.getTotalImage() > 0)
			pieDataset.setValue("IMAGE", contentInfo.getTotalImage());
		if(contentInfo.getTotalOthers() > 0)
			pieDataset.setValue("OTHERS", contentInfo.getTotalOthers());
		
		JFreeChart pieChart = ChartFactory.createPieChart(pageName + " - Número de recursos",//title
														 pieDataset,//dataset
														 true,//legend
														 false,
														 false);
		File fileChart = new File(pageName+"ttr.png");
		try {
			ChartUtilities.saveChartAsPNG(fileChart, pieChart, 640, 480);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void printChartResources(String pageName, ContentInfo contentInfo){
		DefaultPieDataset pieDatabase= new DefaultPieDataset();
		if(contentInfo.getHtml() > 0)
			pieDatabase.setValue("HTML", contentInfo.getHtml());
		if(contentInfo.getCss() > 0)
			pieDatabase.setValue("CSS", contentInfo.getCss());
		if(contentInfo.getJavaScript() > 0)
			pieDatabase.setValue("JAVASCRIPT", contentInfo.getJavaScript());
		if(contentInfo.getImage() > 0)
			pieDatabase.setValue("IMAGE", contentInfo.getImage());
		if(contentInfo.getOthers() > 0)
			pieDatabase.setValue("OTHERS", contentInfo.getOthers());
		
		JFreeChart pieChart = ChartFactory.createPieChart(pageName + " - Total de recursos",//title
				 pieDatabase,//dataset
				 true,//legend
				 false,
				 false);
		
		File fileChart = new File(pageName+"tr.png");
		try {
			ChartUtilities.saveChartAsPNG(fileChart, pieChart, 640, 480);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
