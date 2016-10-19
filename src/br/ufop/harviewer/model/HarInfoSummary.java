package br.ufop.harviewer.model;

public class HarInfoSummary {

	private EntryTimings entryTimings;
	private ContentInfo contentInfo;
	private String name;
	private PageTimings pageTimings;
	private String pageName;

	public HarInfoSummary(String name, EntryTimings entryTimings,
			ContentInfo contentInfo, PageTimings pageTimings,String pageName) {
		this.name = name;
		this.entryTimings = entryTimings;
		this.contentInfo = contentInfo;
		this.pageTimings = pageTimings;
		this.pageName = pageName;

	}
	
	
	public String getPageName() {
		return pageName;
	}



	public void setPageName(String pageName) {
		this.pageName = pageName;
	}



	public String getName() {
		return name;
	}

	public PageTimings getPageTimings() {
		return pageTimings;
	}

	public void setPageTimings(PageTimings pageTimings) {
		this.pageTimings = pageTimings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntryTimings getEntryTimings() {
		return entryTimings;
	}

	public void setEntryTimings(EntryTimings entryTimings) {
		this.entryTimings = entryTimings;
	}

	public ContentInfo getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(ContentInfo contentInfo) {
		this.contentInfo = contentInfo;
	}

	public String printsCSVFormat() {
		return name + "," 
				+ contentInfo.printsCSVFormat() + ","
				+ entryTimings.printsCSVFormat() + ","
				+ pageTimings.printsCSVFormat() + "\n";
	}
}
