package br.ufop.chartgenerator.data.api;

import br.ufop.chartgenerator.data.model.CsvSummary;

public interface ICsvPreview {
	
	void readCsvFile(String csvPath);
	
	CsvSummary getCsvSummary();
	
	//remover
	void printTest();
	
}
