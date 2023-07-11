package com.raphael.itoosabi.exceptions;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String message) {
        super(message);
    }
}
