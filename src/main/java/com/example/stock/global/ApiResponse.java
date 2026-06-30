package com.example.stock.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private T data;
    private int code;
    private String message;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data, HttpStatus.OK.value(), "성공");
    }

    public static <T> ApiResponse<T> fail(T data, int code, String message) {
        return new ApiResponse<T>(data, code, message);
    }
}