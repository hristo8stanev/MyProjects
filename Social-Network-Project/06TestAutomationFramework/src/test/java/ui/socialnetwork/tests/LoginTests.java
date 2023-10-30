package ui.socialnetwork.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import static com.telerikacademy.testframework.Constants.*;

public class LoginTests extends BaseTestSetup {
    String username = "";
    String password = "";
    String adminUsername = "admin";

    @Test
    @Tag("FHKT-15")
    public void nonAdminUserAuthentication() {
        username += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(username, password);
        loginUser(username, password);
        //ASSERT
        loginPage.assertAuthenticatedUser();
    }

    @Test
    @Tag("FHKT-17")
    public void adminUserAuthenticationTest() {
        adminUsername += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(adminUsername, password);
        loginUser(adminUsername, password);
        //ASSERT
        loginPage.assertAdminAuthenticatedUser();
    }
}
