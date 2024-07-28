package com.veevasys.runners;

import com.veevasys.configuration.Config;
import com.veevasys.configuration.TestConfig;
import com.veevasys.driver.LocalDriverFactory;
import com.veevasys.driver.LocalDriverManager;
import com.veevasys.utils.LogUtils;
import org.apache.log4j.Logger;

import java.util.Optional;

public class ExecutionSetup {

    protected static final Logger log = Logger.getLogger(ExecutionSetup.class);
    public static int retryCount;
    public static String environment;
    public static boolean isWebTest = true;
    public static String execution;
    public static boolean isLocalExecution = false;
    public static Config config;

    public static Config setUp(String browser, String env) {
        try {

            config = Config.getInstance();

            //retryCount = config.getTestConfig().getRetryCount();
            execution = config.getTestConfig().getExecution();
            config.getTestConfig().setEnvironment(env);

			/*if (isWebTest && execution.equalsIgnoreCase("local")) {
				Optional.ofNullable(config.getWebDriverConfig())
						.orElseThrow(() -> new RuntimeException("webDriverConfig not defined "));
				config.getWebDriverConfig().setDriver(browser);
			}*/
            LogUtils.info("BROWSER SET in Execution file is :: " + browser);
            config.getWebDriverConfig().setDriver(browser);

            //USING isLocalExecution in DriverManager
            if (execution.equalsIgnoreCase("local")) {
                isLocalExecution = true;
            } else {
                throw new RuntimeException("execution type is invalid. valid: remote/local");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return config;
    }
}
