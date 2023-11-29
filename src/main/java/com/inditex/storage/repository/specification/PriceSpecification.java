package com.inditex.storage.repository.specification;

import com.inditex.storage.api.SearchCriteria;
import com.inditex.storage.model.PriceEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PriceSpecification implements Specification<PriceEntity> {

    private final SearchCriteria searchCriteria;

    public PriceSpecification(final SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
    @Override
    public Predicate toPredicate(final Root<PriceEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
