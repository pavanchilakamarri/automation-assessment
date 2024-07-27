package com.veevasys.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class Config {
    private TestConfig testConfig;

    private WebDriverConfig webDriverConfig;

    private static Config instance;

    public Config() {
    }

    public static synchronized Config getInstance() {

        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {

                    File file = new File("../automation-framework/src/main/resources/test-properties.yaml");
                    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                    try {
                        instance = objectMapper.readValue(file, Config.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Application config info " + instance.toString());
                }
            }
        }
        return instance;
    }


    public TestConfig getTestConfig() {
        return testConfig;
    }

    public WebDriverConfig getWebDriverConfig() {
        return webDriverConfig;
    }


    public void setTestConfig(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    public void setWebDriverConfig(WebDriverConfig webdriverConfig) {
        this.webDriverConfig = webdriverConfig;
    }


}

