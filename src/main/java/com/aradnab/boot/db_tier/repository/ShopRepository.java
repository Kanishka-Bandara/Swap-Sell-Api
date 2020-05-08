package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
