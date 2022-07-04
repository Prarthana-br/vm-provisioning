package com.ripple.vmprovisioning.exceptions;

public class AuthorizationException extends RestException{

    private static final int HTTP_STATUS_CODE = 403;

    public AuthorizationException() {
        super(HTTP_STATUS_CODE);
    }

    public AuthorizationException(String message) {
        super(message, HTTP_STATUS_CODE);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause, HTTP_STATUS_CODE);
    }

    public AuthorizationException(Throwable cause) {
        super(cause, HTTP_STATUS_CODE);
    }

    public AuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, HTTP_STATUS_CODE);
    }
}
