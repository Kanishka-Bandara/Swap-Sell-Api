package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
