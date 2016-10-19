package br.ufop.testmgr.spec.api;

import br.ufop.performance.checker.testsuite.model.TestSuite;

public interface IPerformanceTestingSchedule {

	public TestSuite loadTestSuiteConfiguration(String testConfigPath);

	public void runPeriodically(long x_in_x_seconds, int y_times);

	public void readHarFilesFromDirectory(String harConfigPath);

}
