package com.inditex.storage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

@Entity
@Table(name = "price")
@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "Id cannot be null")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BrandEntity brand;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "price_start_date")
    private Timestamp priceStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "price_end_date")
    private Timestamp priceEndDate;

    @Column(name = "price_list")
    private int priceList;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "price_priority")
    private int pricePriority;

    @Column(name = "price", precision = 2)
    private BigDecimal price;

    @Column(name = "currency")
    private Currency currency;
}
