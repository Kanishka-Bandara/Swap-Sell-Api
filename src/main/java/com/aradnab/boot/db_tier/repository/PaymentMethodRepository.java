package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
