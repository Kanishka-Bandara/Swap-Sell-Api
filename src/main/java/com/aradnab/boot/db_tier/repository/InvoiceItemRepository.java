package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {
}
