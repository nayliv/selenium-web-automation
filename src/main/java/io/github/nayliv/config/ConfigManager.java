package io.github.nayliv.config;

import io.github.nayliv.driver.BrowserType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final String CONFIG_FILE = "config/application.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private ConfigManager() {
    }

    private static void loadProperties() {
        try (InputStream inputStream = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Configuration file not found: " + CONFIG_FILE
                );
            }

            PROPERTIES.load(inputStream);

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Unable to load configuration file: " + CONFIG_FILE,
                    exception
            );
        }
    }

    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Configuration property not found or empty: " + key
            );
        }

        return value.trim();
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static BrowserType getBrowser() {
        String browser = getProperty("browser").toUpperCase();

        try {
            return BrowserType.valueOf(browser);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Unsupported browser configured: " + browser,
                    exception
            );
        }
    }

    public static int getTimeout() {
        String timeout = getProperty("timeout");

        try {
            return Integer.parseInt(timeout);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Invalid timeout value: " + timeout,
                    exception
            );
        }
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                getProperty("headless")
        );
    }
}