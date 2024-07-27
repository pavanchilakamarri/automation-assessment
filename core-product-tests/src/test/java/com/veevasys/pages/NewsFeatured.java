package com.veevasys.pages;

import com.veevasys.driver.WebDriverUtils;
import com.veevasys.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NewsFeatured {

    WebDriverUtils driver;

    private By videosCount = By.xpath("//h3[text()='VIDEOS']/following::ul[@data-testid='content-grid-']/li");

    private By oldVideos = By.xpath("//h3[text()='VIDEOS']/following::div[@data-testid='tile-meta']//span");

    ////h3[text()='VIDEOS']/following::div[@data-testid='tile-meta']//span

    public NewsFeatured() {
        this.driver = new WebDriverUtils();
    }


    public void validateTitle() {
        String title = "News & Media | NBA.com";
        driver.validateTitle(title);
    }


    public int totalVideosCount() {

        driver.scrollToElement(videosCount);
        return driver.findElements(videosCount).size();
    }

    public int videosPresentMoreDays(int days) {

        int totalVideos = driver.findElements(videosCount).size();

        List<WebElement> listOfCards = driver.findElements(oldVideos);
        int i = 1;
        System.out.println("SIZE of grid is :: " + listOfCards.size());
        for (WebElement we : listOfCards) {

            int currentVal = Integer.parseInt(String.valueOf(we.getText().charAt(0)));
            LogUtils.info("CURRENT VALUE OF VIDEO is :: " + currentVal);
            LogUtils.info("EXPECTED VALUE :: " + days);
            if (currentVal < days)
                totalVideos = totalVideos - 1;
            else
                break;

        }

        return totalVideos;
    }

}
