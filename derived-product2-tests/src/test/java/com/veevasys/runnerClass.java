package com.veevasys;

import com.veevasys.driver.LocalDriverManager;
import com.veevasys.report.ExtentReportManager;
import com.veevasys.runners.ExecutionSetup;
import com.veevasys.utils.LogUtils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethodListener;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.annotations.*;

import java.io.IOException;

@CucumberOptions(tags = "${cucumber.tag}", glue = {"com.veevasys"},
        plugin = {"html:target/cucumber-reports/HomePage/cucumber-pretty"},
        features = {"src/test/resources/Features"})
public class runnerClass extends AbstractTestNGCucumberTests implements ITestListener, ISuiteListener, IInvokedMethodListener {

    @BeforeSuite
    public void beforeSuite() {

        {
            System.out.println("BEFORE SUITE called ... ");
            ExtentReportManager.initReports();

        }

    }

    @Parameters(value = {"BROWSER", "ENV"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, @Optional("CP") String env) throws IOException, InterruptedException {
        System.out.println("setUp called ... ");
        ExecutionSetup.setUp(browser, env);
        LogUtils.info("SET UP for project is success");

    }

    @AfterTest
    public void tearDown() {
        // WebDriver driver
        WebDriver driver = LocalDriverManager.getWebDriver();
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AFTER SUITE called ... ");
        ExtentReportManager.flushReports();
    }

}
