package com.example.demo.web.controller;

import com.example.demo.core.expection.ErrorResponse;
import com.example.demo.core.expection.UserAlreadyExistRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistRuntimeException exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.SEE_OTHER, exception.getMessage()), HttpStatus.SEE_OTHER);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
