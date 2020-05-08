package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
