package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}
