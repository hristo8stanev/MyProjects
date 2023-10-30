package pages.wearesocialnetwork;

import com.telerikacademy.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseSocialPage extends BasePage {
    public BaseSocialPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

}
