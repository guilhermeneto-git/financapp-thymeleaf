package com.gneto.financapp.controller.error;

public enum EntityErrorCode {

    DUPLICATE_DATA(1062, "Duplicate Data");

    private final int code;

    private final String message;

    private Exception exception;

    EntityErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
