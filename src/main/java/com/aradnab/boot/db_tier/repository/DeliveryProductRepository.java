package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.DeliveryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryProductRepository extends JpaRepository<DeliveryProduct, Integer> {
}
