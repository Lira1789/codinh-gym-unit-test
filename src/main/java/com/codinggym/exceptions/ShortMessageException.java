package com.codinggym.exceptions;

public class ShortMessageException extends RuntimeException {

    private static final String MESSAGE = "Message is too short";

    public ShortMessageException() {
        super(MESSAGE);
    }

    public ShortMessageException(String message) {
        super(message);
    }

}
