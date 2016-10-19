package br.ufop.harviewer.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonParseException;

import br.ufop.harviewer.api.CSVFile;
import br.ufop.harviewer.api.IHarViewer;
import br.ufop.harviewer.model.ContentInfo;
import br.ufop.harviewer.model.EntryTimings;
import br.ufop.harviewer.model.HarDirectoryConfig;
import br.ufop.harviewer.model.HarInfoSummary;
import br.ufop.harviewer.model.PageTimings;

import edu.umass.cs.benchlab.har.HarEntry;
import edu.umass.cs.benchlab.har.HarEntryTimings;
import edu.umass.cs.benchlab.har.HarLog;
import edu.umass.cs.benchlab.har.HarPage;
import edu.umass.cs.benchlab.har.HarPageTimings;

import edu.umass.cs.benchlab.har.tools.HarFileReader;

class HarViewer implements IHarViewer {

	private HarDirectoryConfig harDirectoryConfig;
	private List<HarInfoSummary> harInfoList = new ArrayList<HarInfoSummary>();
	private HarDirectoryConfig harConfig;

	private EntryTimings summarizeRequestTimes(HarLog log) {

		EntryTimings entryTimings = null;

		long blocking = 0, waiting = 0, receiving = 0;

		List<HarEntry> harEntries = log.getEntries().getEntries();
		for (HarEntry entry : harEntries) {
			HarEntryTimings har_entry_time = entry.getTimings();
			blocking += har_entry_time.getBlocked();
			waiting += har_entry_time.getWait();
			receiving += har_entry_time.getReceive();

		}

		entryTimings = new EntryTimings.Builder().blocking(blocking)
				.waiting(waiting).receiving(receiving).build();
		return entryTimings;
	}

	private ContentInfo summarizeContentTypes(HarLog log) {

		ContentInfo contentInfo;
		// optional
		long html = 0, javaScript = 0, image = 0, css = 0, others = 0;
		int totalOthers = 0, totalCss = 0, totalHtml = 0, totalJavaScript = 0, totalImage = 0;

		List<HarEntry> harEntries = log.getEntries().getEntries();

		for (HarEntry entry : harEntries) {

			String mimeType = entry.getResponse().getContent().getMimeType();
			long contentSize = entry.getResponse().getContent().getSize();
			if (mimeType.contains("html")) {
				html = html + contentSize;
				totalHtml += 1;
			} else if (mimeType.contains("javascript")) {
				javaScript = javaScript + contentSize;
				totalJavaScript += 1;
			} else if (mimeType.contains("css")) {
				css = css + contentSize;
				totalCss += 1;
			} else if (mimeType.contains("image")) {
				image += contentSize;
				totalImage += 1;
			} else {
				others = others + contentSize;
				totalOthers += 1;
			}
		}

		contentInfo = new ContentInfo.Builder().css(css).html(html)
				.image(image).javascript(javaScript).others(others)
				.totalCss(totalCss).totalHtml(totalHtml).totalImage(totalImage)
				.totalJavascript(totalJavaScript).totalOthers(totalOthers)
				.build();

		return contentInfo;
	}

	public HarInfoSummary readHarFile(String harPathName, String pageName) {

		HarLog log = null;
		File f = new File(harPathName);
		System.out.println("::::"+harPathName);
		HarFileReader r = new HarFileReader();

		try {
			log = r.readHarFile(f);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return summarizeAllHarInfo(harPathName, pageName, log);
	}

	private HarInfoSummary summarizeAllHarInfo(String harPathName,
			String pageName, HarLog log) {

		EntryTimings entryTimings = summarizeRequestTimes(log);

		ContentInfo contentInfo = summarizeContentTypes(log);

		PageTimings pageTimings = summarizePageLoad(log);

		HarInfoSummary harInfoSummary = new HarInfoSummary(harPathName,
				entryTimings, contentInfo, pageTimings, pageName);
		// TODO Auto-generated method stub
		return harInfoSummary;
	}

	private PageTimings summarizePageLoad(HarLog log) {

		PageTimings pageTimings;

		long on_load = 0, on_content_load = 0;
		// recuperando tempo de carregamento de cada pagina continda no log

		List<HarPage> pages = log.getPages().getPages();

		for (HarPage page : pages) {
			HarPageTimings page_time = page.getPageTimings();
			on_load += page_time.getOnLoad();
			on_content_load += page_time.getOnContentLoad();
			// = onLoad + page_time.getOnLoad(); on_content_load +=
			page_time.getOnContentLoad();
		}

		pageTimings = new PageTimings(on_load, on_content_load);

		return pageTimings;
	}

	@Override
	public void exportHarFilesToCSVFile(String harConfigPath) {

		String csvContent = " ";

		readHarFilesFromDirectory(harConfigPath);
		System.out.println(harConfigPath);

		for (int i = 0; i < harInfoList.size(); i++) {

			HarInfoSummary harInfoSummary = harInfoList.get(i);

			csvContent = csvContent + harInfoSummary.getPageName() + ","
					+ harInfoSummary.printsCSVFormat();

		}

		csvContent = CSVFile.CSVHEADER + CSVFile.CSVSUBHEADER + csvContent;

		FileWriter fw = null;
		try {
			fw = new FileWriter(harConfig.getCsvFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter out = new PrintWriter(fw);
		out.print(csvContent);

		// Flush the output to the file
		out.flush();

		// Close the Print Writer
		out.close();

		// Close the File Writer
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// busca por arquivo no diretório
	private File[] finder(String dirName) {

		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".har");
			}
		});

	}

	private HarDirectoryConfig loadHarDirectoryConfigurations(
			String harDirectoryConfigPath) {

		harDirectoryConfig = HarDirectoryConfigLoder
				.loadHarDirectoryConfig(harDirectoryConfigPath);

		return harDirectoryConfig;

	}

	@Override
	public List<HarInfoSummary> readHarFilesFromDirectory(String harConfigPath) {

		harConfig = loadHarDirectoryConfigurations(harConfigPath);
		String directoryPath = harConfig.getHarDirectoryPath();
		System.out.println(directoryPath);
		File[] files = finder(directoryPath);
		//System.out.println(files.length);
		Arrays.sort(files);

		List<String> pagesList = harConfig.getPageNames();

		int numberOfPages = harConfig.getPageNames().size();
		int pageIndex = 0;
		
		System.out.println("Numero de pag: "+numberOfPages);

		for (int i = 0; i < files.length; i++) {
			if (pageIndex == numberOfPages)
				pageIndex = 0;

			HarInfoSummary harInfoSummary = readHarFile(files[i].getPath(),
														pagesList.get(pageIndex));

			harInfoList.add(harInfoSummary);
			pageIndex++;

		}
		return harInfoList;
	}

}
