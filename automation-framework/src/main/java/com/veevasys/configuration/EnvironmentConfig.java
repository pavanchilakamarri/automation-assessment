package com.veevasys.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import static com.veevasys.runners.ExecutionSetup.config;

public class EnvironmentConfig {
    private static EnvironmentConfig instance;

    private EnvironmentConfig() {

    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static synchronized EnvironmentConfig getInstance() {
        if (instance == null) {
            synchronized (EnvironmentConfig.class) {
                if (instance == null) {

                    Yaml yaml = new Yaml();
                    InputStream inputStream = EnvironmentConfig.class.getClassLoader()
                            .getResourceAsStream("environment-properties.yaml");
                    Map<String, Map> obj = yaml.load(inputStream);
                    instance = new EnvironmentConfig();
                    String env = config.getTestConfig().getEnvironment();
                    instance.setUrl(Optional.ofNullable(obj.get(env).get("url").toString()).orElseThrow(() -> new RuntimeException("URL not found in envirnment properties")));

                }
            }
        }

        return instance;
    }


}
