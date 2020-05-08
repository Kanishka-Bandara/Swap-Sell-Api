package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.SavedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedUserRepository extends JpaRepository<SavedUser, Integer> {
}
