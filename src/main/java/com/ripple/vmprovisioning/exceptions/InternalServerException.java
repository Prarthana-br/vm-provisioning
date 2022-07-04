package com.ripple.vmprovisioning.exceptions;

public class InternalServerException extends RestException{

    private static final int HTTP_STATUS_CODE = 500;

    public InternalServerException() {
        super(HTTP_STATUS_CODE);
    }

    public InternalServerException(String message) {
        super(message, HTTP_STATUS_CODE);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause, HTTP_STATUS_CODE);
    }

    public InternalServerException(Throwable cause) {
        super(cause, HTTP_STATUS_CODE);
    }

    public InternalServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, HTTP_STATUS_CODE);
    }
}
