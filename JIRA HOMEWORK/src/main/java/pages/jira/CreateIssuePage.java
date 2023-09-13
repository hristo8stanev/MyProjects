package pages.jira;


import org.junit.Before;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class CreateIssuePage extends BaseJiraPage {
    public static String timestamp;
    public static DateTimeFormatter dtf;

    public CreateIssuePage(WebDriver driver) {
        super(driver, "jira.homePage");
    }

    @Before
    public void initialSetup() {
        dtf = DateTimeFormatter.ISO_INSTANT;
        Instant time = Instant.now();
        timestamp = dtf.format(time);
    }

    public void enterProject() {

        actions.waitForElementPresent("jira.projects");
        actions.clickElement("jira.projects");

        actions.waitForElementPresent("jira.viewAllProjects");
        actions.clickElement("jira.viewAllProjects");

        actions.waitForElementPresent("jira.searchField");
        actions.typeValueInField("x-ray-project-demo", "jira.searchField"); // <---TYPE YOUR PROJECT NAME PLEASE

        actions.waitForElementPresent("jira.findProjectNameByText");// Please enter a variable and input your project name.
        actions.clickElement("jira.findProjectNameByText"); //Please enter a variable and input your project name.

    }

    public void createStory() {

        String summaryStory = getUIMappingByKey("jira.summaryStory");
        String descriptionStory = getUIMappingByKey("jira.desctionStory");

        actions.waitForElementClickable("jiracreate-button-wrapper");
        actions.clickElement("jiracreate-button-wrapper");

        actions.waitForElementClickable("jira.issue-type-selecty");
        actions.clickElement("jira.issue-type-selecty");

        actions.waitForElementClickable("jira.react-select");
        actions.clickElement("jira.react-select");

        actions.waitForElementClickable("jira.summaryStoryxpath");
        actions.typeValueInField(summaryStory, "jira.summaryStoryxpath");

        actions.waitForElementClickable("jira.descriptionStoryxpath");
        actions.typeValueInField(descriptionStory, "jira.descriptionStoryxpath");

        actions.waitForElementClickable("jira.linked.create-button");
        actions.clickElement("jira.linked.create-button");

        actions.waitForElementVisible("jira.navigation-apps-sidebar");
        actions.waitForElementClickable("jira.navigation-apps-sidebar");
        actions.clickElement("jira.navigation-apps-sidebar");

    }

    public void createBug() {
        String summaryBug = getUIMappingByKey("jira.summaryBug");
        String descriptionBug = getUIMappingByKey("jira.descriptionBug");

        actions.waitForElementClickable("jiracreate-button-wrapper");
        actions.clickElement("jiracreate-button-wrapper");

        actions.waitForElementClickable("jira.issue-type-selecty");
        actions.clickElement("jira.issue-type-selecty");

        actions.waitForElementClickable("jira.react-selectBug");
        actions.clickElement("jira.react-selectBug");

        actions.waitForElementClickable("jira.summaryStoryxpath");
        actions.typeValueInField(summaryBug, "jira.summaryStoryxpath");

        actions.waitForElementClickable("jira.descriptionStoryxpath");
        actions.typeValueInField(descriptionBug, "jira.descriptionStoryxpath");

        actions.waitForElementClickable("jira.linked.create-button");
        actions.clickElement("jira.linked.create-button");

        actions.waitForElementVisible("jira.navigation-apps-sidebar");
        actions.waitForElementClickable("jira.navigation-apps-sidebar");
        actions.clickElement("jira.navigation-apps-sidebar");

    }
    public void linkedBugToStory() {

        String summaryStory = getUIMappingByKey("jira.summaryStory");
        String descriptionStory = getUIMappingByKey("jira.desctionStory");

        String summaryBug = getUIMappingByKey("jira.summaryBug");
        String descriptionBug = getUIMappingByKey("jira.descriptionBug");

        actions.waitForElementClickable("jiracreate-button-wrapper");
        actions.clickElement("jiracreate-button-wrapper");

        actions.waitForElementClickable("jira.issue-type-selecty");
        actions.clickElement("jira.issue-type-selecty");

        actions.waitForElementClickable("jira.react-select");
        actions.clickElement("jira.react-select");

        actions.waitForElementClickable("jira.summaryStoryxpath");
        actions.typeValueInField(summaryStory, "jira.summaryStoryxpath");

        actions.waitForElementClickable("jira.descriptionStoryxpath");
        actions.typeValueInField(descriptionStory, "jira.descriptionStoryxpath");

        actions.waitForElementClickable("jira.linked.create-button");
        actions.clickElement("jira.linked.create-button");

        actions.waitForElementVisible("//a[@data-testid='navigation-apps-sidebar-software-classic.ui.menu.issues-link--item' and .//span[@data-item-title='true' and text()='Issues']]");
        actions.waitForElementClickable("//a[@data-testid='navigation-apps-sidebar-software-classic.ui.menu.issues-link--item' and .//span[@data-item-title='true' and text()='Issues']]");
        actions.clickElement("//a[@data-testid='navigation-apps-sidebar-software-classic.ui.menu.issues-link--item' and .//span[@data-item-title='true' and text()='Issues']]");

        actions.waitForElementClickable("//a[contains(@class, '_1nmz1hna') and @target='_self']");
        actions.clickElement("//a[contains(@class, '_1nmz1hna') and @target='_self']");

        actions.waitForElementClickable("//button[@data-testid='issue.issue-view.views.issue-base.foundation.quick-add.quick-add-item.link-issue']");
        actions.clickElement("//button[@data-testid='issue.issue-view.views.issue-base.foundation.quick-add.quick-add-item.link-issue']");

        actions.waitForElementClickable("//button[@data-testid='issue.views.issue-base.content.issue-links.add.create-linked-issue-button.create-linked-issue-button']");
        actions.clickElement("//button[@data-testid='issue.views.issue-base.content.issue-links.add.create-linked-issue-button.create-linked-issue-button']");

        //BUG
        actions.waitForElementClickable("//*[@id='issue-create.ui.modal.create-form.type-picker.issue-type-select']");
        actions.clickElement("//*[@id='issue-create.ui.modal.create-form.type-picker.issue-type-select']");

        actions.waitForElementClickable("//div[contains(text(), 'Bug')]");
        actions.clickElement("//div[contains(text(), 'Bug')]");

        actions.waitForElementClickable("jira.summaryStoryxpath");
        actions.typeValueInField(summaryBug, "jira.summaryStoryxpath");

        actions.waitForElementClickable("jira.descriptionStoryxpath");
        actions.typeValueInField(descriptionBug, "jira.descriptionStoryxpath");

        actions.waitForElementClickable("jira.linked.create-button");
        actions.clickElement("jira.linked.create-button");

    }
}


