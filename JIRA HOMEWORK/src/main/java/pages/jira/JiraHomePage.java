package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class JiraHomePage extends BasePage {

    public JiraHomePage(WebDriver driver) {
        super(driver, "jira.homePage");
    }

    public void assertResultIsPresentJira() {
        String jiraResult = getUIMappingByKey("jira.authenticate.result");
        actions.waitForElementPresent("jira.authenticate.path", jiraResult);
    }
}
