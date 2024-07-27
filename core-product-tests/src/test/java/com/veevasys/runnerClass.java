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

        try {
            ExtentReportManager.initReports();
            LogUtils.info("EXTENT REPORT is initialised");

        } catch (Exception e) {
            LogUtils.error("EXTENT REPORT failed to initialised");
            LogUtils.error(e.getStackTrace());

        }

    }

    @Parameters(value = {"BROWSER", "ENV"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, @Optional("CP") String env) throws IOException, InterruptedException {
        try {

           ExecutionSetup.setUp(browser, env);
            LogUtils.info("Execution SETUP SUCCESS...");
        } catch (Exception e) {
            LogUtils.error("Execution SETUP failed");
            LogUtils.error(e.getStackTrace());
        }

    }

    @AfterClass
    public void tearDown() {
        // WebDriver driver
        WebDriver driver = LocalDriverManager.getWebDriver();
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        try {

            ExtentReportManager.flushReports();
            LogUtils.info("EXTENT REPORT is flushed");
        }catch (Exception e){
            LogUtils.info("EXTENT REPORT is not able to flushed");
            LogUtils.error(e.getStackTrace());
        }
    }

}
