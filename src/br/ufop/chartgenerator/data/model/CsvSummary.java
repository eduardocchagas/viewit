package br.ufop.chartgenerator.data.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import br.ufop.harviewer.model.ContentInfo;
import br.ufop.harviewer.model.EntryTimings;
import br.ufop.harviewer.model.PageTimings;

public class CsvSummary {
	
	private SortedMap<String, CsvInfoSummary> csvInfoSummaries = new TreeMap<String, CsvInfoSummary>();
	
	public void add(String pageName, String harName, ContentInfo contentInfo,
						EntryTimings entryTimings, PageTimings pageTimings){
		if(csvInfoSummaries.containsKey(pageName)){
			csvInfoSummaries.get(pageName).add(harName, contentInfo, entryTimings, pageTimings);
		}else
			csvInfoSummaries.put(pageName, new CsvInfoSummary(harName, 
								 contentInfo, entryTimings, pageTimings));
	}

	public void printTest() {
		// Get a set of the entries
	      Set set = csvInfoSummaries.entrySet();
	      // Get an iterator
	      Iterator i = set.iterator();
	      // Display elements
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.println(me.getKey() + ": ");
	         csvInfoSummaries.get((String)me.getKey()).printTest();
	      }
		
	}
	
	public List<String> getPageNames(){
		List<String> aux = new ArrayList<String>();
		// Get a set of the entries
	      Set set = csvInfoSummaries.entrySet();
	      // Get an iterator
	      Iterator i = set.iterator();
	      while(i.hasNext()){
	    	  Map.Entry me = (Map.Entry)i.next();
	    	  aux.add((String)me.getKey());
	      }
	      return aux;
	}
	
	public ContentInfo getContentInfoByPage(String pageName){
		return csvInfoSummaries.get(pageName).getContentInfo();
	}
	
	public CsvInfoSummary getCsvInfoSummaryByPage(String pageName){
		return csvInfoSummaries.get(pageName);
	}
	
	public List<EntryTimings> getEntryTimingsList(String pageName){
		return csvInfoSummaries.get(pageName).getEntryTimingsList();
	}
	
	public List<PageTimings> getPageTimingsList(String pageName){
		return csvInfoSummaries.get(pageName).getPageTimingsList();
	}
	
	public List<String> getHarNamesList(String pageName){
		return csvInfoSummaries.get(pageName).getHarNamesList();
	}
	
}
