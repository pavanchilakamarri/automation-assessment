package com.veevasys.driver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.veevasys.configuration.Config;
import com.veevasys.runners.ExecutionSetup;
import com.veevasys.utils.LogUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.veevasys.report.ExtentReportManager.fail;

public class WebDriverUtils {

    private WebDriver driver;
    private Actions actions;

    public WebDriverUtils() {
        if (LocalDriverManager.getWebDriver() != null) {
            this.driver = LocalDriverManager.getWebDriver();

        }

        actions = new Actions(driver);

    }

    public WebDriver getWebDriver() {
        return LocalDriverManager.getWebDriver();
    }

    public WebElement findElement(By locator) {
        try {
            return new WebDriverWait(driver, 10)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            fail("Element found failed" + exceptionToString(e));
            return null;
        }

    }

    public List<WebElement> findElements(By locator) {

        return driver.findElements(locator);
    }

    public WebDriverUtils visit(String url) {
        driver.get(url);
        return this;
    }

    public WebDriverUtils click(By locator) {

        findElement(locator).click();
        return this;
    }

    public WebDriverUtils mouseOver(By locator) {

        WebElement menu = new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(locator));
        actions.moveToElement(findElement(locator)).perform();
        return this;

    }

    public void mouseOverAndClick(By locator) {

        try {
            WebElement menu = new WebDriverWait(driver, 10).until(ExpectedConditions
                    .elementToBeClickable(locator));
       /* actions.moveToElement(menu)
                .moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//li[@id='menu-item-6380']//a[contains(text(),'Alle Cloudwisers')]"))))
                .click().build().perform();*/

            actions.moveToElement(findElement(locator)).click().perform();
        }catch (Exception e){
            fail("Not able to hover and click " + exceptionToString(e));
        }
    }


    public void hoverAndClickMultipleElements(By mainLocator, By subLocator) {

        try {
            WebElement menu = new WebDriverWait(driver, 10).until(ExpectedConditions
                    .presenceOfElementLocated(mainLocator));
       /* actions.moveToElement(menu)
                .moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//li[@id='menu-item-6380']//a[contains(text(),'Alle Cloudwisers')]"))))
                .click().build().perform();*/

            actions.moveToElement(findElement(mainLocator)).moveToElement(findElement(subLocator)).click().build().perform();
        } catch (Exception e) {
            fail("Element not found to over and click" + exceptionToString(e));
            LogUtils.info(e.getStackTrace());

        }
    }

    public boolean isElementDisplayed(By locator) {

        return isElementPresent(locator) && findElement(locator).isDisplayed();
    }

    private boolean isElementPresent(By locator) {
        return !findElements(locator).isEmpty();
    }

    public boolean validateTitle(String title) {

        System.out.println("TITLE IS :: " + driver.getTitle());
        if (driver.getTitle().equalsIgnoreCase(title)) {
            return true;
        }
        return false;
    }

    public void switchToNewWindow() {

        String currentTab = driver.getWindowHandle();
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        windows.remove(currentTab);
        for (String tab : windows) {
            driver.switchTo().window(tab);
        }

    }

    public String getText(WebElement locator) {

        return locator.getText();
    }

    public boolean isElementEnabled(By locator) {

        return isElementPresent(locator) && findElement(locator).isEnabled();
    }

    public WebDriverUtils clickByJS(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", findElement(locator));
        return this;
    }

    public WebDriverUtils scrollToElement(By locator) {
        scrollToTop();
        WebElement element = findElement(locator);
        scrollToOffset(element.getLocation().x, element.getLocation().y);
        return this;
    }

    public WebDriverUtils scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
        return this;
    }

    public WebDriverUtils scrollToOffset(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(" + x + "," + (y - 100) + ")");
        return this;
    }

    private static String exceptionToString(Exception e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
