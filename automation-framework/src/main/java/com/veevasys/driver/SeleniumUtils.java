package com.veevasys.driver;

/*import com.gap.platform.common.ElementFormatter;
import com.gap.platform.junitutils.JunitListener;
import com.gap.platform.runners.CucumberRunner;
import com.gap.platform.runners.ExecutionSetup;*/
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Deprecated
public class SeleniumUtils extends LocalDriverManager {
/*

    protected static final Logger log = Logger.getLogger(SeleniumUtils.class);
    private static final String SEPARATOR = System.getProperty("file.separator");

    public static WebDriver getDriver() {
        return LocalDriverManager.getWebDriver();
    }

    */
/**
     * This method used to wait for given web element to be present and visible.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void waitForElement(ElementFormatter elementDetails) {
        LinkedHashMap<String, String> elementsMap = elementDetails.getElementsMap();
        int flag = 0;
        for (Map.Entry<String, String> element : elementsMap.entrySet()) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), 30);
                wait.until(ExpectedConditions.presenceOfElementLocated(getElementLocator(element.getKey(), element.getValue())));
            } catch (Exception e) {
                flag++;
                if (flag < elementsMap.size())
                    continue;
                else {
                   log.error("",e);
                    fail(elementDetails.getelementName() + " " + exceptionToString(e));
                }
            }
        }
    }

    */
/**
     * This method used to wait for given web element to be clickable.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void waitForElementToBeClickable(ElementFormatter elementDetails) {
        LinkedHashMap<String, String> elementsMap = elementDetails.getElementsMap();
        int flag = 0;
        for (Map.Entry<String, String> element : elementsMap.entrySet()) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), 30);
                wait.until(ExpectedConditions.elementToBeClickable(getElementLocator(element.getKey(), element.getValue())));
            } catch (Exception e) {
                flag++;
                if (flag < elementsMap.size())
                    continue;
                else {
                    fail(elementDetails.getelementName() + " " + exceptionToString(e));
                }
            }
        }
    }

    */
/**
     * This method is used to check web element is visible or not.
     *
     * @param elementDetails
     * @return
     *//*

    @Deprecated
    public static boolean isVisible(ElementFormatter elementDetails) {
        boolean flagValue = false;
        LinkedHashMap<String, String> elementsMap = elementDetails.getElementsMap();
        int flag = 0;
        for (Map.Entry<String, String> element : elementsMap.entrySet()) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), 30);
                wait.until(ExpectedConditions.presenceOfElementLocated(getElementLocator(element.getKey(), element.getValue())));
                wait.until(ExpectedConditions.visibilityOfElementLocated(getElementLocator(element.getKey(), element.getValue())));
                flagValue = true;
                break;
            } catch (Exception e) {
                flag++;
                if (flag < elementsMap.size())
                    continue;
                else {

                    return false;
                }
            }
        }
        return flagValue;

    }

    */
/**
     * This method is used to hover the cursor on given web element.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void mouseHoverOnElement(ElementFormatter elementDetails) {
        try {
            Actions action = new Actions(getDriver());
            WebElement element = returnWebElement(elementDetails);
            action.moveToElement(element).build().perform();
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to move the cursor on given web element using java script executor.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void movingToElementJS(ElementFormatter elementDetails) {
        try {
            WebElement element = returnWebElement(elementDetails);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to click on given web element.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void clickOnElement(ElementFormatter elementDetails) {
        try {
            returnWebElement(elementDetails).click();
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to enter text into given web element.
     *
     * @param elementDetails
     * @param text
     *//*

    @Deprecated
    public static void enterTextIntoTextBox(ElementFormatter elementDetails, String text) {
        try {
            WebElement textBox = returnWebElement(elementDetails);
            textBox.clear();
            textBox.sendKeys(text);
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to select visible text option available from drop box.
     *
     * @param elementDetails
     * @param text
     *//*

    @Deprecated
    public static void selectVisibleTextOptionInDropDown(ElementFormatter elementDetails, String text) {
        try {
            Select dropDown = new Select(returnWebElement(elementDetails));
            dropDown.selectByVisibleText(text);
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to select option value available from drop box.
     *
     * @param elementDetails
     * @param value
     *//*

    @Deprecated
    public static void selectOptionValueInDropDown(ElementFormatter elementDetails, String value) {
        try {
            Select dropDown = new Select(returnWebElement(elementDetails));
            dropDown.selectByValue(value);
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to check given value is available in options of select box.
     *
     * @param elementDetails
     * @param value
     * @return
     *//*

    @Deprecated
    public boolean isSelectOptionAvailable(ElementFormatter elementDetails, String value) {
        boolean b = false;
        Select dropDown = new Select(returnWebElement(elementDetails));
        List<WebElement> options = dropDown.getOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(value)) {
                b = true;
                break;
            }
        }
        return b;
    }

    */
/**
     * This method is used to check given value and attribute are available in options of select box.
     *
     * @param elementDetails
     * @param attribute
     * @param value
     * @return
     *//*

    @Deprecated
    public boolean isSelectOptionAvailable(ElementFormatter elementDetails, String attribute, String value) {
        boolean b = false;
        Select dropDown = new Select(returnWebElement(elementDetails));
        List<WebElement> options = dropDown.getOptions();
        for (WebElement option : options) {
            if (option.getAttribute(attribute).trim().equalsIgnoreCase(value)) {
                b = true;
                break;
            }
        }
        return b;
    }

    */
/**
     * This method is used to double click on given web element.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void doubleClickOnElement(ElementFormatter elementDetails) {
        try {
            Actions action = new Actions(getDriver());
            WebElement element = returnWebElement(elementDetails);
            action.doubleClick(element).build().perform();
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    // Method for testing Title of the page...

    */
/**
     * This method is used to validate title of the web page.
     *
     * @param text
     * @return
     *//*

    @Deprecated
    public static boolean validateTitle(String text) {

        WebDriver driver = getDriver();
        return driver.getTitle().equalsIgnoreCase(text);

    }

    // Method for selecting value from sub-menu

    */
/**
     * This method is used to select sub-menu option using hover on main menu.
     *
     * @param parentElement
     * @param childElement
     *//*

    @Deprecated
    public static void selectItemOnHover(ElementFormatter parentElement, ElementFormatter childElement) {

        Actions a = new Actions(getDriver());
        a.moveToElement(returnWebElement(parentElement)).build().perform();
        waitForElementToBeClickable(childElement);
        a.moveToElement(returnWebElement(childElement)).click();
        a.build().perform();

    }

    // Get Text from Web page...

    */
/**
     * This method is used to get visible text from given web element.
     *
     * @param elementDetails
     * @return
     *//*

    @Deprecated
    public static String getTextFromElement(ElementFormatter elementDetails) {
        String text = null;
        try {
            text = returnWebElement(elementDetails).getText();
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
        return text;
    }

    // Verifying text present in page or not...

    */
/**
     * This method is used to verify given text is present in page or not.
     *
     * @param text
     * @return
     *//*

    @Deprecated
    public static boolean verifyTextPresent(String text) {

        try {
            if (getDriver().findElement(By.xpath("//*[contains (.,' " + text + "')]")).isDisplayed())
                return true;

        } catch (Exception e) {
            fail(exceptionToString(e));
        }

        return false;
    }

    */
/**
     * This method is used to click on given web element using actions class.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void clickUsingActions(ElementFormatter elementDetails) {
        try {
            Actions act = new Actions(getDriver());
            WebElement element = returnWebElement(elementDetails);
            act.moveToElement(element).click().build().perform();
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }

    }

    */
/**
     * @param elementDetails
     *//*

    @Deprecated
    public static void clickUsingJavaScript(ElementFormatter elementDetails) {
        try {
            WebElement element = returnWebElement(elementDetails);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", element);
            waitForAjaxtoComplete();
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }

    }

    */
/**
     * This method is used to switch the current window frame to given window frame.
     *
     * @param elementDetails
     *//*

    @Deprecated
    public static void switchToFrame(ElementFormatter elementDetails) {
        try {
            waitForAjaxtoComplete();
            getDriver().switchTo().frame(returnWebElement(elementDetails));
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to press given keyboard key.
     *
     * @param elementDetails
     * @param keyName
     *//*

    @Deprecated
    public static void pressKeyboardKey(ElementFormatter elementDetails, String keyName) {
        try {
            WebElement control = returnWebElement(elementDetails);
            control.sendKeys(keyName);
        } catch (Exception e) {
            fail(elementDetails.getelementName() + " " + exceptionToString(e));
        }
    }

    */
/**
     * This method is used to check its wait for jQuery to be loaded or not.
     *
     * @return
     *//*

    @Deprecated
    public static boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    */
/**
     * This method is used to wait until the Ajax to be completed.
     *
     * @return
     *//*

    @Deprecated
    public static boolean waitForAjaxtoComplete() {
        JavascriptExecutor je = (JavascriptExecutor) getDriver();
        String jQueryActiveScript = "return jQuery.active";
        long activeQueries = 300L;
        int i = 0;
        do {
            i++;
            try {
                activeQueries = (long) je.executeScript(jQueryActiveScript);
            } catch (Exception e) {

                activeQueries = 300L;
                i = i + 5;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("", e);
            }
        } while (activeQueries > 0 && i < 30);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail(exceptionToString(e));
        }
        return activeQueries == 0L;
    }

    */
/**
     * This method is used to switch back to default content.
     *//*

    @Deprecated
    public static void switchToDefaultContent() {
        WebDriver driver = getDriver();
        driver.switchTo().defaultContent();
    }

    */
/**
     * This method is used to close the current web driver.
     *//*

    @Deprecated
    public void closeDriver() {
        try {
            getDriver().close();
        } catch (Exception e1) {
            log.error("", e1);
        }
        getDriver().quit();
    }

    */
/**
     * This method is used to capture screen shot.
     *
     * @param testCaseName
     *//*

    @Deprecated
    public static void onTestFailureScreenshot(String testCaseName,String folder) {

        String path = "";
        if (ExecutionSetup.isWebTest) {
            for (int i = 0; i < 1; i++) {
            try {
                WebDriver augmentedDriver = new Augmenter().augment(getDriver());
                File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
                String folderName = folder;
                path = folderName + SEPARATOR + "screenshots" + SEPARATOR + testCaseName + ".png";
                FileUtils.copyFile(source, new File(path));
            } catch (Exception e) {
                continue;
            }}
        }
    }
    
    @Deprecated
    public static synchronized String takeScreenshot(String screenshotName) {
        String screenshotPath = "";
        String html = "";
        if (ExecutionSetup.isScreenshot) {
            for (int i = 0; i < 1; i++) {

            try {
                String unixTimestamp = String.valueOf((Instant.now().getEpochSecond()));
                //Thread.sleep(1000);
                TakesScreenshot ts = (TakesScreenshot) getDriver();
                File file = ts.getScreenshotAs(OutputType.FILE);
                String folderName="";
                if(!ExecutionSetup.isCucumberTest) {
                     folderName = JunitListener.testOutputFolder;
                }else {
                     folderName = CucumberRunner.testOutputFolder;
                }
            
                screenshotPath = folderName + SEPARATOR + "screenshots" + SEPARATOR;
                new File(screenshotPath);
                screenshotPath = screenshotPath + screenshotName + "_" + unixTimestamp + ".png";
                FileUtils.copyFile(file, new File(screenshotPath));
                String path = "screenshots"+ SEPARATOR + screenshotName + "_" + unixTimestamp + ".png";
                html = " <a target=_blank href=" + path.replaceAll(" ", "%20") + ">Screenshot  </a>";
            } catch (Exception e) {
            continue;
            }

        }}
        return html;

    }

    */
/**
     * This method is used to return elements of given web element.
     *
     * @param elementDetails
     * @return
     *//*

    @Deprecated
    public static WebElement returnWebElement(ElementFormatter elementDetails) {
        WebElement webElement = null;
        LinkedHashMap<String, String> elementsMap = elementDetails.getElementsMap();
        int flag = 0;
        for (Map.Entry<String, String> element : elementsMap.entrySet()) {
            try {
                webElement = getDriver().findElement(getElementLocator(element.getKey(), element.getValue()));
            } catch (Exception e) {
                flag++;
                if (flag < elementsMap.size())
                    continue;
                else
                    fail(elementDetails.getelementName() + " " + exceptionToString(e));
            }
        }
        return webElement;
    }

    */
/**
     * This method is used to get locator of web element.
     *
     * @param type
     * @param value
     * @return
     *//*

    @Deprecated
    public static By getElementLocator(String type, String value) {
        By elementLocator = null;
        switch (type) {
            case "XPATH":
                elementLocator = By.xpath(value);
                break;
            case "CSS":
                elementLocator = By.cssSelector(value);
                break;
            case "ID":
                elementLocator = By.id(value);
                break;
            case "CLASS":
                elementLocator = By.className(value);
                break;
            case "NAME":
                elementLocator = By.name(value);
                break;
            case "LINKTEXT":
                elementLocator = By.linkText(value);
                break;
            case "PARTIALLINKTEXT":
                elementLocator = By.partialLinkText(value);
                break;
            case "TAGNAME":
                elementLocator = By.tagName(value);
                break;
            default:
                log.info("Element Type does not match");
                break;
        }
        return elementLocator;
    }
    
    private static String exceptionToString(Exception e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
*/
}
