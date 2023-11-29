package com.inditex.storage.converter;

import com.inditex.storage.api.SearchCriteria;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchCriteriaDtoToSearchCriteriaConverter implements Converter<SearchCriteriaDto, SearchCriteria> {
    @Override
    public SearchCriteria convert(final SearchCriteriaDto source) {
        return SearchCriteria.builder()
                .dateOfApplication(source.getDateOfApplication())
                .brandId(source.getBrandId())
                .productId(source.getProductId())
                .build();
    }
}
