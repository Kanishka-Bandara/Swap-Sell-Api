package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ViewedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewedProductRepository extends JpaRepository<ViewedProduct, Integer> {
}
