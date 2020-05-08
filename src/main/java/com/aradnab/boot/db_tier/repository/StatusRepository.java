package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
