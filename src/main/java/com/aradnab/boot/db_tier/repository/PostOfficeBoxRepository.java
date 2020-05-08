package com.aradnab.boot.db_tier.repository;

import com.aradnab.boot.db_tier.entity.PostOfficeBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostOfficeBoxRepository extends JpaRepository<PostOfficeBox, Integer> {
}
