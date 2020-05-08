package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
