package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.BuyingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyingProductRepository extends JpaRepository<BuyingProduct, Integer> {
}
