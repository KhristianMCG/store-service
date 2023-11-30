package com.inditex.storage.infra.rest.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Builder
public class SearchCriteria implements Serializable {

    private Timestamp dateOfApplication;
    private Integer productId;
    private Integer brandId;
}
