package core;

import org.example.BrowserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

import static org.example.Constants.*;
import static org.example.BrowserType.*;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeEach
    public void beforeAllTests() {
        driver = startBrowser(BrowserType.CHROME);

        //Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Navigate to saucedemo.com
        driver.get(SAUCE_URL);

    }

    @AfterEach
    public void afterTest() {
        // close driver
        driver.close();
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

        authenticateWithUser("standard_user", "secret_sauce");

        WebElement backpack = getProductByTitle(SAUCE_LABS_BACKPACK);
        backpack.findElement(By.className("btn_inventory")).click();

        WebElement tShirt = getProductByTitle(SAUCE_LABS_BOLT_T_SHIRT);
        tShirt.findElement(By.className("btn_inventory")).click();

        // Click on shopping Cart
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    protected static void fillShippingDetails(ShippingDetails info) {

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(info.firstName);
        driver.findElement(By.id("last-name")).sendKeys(info.lastName);
        driver.findElement(By.id("postal-code")).sendKeys(info.postCode);
        driver.findElement(By.id("continue")).click();
    }
    protected static String calculatePrice(List<WebElement> prices) {
        String firstPrice = prices.get(0).getText().substring(1);
        String secondPrice = prices.get(1).getText().substring(1);
        String vat = driver.findElement(By.className("summary_tax_label")).getText().substring(6);

        double calculatedTotal = Double.parseDouble(firstPrice) + Double.parseDouble(secondPrice) + Double.parseDouble(vat);
        String expectedResult = String.format("Total: $%.2f", calculatedTotal);
        return expectedResult;
    }
}

