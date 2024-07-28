package com.veevasys.utils;

import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileConfigUtils {

    private FileConfigUtils() {
    }

    private static final Logger log = Logger.getLogger(FileConfigUtils.class.getName());

    /**
     * It is used to load properties from properties file
     *
     * @param filePath
     * @return
     */
    public static Properties initialize(String filePath) {

        Properties props = new Properties();
        try {
            props.load(new FileReader(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }

    /**
     * This method is used to read specific property value.
     *
     * @param key
     * @param filePath
     * @return
     */
    public static String readConfigFile(String key, String filePath) {
        String propertyValue = null;
        try {

            LogUtils.info("FILE PATH IS :: " + filePath);
            Properties testdataConfig = initialize(filePath);
            propertyValue = testdataConfig.getProperty(key);

        } catch (Exception e) {
            log.log(Level.SEVERE, "script exception" + e);
        }
        return propertyValue;
    }

    /**
     * This method is used to write property and value into file.
     *
     * @param key
     * @param value
     * @param filePath
     */
    public static synchronized void writeConfigFile(String key, String value, String filePath) {
        Properties testdataConfig = initialize(filePath);
        File file = new File(filePath);
        try (OutputStream writeContents = new FileOutputStream(file)) {
            testdataConfig.setProperty(key, value);
            testdataConfig.store(writeContents, "");
        } catch (Exception e) {
            log.log(Level.SEVERE, "script exception" + e);
        }

    }


}

