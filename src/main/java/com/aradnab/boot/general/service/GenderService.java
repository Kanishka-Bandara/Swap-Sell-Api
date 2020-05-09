package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Gender;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.CrudRepository;
import com.aradnab.boot.general.service.service_controller.GenderServiceInterface;
import com.aradnab.boot.db_tier.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class GenderService implements GenderServiceInterface {

    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private EntityManager em;

    @Override
    public Gender createGender(Gender gender) {
        gender.setSavedAt(new Date());
        gender.setLastUpdatedAt(new Date());
//        gender.setDeletedAt(new Date());
        gender.setStatus(Status.LIVE_ACTIVE_STATUS);
        return genderRepository.save(gender);
    }

    @Override
    public Gender updateGender(int id, Gender gender) {
        Optional<Gender> genderDB = this.genderRepository.findById(id);
        if (genderDB.isPresent()) {
            Gender genderUpdate = genderDB.get();
            if (genderUpdate.getStatus() != Status.DELETE_STATUS) {
                genderUpdate.setGender(gender.getGender());
                genderUpdate.setStatus(gender.getStatus());
                genderUpdate.setLastUpdatedAt(new Date());
                genderUpdate.setLastUpdatedAt(new Date());
                return this.genderRepository.save(genderUpdate);
            } else {
                throw new ResourceNotFoundException("Record Not Found with id : " + id);
            }
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + gender.getId());
        }
    }

    @Override
    public List<Gender> getAll() {
        return em.createQuery("from Gender  g where g.status!=" + Status.DELETE_STATUS, Gender.class).getResultList();
    }

    @Override
    public Gender getGenderByID(int genderId, byte status) {
        Optional<Gender> genderDB = this.genderRepository.findById(genderId);
        if (genderDB.isPresent()) {
            Gender g = genderDB.get();
            if (g.getStatus() != Status.DELETE_STATUS) {
                return g;
            } else {
                throw new ResourceNotFoundException("Record Not Found with id : " + genderId);
            }
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + genderId);
        }
    }

    @Override
    public int deleteGender(int genderId) {
        Optional<Gender> genderDB = this.genderRepository.findById(genderId);
        if (genderDB.isPresent()) {
            Gender g = genderDB.get();
            if (g.getStatus() != Status.DELETE_STATUS) {
                Gender genderUpdate = genderDB.get();
                genderUpdate.setStatus(Status.DELETE_STATUS);
                genderUpdate.setDeletedAt(new Date());
                this.genderRepository.save(genderUpdate);
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Gender getGenderByName(String gender) {
//        List<GenderModel> rl = em.createQuery("from GenderModel  g where g.gender=" + gender + " and g.status!=" + Status.DELETE_STATUS, GenderModel.class).getResultList();
        List<Object> gl = CrudRepository.searchByName(em, Gender.class, "gender", gender);
        if (gl.size() > 0) {
            for (int i=0;i<gl.size();i++){
                Gender g = (Gender)gl.get(i);
                if (g.getStatus()!=Status.DELETE_STATUS){
                    return g;
                }
            }
            throw new ResourceNotFoundException("Record Not Found with gender : " + gender);
        } else {
            throw new ResourceNotFoundException("Record Not Found with gender : " + gender);
        }
    }
}
