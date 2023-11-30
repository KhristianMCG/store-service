package com.inditex.storage.infra;

import com.inditex.storage.service.PriceService;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceApiDelegateImplTest {

    @InjectMocks
    private PriceApiDelegateImpl priceApiDelegate;

    @Mock
    private PriceService priceService;

    @Test
    void prices() {
        final SearchCriteriaDto searchCriteriaDto = mock(SearchCriteriaDto.class);
        final PriceDto priceDto = mock(PriceDto.class);
        final List<PriceDto> priceDtos = List.of(priceDto);

        when(priceService.findAllFilteredPrices(searchCriteriaDto)).thenReturn(priceDtos);

        final ResponseEntity<List<PriceDto>> response = priceApiDelegate.prices(searchCriteriaDto);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(priceDto);
    }
}