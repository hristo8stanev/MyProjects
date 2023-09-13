package test.cases.jira;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pages.jira.LoginPageJira;
import pages.trello.LoginPage;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    static UserActions actions = new UserActions();


    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("jira.homePage");
    }

    @AfterClass
    public static void tearDown() {
        UserActions.quitDriver();
    }

    public static void login() {
        LoginPageJira loginPageJira = new LoginPageJira(actions.getDriver());
        loginPageJira.loginUserJira("user");
    }
}
