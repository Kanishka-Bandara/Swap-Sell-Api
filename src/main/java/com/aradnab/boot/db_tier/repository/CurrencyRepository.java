package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
