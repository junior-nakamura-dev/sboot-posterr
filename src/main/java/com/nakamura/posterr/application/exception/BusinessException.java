package com.nakamura.posterr.application.exception;

import org.springframework.http.HttpStatus;

public interface BusinessException {
    HttpStatus getExceptionStatusCode();
    String getExceptionDescription();
}
