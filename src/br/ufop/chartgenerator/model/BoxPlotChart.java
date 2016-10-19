package br.ufop.chartgenerator.model;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.plot.impl.PlotOrientedAbstration;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("boxPlotChart")
public class BoxPlotChart extends ChartTypeSuite {
	
	@XStreamAsAttribute
	private String title;
	
	@XStreamAlias("pages")
	private Pages pages;
	
	@XStreamAlias("dataViews")
	private DataViews dataViews;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public DataViews getDataViews() {
		return dataViews;
	}

	public void setDataViews(DataViews dataViews) {
		this.dataViews = dataViews;
	}

	@Override
	public void plotChart(ICsvPreview csvPreview) {
		plotBot = new PlotOrientedAbstration(csvPreview);
		
		plotBot.boxPlotChart(this.getChartFolder(), title, pages, dataViews);
	}

}
