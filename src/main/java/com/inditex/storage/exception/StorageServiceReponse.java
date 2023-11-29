package com.inditex.storage.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonTypeName("StorageServiceReponse")
public final class StorageServiceReponse implements Serializable {

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("message")
    private String message;

    @Builder
    private StorageServiceReponse(final String errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
