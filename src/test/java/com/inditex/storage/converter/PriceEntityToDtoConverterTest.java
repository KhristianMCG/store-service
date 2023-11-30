package com.inditex.storage.converter;

import com.inditex.storage.model.BrandEntity;
import com.inditex.storage.model.PriceEntity;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceEntityToDtoConverterTest {

    private final PriceEntityToDtoConverter priceEntityToDtoConverter = new PriceEntityToDtoConverter();

    @Test
    void convert() {
        final PriceEntity priceEntity = mock(PriceEntity.class);
        final BrandEntity brandEntity = mock(BrandEntity.class);
        when(priceEntity.getBrand()).thenReturn(brandEntity);
        when(priceEntity.getBrand().getId()).thenReturn(1L);
        when(priceEntity.getPriceStartDate()).thenReturn(Timestamp.from(Instant.now()));
        when(priceEntity.getPriceEndDate()).thenReturn(Timestamp.from(Instant.now()));
        when(priceEntity.getPriceList()).thenReturn(1);
        when(priceEntity.getProductId()).thenReturn(35455);
        when(priceEntity.getPrice()).thenReturn(BigDecimal.valueOf(34.67));

        final PriceDto priceDto = priceEntityToDtoConverter.convert(priceEntity);

        assertThat(priceDto).isNotNull();
        assertThat(priceDto.getBrandId()).isEqualTo(priceEntity.getBrand().getId().toString());
        assertThat(priceDto.getProductId()).isEqualTo(String.valueOf(priceEntity.getProductId()));
        assertThat(priceDto.getDateOfApplicationStart()).isEqualTo(String.valueOf(priceEntity.getPriceStartDate()));
        assertThat(priceDto.getDateOfApplicationEnd()).isEqualTo(String.valueOf(priceEntity.getPriceEndDate()));
        assertThat(priceDto.getPriceListId()).isEqualTo(String.valueOf(priceEntity.getPriceList()));
        assertThat(priceDto.getPrice()).isEqualTo(String.valueOf(priceEntity.getPrice()));
    }

    @Test
    void convertObjects() {
        final PriceEntity priceEntity = mock(PriceEntity.class);
        final BrandEntity brandEntity = mock(BrandEntity.class);
        final List<PriceEntity> priceEntities = List.of(priceEntity);
        when(priceEntity.getBrand()).thenReturn(brandEntity);
        when(priceEntity.getBrand().getId()).thenReturn(1L);
        when(priceEntity.getPriceStartDate()).thenReturn(Timestamp.from(Instant.now()));
        when(priceEntity.getPriceEndDate()).thenReturn(Timestamp.from(Instant.now()));
        when(priceEntity.getPriceList()).thenReturn(1);
        when(priceEntity.getProductId()).thenReturn(35455);
        when(priceEntity.getPrice()).thenReturn(BigDecimal.valueOf(34.67));

        final List<PriceDto> priceDtos = priceEntityToDtoConverter.convertObjects(priceEntities);

        assertThat(priceDtos).isNotNull().hasSize(1);
    }
}