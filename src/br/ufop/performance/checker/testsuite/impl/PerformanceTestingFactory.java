package br.ufop.performance.checker.testsuite.impl;


import br.ufop.performance.checker.testsuite.api.IPerformanceTesting;

public class PerformanceTestingFactory {

	/* Get actual class name to be printed on */
	// static final Logger logger = Logger.getLogger(ComponentFactory.class);
	// se novas interfaces providas forem acrescentadas, considerar usar ENUM

	public static IPerformanceTesting createInstance(
			ProvidedInterface provInterface) {

		switch (provInterface) {
		case ITESTSUITE:

			return new PerformanceTesting();

		default:
			return null;
		}

	}

	public enum ProvidedInterface {

		ITESTSUITE;

	}

}
