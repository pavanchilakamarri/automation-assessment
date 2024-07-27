package com.veevasys.pages;

import com.veevasys.driver.WebDriverUtils;
import com.veevasys.utils.FileConfigUtils;
import com.veevasys.utils.TextFileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class BullsHomePage {


    private By footer = By.xpath("//footer[@data-testid='footer']");
    private By footerLinks = By.xpath("//footer[@data-testid='footer']/div[2]/div//a");

    private Map<String, Integer> dupLinks = new HashMap<>();

    Set<String> st = new HashSet<>();
    WebDriverUtils driver;

    public BullsHomePage() {
        this.driver = new WebDriverUtils();
    }

    public void findingAllLinks() {

      driver.scrollToElement(footer);

        List<WebElement> linksList = driver.findElements(footerLinks);

        for (WebElement we : linksList) {

            String link = we.getAttribute("href");


            if (st.contains(link)) {

                if (dupLinks.containsKey(link))
                    dupLinks.put(link, dupLinks.get(link) + 1);
                else
                    dupLinks.put(link, 1);

            } else
                st.add(link);

        }
        System.out.println("NUMBER OF DUPLICATE LINKS ARE "+ dupLinks.size());
        String path = FileConfigUtils.readConfigFile("csvFile","src/test/resources/test.properties");
        TextFileUtils textFileUtils = new TextFileUtils(path);
        for (String s: st) {
            textFileUtils.writeToFile(s);
            System.out.println(s);
            textFileUtils.newLine();
        }
        textFileUtils.closeFile();
    }


}
