package com.example.stock.global.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    STOCK_NOT_FOUND(400, "존재하지 않는 상품입니다."),
    ALREADY_EXISTS_STOCK_NAME(400, "이미 존재하는 상품 이름입니다.");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}