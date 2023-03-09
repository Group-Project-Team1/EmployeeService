package com.example.employeeservice.Exception;

public class CannotAccessOtherUsersDataException extends RuntimeException {
    public CannotAccessOtherUsersDataException(String message) {
        super(String.format(message));
    }
}
