package com.ripple.vmprovisioning.integration.db.exceptions;

public class PersistenceTransientException extends RuntimeException {

    public PersistenceTransientException() {
    }

    public PersistenceTransientException(String message) {
        super(message);
    }

    public PersistenceTransientException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceTransientException(Throwable cause) {
        super(cause);
    }

    public PersistenceTransientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
