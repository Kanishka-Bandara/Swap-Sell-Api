package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, Integer> {
}
