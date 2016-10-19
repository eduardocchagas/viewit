package br.ufop.chartgenerator.plot.api;

import br.ufop.chartgenerator.model.DataViews;
import br.ufop.chartgenerator.model.Pages;

public interface IPlotOrientedAbstration {
	
	public void plotPieChart();

	void pieChart(String pageName, String type, String information);
	
	void boxPlotChart(String chartFolder, String title, Pages pages, DataViews dataViews);
	
	void barChart(String chartFolder, String title, Pages pages);
	
	void lineChart(String chartFolder, String title, Pages pages);

}
