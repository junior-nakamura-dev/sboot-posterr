package com.nakamura.posterr.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class AlreadyFollowThisUserException extends Exception implements BusinessException {
    private final ExceptionMessage exceptionMessage = ExceptionMessage.ALREADY_FOLLOW_THIS_USER;

    @Override
    public HttpStatus getExceptionStatusCode() {
        return exceptionMessage.getHttpCode();
    }

    @Override
    public String getExceptionDescription() {
        return exceptionMessage.getMessage();
    }
}
