package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
