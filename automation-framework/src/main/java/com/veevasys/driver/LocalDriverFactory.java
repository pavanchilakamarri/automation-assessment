package com.veevasys.driver;


import com.veevasys.configuration.Config;
import com.veevasys.configuration.WebDriverConfig;
import com.veevasys.runners.ExecutionSetup;
import com.veevasys.utils.LogUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.Locale;

public class LocalDriverFactory {

    private LocalDriverFactory() {
    }

    protected ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    // protected static final Logger log = Logger.getLogger(LocalDriverFactory.class);
    private static WebDriverConfig driverConfig;
    private static String localBrowser;

    /**
     * This method is used to create web driver instance.
     */
    public static synchronized void createInstance() {

        try {
            driverConfig = Config.getInstance().getWebDriverConfig();

            if (ExecutionSetup.isLocalExecution) {
                localBrowser = driverConfig.getDriver().toString();
                synchronized (Config.class) {
                    if (localBrowser.equalsIgnoreCase("chrome")) {
                        WebDriverManager.chromedriver().setup();
                        String switches = driverConfig.getChromeSwitches();
                        WebDriver driver = new ChromeDriver();
                        driver.manage().window().maximize();
                        LocalDriverManager.setWebDriver(driver);
                        LogUtils.info("In Driver Class ... " + LocalDriverManager.getWebDriver());
                        return;
                    } else if (localBrowser.equalsIgnoreCase("edge")) {
                        WebDriverManager.edgedriver().setup();
                        WebDriver driver = new EdgeDriver();
                        driver.manage().window().maximize();
                        LocalDriverManager.setWebDriver(driver);
                        LogUtils.info("In Driver Class ... " + LocalDriverManager.getWebDriver());
                        return;
                    } /*else if (localBrowser.equalsIgnoreCase("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    String prefs = driverConfig.getFirefoxPreferences();
                    WebDriver webdriver = null;
                    webdriver = new FirefoxDriver();
                    webdriver.manage().window().maximize();
                    LocalDriverManager.setWebDriver(webdriver);
                    return;
                } else if (localBrowser.equalsIgnoreCase("ie") || localBrowser.equalsIgnoreCase("iexplorer") || localBrowser.equalsIgnoreCase("internetexplorer")) {
                    WebDriverManager.iedriver().setup();
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    ieOptions.takeFullPageScreenshot();
                    ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                    ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
                    ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
                    ieOptions.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                    ieOptions.setCapability("ignoreProtectedModeSettings", true);

                    WebDriver driver = new InternetExplorerDriver(ieOptions);
                    driver.manage().window().maximize();
                    LocalDriverManager.setWebDriver(driver);
                    return;
                } */ else if (localBrowser.equalsIgnoreCase("safari")) {
                        WebDriverManager.safaridriver().setup();
                        WebDriver driver = new SafariDriver();
                        driver.manage().window().maximize();
                        LocalDriverManager.setWebDriver(driver);
                        LogUtils.info("In Driver Class ... " + LocalDriverManager.getWebDriver());
                        return;
                    } else {
                        throw new RuntimeException("execution type is invalid. valid: local");
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.error("", e);
        }
    }
}
