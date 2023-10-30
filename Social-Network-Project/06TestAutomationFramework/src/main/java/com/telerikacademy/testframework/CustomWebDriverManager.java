package com.telerikacademy.testframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        private WebDriver driver = setupBrowser();

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        public WebDriver getDriver() {
            if (driver == null) {
                setupBrowser();
            }
            return driver;
        }

        private WebDriver setupBrowser() {
            String browserType = Utils.getConfigPropertyByKey("config.browserType");
            WebDriver driver;

            switch (browserType) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "chrome_headless":
                    ChromeOptions chromeOptionsHeadless = new ChromeOptions();
                    chromeOptionsHeadless.addArguments("--headless=new");
                    driver = new ChromeDriver(chromeOptionsHeadless);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "firefox_headless":
                    FirefoxOptions firefoxOptionsHeadless = new FirefoxOptions();
                    firefoxOptionsHeadless.addArguments("--headless=new");
                    driver = new FirefoxDriver(firefoxOptionsHeadless);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    throw new RuntimeException("Browser type in config does not match any known values: " + browserType);
            }

            driver.manage().window().maximize();
            this.driver = driver;
            return driver;
        }
    }
}
