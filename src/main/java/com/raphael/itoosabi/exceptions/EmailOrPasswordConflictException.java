package com.raphael.itoosabi.exceptions;

public class EmailOrPasswordConflictException extends RuntimeException{
    public EmailOrPasswordConflictException(String message) {
        super(message);
    }
}
