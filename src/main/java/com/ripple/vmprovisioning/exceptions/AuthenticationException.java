package com.ripple.vmprovisioning.exceptions;

public class AuthenticationException extends RestException{

    private static final int HTTP_STATUS_CODE = 401;

    public AuthenticationException() {
        super(HTTP_STATUS_CODE);
    }

    public AuthenticationException(String message) {
        super(message, HTTP_STATUS_CODE);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause, HTTP_STATUS_CODE);
    }

    public AuthenticationException(Throwable cause) {
        super(cause, HTTP_STATUS_CODE);
    }

    public AuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, HTTP_STATUS_CODE);
    }
}
