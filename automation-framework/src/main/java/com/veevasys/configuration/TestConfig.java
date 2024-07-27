package com.veevasys.configuration;

import java.util.Optional;

public class TestConfig {

    private String execution = "local";
    private int retryCount = 0;
    private int threadCount = 1;
    private boolean webTest = true;
    private String environment;

    private String projectName;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getExecution() {
        Optional.ofNullable(execution).orElseThrow(() -> new RuntimeException("execution not defined in testConfig"));

        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public boolean isWebTest() {
        return webTest;
    }

    public void setWebTest(boolean webTest) {
        this.webTest = webTest;
    }

    public String getEnvironment() {

        Optional.ofNullable(environment).orElseThrow(() -> new RuntimeException("environment not defined in testConfig"));
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}

