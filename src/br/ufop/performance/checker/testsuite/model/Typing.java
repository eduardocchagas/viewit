package br.ufop.performance.checker.testsuite.model;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.checker.testsuite.action.impl.ActionOrientedAbstraction;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("typing")
public class Typing extends PerformanceTestCase {

	@XStreamAsAttribute
	private String description;

	@XStreamAlias("key")
	private String key;

	@XStreamAlias("locator")
	private ByLocator locator;

	@XStreamAsAttribute
	private String stepID;

	public ByLocator getLocator() {
		return locator;
	}

	public void setLocator(ByLocator locator) {
		this.locator = locator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
	}

	@Override
	public void executeTest(WebDriver webdriver) {

		actionBot = new ActionOrientedAbstraction(webdriver);

		actionBot.type(locator.getByObject(locator.getBy(),
				 locator.getValue()), key);

		
		
	}

}
