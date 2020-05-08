package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ReturnOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnOrderProductRepository extends JpaRepository<ReturnOrderProduct, Integer> {
}
