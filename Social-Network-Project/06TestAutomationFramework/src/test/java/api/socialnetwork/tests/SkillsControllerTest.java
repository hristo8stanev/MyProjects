package api.socialnetwork.tests;

import api.socialnetwork.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.Utils.isValid;
import static apisocialnetwork.JSONRequests.SKILLS_BODY;
import static apisocialnetwork.ShowMessages.*;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SkillsControllerTest extends BaseTestSetup {
    static Logger logger = Logger.getLogger("");

    @Test
    public static void createSkillTest() {

        Response response = createSkill();

        logger.info(SHOW_MESSAGE_RESPONSE_BODY + response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();
        String responseBodySkill = response.getBody().jsonPath().getString("skill");
        String responseBodyCategoryName = response.getBody().jsonPath().getString("category.name");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertEquals(responseBodySkill, SKILL_DESCRIPTION, ERROR_MESSAGE_RESPONSE_SKILL_DESCRIPTION);
        assertEquals(responseBodyCategoryName, SKILL_NAME, ERROR_MESSAGE_RESPONSE_SKILL_NAME);
        assertTrue(isValid(SKILLS_BODY), ERROR_MESSAGE_BODY_NOT_VALID_JSON);

        logger.info(SHOW_MESSAGE_CREATED_SKILL_SILL_ID);

    }

    @Test
    public static void getAllSkillsTest() {

        Response response = showAllSkills();

        logger.info(SHOW_MESSAGE_RESPONSE_BODY + response.asPrettyString());

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        assertTrue(isValid(SKILLS_BODY), ERROR_MESSAGE_BODY_NOT_VALID_JSON);
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertTrue(responseBody.length() > 0, ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(response.getBody().asPrettyString());
        logger.info(SHOW_MESSAGE_SHOW_ALL_SKILLS);
    }

    @Test
    public static void deleteSkillTest() {

        if (isNull(SKILL_ID)) {
            createSkill();
        }

        Response response = deleteSkill();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        assertEquals(SC_OK, statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(SHOW_MESSAGE_DELETED_SKILL);

    }


    @Test
    public void editSkillTest() {

        createSkill();

        Response response = editSkill();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(SC_OK, statusCode, ERROR_MESSAGE_INCORRECT_STATUS);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        logger.info(SHOW_MESSAGE_EDITED_SKILL);
    }
}

