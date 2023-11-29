package com.inditex.storage.api;

import lombok.Builder;

@Builder
public class SearchCriteria {

    private String dateOfApplication;

    private Integer productId;

    private Integer brandId;
}
