package com.inditex.storage.infra.rest.api;

import com.inditex.storage.infra.rest.api.controller.PricesApiDelegate;
import com.inditex.storage.service.PriceService;
import io.micrometer.core.annotation.Timed;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PriceApiDelegateImpl implements PricesApiDelegate {

    private final PriceService priceService;

    public PriceApiDelegateImpl(final PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    @Timed
    public ResponseEntity<PriceDto> prices(final SearchCriteriaDto searchCriteriaDto) {
        return ResponseEntity.ok(priceService.findAllFilteredPrices(searchCriteriaDto));
    }
}
