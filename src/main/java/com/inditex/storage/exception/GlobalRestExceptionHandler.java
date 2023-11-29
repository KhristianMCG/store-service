package com.inditex.storage.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER_G_R_E_H = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);
    private final RestErrorFactory restErrorFactory;

    public GlobalRestExceptionHandler(final RestErrorFactory restErrorFactory) {
        this.restErrorFactory = restErrorFactory;
    }

    @ExceptionHandler
    public ResponseEntity<RestError<String>> handleApplicationException(final ApplicationException e) {
        LOGGER_G_R_E_H.info("handleApplicationException", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(this.restErrorFactory.createError(e, e.getClass().getSimpleName(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<RestError<String>> handleIllegalArgumentException(final IllegalArgumentException e) {
        LOGGER_G_R_E_H.error("handleIllegalArgumentException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(this.restErrorFactory.createError(e, e.getClass().getSimpleName(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<RestError<String>> handleRuntimeException(final RuntimeException e) {
        LOGGER_G_R_E_H.error("handleRuntimeException", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(this.restErrorFactory.createError(e, "INTERNAL_SERVER_ERROR", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<RestError<String>> handleNoPricesWereFoundException(final NoPricesWereFoundException e) {
        LOGGER_G_R_E_H.info("handleNoPricesWereFoundException", e);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(this.restErrorFactory.createError(e, e.getClass().getSimpleName(), e.getMessage()));
    }
}
