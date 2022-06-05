package com.nakamura.posterr.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CantFollowYourselfException extends Exception implements BusinessException {
    private final ExceptionMessage exceptionMessage = ExceptionMessage.ERROR_CANT_FOLLOW_YOURSELF;

    @Override
    public HttpStatus getExceptionStatusCode() {
        return exceptionMessage.getHttpCode();
    }

    @Override
    public String getExceptionDescription() {
        return exceptionMessage.getMessage();
    }
}
