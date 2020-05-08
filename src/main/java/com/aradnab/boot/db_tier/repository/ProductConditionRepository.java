package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ProductCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductConditionRepository extends JpaRepository<ProductCondition, Integer> {
}
