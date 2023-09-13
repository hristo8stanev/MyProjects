package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssuePages extends BaseJiraPage {
    public IssuePages(WebDriver driver) {
        super(driver, "jira.homePage");
    }

    public void assertStoryCreated() {
        String expectedResult = "jira.summaryStory";
        actions.waitForElementPresent("jira.createdStory",expectedResult);
    }
    public void assertBugCreated() {
        String expectedResult = "jira.summaryBug";
        actions.waitForElementPresent("jira.createdBug", expectedResult);
    }

    public void assertBugLinkedToStory(){
        String expectedResult = "jira.summaryBug";
        actions.waitForElementPresent("jira.linked.bug.to.story", expectedResult);
    }

}
