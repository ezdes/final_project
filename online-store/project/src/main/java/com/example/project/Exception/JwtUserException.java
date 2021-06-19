package com.example.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class JwtUserException extends Exception{
    public JwtUserException() {
    }

    public JwtUserException(String message) {
        super(message);
    }
}
