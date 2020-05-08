package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
