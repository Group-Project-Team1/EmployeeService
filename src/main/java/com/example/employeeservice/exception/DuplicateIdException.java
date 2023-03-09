package com.example.employeeservice.exception;

public class DuplicateIdException extends RuntimeException{
    public DuplicateIdException(String message) {
        super(String.format(message));
    }
}
