package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
}
