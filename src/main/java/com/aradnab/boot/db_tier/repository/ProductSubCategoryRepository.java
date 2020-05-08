package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Integer> {
}
