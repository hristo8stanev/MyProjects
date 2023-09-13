package test.cases.jira;
import org.junit.Before;
import org.junit.Test;
import pages.jira.CreateIssuePage;
import pages.jira.IssuePages;
import pages.jira.JiraHomePage;


public class JiraTest extends BaseTest {

    @Before
    public void testSetup() {
        login();

    }

    @Test
    public void userAuthenticated_when_validCredentialsProvided() {

        // add Assert
        JiraHomePage results = new JiraHomePage(actions.getDriver());
        results.assertResultIsPresentJira();

    }

    @Test
    public void createAstory_when_validDataProvided() {

        CreateIssuePage createIssue = new CreateIssuePage(actions.getDriver());
        createIssue.enterProject();
        createIssue.createStory();


        //add Assert
        IssuePages issuePages = new IssuePages(actions.getDriver());
        issuePages.assertStoryCreated();
    }


    @Test
    public void createBug_when_validDataProvided() {

        CreateIssuePage createIssue = new CreateIssuePage(actions.getDriver());
        createIssue.enterProject();
        createIssue.createBug();

        //add Assert
        IssuePages issuePages = new IssuePages(actions.getDriver());
        issuePages.assertBugCreated();
    }

    @Test
    public void linkedBugToStory_when_validDataProvided() {

        CreateIssuePage createIssue = new CreateIssuePage(actions.getDriver());
        createIssue.enterProject();
        createIssue.linkedBugToStory();

        //add Assert
        IssuePages issuePages = new IssuePages(actions.getDriver());
        issuePages.assertBugLinkedToStory();

    }
}
