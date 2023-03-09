package com.example.employeeservice.Exception;

public class BadInputException extends RuntimeException{
    public BadInputException(String message) {
        super(String.format(message));
    }
}
