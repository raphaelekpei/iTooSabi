package com.raphael.itoosabi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> PostNotFoundExceptionHandler(PostNotFoundException ex) {

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CommentNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> CommentNotFoundExceptionHandler(CommentNotFoundException ex) {

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailSendingException.class)
    public ResponseEntity<ApiErrorResponse> EmailSendingExceptionHandler(EmailSendingException ex) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
              .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .message(ex.getMessage())
              .timestamp(LocalDateTime.now())
              .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
