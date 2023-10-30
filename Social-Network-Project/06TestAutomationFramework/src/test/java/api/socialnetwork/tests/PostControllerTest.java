package api.socialnetwork.tests;

import api.socialnetwork.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.ShowMessages.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class PostControllerTest extends BaseTestSetup {
    Logger logger = Logger.getLogger("");

    @Test
    public void createPostSuccessfullyTest() {

        createAndRegisterUser();
        loginUser();

        Response response = createPost();

        int statusCode = response.getStatusCode();
        String postContent = response.getBody().jsonPath().getString("content");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(postContent, POST_DESCRIPTION, ERROR_MESSAGE_POST_DESCRIPTION);

        logger.info(SHOW_MESSAGE_RESPONSE_BODY + response.getBody().asPrettyString());
        logger.info(SHOW_MESSAGE_POST_CREATED_AND_POST_ID);

        deletePostTearDown();

    }

    @Test
    public void getAllPostsTest() {

        Response response = showAllPosts();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);

        logger.info(SHOW_MESSAGE_GET_ALL_POSTS);
    }

    @Test
    public void showAllProfilePosts_Successful() {

        createAndRegisterUser();
        loginUser();
        createPost();

        Response response = showAllProfilePosts();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        logger.info(SHOW_MESSAGE_RESPONSE_BODY + responseBody);

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.length() > 2, ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(SHOW_MESSAGE_GET_ALL_PROFILE_POSTS);

        deletePostTearDown();

    }


    @Test
    public void editPostsTest() {

        createAndRegisterUser();
        loginUser();
        createPost();

        Response response = editProfilePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(SHOW_MESSAGE_EDITED_POST);

        deletePostTearDown();
    }

    @Test
    public void likeProfilePostTest() {

        createAndRegisterUser();
        loginUser();
        createPost();

        Response response = likePost();

        int statusCode = response.getStatusCode();
        boolean liked = response.jsonPath().getBoolean("liked");

        String postIdFromResponse = response.jsonPath().getString("postId");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(postIdFromResponse, POST_ID, ERROR_MESSAGE_RESPONSE_BODY_POST_ID);
        assertTrue(liked, ERROR_MESSAGE_LIKED_POST);

        logger.info(SHOW_MESSAGE_POST_LIKED);

        deletePostTearDown();

    }

    @Test
    public void deletePostsTest() {

        createAndRegisterUser();
        loginUser();
        createPost();

        Response response = deletePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(SHOW_MESSAGE_POST_DELETED);
    }

    public void deletePostTearDown() {

        Response response = deletePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();
        logger.info(responseBody);

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(SHOW_MESSAGE_POST_DELETED);

    }
}




