package com.inditex.storage.service;

import com.inditex.storage.api.SearchCriteria;
import com.inditex.storage.converter.PriceEntityToDtoConverter;
import com.inditex.storage.repository.PriceRepository;
import com.inditex.storage.repository.specification.PriceSpecification;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceEntityToDtoConverter priceEntityToDtoConverter;
    public PriceService(final PriceRepository priceRepository, final PriceEntityToDtoConverter priceEntityToDtoConverter) {
        this.priceRepository = priceRepository;
        this.priceEntityToDtoConverter = priceEntityToDtoConverter;
    }

    public List<PriceDto> findAllFilteredPrices(final SearchCriteria searchCriteria) {
        final PriceSpecification priceSpecification = new PriceSpecification(searchCriteria);
        return priceEntityToDtoConverter.convertObjects(this.priceRepository.findAll(priceSpecification));
    }
}
