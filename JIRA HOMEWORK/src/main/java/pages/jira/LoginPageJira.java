package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPageJira extends BaseJiraPage {


    public LoginPageJira(WebDriver driver) {
        super(driver, "jira.loginPage");
    }

    public void loginUserJira(String userKey) {

        String email = getConfigPropertyByKey("jira.users." + userKey + ".email");
        String password = getConfigPropertyByKey("jira.users." + userKey + ".password");

        navigateToPage();
        assertPageNavigated();

        actions.waitForElementVisible("jira.loginPage.email");
        actions.typeValueInField(email, "jira.loginPage.email");

        actions.waitForElementVisible("jira.loginPage.loginSubmitButton");
        actions.clickElement("jira.loginPage.loginSubmitButton");

        actions.waitForElementVisible("jira.loginPage.password");
        actions.typeValueInField(password, "jira.loginPage.password");

        actions.waitForElementVisible("jira.loginPage.loginSubmitButton");
        actions.clickElement("jira.loginPage.loginSubmitButton");

        actions.waitForElementVisible("jira.loginPage.projectButton");//<-- PLEASE ENTER YOUR ID
        //for example this is mine 'start-product__JIRA_SOFTWARE_9645b08e-f41f-4496-bb1d-7ec7fcaa19e4'

        actions.clickElement("jira.loginPage.projectButton");

    }
}
