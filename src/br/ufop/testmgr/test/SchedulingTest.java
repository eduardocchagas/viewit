package br.ufop.testmgr.test;

import br.ufop.testmgr.impl.TestMgrFactory;
import br.ufop.testmgr.impl.TestMgrFactory.TestMgrProvidedInterface;
import br.ufop.testmgr.spec.api.IPerformanceTestingSchedule;

public class SchedulingTest {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		int y_times = 360; // testes serao executados num total de 3
		// vezes
		long x_in_x_seconds = 2 * 60 * 1000; // cada teste inicia
		IPerformanceTestingSchedule testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
				.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
		
		/*
		testSchedule
				.loadTestSuiteConfiguration("src/com/sensedia/testmgr/executionconfig/homeUfop.xml");
		
		testSchedule.runPeriodically(x_in_x_seconds, y_times);
		*/
		
		
		
		
		
		
		
		
		
		
		testSchedule
			.readHarFilesFromDirectory(
				"src/com/sensedia/testmgr/executionconfig/testeUfopHar.xml");
		

	}

}
