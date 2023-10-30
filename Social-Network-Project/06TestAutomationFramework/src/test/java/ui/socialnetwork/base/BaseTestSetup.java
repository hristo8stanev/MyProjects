package ui.socialnetwork.base;


import com.telerikacademy.testframework.CustomWebDriverManager;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.wearesocialnetwork.*;

public class BaseTestSetup {
    public static UserActions actions = new UserActions();
    //PAGES
    public static HomePage homePage;
    public static PersonalProfilePage personalProfilePage;
    public static LogoutPage logoutPage;

    public static RegisterPage registerPage;
    public static LoginPage loginPage;
    public static PostPage postPage;
    public static CommentPage commentPage;

    @BeforeEach
    public void setUp() {
        UserActions.loadBrowser("social.network.homepage");
        WebDriver driver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        postPage = new PostPage(driver);
        logoutPage = new LogoutPage(driver);
        personalProfilePage = new PersonalProfilePage(driver);
        commentPage = new CommentPage(driver);
    }

    @AfterEach
    public void tearDown() {
        UserActions.quitDriver();
    }

    public static void registerUser(String username, String password) {
        registerPage.registerUser(username, password);
    }

    public static void loginUser(String username, String password) {
        loginPage.loginUser(username, password);
    }
}

