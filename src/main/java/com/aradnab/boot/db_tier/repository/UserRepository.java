package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
