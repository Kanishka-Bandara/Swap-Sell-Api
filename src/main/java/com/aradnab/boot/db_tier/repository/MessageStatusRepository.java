package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageStatusRepository extends JpaRepository<MessageStatus, Integer> {
}
