package core;

import org.example.BrowserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void beforeAllTests() {
        driver = startBrowser(BrowserType.CHROME);

        //Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Navigate to saucedemo.com
        driver.get(SAUCE_URL);

        authenticateWithUser("standard_user", "secret_sauce");
    }

    @AfterEach
    public void afterTest() {
        // close driver
        driver.close();
    }

    protected static WebDriver startBrowser(BrowserType browserType) {
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


    protected static void authenticateWithUser(String username, String pass) {

        WebElement usernameInput = driver.findElement(By.xpath("//input[@data-test='username']"));
        usernameInput.sendKeys(username);

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        loginButton.click();

        WebElement inventoryPageTitle = driver.findElement(By.xpath("//div[@class='app_logo']"));
        wait.until(ExpectedConditions.visibilityOf(inventoryPageTitle));
    }

    protected WebElement getProductByTitle(String title) {
        return driver.findElement(By.xpath
                (String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", title)));
    }

    protected void addItemsToShoppingCard() {
        WebElement backpack = getProductByTitle(SAUCE_LABS_BACKPACK);
        backpack.findElement(By.className("btn_inventory")).click();

        WebElement tShirt = getProductByTitle(SAUCE_LABS_BOLT_T_SHIRT);
        tShirt.findElement(By.className("btn_inventory")).click();

        // Click on shopping Cart
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    protected static void fillShippingDetails(String firstName, String lastName, String postCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postCode);
    }

    protected static String calculatePrice() {
        double expectedPrice = 29.99 + 15.99 + 3.68;
        String expectedResult = String.format("Total: $%.2f", expectedPrice);
        return expectedResult;
    }
}

