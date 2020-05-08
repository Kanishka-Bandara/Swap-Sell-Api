package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
}
