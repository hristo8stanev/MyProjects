package bingsearchtests;

import basesetup.BaseSetupClass;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


import static org.example.Constants.*;


public class BingSearchByTerms extends BaseSetupClass {

    @Test
    public void resultFound_when_searchValidTermsProvided() {

        //Navigate to bing.com
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(BING_URL);

        // Agree to consent
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//button[@id='bnp_btn_accept']")));
        WebElement agreeToCookies = driver.findElement
                (By.xpath("//button[@id='bnp_btn_accept']"));
        agreeToCookies.click();


        //Type text in search field
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//input[@type='search' and @id='sb_form_q']")));
        WebElement searchField = driver.findElement(By.xpath("//input[@type='search' and @id='sb_form_q']"));
        searchField.sendKeys(TELERIK_ACADEMY);

        //Click search button
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//label[@role='button']")));
        WebElement searchButton = driver.findElement(By.xpath("//label[@role='button']"));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();

        //Assert result found
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//h2)[1]"))));
        WebElement firstResult = driver.findElement(By.xpath("(//h2)[1]"));
        wait.until(ExpectedConditions.visibilityOf(firstResult));

        Assertions.assertTrue((firstResult.getText().contains(EXPECTED_RESULT) ||
                firstResult.getText().contains(EXPECTED_RESULT_BING)), ERROR_MESSAGE);

    }
}

