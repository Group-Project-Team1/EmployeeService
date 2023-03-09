package com.example.employeeservice.Exception;

import java.time.format.DateTimeParseException;

public class WrongDateFormatException extends RuntimeException {
    public WrongDateFormatException(String message) {
        super(String.format(message));
    }
}
