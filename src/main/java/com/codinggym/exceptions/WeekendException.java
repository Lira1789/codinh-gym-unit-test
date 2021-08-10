package com.codinggym.exceptions;


public class WeekendException extends RuntimeException {

    public static final String MESSAGE = "You are not allowed to monitor employee on weekends";

    public WeekendException() {
        super(MESSAGE);
    }

    public WeekendException(String message) {
        super(message);
    }

}
