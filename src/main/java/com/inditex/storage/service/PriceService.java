package com.inditex.storage.service;

import com.inditex.storage.converter.PriceEntityToDtoConverter;
import com.inditex.storage.repository.PriceRepository;
import com.inditex.storage.repository.specification.PriceSpecification;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceEntityToDtoConverter priceEntityToDtoConverter;
    private final PriceSpecification priceSpecification;

    public PriceService(final PriceRepository priceRepository,
                        final PriceEntityToDtoConverter priceEntityToDtoConverter,
                        final PriceSpecification priceSpecification) {
        this.priceRepository = priceRepository;
        this.priceEntityToDtoConverter = priceEntityToDtoConverter;
        this.priceSpecification = priceSpecification;
    }

    public List<PriceDto> findAllFilteredPrices(final SearchCriteriaDto searchCriteria) {
        priceSpecification.setSearchCriteriaDto(searchCriteria);
        return priceEntityToDtoConverter.convertObjects(this.priceRepository.findAll(priceSpecification));
    }
}
