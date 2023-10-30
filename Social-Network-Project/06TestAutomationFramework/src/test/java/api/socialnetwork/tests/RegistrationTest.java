package api.socialnetwork.tests;

import api.socialnetwork.base.BaseTestSetup;
import apisocialnetwork.Utils;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static apisocialnetwork.Constants.USERNAME;
import static apisocialnetwork.Constants.USER_ID;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.ShowMessages.CORRECT_REGISTER_RETURN_MESSAGE;
import static apisocialnetwork.ShowMessages.SHOW_MESSAGE_RESPONSE_BODY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTestSetup {
    Logger logger = Logger.getLogger("");
    Response response;

    @Test
    public void registerUserTest() {

        response = createAndRegisterUser();

        int statusCode = response.getStatusCode();
        String responseReturnMessage = response.asPrettyString();
        String responseUsername = response.getBody().asString().split(" ")[3];
        String responseID = response.getBody().asString().split(" ")[6];

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertEquals(responseReturnMessage, String.format(CORRECT_REGISTER_RETURN_MESSAGE, USERNAME, USER_ID), "Response message is incorrect.");
        assertEquals(responseID, USER_ID, ERROR_MESSAGE_USER_ID);
        assertEquals(responseUsername, USERNAME, ERROR_MESSAGE_USERNAME);

        logger.info(SHOW_MESSAGE_RESPONSE_BODY + responseReturnMessage);
    }

    @AfterEach
    public void cleanUp() {
        Utils.deleteUser("username", response.jsonPath().getString("username"));
    }

}