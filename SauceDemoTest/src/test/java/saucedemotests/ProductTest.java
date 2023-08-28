package saucedemotests;

import core.BaseClass;

import org.example.BrowserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Constants.*;

public class ProductTest extends BaseClass {




    @Test
    public void productAddedToShoppingCart_when_addToCart() {

        addItemsToShoppingCard();

        //  // Assert Items and Totals
        var items = driver.findElements(By.className("inventory_item_name"));
        var price = driver.findElements(By.className("inventory_item_price"));

        Assertions.assertEquals(EXPECTED_ITEMS, items.size(), ITEM_ERROR_MESSAGE);

        Assertions.assertEquals(SAUCE_LABS_BACKPACK, items.get(0).getText(), ITEM_TITLE_ERROR_MESSAGE);
        Assertions.assertEquals(BACK_PRICE, price.get(0).getText(), ITEM_PRICE_ERROR_MESSAGE);

        Assertions.assertEquals(SAUCE_LABS_BOLT_T_SHIRT, items.get(1).getText(), ITEM_TITLE_ERROR_MESSAGE);
        Assertions.assertEquals(SHIRT_PRICE, price.get(1).getText(), ITEM_PRICE_ERROR_MESSAGE);

}

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation() {

        addItemsToShoppingCard();
        driver.findElement(By.id("checkout")).click();

        // fill form
        fillShippingDetails("Dimitar", "Petkov", "888");
        driver.findElement(By.id("continue")).click();

        var items = driver.findElements(By.className("inventory_item_name"));
        var total = driver.findElement(By.className("summary_total_label")).getText();

        String expectedResult = calculatePrice();

        //  // Assert Items and Totals
        Assertions.assertEquals(EXPECTED_ITEMS, items.size(), ITEM_ERROR_MESSAGE);
        Assertions.assertEquals(SAUCE_LABS_BOLT_T_SHIRT, items.get(1).getText(), ITEM_TITLE_ERROR_MESSAGE);
        Assertions.assertEquals(SAUCE_LABS_BACKPACK, items.get(0).getText(), ITEM_TITLE_ERROR_MESSAGE);

        Assertions.assertEquals(expectedResult, total, CALCULATE_PRICE_ERROR);
    }



    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {
        // Add Backpack and T-shirt to shopping cart
        addItemsToShoppingCard();

        driver.findElement(By.id("checkout")).click();

        // Fill Contact Details
        fillShippingDetails("Dimitar", "Petkov", "888");
        driver.findElement(By.id("continue")).click();

        // Complete Order
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        var checkout = driver.findElement(By.className("title")).getText();
        var complete = driver.findElement(By.className("complete-header")).getText();
        var items = driver.findElements(By.className("inventory_item_name"));

        // Assert Items removed from Shopping Cart
        Assertions.assertEquals(CHECKOUT_COMPLETE, checkout, ITEM_ERROR_MESSAGE);
        Assertions.assertEquals(COMPLETE_ORDER, complete, ITEM_ERROR_MESSAGE);
        Assertions.assertEquals(0, items.size(), ITEM_ERROR_MESSAGE);
    }
}

