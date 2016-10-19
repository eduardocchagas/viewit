package br.ufop.chartgenerator.data.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.model.CsvSummary;
import br.ufop.harviewer.model.ContentInfo;
import br.ufop.harviewer.model.EntryTimings;
import br.ufop.harviewer.model.PageTimings;

class CsvPreview implements ICsvPreview {

	private CsvSummary csvSumary = new CsvSummary();

	@Override
	public void readCsvFile(String csvPath) {
		FileInputStream stream;
		InputStreamReader reader;
		BufferedReader br;
		String line;
		String auxLine[];
		try {
			stream = new FileInputStream(csvPath);
			reader = new InputStreamReader(stream);
			br = new BufferedReader(reader);
			
			//ler e descartar duas primeiras linhas(cabeçalho do arquivo csv)
			line = br.readLine();
			line = br.readLine();
			
			//primeira linha com informações
			line = br.readLine();
			
			while (line != null) {
				auxLine = line.split(",");
				String pageName = auxLine[0].replaceAll("\\s+","");
				String harName = auxLine[1];
				ContentInfo contentInfo = new ContentInfo.Builder()
						.totalImage(Integer.parseInt(auxLine[2]))
						.totalCss(Integer.parseInt(auxLine[3]))
						.totalJavascript(Integer.parseInt(auxLine[4]))
						.totalHtml(Integer.parseInt(auxLine[5]))
						.totalOthers(Integer.parseInt(auxLine[6]))
						
						.image(Long.parseLong(auxLine[8]))
						.css(Long.parseLong(auxLine[9]))
						.javascript(Long.parseLong(auxLine[10]))
						.html(Long.parseLong(auxLine[11]))
						.others(Long.parseLong(auxLine[12]))
						.build();
				
				EntryTimings entryTimings = new EntryTimings.Builder()
						.blocking(Long.parseLong(auxLine[14]))
						.waiting(Long.parseLong(auxLine[15]))
						.receiving(Long.parseLong(auxLine[16]))
						.build();
				
				Long onload = Long.parseLong(auxLine[18]);
				Long onContentLoad = Long.parseLong(auxLine[19]);
				PageTimings pageTimings = new PageTimings(onload, onContentLoad);
				this.csvSumary.add(pageName, harName, contentInfo, entryTimings, pageTimings);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}

	@Override
	public void printTest() {
		csvSumary.printTest();
		
	}

	@Override
	public CsvSummary getCsvSummary() {
		return this.csvSumary;
	}

}
