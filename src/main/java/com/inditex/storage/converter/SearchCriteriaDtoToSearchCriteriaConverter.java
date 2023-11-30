package com.inditex.storage.converter;

import com.inditex.storage.infra.rest.model.SearchCriteria;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class SearchCriteriaDtoToSearchCriteriaConverter implements AbstractListConverter<SearchCriteriaDto, SearchCriteria> {

    @Override
    public SearchCriteria convert(final SearchCriteriaDto source) {
        return SearchCriteria.builder()
                .brandId(source.getBrandId())
                .productId(source.getProductId())
                .dateOfApplication(Timestamp.valueOf(source.getDateOfApplication()))
                .build();
    }
}
