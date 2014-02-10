package com.epam.kharkiv.cdp.oleshchuk.cinema.exception;

public class CqrsVersionException extends RuntimeException {

    public CqrsVersionException(String message) {
        super(message);
    }

}
