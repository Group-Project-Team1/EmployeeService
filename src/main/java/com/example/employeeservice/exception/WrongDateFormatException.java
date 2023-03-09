package com.example.employeeservice.exception;

public class WrongDateFormatException extends RuntimeException {
    public WrongDateFormatException(String message) {
        super(String.format(message));
    }
}
