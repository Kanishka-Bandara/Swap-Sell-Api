package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ContactNumberType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactNumberTypeRepository extends JpaRepository<ContactNumberType, Integer> {
}
