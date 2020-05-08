package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Integer> {
}
