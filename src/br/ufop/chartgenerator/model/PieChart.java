package br.ufop.chartgenerator.model;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.plot.impl.PlotOrientedAbstration;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("pieChart")
public class PieChart extends ChartTypeSuite{
	
	@XStreamAsAttribute
	private String title;
	
	@XStreamAsAttribute
	private String pageName;
	
	@XStreamAlias("type")
	private String type;
	
	@XStreamAlias("information")
	private String information;
	
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void plotChart(ICsvPreview csvPreview) {
		
		plotBot = new PlotOrientedAbstration(csvPreview);
		
		plotBot.pieChart(pageName, type, information);
	}

}
