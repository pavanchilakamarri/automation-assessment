package com.veevasys.pages;

import com.veevasys.driver.WebDriverUtils;
import com.veevasys.utils.FileConfigUtils;
import com.veevasys.utils.TextFileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MensPage {

    WebDriverUtils driver;
    private By shopMen = By.xpath("(//a[contains(@title,'Men')])[1]");

    private By menSelected = By.xpath("(//h2[text()='Your Selections']/following::span[. ='Men'])[1]");

    private By jacketRadioButton = By.xpath("//span[. ='Jackets']");

    private By gridList = By.xpath("//div[@class='columns small-5 medium-12']");

    private String findPrice = "(//span[@class='lowest']//span[@class='sr-only'])[";

    private String findTitle = "(//div[@class='product-card-title'])[";

    private By alertPopUp = By.xpath("//i[@aria-label='Close Pop-Up'][@role='button']");

    private By nextPage = By.xpath("//div[@class='grid-paginator']//a[@aria-label='next page'][@aria-disabled='false']");

    ////div[@data-trk-id='product-grid']/div/div[2]/div/following::span[@class='sr-only'][2]
    ////span[contains(text(),'Regular')]/span/span[@class='sr-only']


    public MensPage() {
        this.driver = new WebDriverUtils();
    }

    public void validateMenSelected() {

        driver.switchToNewWindow();

        Assert.assertTrue("Men option not selected", driver.isElementDisplayed(menSelected));

    }

    public void clickJacketFilter() {


        if (driver.isElementPresent(alertPopUp))
            driver.click(alertPopUp);
        driver.click(jacketRadioButton);
    }

    public void retrieveProductDetails() throws IOException {


        String txtPath = FileConfigUtils.readConfigFile("txtPath", "src/test/resources/test.properties");
        TextFileUtils textFileUtils = new TextFileUtils(txtPath);
        boolean pagenation = true;
        while (pagenation) {
            List<WebElement> listOfCards = driver.findElements(gridList);
            int i = 1;
            System.out.println("SIZE of grid is :: " + listOfCards.size());
            for (WebElement we : listOfCards) {

                textFileUtils.writeToFile("PRODUCT PRICE IS :: " + we.findElement(By.xpath(findPrice + i + "]")).getText());
                textFileUtils.writeToFile(" PRODUCT TITLE IS :: " + we.findElement(By.xpath(findTitle + i + "]")).getText());

                i++;
                textFileUtils.newLine();
            }


            if (driver.isElementDisplayed(nextPage))
                driver.click(nextPage);
            else
                pagenation = false;
        }
        textFileUtils.closeFile();
    }

}
