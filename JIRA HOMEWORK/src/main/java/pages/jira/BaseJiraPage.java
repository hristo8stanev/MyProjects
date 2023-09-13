package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BaseJiraPage extends BasePage {
    public BaseJiraPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void clickonProject(){
        actions.waitForElementClickable("//span[@class='css-178ag6o' and text()='Projects']");
        actions.clickElement("//span[@class='css-178ag6o' and text()='Projects']");

        actions.waitForElementClickable("//span[@class='css-g7kw2k']");
        actions.clickElement("//span[@class='css-g7kw2k']");
    }
}
