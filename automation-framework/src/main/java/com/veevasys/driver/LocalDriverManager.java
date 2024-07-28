package com.veevasys.driver;

import com.veevasys.configuration.Config;
import com.veevasys.configuration.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LocalDriverManager {
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	private static WebDriverConfig	driverConfig;

	static {
		driverConfig = Config.getInstance().getWebDriverConfig();
	}

	public static WebDriver getWebDriver() {
		return webDriver.get();
	}

	@Deprecated
	public static WebDriver getDriver() {
		return webDriver.get();
	}

	public static void setWebDriver(WebDriver driver) {

		webDriver.set(driver);
	}


}