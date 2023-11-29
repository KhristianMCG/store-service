package com.inditex.storage.infra;

import com.inditex.storage.api.SearchCriteria;
import com.inditex.storage.converter.SearchCriteriaDtoToSearchCriteriaConverter;
import com.inditex.storage.service.PriceService;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.inditex.storage.infra.rest.api.controller.PricesApiDelegate;

import java.util.List;

@Service
public class PriceApiDelegateImpl implements PricesApiDelegate {

    private final PriceService priceService;
    private final SearchCriteriaDtoToSearchCriteriaConverter searchCriteriaDtoToSearchCriteriaConverter;

    public PriceApiDelegateImpl(final PriceService priceService, final SearchCriteriaDtoToSearchCriteriaConverter searchCriteriaDtoToSearchCriteriaConverter) {
        this.priceService = priceService;
        this.searchCriteriaDtoToSearchCriteriaConverter = searchCriteriaDtoToSearchCriteriaConverter;
    }

    @Override
    public ResponseEntity<List<PriceDto>> prices(final SearchCriteriaDto searchCriteriaDto) {
        final SearchCriteria searchCriteria = searchCriteriaDtoToSearchCriteriaConverter.convert(searchCriteriaDto);
        return ResponseEntity.ok(priceService.findAllFilteredPrices(searchCriteria));
    }
}
