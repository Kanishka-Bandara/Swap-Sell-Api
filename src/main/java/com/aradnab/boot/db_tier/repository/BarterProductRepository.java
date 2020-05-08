package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.BarterProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarterProductRepository extends JpaRepository<BarterProduct, Integer> {
}
