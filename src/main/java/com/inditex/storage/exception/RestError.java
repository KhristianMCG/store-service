package com.inditex.storage.exception;

import lombok.Getter;

import java.util.Objects;

@Getter
public class RestError<T> {

    private final String exceptionName;

    private final String errorCode;

    private final T content;

    RestError(final Throwable throwable, final String errorCode, final T content) {
        Objects.requireNonNull(throwable);
        this.exceptionName = throwable.getClass().getSimpleName();
        this.errorCode = errorCode;
        this.content = content;
    }
}
