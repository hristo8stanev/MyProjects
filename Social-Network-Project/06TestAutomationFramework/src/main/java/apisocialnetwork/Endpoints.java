package apisocialnetwork;

import static apisocialnetwork.Constants.*;

public class Endpoints {

    public static final String BASE_URL = "http://localhost:8081";
    public static final String REGISTER_ENDPOINT = "/api/users/";
    public static final String GET_ALL_POSTS_ENDPOINT = "/api/post/";
    public static final String GET_PROFILE_POSTS = "/api/users/" + "%s" + "/posts";
    public static final String COMMENT_ENDPOINT = "/api/comment/auth/creator";
    public static final String CREATE_POST_ENDPOINT = "/api/post/auth/creator";
    public static final String AUTHENTICATE_ENDPOINT = "/authenticate";
    public static final String CREATE_SKILL_ENDPOINT = "/api/skill/create";
    public static final String SKILL_ENDPOINT = "/api/skill";
    public static final String EDIT_SKILL_ENDPOINT = "/api/skill/edit";
    public static final String DELETE_SKILL_ENDPOINT = "/api/skill/delete";
    public static final String SHOW_CREATED_COMMENTS = BASE_URL + "/api/comment/single?commentId=" + "%s";
    public static final String DELETE_POSTS = "/api/post/auth/manager";
    public static final String SEND_CONNECTION_REQUEST_ENDPOINT = "/api/auth/request";
    public static final String EDITED_COMMENT = "/api/comment/auth/editor?commentId=" + "%s" +
            "&content=" + EDIT_COMMENT +
            "&name=" + UNIQUE_NAME;
    public static final String LIKED_COMMENT = "/api/comment/auth/likesUp?commentId=" + "%s";
    public static final String DELETE_COMMENT = "/api/comment/auth/manager";
    public static final String LIKE_POST = "/api/post/auth/likesUp?postId=" + "%s";
    public static final String EDIT_POST = "/api/post/auth/editor";
    public static final String API_USERS_AUTH = "/api/users/auth/";
    public static final String CONNECTION_REQUEST_ENDPOINT = "/api/auth/users/";
    public static final String CONNECTION_REQUEST_APPROVE_ENDPOINT = "/request/approve";

}


