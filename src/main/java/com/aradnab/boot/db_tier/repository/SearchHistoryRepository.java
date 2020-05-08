package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {
}
