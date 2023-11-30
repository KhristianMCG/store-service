package com.inditex.storage.repository.specification;

import com.inditex.storage.model.PriceEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceSpecificationTest {

    @Spy
    private final PriceSpecification priceSpecification = new PriceSpecification();

    @Test
    @Disabled
    void toPredicate() {
        final CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        final CriteriaQuery<PriceEntity> criteriaQuery = mock(CriteriaQuery.class);
        final Root<PriceEntity> root = mock(Root.class);
        final SearchCriteriaDto searchCriteriaDto = mock(SearchCriteriaDto.class);
        final Predicate predicate = mock(Predicate.class);

        when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        when(criteriaQuery.distinct(true)).thenReturn(criteriaQuery);

        priceSpecification.setSearchCriteriaDto(searchCriteriaDto);

        final Predicate predicateResponse = priceSpecification.toPredicate(root, criteriaQuery, criteriaBuilder);

        assertThat(predicateResponse).isNotNull();
    }

    @Test
    void setSearchCriteriaDto() {
        final SearchCriteriaDto searchCriteriaDto = mock(SearchCriteriaDto.class);

        priceSpecification.setSearchCriteriaDto(searchCriteriaDto);

        verify(priceSpecification).setSearchCriteriaDto(searchCriteriaDto);
    }
}