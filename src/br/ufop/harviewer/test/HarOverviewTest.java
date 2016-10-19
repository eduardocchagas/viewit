package br.ufop.harviewer.test;

import org.junit.Before;
import org.junit.Test;

import br.ufop.harviewer.api.IHarViewer;
import br.ufop.harviewer.impl.HarViewerFactory;
import br.ufop.harviewer.model.HarInfoSummary;

public class HarOverviewTest {

	private IHarViewer harviewer;
	private String harFilePath;
	private HarInfoSummary harInfo;

	@Before
	public void initialize() {
		harviewer = (IHarViewer) HarViewerFactory
				.createInstance(HarViewerFactory.IHARVIEWER);
		harFilePath = "src/harRepository/fleury_scenario1/fleury.sensedia.com+2012-06-27+00-15-12.har";

		// load har directory config

	}

	@Test
	public void readHarFile() {

		harInfo = harviewer.readHarFile(harFilePath, "Login");
		System.out.println("HarViewerCSVFORMAT: " + harInfo.printsCSVFormat());

		/*
		 * System.out.println("\n\n-> EntryTimings: " +
		 * harInfoSummary.getEntryTimings().toString() + "\n\n-> Page Timings: "
		 * + harInfoSummary.getPageTimings().toString() +
		 * "\n\n-> Content Info: " +
		 * harInfoSummary.getContentInfo().toString());
		 * harviewer.printAllHarInfo
		 * ("src/com/sensedia/harviewer/test/har/WriteTest.csv");
		 */
	}

	@Test
	public void printsCSVFormat() {


	}
}
