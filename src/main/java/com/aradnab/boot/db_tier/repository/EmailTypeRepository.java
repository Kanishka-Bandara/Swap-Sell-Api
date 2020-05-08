package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.EmailType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTypeRepository extends JpaRepository<EmailType, Integer> {
}
