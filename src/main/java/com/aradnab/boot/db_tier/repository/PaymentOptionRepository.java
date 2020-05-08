package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.PaymentOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Integer> {
}
