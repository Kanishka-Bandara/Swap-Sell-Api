package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Integer> {
}
