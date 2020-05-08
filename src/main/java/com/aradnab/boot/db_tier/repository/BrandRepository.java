package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
