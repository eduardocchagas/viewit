package br.ufop.harviewer.impl;

public class HarViewerFactory {

	public static final String IHARVIEWER = "IHarviewer";

	public static Object createInstance(String provideInterface) {
		if (provideInterface == IHARVIEWER)
			return new HarViewer();
		else
			return null;
	}

}
