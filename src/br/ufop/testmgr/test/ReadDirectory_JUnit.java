package br.ufop.testmgr.test;

import org.junit.Before;
import org.junit.Test;

import br.ufop.testmgr.impl.TestMgrFactory;
import br.ufop.testmgr.impl.TestMgrFactory.TestMgrProvidedInterface;
import br.ufop.testmgr.spec.api.IPerformanceTestingSchedule;

public class ReadDirectory_JUnit {

	private IPerformanceTestingSchedule testSchedule;

	int y_times = 10; // testes serao executados num total de 10
	// vezes
	long x_in_x_seconds = 3 * 60 * 1 * 1000; // cada teste inicia

	@Before
	public void initialize() {
		testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
				.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
	}

	@Test
	public void schedulingATeste() {
		// testSchedule.loadTestSuiteConfiguration();
		testSchedule.readHarFilesFromDirectory("src/com/sensedia/testmgr/executionconfig/serasaHarDirectoryConfig.xml");
		// testSchedule.runPeriodically(x_in_x_seconds, y_times);
	}
}
