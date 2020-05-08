package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}