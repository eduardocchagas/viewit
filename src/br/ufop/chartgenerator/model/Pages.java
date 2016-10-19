package br.ufop.chartgenerator.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author admin
 *
 */
@XStreamAlias("pages")
public class Pages {
	
	@XStreamImplicit(itemFieldName = "pageName")
	private List<String> pages;

	public List<String> getPages() {
		return pages;
	}

	public void setPages(List<String> pages) {
		this.pages = pages;
	}
	
	
}
