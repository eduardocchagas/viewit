package br.ufop.chartgenerator.data.model;

import java.util.ArrayList;
import java.util.List;

import br.ufop.harviewer.model.ContentInfo;
import br.ufop.harviewer.model.EntryTimings;
import br.ufop.harviewer.model.PageTimings;

public class CsvInfoSummary {
	
	private List<String> harNameList = new ArrayList<String>();
	private List<ContentInfo> contentInfoList = new ArrayList<ContentInfo>();
	private List<EntryTimings> entryTimingsList = new ArrayList<EntryTimings>();
	private List<PageTimings> pageTimingsList = new ArrayList<PageTimings>();
	
	

	public CsvInfoSummary(String harName, ContentInfo contentInfo,
			EntryTimings entryTimings, PageTimings pageTimings) {
		this.harNameList.add(harName);
		this.contentInfoList.add(contentInfo);
		this.entryTimingsList.add(entryTimings);
		this.pageTimingsList.add(pageTimings);
	}
	
	public void add(String harName, ContentInfo contentInfo,
			EntryTimings entryTimings, PageTimings pageTimings){
		this.harNameList.add(harName);
		this.contentInfoList.add(contentInfo);
		this.entryTimingsList.add(entryTimings);
		this.pageTimingsList.add(pageTimings);
	}
	
	public ContentInfo getContentInfo(){
		return this.contentInfoList.get(0);
	}
	
	public List<EntryTimings> getEntryTimingsList(){
		return this.entryTimingsList;
	}
	
	public void printTest(){
		for(int i = 0;i < harNameList.size();i++){
			System.out.println("Har file: "+ harNameList.get(i));
			System.out.println(contentInfoList.get(i).printsCSVFormat());
			System.out.println(entryTimingsList.get(i).printsCSVFormat());
			System.out.println(pageTimingsList.get(i).printsCSVFormat());
		}
	}

	public List<PageTimings> getPageTimingsList() {
		return this.pageTimingsList;
	}
	
	public List<String> getHarNamesList(){
		return this.harNameList;
	}

}
