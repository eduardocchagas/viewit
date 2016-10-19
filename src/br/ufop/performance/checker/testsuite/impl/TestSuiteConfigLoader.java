package br.ufop.performance.checker.testsuite.impl;

import java.io.FileInputStream;
import java.io.InputStream;

import br.ufop.performance.checker.testsuite.model.TestSuite;
import com.thoughtworks.xstream.XStream;

class TestSuiteConfigLoader {



	public static TestSuite loadTestSuiteConfiguration(String configXmlPath) {
		try {

			InputStream in = new FileInputStream(configXmlPath);
			XStream xstream = new XStream();

			xstream.processAnnotations(TestSuite.class);
			TestSuite config = (TestSuite) xstream.fromXML(in);
			return config;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
