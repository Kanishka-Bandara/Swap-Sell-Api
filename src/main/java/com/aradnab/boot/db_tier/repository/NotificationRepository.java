package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
