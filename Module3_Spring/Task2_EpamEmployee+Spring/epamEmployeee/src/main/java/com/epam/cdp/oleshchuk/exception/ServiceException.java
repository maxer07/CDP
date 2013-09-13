package com.epam.cdp.oleshchuk.exception;

/**
 * User: Maksym_Oleshchuk
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
