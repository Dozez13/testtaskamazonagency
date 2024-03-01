package com.example.demo.core.expection;

public class UserAlreadyExistRuntimeException extends RuntimeException {
    public UserAlreadyExistRuntimeException(String message) {
        super(message);
    }
}
