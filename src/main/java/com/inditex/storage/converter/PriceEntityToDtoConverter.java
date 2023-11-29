package com.inditex.storage.converter;

import com.inditex.storage.model.PriceEntity;
import om.inditex.storage.infra.rest.api.model.PriceDto;
import org.springframework.stereotype.Component;

@Component
public class PriceEntityToDtoConverter implements AbstractListConverter<PriceEntity, PriceDto> {
    @Override
    public PriceDto convert(final PriceEntity source) {
        return PriceDto.builder()
                .price(source.getPrice())
                .brandId(Math.toIntExact(source.getBrandId().getId()))
                .productId(source.getProductId())
                .priceListId(source.getPriceList())
                .dateOfApplicationStart(source.getPriceStartDate().toString())
                .dateOfApplicationEnd(source.getPriceEndDate().toString())
                .build();
    }
}
