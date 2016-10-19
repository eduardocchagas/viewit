package br.ufop.performance.checker.testsuite.model;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.checker.testsuite.action.impl.ActionOrientedAbstraction;

public abstract class PerformanceTestCase {
	
	ActionOrientedAbstraction actionBot;
	
	public abstract void executeTest(WebDriver webDriver);

	
}
