package com.inditex.storage.service;

import com.inditex.storage.converter.PriceEntityToDtoConverter;
import com.inditex.storage.converter.SearchCriteriaDtoToSearchCriteriaConverter;
import com.inditex.storage.infra.rest.model.SearchCriteria;
import com.inditex.storage.model.PriceEntity;
import com.inditex.storage.repository.PriceRepository;
import com.inditex.storage.repository.specification.PriceSpecification;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;
    @Mock
    private PriceEntityToDtoConverter priceEntityToDtoConverter;
    @Mock
    private PriceRepository priceRepository;
    @Mock
    private PriceSpecification priceSpecification;
    @Mock
    private SearchCriteriaDtoToSearchCriteriaConverter searchCriteriaDtoToSearchCriteriaConverter;

    @Test
    void findAllFilteredPrices() {
        final SearchCriteriaDto searchCriteriaDto = mock(SearchCriteriaDto.class);
        final PriceEntity priceEntity = mock(PriceEntity.class);
        final List<PriceEntity> priceEntities = List.of(priceEntity);

        when(searchCriteriaDtoToSearchCriteriaConverter.convert(searchCriteriaDto)).thenReturn(mock(SearchCriteria.class));
        when(priceRepository.findAll(priceSpecification)).thenReturn(priceEntities);
        when(priceEntityToDtoConverter.convertObjects(priceEntities)).thenReturn(List.of(mock(PriceDto.class)));

        final PriceDto priceDto = priceService.findAllFilteredPrices(searchCriteriaDto);

        assertThat(priceDto).isNotNull();
    }
}