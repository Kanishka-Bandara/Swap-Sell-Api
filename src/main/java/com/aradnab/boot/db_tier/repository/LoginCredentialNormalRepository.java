package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.LoginCredentialNormal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginCredentialNormalRepository extends JpaRepository<LoginCredentialNormal, Integer> {
}
