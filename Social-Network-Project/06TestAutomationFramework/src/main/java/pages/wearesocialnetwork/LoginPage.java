package pages.wearesocialnetwork;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseSocialPage {

    public LoginPage(WebDriver driver) {
        super(driver, "social.network.login.page");
    }

    public void loginUser(String username, String password) {

        navigateToPage();

        actions.waitForElementVisible("login.page.username");
        actions.typeValueInField(username, "login.page.username");

        actions.waitForElementVisible("login.page.password");
        actions.typeValueInField(password, "login.page.password");

        actions.clickElement("login.submit.button");

        actions.waitForElementVisible("header.member.menu.button");
    }

    public void assertAdminAuthenticatedUser() {
        actions.assertElementPresent("header.admin.member.button");
    }

    public void assertAuthenticatedUser() {
        actions.assertElementPresent("header.member.menu.button");
    }
}
