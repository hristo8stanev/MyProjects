package ui.socialnetwork.tests;

import apisocialnetwork.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class ConnectionTests extends BaseTestSetup {
    String usernameReceiver = "";
    String passwordReceiver = "";
    String usernameSender = "";
    String passwordSender = "";

    @BeforeEach
    public void setupLogin() {
        usernameReceiver = registerPage.generateUser();
        passwordReceiver = registerPage.generatePassword();
        registerUser(usernameReceiver, passwordReceiver);
        loginUser(usernameReceiver, passwordReceiver);
    }

    @Test
    @Tag("FHKT-105")
    public void sendingConnectionRequestToAnotherUserTest() {

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.setFirstLastNamesAndBirthdate(usernameReceiver, usernameReceiver);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();
        logoutPage.logoutSuccessfully();

        usernameSender = registerPage.generateUser();
        passwordSender = registerPage.generatePassword();
        registerUser(usernameSender, passwordSender);
        loginUser(usernameSender, passwordSender);
        homePage.searchUserByKnownUsername(usernameReceiver);
        homePage.clickOnUserAfterSearch();
        homePage.sendConnectionToSearchedUser();

        homePage.validateSearchedUsernameInSearchResults(usernameReceiver, usernameReceiver);
        homePage.verifySuccessfulConnectionRequestMessage();

    }

    @Test
    @Tag("FHKT-106")
    public void approveAlreadySentConnectionRequestTest() {

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.setFirstLastNamesAndBirthdate(usernameReceiver, usernameReceiver);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();
        logoutPage.logoutSuccessfully();

        usernameSender = Utils.generateUniqueUsername();
        passwordSender = Utils.generateUniquePassword();
        registerUser(usernameSender, passwordSender);
        loginUser(usernameSender, passwordSender);
        homePage.searchUserByKnownUsername(usernameReceiver);
        homePage.clickOnUserAfterSearch();
        homePage.sendConnectionToSearchedUser();
        logoutPage.logoutSuccessfully();
        loginPage.loginUser(usernameReceiver, passwordReceiver);
        homePage.clickOnPersonalProfile();

        personalProfilePage.approveReceivedConnectionRequest();
        personalProfilePage.validateReceivedConnectionRequestApproved();

    }

    @Test
    @Tag("FHKT-107")
    public void disconnectFromAlreadyConnectedUserTest() {
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.setFirstLastNamesAndBirthdate(usernameReceiver, usernameReceiver);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();
        logoutPage.logoutSuccessfully();

        usernameSender = Utils.generateUniqueUsername();
        passwordSender = Utils.generateUniquePassword();
        registerUser(usernameSender, passwordSender);
        loginUser(usernameSender, passwordSender);
        homePage.searchUserByKnownUsername(usernameReceiver);
        homePage.clickOnUserAfterSearch();
        homePage.sendConnectionToSearchedUser();

        logoutPage.logoutSuccessfully();
        loginPage.loginUser(usernameReceiver, passwordReceiver);
        homePage.clickOnPersonalProfile();
        personalProfilePage.approveReceivedConnectionRequest();

        logoutPage.logoutSuccessfully();
        loginPage.loginUser(usernameSender, passwordSender);
        homePage.searchUserByKnownUsername(usernameReceiver);
        homePage.clickOnUserAfterSearch();
        homePage.disconnectFromAlreadyConnectedUser();

        homePage.validateDisconnectionFromAlreadyConnectedUser();
    }
}
