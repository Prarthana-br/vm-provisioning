package com.ripple.vmprovisioning.exceptions;

public class BadRequestException extends RestException{

    private static final int HTTP_STATUS_CODE = 400;

    public BadRequestException() {
        super(HTTP_STATUS_CODE);
    }

    public BadRequestException(String message) {
        super(message, HTTP_STATUS_CODE);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, HTTP_STATUS_CODE);
    }

    public BadRequestException(Throwable cause) {
        super(cause, HTTP_STATUS_CODE);
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, HTTP_STATUS_CODE);
    }
}
