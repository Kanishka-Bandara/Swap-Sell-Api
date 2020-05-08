package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Integer> {
}
