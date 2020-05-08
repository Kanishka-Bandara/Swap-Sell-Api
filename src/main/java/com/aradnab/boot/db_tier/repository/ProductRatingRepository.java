package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Integer> {
}
