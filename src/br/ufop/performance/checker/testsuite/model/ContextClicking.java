package br.ufop.performance.checker.testsuite.model;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.checker.testsuite.action.impl.ActionOrientedAbstraction;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("contextClicking")
public class ContextClicking extends PerformanceTestCase{
	
	@XStreamAlias("locator")
	private ByLocator locator;

	@XStreamAsAttribute
	private String description;

	@XStreamAsAttribute
	private String stepID;
	
	@XStreamAsAttribute
	private String name;
	
	
	
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



	public String getStepID() {
		return stepID;
	}



	public void setStepID(String stepID) {
		this.stepID = stepID;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public void executeTest(WebDriver webDriver) {
		this.actionBot = new ActionOrientedAbstraction(webDriver);
		actionBot.contextClicking(locator.getByObject(locator.getBy(),locator.getValue()), this.name);
	}

}
