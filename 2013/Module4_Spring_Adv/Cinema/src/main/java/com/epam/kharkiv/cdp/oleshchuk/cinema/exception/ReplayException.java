package com.epam.kharkiv.cdp.oleshchuk.cinema.exception;

public class ReplayException extends RuntimeException {

    public ReplayException() {
    }

    public ReplayException(String message) {
        super(message);
    }

    public ReplayException(String message, Throwable cause) {
        super(message, cause);
    }
}
