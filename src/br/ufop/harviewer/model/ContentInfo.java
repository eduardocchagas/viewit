package br.ufop.harviewer.model;

public class ContentInfo {

	// optional
	private long html;
	private long javaScript;
	private long image;
	private long css;
	private long others;
	private int totalHtml;
	private int totalJavaScript;
	private int totalImage;
	private int totalOthers;
	private int totalCss;
	private int totalOfEntries;
	private long totalSizeOfEntries;

	public int getTotalOfEntries() {
		return totalOfEntries;
	}

	public long getTotalSizeOfEntries() {
		return totalSizeOfEntries;
	}

	public long getHtml() {
		return html;
	}

	public long getJavaScript() {
		return javaScript;
	}

	public long getImage() {
		return image;
	}

	public long getCss() {
		return css;
	}

	public long getOthers() {
		return others;
	}

	public int getTotalHtml() {
		return totalHtml;
	}

	public int getTotalJavaScript() {
		return totalJavaScript;
	}

	public int getTotalImage() {
		return totalImage;
	}

	public int getTotalOthers() {
		return totalOthers;
	}

	public int getTotalCss() {
		return totalCss;
	}

	public static class Builder {
		// required

		// optional
		private long html;
		private long javaScript;
		private long image;
		private long css;
		private long others;
		private int totalHtml;
		private int totalJavaScript;
		private int totalImage;
		private int totalOthers;
		private int totalCss;

		public Builder() {

		}

		public Builder html(long value) {
			html = value;

			return this;
		}

		public Builder javascript(long value) {
			javaScript = value;
			return this;
		}

		public Builder image(long value) {
			image = value;
			return this;
		}

		public Builder css(long value) {
			css = value;
			return this;
		}

		public Builder others(long value) {
			others = value;
			return this;
		}

		public Builder totalHtml(int value) {
			totalHtml = value;
			return this;
		}

		public Builder totalJavascript(int value) {
			totalJavaScript = value;
			return this;
		}

		public Builder totalImage(int value) {
			totalImage = value;
			return this;
		}

		public Builder totalCss(int value) {
			totalCss = value;
			return this;
		}

		public Builder totalOthers(int value) {
			totalOthers = value;
			return this;
		}

		public ContentInfo build() {
			return new ContentInfo(this);
		}

	}

	private ContentInfo(Builder builder) {

		// optional
		html = builder.html;
		javaScript = builder.javaScript;
		image = builder.image;
		css = builder.css;
		others = builder.others;
		totalHtml = builder.totalHtml;
		totalJavaScript = builder.totalJavaScript;
		totalImage = builder.totalImage;
		totalOthers = builder.totalOthers;
		totalCss = builder.totalCss;
		totalOfEntries = totalCss + totalHtml + totalImage + totalJavaScript
				+ totalOthers;
		totalSizeOfEntries = css + html + image + javaScript + others;

	}

	public String printsCSVFormat() {

		return totalImage + "," + totalCss + "," + totalJavaScript + ","
				+ totalHtml + "," + totalOthers + "," + totalOfEntries + ","
				+
				// valores
				image + "," + css + "," + javaScript + "," + html + ","
				+ others + "," + totalSizeOfEntries;
	}

	@Override
	public String toString() {

		return "\nDescrp (" + "size" + "," + "total" + ")" + "\nhtm (" + html
				+ "," + totalHtml + ")" + "\njavascript (" + javaScript + ","
				+ totalJavaScript + ")" + "\nimage (" + image + ","
				+ totalImage + ")" + "\nothers (" + others + "," + totalOthers
				+ ")" + "\ncss (" + css + "," + totalCss + ")"
				+ "\n\n--Total of Entries: " + totalOfEntries +

				"\n\n--Total Size: " + totalSizeOfEntries;
	}
}
