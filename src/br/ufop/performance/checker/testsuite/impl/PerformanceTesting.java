package br.ufop.performance.checker.testsuite.impl;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import br.ufop.performance.checker.testsuite.api.IPerformanceTesting;
import br.ufop.performance.checker.testsuite.model.TestSuite;

class PerformanceTesting implements IPerformanceTesting {

	private TestSuite testSuite;

	private WebDriver webDriver;

	private FirefoxProfile profile;

	public TestSuite loadTestSuite(String testSuitePath) {

		// inicialmente o numero de passos eh o.
		
		testSuite = TestSuiteConfigLoader.loadTestSuiteConfiguration(testSuitePath);
		
		testSuite.sortTestCasesByStepId();

		return testSuite;

	}

	public void run() {

		// recuperar passos na ordem que devem ser exutados
		// configurar FirefoxProfile
		setProfilePreferences();
		//FirefoxBinary binary = new FirefoxBinary(new
		//		File("C:\\Users\\admin\\Desktop\\FirefoxPortable\\FirefoxPortable.exe"));
		
		webDriver = new FirefoxDriver(profile);
		//webDriver = new FirefoxDriver(binary, profile);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webDriver.manage().window().maximize();
		testSuite.executeTest(webDriver);
		webDriver.quit();
	}

	private void setProfilePreferences() {

		profile = new FirefoxProfile();

		// instalar Firebug e NetExport no Firefox profile.
		/*
		File firebug = new File("src/firefox.add-ons/firebug-1.7.3.xpi");
		//File firebug = new File("src/firefox.add-ons/firebug-1.12.8b1.xpi");
		//File firebug = new File("src/firefox.add-ons/firebug-2.0.8.xpi");
		//File netExport = new File("src/firefox.add-ons/netExport-0.8b21.xpi");
		File netExport = new File("src/firefox.add-ons/netExport-0.9b6.xpi");
		File fireStarter = new File("src/firefox.add-ons/fireStarter-0.1a6.1.xpi");
		*/
		
		File firebug = new File("src/firefox.add-ons/firebug-2.0.9-fx.xpi");
		File netExport = new File("src/firefox.add-ons/netExport-0.9b6.xpi");
		
		try {
			profile.addExtension(firebug);
			profile.addExtension(netExport);

			//profile.addExtension(fireStarter);

		} catch (IOException e) {
			throw new RuntimeException(
					"Could not load required extensions, did you download them to the above location? ",
					e);
		}

		// configuracoes do Firebug
		/*
		profile.setPreference("extensions.firebug.currentVersion", "1.7.3");
		//profile.setPreference("extensions.firebug.currentVersion", "1.12.8b1");
		//profile.setPreference("extensions.firebug.currentVersion", "2.0.8");
		profile.setPreference("extensions.firebug.DBG_NETEXPORT", false);
		profile.setPreference("extensions.firebug.onByDefault", true);
		profile.setPreference("extensions.firebug.defaultPanelName", "net");
		profile.setPreference("extensions.firebug.net.enableSites", true);
		profile.setPreference("extensions.firebug.net.persistent", false);
		profile.setPreference(
				"extensions.firebug.netexport.alwaysEnableAutoExport", true);
		//profile.setPreference("extensions.firebug.addonBarOpened", true);
		profile.setPreference("extensions.firebug.addonBarOpened", true);
		
		profile.setPreference("extensions.firebug.allPagesActivation", "on");
		
		// configuracoes do NetExport
		profile.setPreference("extensions.firebug.netexport.autoExportToFile",
				true);
		profile.setPreference(
				"extensions.firebug.netexport.autoExportToServer", false);

		// diretorio onde serao salvos arquivos do tipo har

		profile.setPreference("extensions.firebug.netexport.defaultLogDir",
				testSuite.getHarDirectoryPath());
		profile.setPreference("extensions.firebug.netexport.showPreview", true);
		profile.setPreference(
				"extensions.firebug.netexport.sendToConfirmation", false);
		profile.setPreference("extensions.firebug.netexport.pageLoadedTimeout",
				1500);
		profile.setPreference("extensions.firebug.netexport.Automation", true);
		
		*/
		// Set default Firefox preferences
        profile.setPreference("app.update.enabled", false);

        String domain = "extensions.firebug.";

        // Set default Firebug preferences
        profile.setPreference(domain + "currentVersion", "2.0.9");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

        // Set default NetExport preferences
        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.showPreview", false);
        profile.setPreference(domain + "netexport.defaultLogDir", testSuite.getHarDirectoryPath());
	}

}
