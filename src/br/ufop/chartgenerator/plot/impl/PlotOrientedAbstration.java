package br.ufop.chartgenerator.plot.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnit;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.CategoryStepRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.RectangleInsets;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.model.CsvSummary;
import br.ufop.chartgenerator.model.DataViews;
import br.ufop.chartgenerator.model.Pages;
import br.ufop.chartgenerator.plot.api.IPlotOrientedAbstration;
import br.ufop.harviewer.model.ContentInfo;
import br.ufop.harviewer.model.EntryTimings;
import br.ufop.harviewer.model.PageTimings;
import br.ufop.utils.skiplabel.CategoryAxisSkipLabels;

public class PlotOrientedAbstration implements IPlotOrientedAbstration{
	
	private final ICsvPreview csvPreview;
	
	public PlotOrientedAbstration(ICsvPreview csvPreview) {
		this.csvPreview = csvPreview;
	}

	@Override
	public void plotPieChart() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pieChart(String pageName, String type, String information) {
		if((type.toUpperCase()).equals("RESOURCE")){
			CsvSummary csvSummary = csvPreview.getCsvSummary();
			List<String> pages = new ArrayList<String>();
			if((pageName.toUpperCase()).equals("ALL")){
				pages = csvSummary.getPageNames();
			}else{
				pages.add(pageName);
			}
			for(String name : pages){
				ContentInfo contentInfo = csvSummary.getContentInfoByPage(name);
				if((information.toUpperCase()).equals("SIZE"))
					printChartResources(name, contentInfo);
				if((information.toUpperCase()).equals("QUANTITY"))
					printChartTotalResources(name, contentInfo);
			}
		}
	}
	
