package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Integer> {
}
