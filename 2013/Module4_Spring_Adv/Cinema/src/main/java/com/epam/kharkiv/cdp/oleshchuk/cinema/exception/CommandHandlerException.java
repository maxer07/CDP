package com.epam.kharkiv.cdp.oleshchuk.cinema.exception;

public class CommandHandlerException extends RuntimeException {

    public CommandHandlerException() {
    }

    public CommandHandlerException(String message) {
        super(message);
    }

    public CommandHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
