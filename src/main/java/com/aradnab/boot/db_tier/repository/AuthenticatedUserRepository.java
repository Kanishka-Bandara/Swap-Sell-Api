package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.AuthenticatedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticatedUserRepository extends JpaRepository<AuthenticatedUser, Integer> {
}
