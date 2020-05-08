package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
