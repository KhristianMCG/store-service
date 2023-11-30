package com.inditex.storage.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.inditex.storage.exception.ErrorMessages.PRICES_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class GlobalRestExceptionHandlerTest {

    private static final String MOCKED_ERROR = "Mocked error";
    private static final String MOCKED_GENERIC_APPLICATION_EXCEPTION_MESSAGE = "Mocked Generic ApplicationException message";
    @Mock
    private RestErrorFactory restErrorFactory;
    @InjectMocks
    private GlobalRestExceptionHandler globalRestExceptionHandler;

    @Test
    void handleApplicationException() {

        final ApplicationException applicationException =
                new ApplicationException(MOCKED_ERROR, MOCKED_GENERIC_APPLICATION_EXCEPTION_MESSAGE);
        final RestError<String> applicationExceptionRestError = new RestError<>(applicationException,
                applicationException.getClass().getSimpleName(),
                applicationException.getMessage());
        when(restErrorFactory.createError(applicationException,
                applicationException.getClass().getSimpleName(),
                applicationException.getMessage())
        ).thenReturn(applicationExceptionRestError);

        ResponseEntity<RestError<String>> restErrorResponseEntity =
                globalRestExceptionHandler.handleApplicationException(applicationException);

        assertThat(restErrorResponseEntity.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
        RestError<String> restError = restErrorResponseEntity.getBody();
        assertThat(restError.getErrorCode()).isEqualTo(applicationException.getClass().getSimpleName());
        assertThat(restError.getContent()).isEqualTo(String.format("%s: %s",
                MOCKED_ERROR,
                MOCKED_GENERIC_APPLICATION_EXCEPTION_MESSAGE));
    }

    @Test
    void handleIllegalArgumentExceptionTest() {
        final IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Mocked IllegalArgumentException");
        final RestError<String> illegalArgumentExceptionRestError = new RestError<>(illegalArgumentException,
                illegalArgumentException.getClass().getSimpleName(),
                "Mocked IllegalArgumentException");
        when(restErrorFactory.createError(illegalArgumentException,
                illegalArgumentException.getClass().getSimpleName(),
                "Mocked IllegalArgumentException")
        ).thenReturn(illegalArgumentExceptionRestError);

        ResponseEntity<RestError<String>> restErrorResponseEntity =
                globalRestExceptionHandler.handleIllegalArgumentException(illegalArgumentException);

        assertThat(restErrorResponseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);

        RestError<String> restError = restErrorResponseEntity.getBody();
        assertThat(restError.getErrorCode()).isEqualTo("IllegalArgumentException");
        assertThat(restError.getContent()).isEqualTo("Mocked IllegalArgumentException");
        assertThat(restError.getExceptionName()).isEqualTo(illegalArgumentException.getClass().getSimpleName());
    }

    @Test
    void handleRuntimeException() {
        final RuntimeException runtimeException = new RuntimeException("Mocked RuntimeException exception");
        final RestError<String> runtimeExceptionRestError = new RestError<>(runtimeException,
                runtimeException.getClass().getSimpleName(),
                "Mocked RuntimeException exception");
        when(restErrorFactory.createError(runtimeException,
                "INTERNAL_SERVER_ERROR",
                "Mocked RuntimeException exception")
        ).thenReturn(runtimeExceptionRestError);

        ResponseEntity<RestError<String>> restErrorResponseEntity = globalRestExceptionHandler.handleRuntimeException(runtimeException);

        assertThat(restErrorResponseEntity.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
        RestError<String> restError = restErrorResponseEntity.getBody();
        assertThat(restError.getErrorCode()).isEqualTo("RuntimeException");
        assertThat(restError.getContent()).isEqualTo("Mocked RuntimeException exception");
        assertThat(restError.getExceptionName()).isEqualTo(RuntimeException.class.getSimpleName());
    }

    @Test
    void handleNoPricesWereFoundException() {
        final NoPricesWereFoundException noPricesWereFoundException =
                new NoPricesWereFoundException(StorageServiceReponse.builder()
                        .errorCode(PRICES_NOT_FOUND.getErrorCode())
                        .message(PRICES_NOT_FOUND.getErrorMessage())
                        .build());
        final RestError<String> applicationExceptionRestError = new RestError<>(noPricesWereFoundException,
                noPricesWereFoundException.getClass().getSimpleName(),
                noPricesWereFoundException.getMessage());
        when(restErrorFactory.createError(noPricesWereFoundException,
                noPricesWereFoundException.getClass().getSimpleName(),
                noPricesWereFoundException.getMessage())
        ).thenReturn(applicationExceptionRestError);

        ResponseEntity<RestError<String>> restErrorResponseEntity =
                globalRestExceptionHandler.handleNoPricesWereFoundException(noPricesWereFoundException);

        assertThat(restErrorResponseEntity.getStatusCode()).isEqualTo(NO_CONTENT);
        RestError<String> restError = restErrorResponseEntity.getBody();
        assertThat(restError.getErrorCode()).isEqualTo(noPricesWereFoundException.getClass().getSimpleName());
        assertThat(restError.getContent()).isEqualTo(String.format("%s: %s",
                PRICES_NOT_FOUND.getErrorMessage(),
                PRICES_NOT_FOUND.getErrorCode()));
    }
}