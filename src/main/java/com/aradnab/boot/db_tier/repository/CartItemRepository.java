package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
