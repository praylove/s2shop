package com.x_s.s2shop.common.exception;


import org.springframework.security.core.AuthenticationException;

public class LoginException extends AuthenticationException {

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String msg, Throwable t) {
        super(msg, t);
    }
}
