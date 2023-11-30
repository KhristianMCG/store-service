package com.inditex.storage.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PRICES_NOT_FOUND("prices.notfound", "Any price for that date %s, brand %s and product %s could be found");

    private final String errorCode;
    private final String errorMessage;

    ErrorMessages(final String errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
