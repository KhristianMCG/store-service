package com.inditex.storage.service;

import com.inditex.storage.converter.PriceEntityToDtoConverter;
import com.inditex.storage.converter.SearchCriteriaDtoToSearchCriteriaConverter;
import com.inditex.storage.exception.NoPricesWereFoundException;
import com.inditex.storage.exception.StorageServiceReponse;
import com.inditex.storage.repository.PriceRepository;
import com.inditex.storage.repository.specification.PriceSpecification;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.inditex.storage.exception.ErrorMessages.PRICES_NOT_FOUND;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceSpecification priceSpecification;
    private final PriceEntityToDtoConverter priceEntityToDtoConverter;
    private final SearchCriteriaDtoToSearchCriteriaConverter searchCriteriaDtoToSearchCriteriaConverter;

    public PriceService(final PriceRepository priceRepository,
                        final PriceSpecification priceSpecification,
                        final PriceEntityToDtoConverter priceEntityToDtoConverter,
                        final SearchCriteriaDtoToSearchCriteriaConverter searchCriteriaDtoToSearchCriteriaConverter) {
        this.priceRepository = priceRepository;
        this.priceSpecification = priceSpecification;
        this.priceEntityToDtoConverter = priceEntityToDtoConverter;
        this.searchCriteriaDtoToSearchCriteriaConverter = searchCriteriaDtoToSearchCriteriaConverter;
    }

    public List<PriceDto> findAllFilteredPrices(final SearchCriteriaDto searchCriteriaDto) {
        priceSpecification.setSearchCriteria(searchCriteriaDtoToSearchCriteriaConverter.convert(searchCriteriaDto));
        final List<PriceDto> priceDtos = priceEntityToDtoConverter.convertObjects(this.priceRepository.findAll(priceSpecification));
        if (priceDtos.isEmpty()) {
            throw new NoPricesWereFoundException(StorageServiceReponse.builder()
                    .errorCode(PRICES_NOT_FOUND.getErrorCode())
                    .message(String.format(PRICES_NOT_FOUND.getErrorMessage(),
                            searchCriteriaDto.getDateOfApplication(),
                            searchCriteriaDto.getBrandId(),
                            searchCriteriaDto.getProductId()))
                    .build());
        }
        return priceDtos;
    }
}
