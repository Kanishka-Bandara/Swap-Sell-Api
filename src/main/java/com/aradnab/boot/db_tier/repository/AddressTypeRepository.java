package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressTypeRepository extends JpaRepository<AddressType, Integer> {
}
