package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}
