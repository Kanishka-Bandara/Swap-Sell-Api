package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.SavedShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedShopRepository extends JpaRepository<SavedShop, Integer> {
}
