package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
