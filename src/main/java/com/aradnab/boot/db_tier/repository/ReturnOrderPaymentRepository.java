package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ReturnOrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnOrderPaymentRepository extends JpaRepository<ReturnOrderPayment, Integer> {
}
