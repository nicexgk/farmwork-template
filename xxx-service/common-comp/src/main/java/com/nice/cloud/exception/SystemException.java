package com.nice.cloud.exception;


public class SystemException extends RuntimeException {
    private static final long serialVersionUID = -810284859715336800L;
    private String code;

    public SystemException() {
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public SystemException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
