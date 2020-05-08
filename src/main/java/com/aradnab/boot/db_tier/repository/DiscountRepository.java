package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
