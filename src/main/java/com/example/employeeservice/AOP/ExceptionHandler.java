package com.example.employeeservice.AOP;

import com.example.employeeservice.exception.BadInputException;
import com.example.employeeservice.exception.CannotAccessOtherUsersDataException;
import com.example.employeeservice.exception.WrongDateFormatException;
import com.example.employeeservice.domain.response.ResponseHandler;
import com.netflix.discovery.shared.transport.TransportException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            ArithmeticException.class,
            NullPointerException.class,
            IllegalStateException.class,
            CannotAccessOtherUsersDataException.class,
            RuntimeException.class,
            WrongDateFormatException.class,
            TransportException.class,
            BadInputException.class
    })
    public ResponseEntity<Object> handleBadRequestExceptions(Exception e){
        return ResponseHandler.generateResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                "BAD REQUEST"
        );
    }

}
