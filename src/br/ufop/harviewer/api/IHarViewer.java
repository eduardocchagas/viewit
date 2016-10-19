package br.ufop.harviewer.api;

import java.util.List;

import br.ufop.harviewer.model.HarInfoSummary;

public interface IHarViewer {

	public HarInfoSummary readHarFile(String harConfigPath, String pageName);

	public void exportHarFilesToCSVFile(String harConfig);
	
	public List<HarInfoSummary> readHarFilesFromDirectory(String directoryPath);

}
