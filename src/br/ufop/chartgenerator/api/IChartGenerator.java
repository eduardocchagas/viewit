package br.ufop.chartgenerator.api;

import br.ufop.chartgenerator.model.ChartSuite;

public interface IChartGenerator {
	
	public ChartSuite loadChartSuite(String chartSuitePath);
	
	public void run();

}
