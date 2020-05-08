package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.LoginCredentialGoogle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginCredentialGoogleRepository extends JpaRepository<LoginCredentialGoogle, Integer> {
}
