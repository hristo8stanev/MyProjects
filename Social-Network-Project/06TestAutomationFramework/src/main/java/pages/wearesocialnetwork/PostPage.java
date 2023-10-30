package pages.wearesocialnetwork;

import com.telerikacademy.testframework.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;

import static com.telerikacademy.testframework.Utils.formatDateTime;
import static com.telerikacademy.testframework.Utils.getCurrentDateTime;

public class PostPage extends BaseSocialPage {
    public String postDescription = "My Post: ";

    public String editPostText = "Edit post to: ";

    public PostPage(WebDriver driver) {
        super(driver, "social.network.homepage");
    }

    public void createPublicPost(String postBody) {
        postDescription = generateDescription();
        actions.waitForElementClickable("new.post.button");
        actions.clickElement("new.post.button");

        actions.waitForElementClickable("post.description");
        actions.typeValueInField(postBody, "post.description");

        actions.waitForElementClickable("choose.public.post");
        actions.clickElement("choose.public.post");

        actions.waitForElementClickable("create.post.button");
        actions.clickElement("create.post.button");


    }

    public void createPrivatePost(String postBody) {
        postDescription = generateDescription();
        actions.waitForElementClickable("new.post.button");
        actions.clickElement("new.post.button");

        actions.waitForElementClickable("post.description");
        actions.typeValueInField(postBody, "post.description");

        actions.waitForElementClickable("choose.private.post");
        actions.clickElement("choose.private.post");

        actions.waitForElementClickable("create.post.button");
        actions.clickElement("create.post.button");
    }

    public void likePublicPost() {

        if (actions.isElementVisible("post.dislike.button")) {
            actions.clickElement("post.dislike.button");
        }

        actions.waitForElementVisible("post.like.button");
        actions.clickElement("post.like.button");

        actions.waitForElementVisible("post.dislike.button");
        actions.waitForElementVisible("//span[@class='position']");
    }

    public void dislikePublicPost() {

        if (actions.isElementVisible("post.like.button")) {
            actions.clickElement("post.like.button");
        }

        actions.waitForElementVisible("post.dislike.button");
        actions.clickElement("post.dislike.button");
    }

    public void userEditPost(String postBody) {
        editPostText += generateDescription();

        actions.waitForElementVisible("edit.post.button");
        actions.clickElement("edit.post.button");

        actions.waitForElementVisible("choose.public.post");
        actions.clickElement("choose.public.post");

        actions.waitForElementClickable("edit.comment.field");
        actions.typeValueInField(postBody, "edit.comment.field");

        actions.waitForElementVisible("edit.post.submit.button");
        actions.clickElement("edit.post.submit.button");

        actions.waitForElementVisible("all.post.of.user");


    }

    public void validatePostCreatedInTheLast1Minute() {

        LocalDateTime currentDateTime = getCurrentDateTime();
        String formattedDateTime = formatDateTime(currentDateTime);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String commentDateTime = actions.readTextFromElement("posts.time.created");
        long result = Utils.compareDates(formattedDateTime, commentDateTime);

        Assertions.assertTrue(result < 1);

    }

    public void validateAnonymousUserCannotSeePrivatePosts() {
        actions.assertElementNotPresent("//span[text()='Public post: false']");

    }

    public void validatePostIsEdited() {
        actions.assertElementPresent("all.post.of.user");
    }

    public void validatePostEditedWithText(String postName) {
        actions.assertElementVisible("post.content", postName);
    }

    public int getLikeCount(String locator) {
        String likesOfElement = actions.readTextFromElement(locator);
        System.out.println("like content:" + likesOfElement);
        int pos = likesOfElement.indexOf(":");
        String strNumber = likesOfElement.substring(pos + 1);
        int likeCount = Integer.parseInt(strNumber.trim());
        return likeCount;

    }

    public void validatePostCreatedWithText(String comment) {
        actions.assertElementVisible("comment.content", comment);
    }

    public void validateTopicIsUnliked() {
        actions.assertElementPresent("post.like.button");
    }

    public void deletePost() {
        actions.waitForElementVisible("delete.post.button");
        actions.clickElement("delete.post.button");

        actions.waitForElementVisible("delete.drop.down.button");
        actions.clickElement("delete.drop.down.button");

        actions.waitForElementVisible("delete.submit.button");
        actions.clickElement("delete.submit.button");

    }

    public void clickOnTheRecentPost() {
        actions.waitForElementVisible("profile.personal.page.button");
        actions.clickElement("profile.personal.page.button");

        actions.waitForElementClickable("latest.activity.button");
        actions.clickElement("latest.activity.button");

        actions.waitForElementClickable("recently.post");
        actions.clickElement("recently.post");
    }

    public void validatePostIsDeleted() {
        actions.assertElementPresent("delete.post.message");


    }

    public void validatePostIsLiked() {
        actions.waitForElementVisible("post.dislike.button");
        actions.assertElementPresent("post.dislike.button");
    }

    public void verifyPostCreated() {
        actions.waitForElementClickable("see.profile.button");

    }

    public void verifyPublicPostCreated() {
        actions.assertElementPresent("public.post.displayed");
    }

    public void verifyPrivatePostCreated() {
        actions.assertElementPresent("private.post.displayed");

    }

    public static String generateDescription() {
        return RandomStringUtils.randomAlphabetic(15);
    }

}
