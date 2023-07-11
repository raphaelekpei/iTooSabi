package com.raphael.itoosabi.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String message) {
        super(message);
    }
}
