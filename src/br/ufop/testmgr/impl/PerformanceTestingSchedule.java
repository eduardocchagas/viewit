package br.ufop.testmgr.impl;

import java.util.*;

import br.ufop.harviewer.api.IHarViewer;
import br.ufop.harviewer.impl.HarViewerFactory;
import br.ufop.performance.checker.testsuite.api.IPerformanceTesting;
import br.ufop.performance.checker.testsuite.impl.PerformanceTestingFactory;
import br.ufop.performance.checker.testsuite.impl.PerformanceTestingFactory.ProvidedInterface;
import br.ufop.performance.checker.testsuite.model.TestSuite;
import br.ufop.testmgr.spec.api.IPerformanceTestingSchedule;

class PerformanceTestingSchedule implements IPerformanceTestingSchedule {

	private int y_times; // testes serao executados num total de tyimes vezes
	private long x_in_x_seconds; // cada teste de x em x minutos

	private Timer timer;
	private IPerformanceTesting testExecution;
	private TestSuite testSuite;

	public TestSuite loadTestSuiteConfiguration(String testConfigPath) {

		testExecution = PerformanceTestingFactory
				.createInstance(ProvidedInterface.ITESTSUITE);

		testSuite = testExecution.loadTestSuite(testConfigPath);
		return testSuite;

	}

	public void runPeriodically(long x_in_x_seconds, int y_times) {
		this.y_times = y_times;
		this.x_in_x_seconds = x_in_x_seconds;
		timer = new Timer();
		
		timer.schedule(new RemindTask(),
				0, // initial delay
				this.x_in_x_seconds);//intervalo entre execuções
	}

	class RemindTask extends TimerTask {
		int numExecutions = y_times;

		public void run() {
			if (numExecutions > 0) {
				testExecution.run();
				numExecutions--;
			} else {
				System.exit(0);
			}
		}
	}

	@Override
	public void readHarFilesFromDirectory(String harConfigPath) {

		IHarViewer harviewer = (IHarViewer) HarViewerFactory
				.createInstance(HarViewerFactory.IHARVIEWER);
		System.err.println(harConfigPath);

		harviewer.exportHarFilesToCSVFile(harConfigPath);
	}

}
