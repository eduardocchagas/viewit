package br.ufop.performance.checker.test;

import org.junit.Test;

import br.ufop.performance.checker.testsuite.api.IPerformanceTesting;
import br.ufop.performance.checker.testsuite.impl.PerformanceTestingFactory;
import br.ufop.performance.checker.testsuite.impl.PerformanceTestingFactory.ProvidedInterface;
import br.ufop.performance.checker.testsuite.model.TestSuite;

public class JUnitTestSuiteLoader {

	@Test
	public void test() {

		IPerformanceTesting testExecution = PerformanceTestingFactory
				.createInstance(ProvidedInterface.ITESTSUITE);

		TestSuite testSuiteSample = testExecution
				.loadTestSuite("src/br/ufop/testmgr/executionconfig/homeUfop.xml");
		System.out.println("Diretorio: " + testSuiteSample.getHarDirectoryPath());

		testExecution.run();
	
	}

}
