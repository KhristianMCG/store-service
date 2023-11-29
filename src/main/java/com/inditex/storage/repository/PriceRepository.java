package com.inditex.storage.repository;

import com.inditex.storage.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long>, JpaSpecificationExecutor<PriceEntity> {

    List<PriceEntity> findAll();
}
