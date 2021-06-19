package com.example.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserAuthException extends Exception{
    public UserAuthException() {
    }

    public UserAuthException(String message) {
        super(message);
    }
}
