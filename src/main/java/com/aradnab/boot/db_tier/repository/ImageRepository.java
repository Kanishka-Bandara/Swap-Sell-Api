package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
