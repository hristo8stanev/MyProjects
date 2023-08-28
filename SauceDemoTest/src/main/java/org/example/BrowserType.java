package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public enum BrowserType {
    FIREFOX,
    FIREFOX_HEADLESS,
    CHROME,
    CHROME_HEADLESS,
    EDGE,
    EDGE_HEADLESS;

    public static WebDriver startBrowser(BrowserType browserType) {
        //Setup Browser
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case CHROME_HEADLESS:
                ChromeOptions chromeOptionsHeadless = new ChromeOptions();
                chromeOptionsHeadless.addArguments("--headless=new");
                return new ChromeDriver(chromeOptionsHeadless);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptionsHeadless = new FirefoxOptions();
                firefoxOptionsHeadless.addArguments("--headless");
                return new FirefoxDriver(firefoxOptionsHeadless);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
            case EDGE_HEADLESS:
                EdgeOptions edgeOptionsHeadless = new EdgeOptions();
                edgeOptionsHeadless.addArguments("--headless=new");
                return new EdgeDriver(edgeOptionsHeadless);
        }
        return null;
    }
}

