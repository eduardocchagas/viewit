package br.ufop.chartgenerator.impl;

import java.io.FileInputStream;
import java.io.InputStream;

import br.ufop.chartgenerator.model.ChartSuite;
import com.thoughtworks.xstream.XStream;

public class ChartSuiteConfigLoader {
	
	public static ChartSuite loadChartSuiteConfiguration(String configXmlPath){
		try {
			InputStream in = new FileInputStream(configXmlPath);
			XStream xstream = new XStream();

			xstream.processAnnotations(ChartSuite.class);
			ChartSuite config = (ChartSuite) xstream.fromXML(in);
			return config;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
