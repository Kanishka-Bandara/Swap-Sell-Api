package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.ContactNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactNumberRepository extends JpaRepository<ContactNumber, Integer> {
}
