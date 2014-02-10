package com.epam.kharkiv.cdp.oleshchuk.cinema.exception;

public class CqrsVersionException extends RuntimeException {

    public CqrsVersionException() {
    }

    public CqrsVersionException(String message) {
        super(message);
    }

    public CqrsVersionException(String message, Throwable cause) {
        super(message, cause);
    }
}
