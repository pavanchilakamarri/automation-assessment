package com.veevasys.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.veevasys.configuration.Config;
import com.veevasys.driver.LocalDriverManager;
import com.veevasys.utils.DateUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.sql.DriverManager;
import java.util.Objects;


public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static String link = "";

    public static void initReports() {
        System.out.println("INIT CALLED ...");
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();

            link = System.getProperty("user.dir") + File.separator + "ExtentReports/" + DateUtils.getCurrentDateTimeCustom("_") + "_" + Config.getInstance().getTestConfig().getProjectName();
        }

//            ExtentPDFReporter pdf = new ExtentPDFReporter("reports/ExtentReports/PdfReport.pdf");
//            try {
//                pdf.loadJSONConfig(new File("src/test/resources/pdf-config.json"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            extentReports.attachReporter(pdf);

        ExtentSparkReporter spark = new ExtentSparkReporter(link);
        extentReports.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);
       /* spark.config().setDocumentTitle(FrameworkConstants.REPORT_TITLE);
        spark.config().setReportName(FrameworkConstants.REPORT_TITLE);
        extentReports.setSystemInfo("Framework Name", FrameworkConstants.REPORT_TITLE);
        extentReports.setSystemInfo("Author", FrameworkConstants.AUTHOR);
*/
        System.out.println("Extent Reports is installed.");


    }

    public static void flushReports() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        ExtentTestManager.unload();
    //    ReportUtils.openReports(link);
    }

    public static void createTest(String testCaseName) {
        // ExtentManager.setExtentTest(extent.createTest(testCaseName));
        ExtentTestManager.setExtentTest(extentReports.createTest(testCaseName));
    }

    public static void createTest(String testCaseName, String description) {
        // ExtentManager.setExtentTest(extent.createTest(testCaseName));
        ExtentTestManager.setExtentTest(extentReports.createTest(testCaseName, description));
    }

    public static void removeTest(String testCaseName) {
        // ExtentManager.setExtentTest(extent.createTest(testCaseName));
        extentReports.removeTest(testCaseName);
    }

    // public static void addCategories(String[] categories) {
    public static void logMessage(String message) {
        ExtentTestManager.getExtentTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        ExtentTestManager.getExtentTest().log(status, message);
    }

    public static void logMessage(Status status, Object message) {
        ExtentTestManager.getExtentTest().log(status, (Throwable) message);
    }

    public static void pass(String message) {
        //System.out.println("ExtentReportManager class: " + ExtentTestManager.getExtentTest());
        ExtentTestManager.getExtentTest().pass(message);
    }

    public static void pass(Markup message) {
        ExtentTestManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        ExtentTestManager.getExtentTest().fail(message);
    }

    public static void fail(Object message) {
        ExtentTestManager.getExtentTest().fail((String) message);
    }

    public static void fail(Markup message) {
        ExtentTestManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        ExtentTestManager.getExtentTest().skip(message);
    }

    public static void skip(Markup message) {
        ExtentTestManager.getExtentTest().skip(message);
    }

    public static void info(Markup message) {
        ExtentTestManager.getExtentTest().info(message);
    }

    public static void info(String message) {
        ExtentTestManager.getExtentTest().info(message);
    }

    public static void warning(String message) {
        ExtentTestManager.getExtentTest().log(Status.WARNING, message);
    }

   /* public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) LocalDriverManager.getWebDriver()).getScreenshotAs(OutputType.BASE64);

        //Base64 from Screenshot of Selenium
        //ExtentTestManager.getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());

        //File Path from Screenshot of Java
        ExtentTestManager.getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(String.valueOf(CaptureHelpers.getScreenshot(message))).build());

    }*/
}
