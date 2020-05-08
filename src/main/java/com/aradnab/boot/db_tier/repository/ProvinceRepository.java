package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
