package ui.socialnetwork.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import static com.telerikacademy.testframework.Constants.logoutPageHeader;

public class LogoutTests extends BaseTestSetup {
    String username = "";
    String password = "";
    String adminUsername = "admin";

    @Test
    @Tag("FHKT-104")
    public void nonAdminUserLoggedOutTest() {
        username += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(username, password);
        loginUser(username, password);
        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccessfulLogout();
        logoutPage.validateLogoutPage(logoutPageHeader);
    }

    @Test
    @Tag("FHKT-266")
    public void adminUserLoggedOutTest() {
        adminUsername += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(adminUsername, password);
        loginUser(adminUsername, password);
        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccessfulLogout();
        logoutPage.validateLogoutPage(logoutPageHeader);
    }
}
