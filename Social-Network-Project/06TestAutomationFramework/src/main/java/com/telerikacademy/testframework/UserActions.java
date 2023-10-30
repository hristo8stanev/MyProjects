package com.telerikacademy.testframework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;

public class UserActions {

    final WebDriver driver;
    int defaultTimeout = Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds"));

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions() {
        this.driver = Utils.getWebDriver();
    }

    public static void loadBrowser(String baseUrlKey) {
        Utils.getWebDriver().get(Utils.getConfigPropertyByKey(baseUrlKey));
    }

    public static void quitDriver() {
        Utils.tearDownWebDriver();
    }

    public void clickElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        Utils.LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void sendKeys(String key, String value, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        Utils.LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    public void keyboardActionArrowDown(String key) {

        String locator = getLocatorValueByKey(key);

        Utils.LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));

        element.sendKeys(Keys.ARROW_DOWN);

    }

    public void keyboardActionEnter(String key) {

        String locator = getLocatorValueByKey(key);

        Utils.LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));

        element.sendKeys(Keys.ENTER);

    }

    public void keyboardActionSpace(String key) {

        String locator = getLocatorValueByKey(key);

        Utils.LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));

        element.sendKeys(Keys.SPACE);

    }

    public void typeValueInField(String value, String field, Object... fieldArguments) {
        String locator = getLocatorValueByKey(field, fieldArguments);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    public void dragAndDropElement(String fromElementLocator, String toElementLocator) {

        String fromLocator = getLocatorValueByKey(fromElementLocator);
        WebElement fromElement = driver.findElement(By.xpath(fromLocator));

        String toLocator = getLocatorValueByKey(toElementLocator);
        WebElement toElement = driver.findElement(By.xpath(toLocator));

        Actions actions = new Actions(driver);

        Action dragAndDrop = actions.clickAndHold(fromElement)
                .moveToElement(toElement)
                .release(toElement)
                .build();
        dragAndDrop.perform();
    }

//    public void dragAndDrop2(String cardName, String listName) {
//        //Actions class method to drag and drop
//        Actions builder = new Actions(driver);
//
//        WebElement from = driver.findElement(By.xpath("//span[text()='" + cardName + "']"));
//
//        WebElement to = driver.findElement(By.xpath("//textarea[@aria-label='" + listName + "']"));
//        //Perform drag and drop
//        builder.dragAndDrop(from, to).build().perform();
//
//    }

    public void refreshPage() throws InterruptedException {
        Thread.sleep(3000);
        driver.navigate().refresh();

    }

    public void scrollDownInPage(String key) {

        WebElement Element = driver.findElement(By.xpath(key));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scrolling down the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);

    }

    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public void scrollUp(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public void mouseHoverBy(String key) {

        String xpath = (Utils.getUIMappingByKey(key));
        WebElement ele = driver.findElement(By.xpath(xpath));

        Actions action = new Actions(driver);

        action.moveToElement(ele).perform();
        ele.click();


    }

    //############# WAITS #########
    public void waitForElementVisible(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementVisibleUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public void waitForElementClickable(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementToBeClickableUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public boolean isElementPresent(String locatorKey, Object... arguments) {
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        String locator = getLocatorValueByKey(locatorKey, arguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    //############# WAITS #########
    public void waitForElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Initialize Wait utility with default timeout from properties
        int defaultTimeout = Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        // 2. Use the method that checks for Element present
        // 3. Fail the test with meaningful error message in case the element is not present
        waitForElementPresenceUntilTimeout(locator, defaultTimeout, arguments);
    }

    public void assertElementPresent(String locator, Object... arguments) {
        Assertions.assertNotNull(driver.findElement(By.xpath(Utils.getUIMappingByKey(locator))),
                format("Element with %s doesn't present.", locator));
    }

    public void assertValueIncreasedBy(int actual, int expected) {
        Assertions.assertEquals(actual, expected, String.format("Actual value: %s is not equal to the expected value: %s one.",
                actual, expected));
    }


    public void assertEmailFieldOnProfilePage(String email) {
        String xpath = String.format("//div[@class='row' and contains(string(), 'Email')]//div[@class='col-md-6' and contains(string(), '%s')]", email);
        String actualEmail = driver.findElement(By.xpath(xpath)).getText();
        Assertions.assertEquals(email, actualEmail, String.format(
                "Expected email is %s, but %s is found.", email, actualEmail));
    }

    public void assertFirstLastNamesFieldOnProfilePage(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        String xpath = String.format("//div[@class='row' and contains(string(), 'Name')]//div[@class='col-md-6' and contains(string(), '%s')]", fullName);
        String actualFullName = driver.findElement(By.xpath(xpath)).getText();
        Assertions.assertEquals(fullName, actualFullName, String.format(
                "Expected Full Name is %s, but %s is found.", fullName, actualFullName));
    }

    public void assertElementVisible(String locator, Object... arguments) {
        Assertions.assertTrue(isElementVisible(locator, arguments),
                format("Element with %s isn't visible.", locator));
    }

    public void assertElementNotPresent(String locator) {
        By xpathLocator = By.xpath(Utils.getUIMappingByKey(locator));

        try {
            WebElement element = driver.findElement(xpathLocator);
            Assertions.fail(format("Element with %s is present, but it shouldn't be.", locator));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Element is not present, which is the expected behavior
        }
    }

    public void selectFromDropdown(String key, String start, int target) {
        Select drop = new Select(driver.findElement(By.xpath(key)));
        drop.selectByVisibleText(start);
        drop.selectByIndex(target);
    }

    public void deleteEmailFied() {
        driver.findElement(By.xpath(Utils.getUIMappingByKey("profile.email.address"))).clear();
    }

    public String readTextFromElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        Utils.LOGGER.info("Reading text from element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        System.out.println(element.toString());

        String text = element.getText();

        return text;
    }

//    public void assertElementPresentWithArg(String locator,  Object... arguments) {
//        String formattedLocator = getLocatorValueByKey(locator, arguments);
//        Assertions.assertNotNull(format("Element with %s doesn't present.", formattedLocator),
//                driver.findElement(By.xpath(getUIMappingByKey(formattedLocator))));
//    }

//    public void assertElementPresent(String locator ) {
//
//        Assert.assertNotNull(format("Element with %s doesn't present.", locator),
//                driver.findElement(By.xpath(getUIMappingByKey(locator))));
//    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue) {
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        String xpath = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(xpath));
        // 2. Get the element attribute
        String value = element.getAttribute(attributeName);
        // 3. Assert equality with expected value
        Assertions.assertEquals(format("Element with locator %s doesn't match", attributeName), getLocatorValueByKey(attributeValue), value);
    }

    private String getLocatorValueByKey(String locator) {
        return format(Utils.getUIMappingByKey(locator));
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return String.format(Utils.getUIMappingByKey(locator), arguments);
    }

    private void waitForElementVisibleUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    public void waitForElementToBeClickableUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    public boolean isElementVisible(String locator, Object... arguments) {
        Duration timeout = Duration.ofSeconds(defaultTimeout);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, arguments);

        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}