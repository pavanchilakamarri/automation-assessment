package com.veevasys.pages;

import com.veevasys.driver.LocalDriverManager;
import com.veevasys.driver.WebDriverUtils;
import com.veevasys.utils.CaptureHelpers;
import com.veevasys.utils.FileConfigUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {


    private By cookiesContinueWithoutDeciding = By.linkText("Continue without Deciding");
    private By alertClose = By.xpath("//div[@role='dialog']/div/div[text()='x']");

    private By shop = By.xpath("(//span[text()='Shop'])[1]");
    private By shopMen = By.xpath("(//a[contains(@title,'Men')])[1]");

    private By menuBar = By.xpath("(//span[text()='...'])[1]");

    private By news_Featured = By.xpath("(//*[text()='News & Features'])[1]");
    WebDriverUtils driver;

    public HomePage() {
        this.driver = new WebDriverUtils();
    }

    public void clickCookies() {

        if (driver.isElementDisplayed(cookiesContinueWithoutDeciding))
            driver.click(cookiesContinueWithoutDeciding);
    }

    public void clickAlertClose() {

        CaptureHelpers.captureScreenshot(LocalDriverManager.getWebDriver(), "AlertBox",
                FileConfigUtils.readConfigFile("EXPORT_CAPTURE_PATH", "src/test/resources/test.properties"));
        driver.click(alertClose);
    }

    public void clickMens() {

        //driver.hoverAndClickMultipleElements(shop,shopMen);
        driver.mouseOver(shop);
        driver.mouseOverAndClick(shopMen);

    }


    public void clickNewsFeatured() {

        driver.mouseOver(menuBar);
        driver.mouseOverAndClick(news_Featured);

        //   driver.clickByJS(news_Featured);


    }


}
