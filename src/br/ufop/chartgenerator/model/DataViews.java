package br.ufop.chartgenerator.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("dataViews")
public class DataViews {
	
	@XStreamImplicit(itemFieldName = "dataView")
	private List<String> dataView;

	public List<String> getDataView() {
		return dataView;
	}

	public void setDataView(List<String> dataView) {
		this.dataView = dataView;
	}
	
}
