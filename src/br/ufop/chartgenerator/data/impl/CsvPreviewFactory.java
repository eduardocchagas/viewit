package br.ufop.chartgenerator.data.impl;


public class CsvPreviewFactory {
	
	public static Object createInstance(ProvidedInterface provideInterface) {
		switch(provideInterface){
		case ICSVPREVIEW:
			return new CsvPreview();
		default:
			return null;
		}
	}
	
	public enum ProvidedInterface{
		ICSVPREVIEW;
	}
}
