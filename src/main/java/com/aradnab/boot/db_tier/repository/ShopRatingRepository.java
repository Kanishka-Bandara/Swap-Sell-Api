package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ShopRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRatingRepository extends JpaRepository<ShopRating, Integer> {
}
