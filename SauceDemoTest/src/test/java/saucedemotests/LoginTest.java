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
import static org.example.Constants.*;


public class LoginTest extends BaseClass {



    @Test
    public void userAuthenticated_when_validCredentialsProvided() {

        // add Assert
        WebElement logoPage = driver.findElement(By.xpath("//div[@class='app_logo']"));
        wait.until(ExpectedConditions.visibilityOf(logoPage));
        var successfulAuthentication = driver.findElement(By.className("title")).getText();
        
        Assertions.assertEquals(EXPECTED_TITLE,successfulAuthentication, USER_UNSUCCESSFUL_AUTHENTICATION);
        Assertions.assertTrue(logoPage.getText().equals(SWAG_LABS), ERROR_MESSAGE);
    }

}

