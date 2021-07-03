package com.example.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
