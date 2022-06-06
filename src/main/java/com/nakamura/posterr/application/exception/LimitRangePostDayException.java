package com.nakamura.posterr.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class LimitRangePostDayException extends Exception implements BusinessException {
    private final ExceptionMessage exceptionMessage = ExceptionMessage.LIMIT_RANGE_POST_DAY;

    @Override
    public HttpStatus getExceptionStatusCode() {
        return exceptionMessage.getHttpCode();
    }

    @Override
    public String getExceptionDescription() {
        return exceptionMessage.getMessage();
    }
}
