package com.veevasys;

import com.veevasys.configuration.EnvironmentConfig;
import com.veevasys.driver.LocalDriverFactory;
import com.veevasys.driver.LocalDriverManager;
import com.veevasys.report.ExtentReportManager;
import com.veevasys.utils.LogUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.apache.log4j.Logger;
import org.testng.Reporter;

public class CucumberHooks {
	protected static final Logger logger = Logger.getLogger(CucumberHooks.class);
	public static ThreadLocal<String> scenarioName = new ThreadLocal<>();


	@Before
	public void beforeScenario(Scenario scenario) {
		LogUtils.info("Running Scenario Name: " + scenario.getName());
		//ScenarioManager.setScenario(scenario);
		// LocalDriverFactory localDriverFactory = new LocalDriverFactory();
		LocalDriverFactory.createInstance();
		LogUtils.info("DRIVER initialised...");
		LocalDriverManager.getWebDriver().get(EnvironmentConfig.getInstance().getUrl());
		ExtentReportManager.createTest(scenario.getName() + " BROWSER " + LocalDriverManager.getWebDriver());
		Reporter.log("Execution setup success...");

	}

	@After
	public void afterScenario(Scenario scenario) {

		//Quit driver in thread local
		LocalDriverManager.getWebDriver().quit();
		//WebUI.stopSoftAssertAll();
		//WebUI.stopSoftAssertAll();
	}


}
