package com.aradnab.boot.general.service;

import com.aradnab.boot.general.exception.ResourceNotFoundException;
import com.aradnab.boot.general.model.Gender;
import com.aradnab.boot.general.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class GenderService implements GenderServiceInterface {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public Gender createGender(Gender gender) {
        gender.setSavedAt(new Date());
        gender.setLastUpdatedAt(new Date());
        gender.setDeletedAt(new Date());
        gender.setStatus(1);
        return genderRepository.save(gender);
    }

    @Override
    public Gender updateGender(int id,Gender gender) {
        Optional<Gender> genderDB = this.genderRepository.findById(id);
        if (genderDB.isPresent()) {
            Gender genderUpdate = genderDB.get();
            genderUpdate.setGender(gender.getGender());
            genderUpdate.setStatus(gender.getStatus());
            genderUpdate.setLastUpdatedAt(new Date());
            return this.genderRepository.save(genderUpdate);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + gender.getId());
        }
    }

    @Override
    public List<Gender> getAll() {
//        List<Gender> genders = new ArrayList();
//        genders.add(new Gender(0, "Male"));
//        genders.add(new Gender(1, "Female"));
//        return genders;
        return this.genderRepository.findAll();
    }

    @Override
    public Gender getGenderByID(int genderId,byte status) {
        Optional<Gender> genderDB = this.genderRepository.findById(genderId);
        if (genderDB.isPresent()) {
            return genderDB.get();
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + genderId);
        }
    }

    @Override
    public void deleteGender(int genderId) {
        Optional<Gender> genderDB = this.genderRepository.findById(genderId);
        if (genderDB.isPresent()) {
            Gender genderUpdate = genderDB.get();
            genderUpdate.setStatus(0);
            this.genderRepository.save(genderUpdate);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + genderId);
        }
    }

    @Override
    public Gender getGenderByName(String gender) {
        return null;
    }
}
