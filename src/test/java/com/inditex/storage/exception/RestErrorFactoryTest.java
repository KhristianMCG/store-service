package com.inditex.storage.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestErrorFactoryTest {

    private static final String MOCKED_CODE = "Mocked code";
    private static final String REST_ERROR = "RestError";
    private final RestErrorFactory restErrorFactory = new RestErrorFactory();

    @Test
    void testCreateError() {

        final RestError<String> error =
                restErrorFactory.createError(new NoPricesWereFoundException(StorageServiceReponse.builder().build()),
                        MOCKED_CODE, REST_ERROR);

        assertThat(error).isNotNull();
        assertThat(error.getExceptionName()).isEqualTo(NoPricesWereFoundException.class.getSimpleName());
        assertThat(error.getErrorCode()).isEqualTo(MOCKED_CODE);
        assertThat(error.getContent()).isEqualTo(REST_ERROR);
    }
}