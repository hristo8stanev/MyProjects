package api.socialnetwork.tests;

import api.socialnetwork.base.BaseTestSetup;
import io.restassured.response.Response;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static apisocialnetwork.Constants.COMMENT_DESCRIPTION;
import static apisocialnetwork.Constants.COMMENT_ID;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.JSONRequests.COMMENT_BODY;
import static apisocialnetwork.Utils.isValid;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class CommentsControllerTest extends BaseTestSetup {
    Logger logger = Logger.getLogger("");

    @Test
    public void createCommentTest() {

        createAndRegisterUser();
        loginUser();
        createPost();

        Response response = createComment();

        int statusCode = response.getStatusCode();
        String commentContent = response.getBody().jsonPath().getString("content");

        assertTrue(isValid(COMMENT_BODY), ERROR_MESSAGE_BODY_NOT_VALID_JSON);
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentContent, COMMENT_DESCRIPTION, ERROR_MESSAGE_RESPONSE_CONTENT);

        logger.info("Status Code: " + statusCode);
        logger.info("Response Body: " + response.getBody().asPrettyString());
    }

    @Test
    public void showCreatedCommentTest() {

        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();

        Response response = showComment();

        int statusCode = response.getStatusCode();
        String createdCommentID = response.getBody().jsonPath().getString("commentId");

        assertNotNull(response.getBody().asPrettyString());
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(createdCommentID, COMMENT_ID, ERROR_MESSAGE_COMMENT_ID);

        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body: " + response.getBody().asString());

    }

    @Test
    public void editCommentTest() {

        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();
        Response response = editComment();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(responseBody);
    }

    @Test
    public void deleteCommentTest() {

        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();
        Response response = deleteComment();
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(responseBody);
    }

    @Test
    public void likeCommentTest() {

        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();

        Response response = likeComment();

        int statusCode = response.getStatusCode();
        int commentIdFromResponse = response.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);

        boolean liked = response.jsonPath().getBoolean("liked");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentIdFromResponse, expectedCommentId, ERROR_MESSAGE_COMMENT_ID);
        assertTrue(liked, ERROR_MESSAGE_LIKED_SHOULD_BE_TRUE);

        logger.info(response.getBody().asPrettyString());

    }

    @Test
    public void dislikeCommentTest() {

        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();

        Response response = likeComment();
        Response responseDisliked = likeComment();

        int statusCode = responseDisliked.getStatusCode();
        int commentIdFromResponse = responseDisliked.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);

        boolean liked = responseDisliked.jsonPath().getBoolean("liked");
        ;

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentIdFromResponse, expectedCommentId, ERROR_MESSAGE_COMMENT_ID);
        assertFalse(liked, ERROR_MESSAGE_LIKED_SHOULD_BE_FALSE);

        logger.info(responseDisliked.getBody().asPrettyString());

    }

    @AfterTest
    public void deletePostTearDown() {
        Response response = deletePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(responseBody);
    }
}
