package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.MessageCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageCategoryRepository extends JpaRepository<MessageCategory, Integer> {
}
