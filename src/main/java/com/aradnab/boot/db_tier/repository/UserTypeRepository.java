package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
