package com.veevasys.stefDefinition;

import com.veevasys.configuration.EnvironmentConfig;
import com.veevasys.driver.LocalDriverManager;
import com.veevasys.pages.BullsHomePage;
import com.veevasys.report.ExtentReportManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DerivedProduct2 {

    BullsHomePage homePage = new BullsHomePage();

    @Given("user is in home page")
    public void user_is_in_home_page(){
        LocalDriverManager.getWebDriver().get(EnvironmentConfig.getInstance().getUrl());
    }

    @When("user retrieves all the links from footer and add to csv file")
    public void userRetrievesAllTheLinksFromFooter() {

        homePage.findingAllLinks();
    }

    @Then("add the href of those links to csv file and add to report")
    public void addTheHrefOfThoseLinksToCsvFile() {

        ExtentReportManager.logMessage("Added the product title and price to a file");
        String path = System.getProperty("user.dir") + "/src/test/resources/footerLink.csv";
        System.out.println("PATH IS :: " + path);
        ExtentReportManager.info("<a href='" + path + "'>click to view text</a>");


    }

    @And("report the duplicate links")
    public void reportTheDuplicateLinks() {

    }
}
