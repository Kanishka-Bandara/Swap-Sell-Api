package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
