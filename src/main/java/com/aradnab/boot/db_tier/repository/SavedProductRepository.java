package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.SavedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedProductRepository extends JpaRepository<SavedProduct, Integer> {
}
