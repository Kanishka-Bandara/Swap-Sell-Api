package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
