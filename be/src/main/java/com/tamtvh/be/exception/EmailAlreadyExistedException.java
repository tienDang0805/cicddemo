package com.tamtvh.be.exception;

public class EmailAlreadyExistedException extends RuntimeException{
    public EmailAlreadyExistedException(String message) {
        super(message);
    }
}
