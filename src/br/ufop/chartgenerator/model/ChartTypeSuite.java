package br.ufop.chartgenerator.model;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.plot.impl.PlotOrientedAbstration;

public abstract class ChartTypeSuite {
	
	PlotOrientedAbstration plotBot;
	private String chartFolder;
	
	public String getChartFolder() {
		return chartFolder;
	}

	public void setChartFolder(String chartFolder) {
		this.chartFolder = chartFolder;
	}

	public abstract void plotChart(ICsvPreview csvPreview);
}
