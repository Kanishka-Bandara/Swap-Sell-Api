package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.CartItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemStatusRepository extends JpaRepository<CartItemStatus, Integer> {
}
