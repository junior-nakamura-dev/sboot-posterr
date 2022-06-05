package com.nakamura.posterr.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {
    ERROR_CANT_FOLLOW_YOURSELF("You can´t follow yourself", HttpStatus.UNPROCESSABLE_ENTITY);

    private String message;
    private HttpStatus httpCode;



}
