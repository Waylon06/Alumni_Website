package com.school.utils;

/**
 * @author Waylon
 */
//public class Code {
//    public static final Integer SUCCESS = 200;
//    public static final Integer ERROR = 400;
//}
public enum HttpStatusCode {
    SUCCESS(200, "SUCCESS"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented");

    private int code;
    private String description;

    HttpStatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}