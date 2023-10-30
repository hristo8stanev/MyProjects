package ui.socialnetwork.tests;

import apisocialnetwork.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import static com.telerikacademy.testframework.Constants.*;

public class PersonalProfileTests extends BaseTestSetup {
    String username = "";
    String password = "";
    String firstName = "first";
    String lastName = "last";
    String email = "";

    @BeforeEach
    public void setupUser() {
        username += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(username, password);
        loginUser(username, password);
    }

    @Test
    @Tag("FHKT-280")
    public void updateUserProfileWithMustHaveFieldsTest() {
        personalProfilePage.enterPersonalProfile();
        firstName += Utils.generateFirstName();
        lastName += Utils.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();

        //assert
        personalProfilePage.assertFirstLastNamesUpdated(firstName, lastName);

    }

    @Test
    @Tag("FHKT-96")
    public void updateUserProfileWithFirstLastNameBirthdayGenderEmailPublicInfoCityTest() {
        personalProfilePage.enterPersonalProfile();
        firstName += Utils.generateFirstName();
        lastName += Utils.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        email = personalProfilePage.generateRandomEmail();
        personalProfilePage.updateUserProfileWithEmailAddress(email);
        personalProfilePage.updateUserProfileWithGender();
        personalInfo += personalProfilePage.generateInfo();
        personalProfilePage.updateUserPublicInfo(personalInfo);
        personalProfilePage.updateCity();
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();

        //assert
        personalProfilePage.assertFirstLastNamesUpdated(firstName, lastName);
        personalProfilePage.assertEmailUpdated(email);
    }

    @Test
    @Tag("FHKT-250")
    public void updatePersonalJobTittleInformationTest() {
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateJobSection();
        //assert
        personalProfilePage.assertJobTitleUpdated(jobTitle);
    }

    @Test
    @Tag("FHKT-251")
    public void updatePersonalSkillsInformationTest() {
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateSkillsSection();

        //assert
        personalProfilePage.assertAvailability();
        personalProfilePage.assertAvailabilityUpdated(skillInfo);

    }


}
