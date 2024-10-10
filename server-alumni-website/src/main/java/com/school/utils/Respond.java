package com.school.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Waylon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Respond<T> {

    private Integer statusCode;
    private String message;
    private T data;

    public static <T> Respond<T> success() {
        return new Respond<>(HttpStatusCode.SUCCESS.getCode(), "success", null);
    }

    public static <T> Respond<T> success(String message) {
        return new Respond<>(HttpStatusCode.SUCCESS.getCode(), message, null);
    }

    public static <T> Respond<T> success(T data) {
        return new Respond<>(HttpStatusCode.SUCCESS.getCode(), "success", data);
    }

    public static <T> Respond<T> success(String message, T data) {
        return new Respond<>(HttpStatusCode.SUCCESS.getCode(), message, data);
    }

    public static <T> Respond<T> fail() {
        return new Respond<>(HttpStatusCode.BAD_REQUEST.getCode(), "fail", null);
    }

    public static <T> Respond<T> fail(String message) {
        return new Respond<>(HttpStatusCode.BAD_REQUEST.getCode(), message, null);
    }

    public static <T> Respond<T> fail(Integer statusCode, String message) {
        return new Respond<>(statusCode, message, null);
    }


    @Override
    public String toString() {
        return "Respond{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}