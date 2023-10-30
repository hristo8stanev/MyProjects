package pages.wearesocialnetwork;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;


public class HomePage extends BaseSocialPage {
    Logger logger = Logger.getLogger("");
    String usernameReceiver;
    String passwordReceiver;
    String usernameSender;
    String passwordSender;

    public HomePage(WebDriver driver) {
        super(driver, "social.network.homepage");
    }


    public void validateAnonymousUserHomePageAccessAndLinksVisibility() {
        actions.assertElementPresent("homepage.header.name");
        actions.assertElementPresent("nav.bar.brand");
        actions.assertElementPresent("register.button");
        actions.assertElementPresent("home.page.sign.in.button");
        actions.assertElementPresent("home.page.home.button");
        actions.assertElementPresent("home.page.latest.post.button");
        actions.assertElementPresent("home.page.about.us");
        actions.assertElementPresent("user.search.button");

        logger.info("HomePage successfully accessed without authentication with visibility of header and page links.");

    }


    public void clickOnRegisterButton() {
        actions.waitForElementVisible("register.button");
        actions.clickElement("register.button");

        actions.waitForElementVisible("register.page.submitButton");
    }

    public void clickOnSignInButton() {
        actions.waitForElementVisible("home.page.sign.in.button");
        actions.clickElement("home.page.sign.in.button");

        actions.waitForElementVisible("login.submit.button");

    }

    public void clickOnHomeButton() {
        actions.waitForElementVisible("home.page.home.button");
        actions.clickElement("home.page.home.button");

    }

    public void clickOnLatestPostsButton() {
        actions.waitForElementVisible("home.page.latest.post.button");
        actions.clickElement("home.page.latest.post.button");

        actions.waitForElementVisible("latest.posts.message");

    }

    public void clickOnWeAreButton() {
        actions.waitForElementClickable("nav.bar.brand");
        actions.clickElement("nav.bar.brand");
    }

    public void clickOnAboutUsButton() {
        actions.waitForElementVisible("home.page.about.us");
        actions.clickElement("home.page.about.us");

        actions.waitForElementVisible("about.us.information.message");

    }

    public void clickOnUserSearchBar() {
        actions.waitForElementVisible("user.search.button");
        actions.clickElement("user.search.button");
        actions.waitForElementVisible("user.member.since.field");

    }

    public void clickOnUserAfterSearch() {
        actions.waitForElementVisible("see.profile.button");
        actions.clickElement("see.profile.button");

    }

    public void sendConnectionToSearchedUser() {
        actions.waitForElementVisible("send.connection.button");
        actions.clickElement("send.connection.button");

    }

    public void clickOnPersonalProfile() {
        actions.waitForElementClickable("profile.personal.page.button");
        actions.clickElement("profile.personal.page.button");

    }

    public void disconnectFromAlreadyConnectedUser() {
        actions.waitForElementClickable("user.disconnect.button");
        actions.clickElement("user.disconnect.button");
    }

    public void validatePersonalProfileButton() {
        actions.assertElementVisible("profile.editProfile.page.button");

    }

    public void searchUserByKnownUsername(String name) {
        actions.waitForElementVisible("home.username.search.input");
        actions.typeValueInField(name, "home.username.search.input");
        actions.clickElement("user.search.button");
    }

    public void verifySuccessfulConnectionRequestMessage() {
        actions.waitForElementVisible("connection.request.sent.success");
        actions.assertElementPresent("connection.request.sent.success");

        logger.info(String.format("Username with %s, and password with %s, sent connection request.",
                usernameSender, passwordSender));
        logger.info(String.format("Username with %s, and password with %s, received connection request.",
                usernameReceiver, passwordReceiver));
    }

    public void validateSearchedUsernameInSearchResults(String firstName, String lastName) {
        actions.assertElementVisible("homepage.search.result.username", firstName, lastName);
    }

    public void validateSearchUserByKnownUsername(String name) {
        actions.assertElementVisible("user.search.result.username.field", name);
        logger.info("User with username 'Public Profile' is visible.");
    }

    public void validateDisconnectionFromAlreadyConnectedUser() {

        actions.waitForElementClickable("send.connection.button");
        logger.info(String.format("User with username %s, and password %s, approved connection request of user %s",
                usernameReceiver, passwordReceiver, usernameSender));
        logger.info(String.format("User with username %s, and password with %s, disconnected from the user with username %s.",
                usernameSender, passwordSender, usernameReceiver));

    }

    public void validateSearchBarShowsUsers() {
        actions.assertElementPresent("user.member.since.field");
    }

    public void validateAboutUsInformationDisplayed() {
        actions.assertElementPresent("about.us.information.message");

    }

    public void validateHomePageHeader(String key) {
        actions.assertElementVisible("weare.homepage.h1", key);
        logger.info("Scroll up action is successful 'The Easiest Way to Hack the Crisis' header is visible.");
    }

    public void validateLatestPostsDisplayed() {
        actions.assertElementPresent("latest.posts.message");
    }

    public void validateRegisterFormFullyDisplayed() {
        actions.assertElementPresent("join.our.community");
        actions.assertElementPresent("register.page.usernameField");
        actions.assertElementPresent("register.page.emailField");
        actions.assertElementPresent("register.page.passwordField");
        actions.assertElementPresent("register.page.confirmPasswordField");
        actions.assertElementPresent("register.page.submitButton");
    }

    public void validateLoginFormDisplayed() {
        actions.assertElementPresent("login.page.message");
        actions.assertElementPresent("login.page.username");
    }

    public void clickOnGoToAdminZoneButton() {
        actions.waitForElementVisible("admin.zone.button");
        actions.clickElement("admin.zone.button");
    }

    public void clickOnViewAllUsersButton() {
        actions.waitForElementVisible("admin.zone.button");
        actions.clickElement("admin.zone.button");

        actions.waitForElementVisible("admin.view.all.users");
        actions.clickElement("admin.view.all.users");

    }

    public void verifyAdminViewAllUsers() {
        actions.assertElementPresent("admin.view.off.all.user");
    }

    public void verifyAdminZoneLinkVisibility() {
        actions.assertElementPresent("admin.zone.button");
    }

    public void verifyAdminZonePageAccess() {
        actions.assertElementPresent("admin.zone.page.header");
    }
}
