package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
}
