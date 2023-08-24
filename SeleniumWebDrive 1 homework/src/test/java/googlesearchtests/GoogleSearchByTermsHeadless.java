package googlesearchtests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.example.Constants.*;
import static org.example.Constants.ERROR_MESSAGE;


public class GoogleSearchByTermsHeadless {
    private static WebDriver driver;
    private static WebDriverWait wait;
    public enum BrowserType {
        FIREFOX_HEADLESS,
        CHROME_HEADLESS,
        EDGE_HEADLESS
    }
    private static WebDriver startBrowser(BrowserType browserType) {
        //Setup Browser
        switch (browserType) {
            case  CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                return new FirefoxDriver(firefoxOptions);
            case EDGE_HEADLESS:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("-headless");
                return new EdgeDriver(edgeOptions);
        }
        return null;
    }

    @BeforeAll
    public static void classSetup() {

        driver = startBrowser(BrowserType.FIREFOX_HEADLESS);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Navigate to google.com
        driver.get(GOOGLE_URL);
        driver.manage().window().maximize();

        // Agree to consent
        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        agreeButton.click();
    }

    @AfterAll
    public static void classDown() {
        //close the browser
        driver.quit();
    }
    @BeforeEach
    public void testSetup() {
        driver.get(GOOGLE_URL);
    }

    @Test
    public void resultFound_when_validSearchTermsProvided() {

        //Type text
        WebElement searchBar = driver.findElement(By.xpath("//textarea[@type='search' and @name='q']"));
        searchBar.sendKeys(TELERIK_ACADEMY);

        //Click search button
        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit' and @name='btnK'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();

        //Assert Result found
        WebElement qaAlphaText = driver.findElement(By.xpath("(//a/h3)[1]"));
       Assertions.assertEquals(EXPECTED_RESULT, qaAlphaText.getText(), ERROR_MESSAGE);
    }
}