	@Override
	public void boxPlotChart(String chartFolder, String title, Pages pages, DataViews dataViews) {
		CsvSummary csvSummary = csvPreview.getCsvSummary();
		BoxAndWhiskerCategoryDataset dataset = createDataset(csvSummary,pages.getPages(),dataViews.getDataView());
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
	
	@Override
	public void barChart(String chartFolder, String title, Pages pages){
		CsvSummary csvSummary = csvPreview.getCsvSummary();
		CategoryDataset dataset = createDataset2(csvSummary,pages.getPages());
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
			ChartUtilities.saveChartAsPNG(fileChart, barChart, 800, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void lineChart(String chartFolder, String title, Pages pages) {
		CsvSummary csvSummary = csvPreview.getCsvSummary();
		CategoryDataset dataset = createLineDataset(csvSummary,pages.getPages());
		
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
		//CategoryAxis xAxis = new CategoryAxisSkipLabels();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		//((CategoryAxisSkipLabels)xAxis).setDisplaySkippedTickMarks(true);
		//NumberAxis rangeAxis = new NumberAxis("Value");
		//xAxis.setTickLabelsVisible(true);
		
		
		
        
		lineChart.setBackgroundPaint(ChartColor.WHITE);
		
		
		/*
		CategoryItemRenderer renderer = new CategoryStepRenderer(true);
        CategoryAxis domainAxis = new CategoryAxisSkipLabels();  // new CategoryAxis("Category");
        domainAxis.setTickMarksVisible(true);
        ((CategoryAxisSkipLabels)domainAxis).setDisplaySkippedTickMarks(true);
        ((CategoryAxisSkipLabels)domainAxis).setDisplaySkippedTickMarks(true);
        NumberAxis rangeAxis = new NumberAxis("Value");
        CategoryPlot plot = new CategoryPlot(
            dataset, domainAxis, rangeAxis, renderer
        );
  
        JFreeChart lineChart = new JFreeChart(title, plot);
 
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        lineChart.setBackgroundPaint(Color.white);
        
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle(0*Math.PI / 2.0);
        
		*/
		//nao muda abaixo
		File fileChart = new File(title+".png");
		try {
			ChartUtilities.saveChartAsPNG(fileChart, lineChart, 800, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//privates uso do JFreeChart
	private void printChartTotalResources(String pageName, ContentInfo contentInfo){
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
		
		JFreeChart pieChart = ChartFactory.createPieChart(pageName + " - Quantidade de arquivos por recurso",//title
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
	private void printChartResources(String pageName, ContentInfo contentInfo){
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
		
		JFreeChart pieChart = ChartFactory.createPieChart(pageName + " - Tamanho dos dados por recurso",//title
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
	
	//criar dataset boxPlot
	private BoxAndWhiskerCategoryDataset createDataset(CsvSummary csvSummary, List<String> pagesIn, List<String> dataViews) {
		DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		
		List<String> pagesFromCsv = csvSummary.getPageNames();//lista de paginas
		
		List<String> pages = new ArrayList<String>();
		//montar lista de páginas caso seja vazia, nula ou possua  SOMENTE nomes de paginas invalidas
		//gera com todas as paginas, se houver apenas um nome de pagina valido gera somente para essa página
		if(pagesIn == null){
			pages.addAll(pagesFromCsv);
		}else{
			if(pagesIn.isEmpty()){
				pages.addAll(pagesFromCsv);
			}else{
				for(String page : pagesIn){
					if(pagesFromCsv.contains(page)){
						pages.add(page);
					}
				}
				if(pages.isEmpty()){
					pages.addAll(pagesFromCsv);
				}
			}
		}
		List<String> validTimeTypes = new ArrayList<String>();
		validTimeTypes.add("blocking");
		validTimeTypes.add("waiting");
		validTimeTypes.add("receiving");
		validTimeTypes.add("onContentLoad");
		validTimeTypes.add("onLoad");
		validTimeTypes.add("totalOfTimings");
		
		
		
		List<String> timeType = new ArrayList<String>();
		//preenche lista de tipos de tempo caso ela seja vazia ou somente com valores invalidos
		//adiciona todos os valores a lista
		if(dataViews == null){
			timeType.addAll(validTimeTypes);
		}else{
			if(dataViews.isEmpty()){
				timeType.addAll(validTimeTypes);
			}else{
				for(String type : validTimeTypes){
					if(dataViews.contains(type)){
						timeType.add(type);
					}
				}
				if(timeType.isEmpty()){
					timeType.addAll(validTimeTypes);
				}
			}
		}
		
		//inicia a construção do dataset
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
	
	//barChart dataset
	private CategoryDataset createDataset2(CsvSummary csvSummary, List<String> pagesIn){
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		
		List<String> pagesValid = csvSummary.getPageNames();//lista de paginas
		List<String> pages = new ArrayList<String>();
		if(pagesIn == null){
			pages.addAll(pagesValid);
		}else{
			if(pagesIn.isEmpty()){
				pages.addAll(pagesValid);
			}else{
				for(String page : pagesIn){
					if(pagesValid.contains(page)){
						pages.add(page);
					}
				}
				if(pages.isEmpty()){
					pages.addAll(pagesValid);
				}
			}
		}
		
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

	private CategoryDataset createLineDataset(CsvSummary csvSummary, List<String> pagesIn) {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		
		List<String> pagesValid = csvSummary.getPageNames();//lista de paginas
		List<String> pages = new ArrayList<String>();
		if(pagesIn == null){
			pages.addAll(pagesValid);
		}else{
			if(pagesIn.isEmpty()){
				pages.addAll(pagesValid);
			}else{
				for(String page : pagesIn){
					if(pagesValid.contains(page)){
						pages.add(page);
					}
				}
				if(pages.isEmpty()){
					pages.addAll(pagesValid);
				}
			}
		}
		
		List<String> harFileNames = csvSummary.getHarNamesList(pages.get(0));
		List<String> categoryHarName = new ArrayList<String>();
		
		for(String harFileName : harFileNames){
			String partes[] = harFileName.split("\\+");
			String date[] = partes[1].split("-");
			String hour[] = partes[2].split("-");
			categoryHarName.add(date[2]+"/"+date[1]+"/"+date[0]+"-"+hour[0]+":"+hour[1]);
			
		}
		int aux = categoryHarName.size()/50;
		for(String page : pages){
			for(int i = 0; i < categoryHarName.size(); i++){
				result.addValue(new Double(
											//csvSummary.getEntryTimingsList(page).get(i).getTotalOfTimings()
											//csvSummary.getEntryTimingsList(page).get(i).getTotalOfTimings() 
											//- 
											csvSummary.getPageTimingsList(page).get(i).getOnload()
											//csvSummary.getPageTimingsList(page).get(i).getOnload()
											), 
									page,
									categoryHarName.get(i)
								);
				
			}
		}
		
		return result;
	}
}
