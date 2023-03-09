package com.example.employeeservice.exception;

public class BadInputException extends RuntimeException{
    public BadInputException(String message) {
        super(String.format(message));
    }
}
