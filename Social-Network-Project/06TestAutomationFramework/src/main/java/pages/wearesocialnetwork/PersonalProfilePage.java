package pages.wearesocialnetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static com.telerikacademy.testframework.Constants.*;

public class PersonalProfilePage extends BaseSocialPage {
    String usernameReceiver = "first";
    String passwordReceiver = "last";
    String usernameSender = "";
    String passwordSender = "";
    Logger logger = Logger.getLogger("");
    String personalInfo;

    public PersonalProfilePage(WebDriver driver) {
        super(driver, "social.network.homepage");
    }

    public void setFirstLastNamesAndBirthdate(String firstName, String lastName) {
        actions.waitForElementVisible("profile.first.name");
        actions.typeValueInField(firstName, "profile.first.name");
        actions.waitForElementVisible("profile.last.name");
        actions.typeValueInField(lastName, "profile.last.name");
        actions.waitForElementVisible("profile.birthday");
        actions.typeValueInField("03/05/1987", "profile.birthday");

    }

    public void updateUserProfileWithEmailAddress(String email) {
        actions.deleteEmailFied();
        actions.waitForElementVisible("profile.email.address");
        actions.typeValueInField(email, "profile.email.address");
    }

    public void updateUserProfileWithGender() {
        actions.waitForElementVisible("profile.inputGender");
        actions.clickElement("profile.inputGender");
    }

    public void enterPersonalProfile() {
        actions.waitForElementVisible("profile.personal.page.button");
        actions.clickElement("profile.personal.page.button");
        actions.waitForElementClickable("profile.editProfile.page.button");
        actions.clickElement("profile.editProfile.page.button");

    }

    public void clickOnUpdateProfileButton() {
        actions.waitForElementClickable("profile.update.personal.profile.button");
        actions.clickElement("profile.update.personal.profile.button");
    }

    public void clickOnNewFriendRequests() {
        actions.waitForElementClickable("personal.profile.new.request.button");
        actions.clickElement("personal.profile.new.request.button");
    }

    public void clickOnConnect() {
        actions.waitForElementClickable("send.connection.button");
        actions.clickElement("send.connection.button");

    }

    public void backToProfileInfo() {
        actions.waitForElementClickable("profile.back.to.profile");
        actions.clickElement("profile.back.to.profile");
    }

    public void approveReceivedConnectionRequest() {
        actions.waitForElementVisible("see.new.connection.requests.button");
        actions.clickElement("see.new.connection.requests.button");
        actions.waitForElementVisible("approve.connection.request.button");
        actions.clickElement("approve.connection.request.button");
    }

    public void validateReceivedConnectionRequestApproved() {
        actions.assertElementVisible("no.pending.request.message");
        logger.info(String.format("Username with %s, and password with %s, sent connection request.",
                usernameSender, passwordSender));
        logger.info(String.format("User with username %s, and password %s, approved connection request of user %s",
                usernameReceiver, passwordReceiver, usernameSender));

    }

    public void assertEmailUpdated(String email) {
        actions.assertEmailFieldOnProfilePage(email);
    }

    public void assertFirstLastNamesUpdated(String firstName, String lastName) {
        actions.assertFirstLastNamesFieldOnProfilePage(firstName, lastName);
    }

    public void updateJobSection() {
        actions.waitForElementClickable("profile.job.tittle");
        actions.clickElement("profile.job.tittle");
        actions.waitForElementClickable("profile.update.job.tittle.button");
        actions.clickElement("profile.update.job.tittle.button");

    }

    public void updateSkillsSection() {
        actions.waitForElementClickable("profile.skills.tittle");
        actions.typeValueInField("Quality Assurance", "profile.skills.tittle");
        actions.waitForElementClickable("profile.skills.tittle");
        actions.typeValueInField("16", "profile.update.availability");
        actions.waitForElementClickable("profile.update.skills.button");
        actions.clickElement("profile.update.skills.button");
    }

    public void updateUserPublicInfo(String info) {
        actions.waitForElementClickable("profile.input.info");
        actions.typeValueInField(info, "profile.input.info");
    }

    public void updateCity() {
        actions.waitForElementClickable("profile.input.city");
        actions.clickElement("profile.input.city");
    }

    public void assertAvailabilityUpdated(String skill) {
        actions.clickElement("profile.personal.info");
        String xpath = String.format("//span[text()='%s']", skill);
        actions.waitForElementVisible(xpath);
        String spanText = driver.findElement(By.xpath(xpath)).getText();
        Assertions.assertEquals(skill, spanText, "Expected availability does not match the actual availability.");
    }

    public void assertAvailability() {
        actions.assertElementPresent("personal.info.availability");
    }


    public void assertJobTitleUpdated(String jobTitle) {
        actions.clickElement("profile.personal.info");
        String xpath = String.format("//span[text()='%s']", jobTitle);
        actions.waitForElementVisible(xpath);
        String spanText = driver.findElement(By.xpath(xpath)).getText();
        Assertions.assertEquals(jobTitle, spanText, "Expected job title does not match the actual job title.");
    }

    public String generateRandomEmail() {
        String username = RandomStringUtils.randomAlphabetic(10);
        return username + "@gmail.com";
    }

    public String generateInfo() {
        personalInfo += RandomStringUtils.randomAlphabetic(25);
        return personalInfo;
    }
}

