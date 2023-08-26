package basesetup;

import org.example.BrowserType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BaseSetupClass {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeAll
    public static void classSetup() {
        driver = startBrowser(BrowserType.FIREFOX_HEADLESS);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterAll
    public static void classDown() {
        //close the browser
        driver.quit();
    }


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