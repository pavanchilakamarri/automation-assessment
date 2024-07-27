package com.veevasys.stefDefinition;

import com.veevasys.pages.HomePage;
import com.veevasys.pages.MensPage;
import com.veevasys.pages.NewsFeatured;
import com.veevasys.report.ExtentReportManager;
import com.veevasys.utils.LogUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class CoreProduct {

    HomePage homePage = new HomePage();
    MensPage mensPage = new MensPage();

    NewsFeatured newsFeatured = new NewsFeatured();


    @Given("user is in home page")
    public void userIsInHomePage() {

        // homePage.clickCookies();
        ExtentReportManager.logMessage("HOME PAGE...");
        homePage.clickAlertClose();
        ExtentReportManager.logMessage("user clicked on alert");

    }

    @When("user clicks on Mens in shop menu")
    public void userClicksOnMensInShopMenu() {
        homePage.clickMens();
        ExtentReportManager.logMessage("User clicked Shop --> Mens");
    }

    @Then("user should be redirected to shop window")
    public void userShouldBeRedirectedToShopWindow() {
        mensPage.validateMenSelected();
        ExtentReportManager.logMessage("successfully redirected to the new window --> Mens");

    }

    @When("user select jacket radio button")
    public void whenSelectJacketRadioButton() {

        mensPage.clickJacketFilter();

    }

    @Then("user should store those data in file")
    public void allTheJacketCardsShouldBeDisplayed() throws IOException {
        mensPage.retrieveProductDetails();

    }


    @And("attach to the report")
    public void attachToTheReport() {

        ExtentReportManager.logMessage("Added the product title and price to a file");
        String path = System.getProperty("user.dir") + "/src/test/resources/productDetails.txt";
        ExtentReportManager.info("<a href='" + path + "'>click to view text</a>");


    }

    @When("user selects News & Feature from menu item")
    public void userSelectsNewsFeatureFromMenuItem() {
        homePage.clickNewsFeatured();
        ExtentReportManager.logMessage("User is redirected to News & Featured page");
    }

    @Then("user should be redirected to News & Feature page")
    public void userShouldBeRedirectedToNewsFeaturePage() {

        newsFeatured.validateTitle();
    }

    @And("user should count the number of videos present in page")
    public void userShouldCountTheNumberOfVideosPresentInPage() {

        int videoCount = newsFeatured.totalVideosCount();
        ExtentReportManager.logMessage("TOTAL Number of videos present in NEWS & FEATURED page is :: " + videoCount);
        LogUtils.info("TOTAL Number of videos present in NEWS & FEATURED page is :: " + videoCount);

    }

    @And("also count number of videos that are greater than <{int}> days")
    public void alsoCountNumberOfVideosThatAreD(int days) {

        int totalOldVideos = newsFeatured.videosPresentMoreDays(days);
        ExtentReportManager.logMessage("TOTAL Number of videos that present >=3 days in NEWS & FEATURED page is :: " + totalOldVideos);
        LogUtils.info("TOTAL Number of videos that present >=3 days in NEWS & FEATURED page is :: " + totalOldVideos);
    }
}
