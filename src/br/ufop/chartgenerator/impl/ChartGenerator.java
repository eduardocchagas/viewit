package br.ufop.chartgenerator.impl;

import br.ufop.chartgenerator.api.IChartGenerator;
import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory.ProvidedInterface;
import br.ufop.chartgenerator.model.ChartSuite;

class ChartGenerator implements IChartGenerator{
	
	private ChartSuite chartSuite;
	
	private ICsvPreview csvPreview;

	@Override
	public ChartSuite loadChartSuite(String chartSuitePath) {
		chartSuite = ChartSuiteConfigLoader.loadChartSuiteConfiguration(chartSuitePath);
		
		chartSuite.addChartsToSetConfig();
		
		/*
		csvPreview = (ICsvPreview)CsvPreviewFactory
				 .createInstance(ProvidedInterface.ICSVPREVIEW);
		csvPreview.readCsvFile(chartSuite.getCsvPath());
		
		*/
		return chartSuite;
	}

	@Override
	public void run() {
		
		configCsvPreview();
		
		chartSuite.plotChart(csvPreview);
		
	}
	
	private void configCsvPreview(){
		csvPreview = (ICsvPreview)CsvPreviewFactory
				 .createInstance(ProvidedInterface.ICSVPREVIEW);
		csvPreview.readCsvFile(chartSuite.getCsvPath());
	}
	
}
