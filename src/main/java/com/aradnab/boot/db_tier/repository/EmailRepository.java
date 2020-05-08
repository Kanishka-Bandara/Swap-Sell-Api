package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {
}
