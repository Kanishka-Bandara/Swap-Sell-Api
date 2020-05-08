package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}
