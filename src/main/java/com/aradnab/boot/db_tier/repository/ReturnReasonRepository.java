package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ReturnReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnReasonRepository extends JpaRepository<ReturnReason, Integer> {
}
