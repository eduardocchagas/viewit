package br.ufop.testmgr.impl;

import br.ufop.testmgr.spec.api.IPerformanceTestingSchedule;

public class TestMgrFactory {

	public static IPerformanceTestingSchedule createInstance(
			TestMgrProvidedInterface provInterface) {

		switch (provInterface) {
		case IPERFORMANCETESTINGSCHEDULE:

			return new PerformanceTestingSchedule();

		default:
			return null;
		}

	}

	public enum TestMgrProvidedInterface {

		IPERFORMANCETESTINGSCHEDULE;

	}

}
