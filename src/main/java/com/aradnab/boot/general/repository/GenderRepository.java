package com.aradnab.boot.general.repository;

import com.aradnab.boot.general.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {

}
