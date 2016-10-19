package br.ufop.performance.checker.testsuite.action.api;

import java.util.List;

import org.openqa.selenium.By;

public interface IActionOrientedAbstraction {

	public void click(By locator);

	public void submit(By locator);

	public void navigate(String URL);

	public void checkBoxes(By locatorCheckBox, List<String> selectedVisibleTexts);

	public void selectOptionInRadioForm(By locatorRadioForm,
			String selectedVisibleText);

	public void type(By locator, String text);

}
