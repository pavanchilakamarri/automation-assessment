/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package com.veevasys.utils;

import com.veevasys.configuration.Config;
import com.veevasys.driver.LocalDriverManager;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelpers {

    // ------Record with Monte Media library---------
    private static String name;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public CaptureHelpers() {

    }

    public static void captureScreenshot(WebDriver driver, String screenName, String ssPath) {

        if (Config.getInstance().getWebDriverConfig().isScreenshots()) {
            try {
                String path = ssPath;
                File file = new File(path);
                if (!file.exists()) {
                    LogUtils.info("No Folder: " + path);
                    file.mkdir();
                    LogUtils.info("Folder created: " + file);
                }

                LogUtils.info("Driver for Screenshot: " + driver);
                // Create reference of TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // Call the capture screenshot function - getScreenshotAs
                File source = ts.getScreenshotAs(OutputType.FILE);
                // result.getName() Get the name of the test case and assign it to the screenshot file name
                FileUtils.copyFile(source, new File(path + File.separator + screenName + "_" + dateFormat.format(new Date()) + ".png"));
                LogUtils.info("Screenshot taken: " + screenName);
                LogUtils.info("Screenshot taken current URL: " + driver.getCurrentUrl());
            } catch (Exception e) {
                LogUtils.info("Exception while taking screenshot: " + e.getMessage());
            }
        }
    }

    /*  public static File getScreenshot(String screenshotName) {
          Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
          String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
          BufferedImage image = null;
          try {
              image = new Robot().createScreenCapture(allScreenBounds);
          } catch (AWTException e) {
              throw new RuntimeException(e);
          }

          String path = SystemHelpers.getCurrentDir() + FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images";
          File folder = new File(path);
          if (!folder.exists()) {
              folder.mkdir();
              LogUtils.info("Folder created: " + folder);
          }

          String filePath = path + File.separator + screenshotName + dateName + ".png";
          File file = new File(filePath);
          try {
              ImageIO.write(image, "PNG", file);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          return file;
      }
  */
    public static void takeScreenshotScenario(Scenario scenario, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) LocalDriverManager.getWebDriver();
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", screenshotName);
        } catch (Exception e) {
            LogUtils.error("Unable to take screenshot");
            LogUtils.error(e);
        }
    }

}
