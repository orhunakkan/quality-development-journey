package com.example.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
