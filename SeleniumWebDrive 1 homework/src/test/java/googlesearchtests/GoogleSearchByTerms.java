package googlesearchtests;

import basesetup.BaseSetupClass;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Constants.*;


public class GoogleSearchByTerms extends BaseSetupClass {


    @Test
    public void resultFound_when_validSearchTermsProvided() {

        //Navigate to bing.com
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(GOOGLE_URL);

        // Agree to consent
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//button[@id='L2AGLb']")));
        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        agreeButton.click();

        //Type text
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//textarea[@type='search' and @name='q']")));
        WebElement searchBar = driver.findElement(By.xpath("//textarea[@type='search' and @name='q']"));
        searchBar.sendKeys(TELERIK_ACADEMY);

        //Click search button
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("(//input[@type='submit' and @name='btnK'])[2]")));
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("(//input[@type='submit' and @name='btnK'])[2]")));
        searchButton.submit();

        //Assert Result found
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("(//a/h3)[1]")));
        WebElement qaAlphaText = driver.findElement(By.xpath("(//a/h3)[1]"));
        Assertions.assertEquals(EXPECTED_RESULT, qaAlphaText.getText(), ERROR_MESSAGE);
    }
}
