package com.inditex.storage.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private static final String MESSAGE_FORMAT_TWO_PARAMS = "%s: %s";

    private final String errorCode;

    public ApplicationException(final String message, final String errorCode) {
        super(String.format(MESSAGE_FORMAT_TWO_PARAMS, message, errorCode));
        this.errorCode = errorCode;
    }
}