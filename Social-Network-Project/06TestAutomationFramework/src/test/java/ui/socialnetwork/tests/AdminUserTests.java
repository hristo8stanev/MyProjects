package ui.socialnetwork.tests;

import apisocialnetwork.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import static com.telerikacademy.testframework.Constants.*;
import static pages.wearesocialnetwork.PostPage.generateDescription;

public class AdminUserTests extends BaseTestSetup {
    String adminUsername = "admin";
    String postDescription = "My Post: ";
    String editPostText = "Edit post to: ";
    String password = "";
    String commentText = "Comment: ";
    String firstName = "";
    String lastName = "";
    String email = "";

    @BeforeEach
    public void setupLogin() {
        postDescription += generateDescription();
        commentText = commentPage.generateRandomComment();
        editedComment = commentPage.generateRandomEditComment();
        adminUsername += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(adminUsername, password);
        loginUser(adminUsername, password);
    }

    @Test
    @Tag("FHKT-266")
    public void adminHomePageLinkTest() {
        homePage.verifyAdminZoneLinkVisibility();
        homePage.clickOnGoToAdminZoneButton();

        homePage.verifyAdminZonePageAccess();
    }

    @Test
    @Tag("FHKT-263")
    public void adminUserViewAllUsersTest() {
        homePage.clickOnGoToAdminZoneButton();
        homePage.clickOnViewAllUsersButton();
        //assert
        homePage.verifyAdminViewAllUsers();
    }

    @Test
    @Tag("FHKT-267")
    public void adminUserCreatePublicPostsTest() {
        postPage.createPublicPost(postDescription);
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();
        postPage.validatePostCreatedInTheLast1Minute();
        postPage.validatePostCreatedWithText(postDescription);
    }

    @Test
    @Tag("FHKT-268")
    public void adminUserCreatePrivatePostsTest() {
        postPage.createPrivatePost(postDescription);
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();
        postPage.validatePostCreatedInTheLast1Minute();
        postPage.validatePostCreatedWithText(postDescription);
    }

    @Test
    @Tag("FHKT-269")
    public void adminUserLikeLatestPostWhenClickLikeButtonTest() {
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();
        //assert
        postPage.validatePostIsLiked();

    }

    @Test
    @Tag("FHKT-270")
    public void adminUserDislikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();
        //assert
        postPage.validateTopicIsUnliked();
    }

    @Test
    @Tag("FHKT-58")
    public void adminUserEditPostTest() {
        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        editPostText += generateDescription();
        postPage.userEditPost(editPostText);
        //assert
        postPage.validatePostEditedWithText(editPostText);
        postPage.validatePostIsEdited();

    }

    @Test
    @Tag("FHKT-271")
    public void adminUserCreateCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();

        commentPage.verifyFirstCommentCreated();
        commentPage.validateCommentCreatedWithText(commentText);
        commentPage.validateCommentAddedInTheLast1Minute();
    }

    @Test
    @Tag("FHKT-273")
    public void adminUserLikeCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();

        int oldLikeCount = commentPage.getLikeCount("comment.old.comment");
        commentPage.userLikeCommentUnderThePost();

        //assert
        commentPage.validateCommentIsLiked();
        commentPage.verifyLikeAmountIncreasedByOne("comment.old.comment", oldLikeCount);
    }

    @Test
    @Tag("FHKT-274")
    public void adminUserDislikeCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText += commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDislikeCommentUnderThePost();
        //assert
        commentPage.validateCommentIsUnliked();
    }

    @Test
    @Tag("FHKT-62")
    public void adminUserEditCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText += commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        editedComment = commentPage.generateRandomEditComment();
        commentPage.userEditCommentUnderThePost(editedComment);
        commentPage.clickOnShowCommentsUnderThePost();
        //assert
        commentPage.validateCommentEditedWithText(editedComment);
        commentPage.validateCommentAddedInTheLast1Minute();
    }

    @Test
    @Tag("FHKT-64")
    public void adminUserDeleteCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText += commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDeleteCommentUnderThePost();
        //assert
        commentPage.verifyCommentDeleted();
    }

    @Test
    @Tag("FHKT-262")
    public void adminUserUpdateUserProfileWithFirstLastNameBirthdayGenderEmailPublicInfoCityTest() {
        personalProfilePage.enterPersonalProfile();
        firstName += Utils.generateFirstName();
        lastName += Utils.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        email = personalProfilePage.generateRandomEmail();
        personalProfilePage.updateUserProfileWithEmailAddress(email);
        personalProfilePage.updateUserProfileWithGender();
        personalInfo += personalProfilePage.generateInfo();
        personalProfilePage.updateUserPublicInfo(personalInfo);
        personalProfilePage.updateCity();
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();

        //assert
        personalProfilePage.assertFirstLastNamesUpdated(firstName, lastName);
        personalProfilePage.assertEmailUpdated(email);

    }
}
