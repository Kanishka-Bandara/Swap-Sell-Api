package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Integer> {
}
