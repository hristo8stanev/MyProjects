package api.socialnetwork.tests;

import api.socialnetwork.base.BaseTestSetup;
import apisocialnetwork.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.ShowMessages.ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT;
import static apisocialnetwork.ShowMessages.SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class UserControllerTest extends BaseTestSetup {

    Logger logger = Logger.getLogger("");
    Response response;

    @Test
    public void loginAndFetchCookiesTest() {

        createAndRegisterUser();
        ValidatableResponse responseBody = loginUser();

        int statusCode = responseBody.extract().statusCode();

        assertEquals(statusCode, SC_MOVED_TEMPORARILY, ERROR_MESSAGE_INCORRECT_STATUS_LOGIN);
        Assert.assertFalse(COOKIE_VALUE.isEmpty(), ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT);

        logger.info(SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE);

    }

    @Test
    public void upgradeExpertiseProfileTest() {

        createAndRegisterUser();
        loginUser();

        response = upgradeExpertiseProfile();

        JsonObject jsonObject = (JsonObject) JsonParser.parseString(response.asString());
        String skill1Content = String.valueOf(jsonObject.getAsJsonArray("skills").get(0).getAsJsonObject().get("skill"));
        skill1Content = skill1Content.replaceAll("[\"<>,]", "");

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertEquals(skill1Content, RANDOM_JOB_TITLE, ERROR_MESSAGE_JOB_TITLE);

    }

    @Test
    public void updateUserProfileTest() {

        createAndRegisterUser();
        loginUser();

        response = updateUserProfile(SEARCHABLE_NAME);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);

    }


    @AfterEach
    public void cleanUp() {
        Utils.deleteUser("username", response.jsonPath().getString("username"));
    }
}



