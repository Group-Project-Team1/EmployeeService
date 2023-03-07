package com.example.employeeservice.AOP;

import com.example.employeeservice.domain.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            ArithmeticException.class,
    })
    public ResponseEntity<Object> handleBadRequestExceptions(Exception e){
        return ResponseHandler.generateResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                "BAD REQUEST"
        );
    }
}