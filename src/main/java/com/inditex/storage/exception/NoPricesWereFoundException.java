package com.inditex.storage.exception;

public class NoPricesWereFoundException extends ApplicationException {
    public NoPricesWereFoundException(final StorageServiceReponse errorResponse) {
        super(errorResponse.getMessage(), errorResponse.getErrorCode());
    }
}
