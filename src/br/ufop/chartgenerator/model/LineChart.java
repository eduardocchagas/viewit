package br.ufop.chartgenerator.model;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.plot.impl.PlotOrientedAbstration;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("lineChart")
public class LineChart extends ChartTypeSuite{
	
	@XStreamAsAttribute
	private String title;
	
	@XStreamAlias("pages")
	private Pages pages;
	
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

	@Override
	public void plotChart(ICsvPreview csvPreview) {
		plotBot = new PlotOrientedAbstration(csvPreview);
		plotBot.lineChart(this.getChartFolder(), this.getTitle(), this.getPages());
	}

}
