package ui.socialnetwork.tests;

import api.socialnetwork.tests.UserControllerTest;
import apisocialnetwork.Utils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import static apisocialnetwork.Constants.RANDOM_EMAIL;
import static apisocialnetwork.Constants.SEARCHABLE_NAME;
import static com.telerikacademy.testframework.Constants.homePageHeader;

public class HomePageTests extends BaseTestSetup {
    UserControllerTest userControllerTest = new UserControllerTest();

    @Test
    @Tag("FHKT-162")
    public void anonymousUserHomePageAccessAndLinksVisibilityTest() {
        homePage.validateAnonymousUserHomePageAccessAndLinksVisibility();
    }

    @Test
    @Tag("FHKT-146")
    public void anonymousUserRegisterFormDisplayWhenRegisterButtonClickedTest() {
        homePage.clickOnRegisterButton();
        //ASSERT
        homePage.validateRegisterFormFullyDisplayed();
    }

    @Test
    @Tag("FHKT-156")
    public void anonymousUserLoginFormDisplayWhenSignInButtonClickedTest() {
        homePage.clickOnSignInButton();
        //ASSERT
        homePage.validateLoginFormDisplayed();
    }

    @Test
    @Tag("FHKT-279")
    public void anonymousUserWeAreButtonNavigatesHomePageTest() {
        homePage.clickOnAboutUsButton();
        homePage.clickOnWeAreButton();
        homePage.validateHomePageHeader(homePageHeader);
    }

    @Test
    @Tag("FHKT-189")
    public void anonymousUserLatestPostsDisplayWhenLatestPostButtonClickedTest() {
        homePage.clickOnLatestPostsButton();
        //ASSERT
        homePage.validateLatestPostsDisplayed();
    }

    @Test
    @Tag("FHKT-190")
    public void anonymousUserAboutUsInformationDisplayedWhenAboutUsButtonClickedTest() {
        homePage.clickOnAboutUsButton();
        //ASSERT
        homePage.validateAboutUsInformationDisplayed();
    }

    @Test
    @Tag("FHKT-193")
    public void anonymousUserSearchAllUsersWhenSearchButtonClickedTest() {
        homePage.clickOnUserSearchBar();
        homePage.validateSearchBarShowsUsers();

    }

    @Test
    @Tag("FHKT-284")
    public void anonymousUserSearchUserByKnownUsernameTest() {
        SEARCHABLE_NAME = registerPage.generateUser();
        RANDOM_EMAIL = Utils.generateRandomEmail();
        userControllerTest.updateUserProfileTest();
        homePage.searchUserByKnownUsername(SEARCHABLE_NAME + " randomLastName");
        homePage.validateSearchUserByKnownUsername(SEARCHABLE_NAME + " randomLastName");
    }

}

