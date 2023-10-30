package apisocialnetwork;

import static apisocialnetwork.Constants.*;

public class ShowMessages {

    public static final String SHOW_MESSAGE_EDITED_SKILL = String.format("Skills with name '%s' was successfully edited.%n", EDITED_SKILLS + UNIQUE_NAME);
    public static final String SHOW_MESSAGE_DELETED_SKILL = String.format("Skill with name'%s' was successfully deleted", SKILL_ID);
    public static final String SHOW_MESSAGE_RESPONSE_BODY = "Show the response body: \n";
    public static final String SHOW_MESSAGE_CREATED_SKILL_SILL_ID = String.format("Skills with name '%s' and Id '%s' was successfully created.%n", SKILL_DESCRIPTION, SKILL_ID);
    public static final String CORRECT_REGISTER_RETURN_MESSAGE = "User with name %s and id %s was created";
    public static final String SHOW_MESSAGE_POST_LIKED = String.format("Post with id:%s was successful liked! ", POST_ID);
    public static final String SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE = String.format("Username was successfully created!\n" +
            " Using Username: %s and Password: %s\nCookie value is: %s ", USERNAME, PASSWORD, COOKIE_VALUE);
    public static final String ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT = "Cookie value is not present";
    public static final String SHOW_MESSAGE_POST_DELETED = String.format("Post with ID: '%s' was successfully deleted", POST_ID);
    public static final String SHOW_MESSAGE_GET_ALL_POSTS = "Successfully fetched all posts.";
    public static final String SHOW_MESSAGE_POST_CREATED_AND_POST_ID = String.format("Post was created successfully!\nPost ID is: %s", POST_ID);
    public static final String SHOW_MESSAGE_GET_ALL_PROFILE_POSTS = "Successfully fetched all Profile posts.";
    public static final String SHOW_MESSAGE_SHOW_ALL_SKILLS = "Successfully fetched all Skills.";
    public static final String SHOW_MESSAGE_EDITED_POST = "Post was successfully edited";
}
