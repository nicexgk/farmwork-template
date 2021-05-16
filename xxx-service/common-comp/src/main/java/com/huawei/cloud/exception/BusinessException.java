package com.huawei.cloud.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Data;

@Data
public class BusinessException extends HystrixBadRequestException {
    private String code;
    private String data;

    public BusinessException(String code) {
        super(code);
        this.code = code;
    }

    public BusinessException(String code, Throwable cause) {
        super(code);
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}