package bingsearchtests;

import googlesearchtests.GoogleSearchByTermsHeadless;
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

public class BingSearchByTermsHeadless {
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
            case CHROME_HEADLESS:
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
        driver.get(BING_URL);
        driver.manage().window().maximize();

        // Agree to consent
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//button[@id='bnp_btn_accept']")));

        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='bnp_btn_accept']"));
        agreeButton.click();
    }

    @AfterAll
    public static void classDown() {
        //close the browser
        driver.quit();
    }

    @BeforeEach
    public void testSetup() {
        //Navigate to google.com
        driver.get(BING_URL);
    }

    @Test
    public void resultFound_when_searchValidTermsProvided() {

        //Type text in search field
        WebElement searchField = driver.findElement(By.xpath("//input[@type='search' and @id='sb_form_q']"));
        searchField.sendKeys(TELERIK_ACADEMY);

        //Click search button
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//label[@role='button']")));

        WebElement searchButton = driver.findElement(By.xpath("//label[@role='button']"));
        searchButton.click();

        //Assert result found
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("(//h2)[1]")));

        WebElement firstResult = driver.findElement(By.xpath("(//h2)[1]"));


        Assertions.assertTrue((firstResult.getText().contains(EXPECTED_RESULT) ||
                firstResult.getText().contains(EXPECTED_RESULT_BING)), ERROR_MESSAGE);

    }
}
