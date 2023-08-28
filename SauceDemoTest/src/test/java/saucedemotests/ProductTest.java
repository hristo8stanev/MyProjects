package saucedemotests;

import core.BaseClass;

import core.ShippingDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static org.example.Constants.*;

public class ProductTest extends BaseClass {


    @Test
    public void productAddedToShoppingCart_when_addToCart() {
        // Add Backpack and T-shirt to shopping cart
        addItemsToShoppingCard();

        //  // Assert Items and Totals
        var items = driver.findElements(By.className("inventory_item_name"));
        var prices = driver.findElements(By.className("inventory_item_price"));


        Assertions.assertEquals(EXPECTED_ITEMS, items.size(), ITEM_ERROR_MESSAGE);

        Assertions.assertEquals(SAUCE_LABS_BACKPACK, items.get(0).getText(), ITEM_TITLE_ERROR_MESSAGE);
        Assertions.assertEquals(BACK_PRICE, prices.get(0).getText(), ITEM_PRICE_ERROR_MESSAGE);

        Assertions.assertEquals(SAUCE_LABS_BOLT_T_SHIRT, items.get(1).getText(), ITEM_TITLE_ERROR_MESSAGE);
        Assertions.assertEquals(SHIRT_PRICE, prices.get(1).getText(), ITEM_PRICE_ERROR_MESSAGE);

        System.out.println(PRODUCTS_SUCCESS_ADD);

    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation() {
        // Add Backpack and T-shirt to shopping cart
        addItemsToShoppingCard();

        // fill form
        fillShippingDetails(new ShippingDetails(FIRST_NAME, LAST_NAME, POST_CODE));


        var items = driver.findElements(By.className("inventory_item_name"));
        var total = driver.findElement(By.className("summary_total_label")).getText();
        var prices = driver.findElements(By.className("inventory_item_price"));

        String expectedResult = calculatePrice(prices);

        //  // Assert Items and Totals
        Assertions.assertEquals(EXPECTED_ITEMS, prices.size(), ITEM_ERROR_MESSAGE);

        Assertions.assertEquals(SAUCE_LABS_BOLT_T_SHIRT, items.get(1).getText(), ITEM_TITLE_ERROR_MESSAGE);
        Assertions.assertEquals(SAUCE_LABS_BACKPACK, items.get(0).getText(), ITEM_TITLE_ERROR_MESSAGE);

        Assertions.assertEquals(expectedResult, total, CALCULATE_PRICE_ERROR);

        System.out.println(ORDER_SUCCESS);
    }


    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {
        // Add Backpack and T-shirt to shopping cart
        addItemsToShoppingCard();

        // Fill Contact Details
        fillShippingDetails(new ShippingDetails(FIRST_NAME, LAST_NAME, POST_CODE));

        // Complete Order
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        var checkout = driver.findElement(By.className("title")).getText();
        var complete = driver.findElement(By.className("complete-header")).getText();
        var items = driver.findElements(By.className("inventory_item_name"));

        // Assert Items removed from Shopping Cart
        Assertions.assertEquals(CHECKOUT_COMPLETE, checkout, ITEM_ERROR_MESSAGE);
        Assertions.assertEquals(COMPLETE_ORDER, complete, ITEM_ERROR_MESSAGE);
        Assertions.assertEquals(0, items.size(), ITEM_ERROR_MESSAGE);

        System.out.println(SHOPPING_CART_SUCCESS);
    }
}

