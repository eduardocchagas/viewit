package br.ufop.harviewer.model;

import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("defaultHarDirectory")
public class HarDirectoryConfig {

	@XStreamAsAttribute
	private String path;
	
	
	@XStreamAsAttribute
	private String csvFileName;


	
	@XStreamImplicit(itemFieldName = "pageName")
	private List<String> harFilePaths;

	public String getHarDirectoryPath() {

		// determinar o diretorio onde arquivos har serao salvos. No caso, eh
		// considerar canonical path
		String canonicalPath = null;
		try {
			canonicalPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// determinar o diretório
		String defaulLogDir = canonicalPath + "\\" + path;

		return defaulLogDir;
	}

	public List<String> getPageNames() {
		return harFilePaths;
	}

	public String getCsvFileName() {
		return csvFileName;
	}

	public void setCsvFilePath(String csvFilePath) {
		this.csvFileName = csvFilePath;
	}
	
	

}
