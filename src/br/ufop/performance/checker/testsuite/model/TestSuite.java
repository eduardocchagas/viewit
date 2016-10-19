package br.ufop.performance.checker.testsuite.model;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("testSuite")
public class TestSuite extends PerformanceTestCase {

	private SortedMap<String, PerformanceTestCase> mapStepId_PerformanceTest;

	@XStreamAsAttribute
	private String description;
	
	@XStreamAsAttribute
	private String harPath;

	// configurar Har
	

	@XStreamImplicit(itemFieldName = "navigating")
	private List<Navigating> navigatingSteps;

	@XStreamImplicit(itemFieldName = "typing")
	private List<Typing> typingSteps;

	@XStreamImplicit(itemFieldName = "ckeckingBoxes")
	private List<CheckingBoxes> ckeckingBoxSteps;

	@XStreamImplicit(itemFieldName = "selectingOption")
	private List<SelectingOption> selectingOptionSteps;

	@XStreamImplicit(itemFieldName = "clicking")
	private List<Clicking> clickingStep;

	@XStreamImplicit(itemFieldName = "submitting")
	private List<Submitting> submittingSteps;
	
	@XStreamImplicit(itemFieldName = "checkingAlert")
	private List<CheckingAlert> checkingAlertSteps;
	
	@XStreamImplicit(itemFieldName = "contextClicking")
	private List<ContextClicking> contextClickingSteps;
	
	public List<Navigating> getNavigatingSteps() {
		return navigatingSteps;
	}

	public void setNavigatingSteps(List<Navigating> navigatingSteps) {
		this.navigatingSteps = navigatingSteps;
	}

	public List<Typing> getTypingSteps() {
		return typingSteps;
	}

	public void setTypingSteps(List<Typing> typingSteps) {
		this.typingSteps = typingSteps;
	}

	public List<CheckingBoxes> getCkeckingBoxSteps() {
		return ckeckingBoxSteps;
	}

	public void setCkeckingBoxSteps(List<CheckingBoxes> ckeckingBoxSteps) {
		this.ckeckingBoxSteps = ckeckingBoxSteps;
	}

	public List<SelectingOption> getSelectingOptionSteps() {
		return selectingOptionSteps;
	}

	public void setSelectingOptionSteps(
			List<SelectingOption> selectingOptionSteps) {
		this.selectingOptionSteps = selectingOptionSteps;
	}

	public List<Clicking> getClickingSteps() {
		return clickingStep;
	}

	public void setClickingSteps(List<Clicking> clickingStep) {
		this.clickingStep = clickingStep;
	}

	public List<Submitting> getSubmittingSteps() {
		return submittingSteps;
	}

	public void setSubmittingSteps(List<Submitting> submittingSteps) {
		this.submittingSteps = submittingSteps;
	}

	public List<CheckingAlert> getCheckingAlertSteps() {
		return checkingAlertSteps;
	}

	public void setCheckingAlertSteps(List<CheckingAlert> checkingAlertSteps) {
		this.checkingAlertSteps = checkingAlertSteps;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SortedMap<String, PerformanceTestCase> getMapStepId_PerformanceTest() {
		return mapStepId_PerformanceTest;
	}

	public void setMapStepId_PerformanceTest(
			SortedMap<String, PerformanceTestCase> mapStepId_PerformanceTest) {
		this.mapStepId_PerformanceTest = mapStepId_PerformanceTest;
	}

	public List<Clicking> getClickingStep() {
		return clickingStep;
	}

	public void setClickingStep(List<Clicking> clickingStep) {
		this.clickingStep = clickingStep;
	}
	
	
	
	public List<ContextClicking> getContextClickingSteps() {
		return contextClickingSteps;
	}

	public void setContextClickingSteps(List<ContextClicking> contextClickingSteps) {
		this.contextClickingSteps = contextClickingSteps;
	}

	public void sortTestCasesByStepId() {
		if (mapStepId_PerformanceTest == null)
			mapStepId_PerformanceTest = new TreeMap<String, PerformanceTestCase>();

		// buscando testes do tipo navegacao
		if (navigatingSteps != null)
			for (Navigating navigate : navigatingSteps) {
				if (mapStepId_PerformanceTest == null)
					System.out.println("NULLLL");
				;
				mapStepId_PerformanceTest.put(navigate.getStepID(), navigate);
			}

		// testes do tipo Clicar
		if (clickingStep != null)
			for (Clicking click : clickingStep) {
				mapStepId_PerformanceTest.put(click.getStepID(), click);
			}
		// do tipo selecionar
		if (selectingOptionSteps != null)
			for (SelectingOption select : selectingOptionSteps) {
				mapStepId_PerformanceTest.put(select.getStepID(), select);
			}

		// do tipo checkBoxes
		if (ckeckingBoxSteps != null)
			for (CheckingBoxes check : ckeckingBoxSteps) {
				mapStepId_PerformanceTest.put(check.getStepID(), check);
			}
		// tipo submeter
		if (submittingSteps != null)
			for (Submitting submit : submittingSteps) {
				mapStepId_PerformanceTest.put(submit.getStepID(), submit);
			}
		// digitar
		if (typingSteps != null)
			for (Typing typingStep : typingSteps) {
				mapStepId_PerformanceTest.put(typingStep.getStepID(), typingStep);
			}
		//alert
		if(checkingAlertSteps != null)
			for(CheckingAlert alert : checkingAlertSteps){
				mapStepId_PerformanceTest.put(alert.getStepID(), alert);
			}
		//contextClicking
		if(contextClickingSteps != null)
			for(ContextClicking contextClicking : contextClickingSteps){
				mapStepId_PerformanceTest.put(contextClicking.getStepID(), contextClicking);
			}
	}

	
	public String getHarDirectoryPath() {
		
		String canonicalPath = null;
		try {
			canonicalPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// determinar o diretório
		String defaulLogDir = canonicalPath + File.separator + harPath;
		return defaulLogDir;
	}

	@Override
	public void executeTest(WebDriver webDriver) {
		Iterator<String> iterator = mapStepId_PerformanceTest.keySet()
				.iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			PerformanceTestCase testCase = mapStepId_PerformanceTest.get(key);
			testCase.executeTest(webDriver);

		}

	}

}
