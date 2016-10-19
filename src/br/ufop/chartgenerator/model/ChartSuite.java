package br.ufop.chartgenerator.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("chartSuite")
public class ChartSuite extends ChartTypeSuite{
	
	private List<ChartTypeSuite> chartsConfig;
	
	@XStreamAsAttribute
	private String csvPath;
	
	@XStreamAsAttribute
	private String chartFolder;
	
	@XStreamImplicit(itemFieldName = "pieChart")
	private List<PieChart> pieCharts;
	
	@XStreamImplicit(itemFieldName = "boxPlotChart")
	private List<BoxPlotChart> boxPlotCharts;
	
	@XStreamImplicit(itemFieldName = "barChart")
	private List<BarChart> barCharts;
	
	@XStreamImplicit(itemFieldName = "lineChart")
	private List<LineChart> lineCharts;
	
	public List<ChartTypeSuite> getChartsConfig() {
		return chartsConfig;
	}

	public void setChartsConfig(List<ChartTypeSuite> chartsConfig) {
		this.chartsConfig = chartsConfig;
	}

	public String getCsvPath() {
		String canonicalPath = null;
		try {
			canonicalPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// determinar o diretório
		String defaulLogDir = canonicalPath + File.separator + csvPath;
		return defaulLogDir;
	}

	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
	}

	public String getChartFolder() {
		String canonicalPath = null;
		try {
			canonicalPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// determinar o diretório
		String defaulLogDir = canonicalPath + File.separator + chartFolder;
		return defaulLogDir;
	}

	public void setChartFolder(String chartFolder) {
		this.chartFolder = chartFolder;
	}

	public List<PieChart> getPieCharts() {
		return pieCharts;
	}

	public void setPieCharts(List<PieChart> pieCharts) {
		this.pieCharts = pieCharts;
	}
	
	public List<BoxPlotChart> getBoxPlotCharts() {
		return boxPlotCharts;
	}

	public void setBoxPlotCharts(List<BoxPlotChart> boxPlotCharts) {
		this.boxPlotCharts = boxPlotCharts;
	}
	
	public List<BarChart> getBarCharts() {
		return barCharts;
	}

	public void setBarCharts(List<BarChart> barCharts) {
		this.barCharts = barCharts;
	}
	
	public List<LineChart> getLineCharts() {
		return lineCharts;
	}

	public void setLineCharts(List<LineChart> lineCharts) {
		this.lineCharts = lineCharts;
	}

	public void addChartsToSetConfig(){
		if(chartsConfig == null)
			chartsConfig = new ArrayList<ChartTypeSuite>();
		
		//pieCharts
		if(pieCharts != null){
			for(PieChart piechart : pieCharts){
				piechart.setChartFolder(chartFolder);
				chartsConfig.add(piechart);
			}
		}
		if(boxPlotCharts != null){
			for(BoxPlotChart boxPlotChart : boxPlotCharts){
				boxPlotChart.setChartFolder(chartFolder);
				chartsConfig.add(boxPlotChart);
			}
		}
		if(barCharts != null){
			for(BarChart barChart : barCharts){
				barChart.setChartFolder(chartFolder);
				chartsConfig.add(barChart);
			}
		}
		if(lineCharts != null){
			for(LineChart lineChart : lineCharts){
				lineChart.setChartFolder(chartFolder);
				chartsConfig.add(lineChart);
			}
		}
	}
	/*
	public void plotChart() {
		// TODO Auto-generated method stub
		
	}
	*/
	@Override
	public void plotChart(ICsvPreview csvPreview) {
		for(ChartTypeSuite chartCg : chartsConfig){
			chartCg.plotChart(csvPreview);
		}
		
	}

}
