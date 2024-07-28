package com.veevasys.configuration;

import java.util.Optional;

public class WebDriverConfig {
    private ThreadLocal<String> driver = new ThreadLocal<>();
    private String chromeSwitches;
    private String firefoxPreferences;
    private int implicitTimeout = 0;
    private int waitForTimeout = 10;
    private int pageLoadTimeout = 0;
    private boolean screenshots = true;


    // Getter Methods

    public String getDriver() {

        driver = Optional.ofNullable(driver).orElseThrow(() -> new RuntimeException("Browser Not found"));

        return driver.get();
    }

    public String getChromeSwitches() {
        chromeSwitches = Optional.ofNullable(chromeSwitches)
                .orElse("start-maximized,disable-popup-blocking,incognito,disable-infobars,ignore-certificate-errors");

        return chromeSwitches;
    }

    public String getFirefoxPreferences() {
        firefoxPreferences = Optional.ofNullable(firefoxPreferences).orElse("-Dfirefox.preferences=browser.download.folderList=2;browser.download.manager.showWhenStarting=false;browser.download.dir=c:\\downloads");

        return firefoxPreferences;
    }

    public void setDriver(String driver) {
        this.driver.set(driver);
    }

    public void setChromeSwitches(String chromeSwitches) {
        this.chromeSwitches = chromeSwitches;
    }

    public void setFirefoxPreferences(String firefoxPreferences) {
        this.firefoxPreferences = firefoxPreferences;
    }

    public int getImplicitTimeout() {
        implicitTimeout = Optional.ofNullable(implicitTimeout).orElse(0);
        return implicitTimeout;
    }

    public int getWaitForTimeout() {

        return waitForTimeout;
    }

    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public boolean isScreenshots() {
        return screenshots;
    }

    public void setScreenshots(boolean screenshots) {
        this.screenshots = screenshots;
    }

    public void setImplicitTimeout(int implicitTimeout) {
        this.implicitTimeout = implicitTimeout;
    }

    public void setWaitForTimeout(int waitForTimeout) {
        this.waitForTimeout = waitForTimeout;
    }
}
