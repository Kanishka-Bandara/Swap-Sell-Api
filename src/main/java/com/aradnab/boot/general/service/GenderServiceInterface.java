package com.aradnab.boot.general.service;

import com.aradnab.boot.general.model.Gender;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface GenderServiceInterface {
    Gender createGender(Gender gender);

    Gender updateGender(int id,Gender gender);

    List<Gender> getAll();

    Gender getGenderByID(int genderId, @Param("status")byte status);

    void deleteGender(int genderId);

    Gender getGenderByName(@Param("gender")String gender);
}
