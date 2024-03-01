package com.example.demo.core.expection;

import org.springframework.security.core.AuthenticationException;

public class AuthMethodIsNotSupportedRuntimeException extends AuthenticationException {
    public AuthMethodIsNotSupportedRuntimeException(String message) {
        super(message);
    }
}
