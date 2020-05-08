package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.SavedSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedSearchRepository extends JpaRepository<SavedSearch, Integer> {
}
