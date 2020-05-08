package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ReturnOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnOrderRepository extends JpaRepository<ReturnOrder, Integer> {
}
