package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.wearesocialnetwork.PostPage;
import ui.socialnetwork.base.BaseTestSetup;

import static com.telerikacademy.testframework.Constants.*;
import static pages.wearesocialnetwork.PostPage.generateDescription;

public class PostTests extends BaseTestSetup {

    String username = "";
    String password = "";
    String postDescription = "My Post:";
    String editPostText = "Edit post to: ";

    @BeforeEach
    public void setupUser() {
        postDescription += generateDescription();
        username += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(username, password);
        loginUser(username, password);
    }

    @Test
    @Tag("FHKT-26")
    public void createPublicPostTest() {
        postPage.createPublicPost(postDescription);
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();
        postPage.validatePostCreatedInTheLast1Minute();
        postPage.validatePostCreatedWithText(postDescription);


    }

    @Test
    @Tag("FHKT-109")
    public void createPrivatePostTest() {
        postPage.createPrivatePost(postDescription);
        //assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();
        postPage.validatePostCreatedInTheLast1Minute();

    }

    @Test
    @Tag("FHKT-142")
    public void anonymousUserCannotSeePrivatePostsTest() {

        postPage.createPrivatePost(postDescription);
        homePage.clickOnHomeButton();
        logoutPage.logoutSuccessfully();
        homePage.clickOnLatestPostsButton();

        postPage.validateAnonymousUserCannotSeePrivatePosts();

    }

    @Test
    @Tag("FHKT-35")
    public void likePostWhenClickLikeButtonTest() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert increase
        postPage.validatePostIsLiked();

    }

    @Test
    @Tag("FHKT-113")
    public void dislikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.dislikePublicPost();
        //assert
        postPage.validateTopicIsUnliked();
    }

    @Test
    @Tag("FHKT-253")
    public void editPostTest() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        editPostText += generateDescription();
        postPage.userEditPost(editPostText);
        //assert
        postPage.validatePostEditedWithText(editPostText);
        postPage.validatePostIsEdited();
    }

    @Test
    @Tag("FHKT-283")
    public void deletePostTest() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        postPage.deletePost();
        //assert
        postPage.validatePostIsDeleted();

    }
}


