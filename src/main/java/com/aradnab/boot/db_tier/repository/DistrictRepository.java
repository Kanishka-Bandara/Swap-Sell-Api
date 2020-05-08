package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
}
