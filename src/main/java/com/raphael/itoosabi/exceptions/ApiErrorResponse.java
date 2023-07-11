package com.raphael.itoosabi.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorResponse {
    private Integer statusCode;
    private String message;
    private LocalDateTime timestamp;
}
