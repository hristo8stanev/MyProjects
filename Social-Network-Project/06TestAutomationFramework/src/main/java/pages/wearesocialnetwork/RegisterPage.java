package pages.wearesocialnetwork;

import com.telerikacademy.pages.BasePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static java.lang.String.format;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver, "social.network.register.page");
    }

    public void registerUser(String generateUser, String generatePassword) {
        navigateToPage();

        actions.waitForElementClickable("register.page.usernameField");
        actions.typeValueInField(generateUser, "register.page.usernameField");

        actions.waitForElementClickable("register.page.emailField");
        actions.typeValueInField("test@abv.bg", "register.page.emailField");

        actions.waitForElementClickable("register.page.passwordField");
        actions.typeValueInField(generatePassword, "register.page.passwordField");

        actions.waitForElementClickable("register.page.confirmPasswordField");
        actions.typeValueInField(generatePassword, "register.page.confirmPasswordField");

        actions.waitForElementClickable("register.page.submitButton");
        actions.clickElement("register.page.submitButton");

    }

    public String generateUser() {
        String username = RandomStringUtils.randomAlphabetic(4);
        return "User" + username;
    }

    public String generatePassword() {
        String randomPass = RandomStringUtils.randomAlphabetic(5);
        return "password" + randomPass;
    }

    public void assertSuccessfulRegistration() {
        actions.assertElementPresent("register.page.successful.register");
        actions.clickElement("register.page.successful.register");

    }
}
