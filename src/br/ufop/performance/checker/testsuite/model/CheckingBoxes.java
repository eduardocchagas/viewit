package br.ufop.performance.checker.testsuite.model;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.checker.testsuite.action.impl.ActionOrientedAbstraction;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ckeckingBoxes")
public class CheckingBoxes extends PerformanceTestCase {

	@XStreamAsAttribute
	private String stepID;

	@XStreamAsAttribute
	private String description;

	@XStreamAlias("locatorRadioForm")
	private ByLocator locatorRadioForm;

	@XStreamImplicit(itemFieldName = "selectedElements_visibleText")
	private List<String> visibleTexts;

	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
	}

	public ByLocator getLocatorRadioForm() {
		return locatorRadioForm;
	}

	public void setLocatorRadioForm(ByLocator locatorRadioForm) {
		this.locatorRadioForm = locatorRadioForm;
	}

	public List<String> getVisibleTexts() {
		return visibleTexts;
	}

	public void setVisibleTexts(List<String> visibleTexts) {
		this.visibleTexts = visibleTexts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void executeTest(WebDriver webdriver) {
		actionBot = new ActionOrientedAbstraction(webdriver);
		actionBot.checkBoxes(locatorRadioForm.getByObject(
				locatorRadioForm.getBy(), locatorRadioForm.getValue()),
				visibleTexts);

	}

}
