package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<Dummy, Integer> {
}
