package com.inditex.storage.exception;

import org.springframework.stereotype.Component;

@Component
public class RestErrorFactory {

    public <T> RestError<T> createError(final Throwable throwable, final String errorCode, final T body) {
        return new RestError<>(throwable, errorCode, body);
    }
}