package br.ufop.harviewer.model;

public class PageTimings {

	private long onload;
	private long onContentLoad;

	public PageTimings(long onload, long onContentLoad) {

		this.onContentLoad = onContentLoad;
		this.onload = onload;

	}

	public long getOnContentLoad() {
		return onContentLoad;
	}

	public void setOnContentLoad(long onContentLoad) {
		this.onContentLoad = onContentLoad;
	}

	public long getOnload() {
		return onload;
	}

	public void setOnload(long onload) {
		this.onload = onload;
	}

	public String printsCSVFormat() {

		// Total,Page load
		return onload + "," + onContentLoad;

	}

	@Override
	public String toString() {
		return "\n\nOnload: " + onload + "\nOn content load: " + onContentLoad;

	}
}
