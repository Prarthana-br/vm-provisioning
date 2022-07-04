package com.ripple.vmprovisioning.exceptions;

public class RestException extends RuntimeException {

    private int httpStatusCode;

    public RestException(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public RestException(String message, int httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;

    }

    public RestException(String message, Throwable cause, int httpStatusCode) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
    }


    public RestException(Throwable cause, int httpStatusCode) {
        super(cause);
        this.httpStatusCode = httpStatusCode;
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int httpStatusCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
