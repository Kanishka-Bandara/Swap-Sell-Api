package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Gender;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Transactional
public interface GenderServiceInterface {
    Gender createGender(Gender gender);

    Gender updateGender(int id,Gender gender);

    List<Gender> getAll();

    Gender getGenderByID(int genderId, @Param("status")byte status);

    int deleteGender(int genderId);

    Gender getGenderByName(@Param("gender")String gender);
}
