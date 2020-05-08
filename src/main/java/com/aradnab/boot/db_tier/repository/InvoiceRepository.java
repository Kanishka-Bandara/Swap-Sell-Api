package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
