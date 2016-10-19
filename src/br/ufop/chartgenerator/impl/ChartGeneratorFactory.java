package br.ufop.chartgenerator.impl;

import br.ufop.chartgenerator.api.IChartGenerator;

public class ChartGeneratorFactory {

	public static IChartGenerator createInstance(ProvidedInterface provideInterface) {
		switch (provideInterface){
		case ICHARTGENERATOR:
			return new ChartGenerator();
		default:
			return null;
		}
	}

	public enum ProvidedInterface {

		ICHARTGENERATOR;

	}
}
