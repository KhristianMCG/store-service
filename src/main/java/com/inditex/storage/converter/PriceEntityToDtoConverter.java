package com.inditex.storage.converter;

import com.inditex.storage.model.PriceEntity;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import org.springframework.stereotype.Component;

@Component
public class PriceEntityToDtoConverter implements AbstractListConverter<PriceEntity, PriceDto> {
    @Override
    public PriceDto convert(final PriceEntity source) {
        return PriceDto.builder()
                .price(String.valueOf(source.getPrice()))
                .brandId(String.valueOf(source.getBrand().getId()))
                .productId(String.valueOf(source.getProductId()))
                .priceListId(String.valueOf(source.getPriceList()))
                .dateOfApplicationStart(source.getPriceStartDate().toString())
                .dateOfApplicationEnd(source.getPriceEndDate().toString())
                .build();
    }
}
