package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
