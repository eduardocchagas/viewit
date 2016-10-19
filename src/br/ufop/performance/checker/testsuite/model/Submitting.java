package br.ufop.performance.checker.testsuite.model;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.checker.testsuite.action.impl.ActionOrientedAbstraction;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("submitting")
public class Submitting extends PerformanceTestCase{

	@XStreamAsAttribute
	private String description;
	
	
	@XStreamAlias("locator")
	private ByLocator locator;
	
	@XStreamAsAttribute
	private String stepID;

	

	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	
	public ByLocator getLocator() {
		return locator;
	}

	public void setLocator(ByLocator locator) {
		this.locator = locator;
	}

	@Override
	public void executeTest(WebDriver webdriver) {
		actionBot = new ActionOrientedAbstraction(webdriver);
		actionBot.submit(locator.getByObject(locator.getBy(),
				 locator.getValue()));
		
	}

}

