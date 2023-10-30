package api.socialnetwork.tests;

import api.socialnetwork.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static apisocialnetwork.ErrorMessages.ERROR_MESSAGE_INCORRECT_STATUS;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class ConnectionControllerTest extends BaseTestSetup {
    Logger logger = Logger.getLogger("");

    @Test
    public void sendConnectionRequestTest() {

        createAndRegisterUserReceiver();
        createAndRegisterUser();
        loginUser();

        Response response = sendRequest();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format(ERROR_MESSAGE_INCORRECT_STATUS, SC_OK));

        logger.info(response.getBody().asPrettyString());
    }


    @Test
    public void approveConnectionRequestTest() {

        createAndRegisterUserReceiver();
        createAndRegisterUser();
        loginUser();
        sendRequest();
        loginUserReceiver();
        showReceivedRequests();

        Response response = approveRequest();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format(ERROR_MESSAGE_INCORRECT_STATUS, SC_OK));

        logger.info(response.getBody().asPrettyString());

    }

}
