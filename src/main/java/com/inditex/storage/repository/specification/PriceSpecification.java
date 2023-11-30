package com.inditex.storage.repository.specification;

import com.inditex.storage.infra.rest.model.SearchCriteria;
import com.inditex.storage.model.BrandEntity_;
import com.inditex.storage.model.PriceEntity;
import com.inditex.storage.model.PriceEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
public class PriceSpecification implements Specification<PriceEntity> {

    private transient SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(final Root<PriceEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria.getBrandId() != null) {
            predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.BRAND).get(BrandEntity_.ID), searchCriteria.getBrandId()));
        }
        if (searchCriteria.getProductId() != null) {
            predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.PRODUCT_ID), searchCriteria.getProductId()));
        }
        if (searchCriteria.getDateOfApplication() != null) {
            predicates.add(criteriaBuilder.between(criteriaBuilder.literal(searchCriteria.getDateOfApplication()),
                    root.get(PriceEntity_.PRICE_START_DATE),
                    root.get(PriceEntity_.PRICE_END_DATE)));
        }
        if (predicates.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.ID), -1));
        }

        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .distinct(true).orderBy(criteriaBuilder.asc(root.get(PriceEntity_.PRICE_PRIORITY))).getRestriction();
    }
}
