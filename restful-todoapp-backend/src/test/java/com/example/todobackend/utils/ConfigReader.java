package com.example.todobackend.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static void loadConfig() {
        if (prop == null) {
            prop = new Properties();
            try (InputStream input = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {
                if (input == null) {
                    System.out.println("Sorry, unable to find config.properties");
                    return;
                }
                prop.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String get(String key) {
        loadConfig();
        return prop.getProperty(key);
    }

}
