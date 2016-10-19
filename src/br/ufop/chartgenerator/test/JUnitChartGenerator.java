package br.ufop.chartgenerator.test;

import org.junit.Test;

import br.ufop.chartgenerator.api.IChartGenerator;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory.ProvidedInterface;
import br.ufop.chartgenerator.model.ChartSuite;

public class JUnitChartGenerator {
	
	@Test
	public void test(){
		IChartGenerator chartExecution = 
				ChartGeneratorFactory.createInstance(ProvidedInterface.ICHARTGENERATOR);
		ChartSuite chartSuiteSample = 
				chartExecution.loadChartSuite("src/com/sensedia/testmgr/executionconfig/chart.xml");
		System.out.println("CSV: "+chartSuiteSample.getCsvPath());
		System.out.println("chartFolder: "+chartSuiteSample.getChartFolder());
		chartExecution.run();
		
		
	}
	
}
