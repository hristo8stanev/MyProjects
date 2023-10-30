package pages.wearesocialnetwork;


import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class LogoutPage extends BaseSocialPage {
    Logger logger = Logger.getLogger("");

    public LogoutPage(WebDriver driver) {
        super(driver, "social.network.homepage");


    }

    public void logoutSuccessfully() {
        actions.waitForElementClickable("logout.button");
        actions.clickElement("logout.button");

    }

    public void assertSuccessfulLogout() {
        actions.assertElementPresent("home.page.sign.in.button");
    }

    public void validateLogoutPage(String key) {
        actions.assertElementVisible("logout.page.text", key);
        logger.info("You have been logged out.");
    }
}
