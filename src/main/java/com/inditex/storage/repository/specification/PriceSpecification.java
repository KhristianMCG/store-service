package com.inditex.storage.repository.specification;

import com.inditex.storage.model.PriceEntity;
import com.inditex.storage.model.PriceEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Setter;
import om.inditex.storage.infra.rest.api.model.SearchCriteriaDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
public class PriceSpecification implements Specification<PriceEntity> {

    private transient SearchCriteriaDto searchCriteriaDto;

    @Override
    public Predicate toPredicate(final Root<PriceEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        if (searchCriteriaDto.getBrandId() != null) {
            predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.BRAND), searchCriteriaDto.getBrandId()));
        }
        if (searchCriteriaDto.getProductId() != null) {
            predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.PRODUCT_ID), searchCriteriaDto.getProductId()));
        }
        if (searchCriteriaDto.getDateOfApplication() != null) {
            predicates.add(criteriaBuilder.between(criteriaBuilder.literal(searchCriteriaDto.getDateOfApplication()),
                    root.get(PriceEntity_.PRICE_START_DATE),
                    root.get(PriceEntity_.PRICE_END_DATE)));
        }
        if (predicates.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.ID), -1));
        }

        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .distinct(true).orderBy(criteriaBuilder.desc(root.get(PriceEntity_.PRIORITY))).getRestriction();
    }
}
