package br.ufop.performance.checker.testsuite.api;

import br.ufop.performance.checker.testsuite.model.TestSuite;

public interface IPerformanceTesting {

	public TestSuite loadTestSuite(String testSuitePath);

	public void run();

}
