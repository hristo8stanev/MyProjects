package com.telerikacademy.testframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class Utils {

    private static final Properties uiMappings = PropertiesManager.PropertiesManagerEnum.INSTANCE.getUiMappings();
    private static final Properties configProperties = PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties();
    public static final Logger LOGGER = LogManager.getRootLogger();

    public static WebDriver getWebDriver() {
        LOGGER.info("Initializing WebDriver");
        return CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
    }

    public static void tearDownWebDriver() {
        LOGGER.info("Quitting WebDriver");
        CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.quitDriver();
    }

    public static String getUIMappingByKey(String key) {
        String value = uiMappings.getProperty(key);
        return value != null ? value : key;
    }

    public static String getConfigPropertyByKey(String key) {
        String value = configProperties.getProperty(key);
        return value != null ? value : key;
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static long compareDates(String dateStr1, String dateStr2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(dateStr1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(dateStr2, formatter);

        Duration duration = Duration.between(dateTime1, dateTime2);

        // Convert the duration to minutes
        return duration.toMinutes();

    }


}
