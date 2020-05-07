package com.aradnab.boot.db_tier.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Gender;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.service.service_controller.GenderServiceInterface;
import com.aradnab.boot.db_tier.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

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
        gender.setDeletedAt(new Date());
        gender.setStatus(Status.LIVE_ACTIVE_STATUS);
        return genderRepository.save(gender);
    }

    @Override
    public Gender updateGender(int id, Gender gender) {
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
//        return this.genderRepository.findAll().stream().filter(gender -> gender.getStatus() != Status.DELETE_STATUS);
        return this.genderRepository.findAll();
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
    public void deleteGender(int genderId) {
        Optional<Gender> genderDB = this.genderRepository.findById(genderId);
        if (genderDB.isPresent()) {
            Gender genderUpdate = genderDB.get();
            genderUpdate.setStatus(Status.DELETE_STATUS);
            this.genderRepository.save(genderUpdate);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + genderId);
        }
    }

    @Override
    public Optional<Gender> getGenderByName(String gender) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Gender> criteria = builder.createQuery(Gender.class);
        Root<Gender> from = criteria.from(Gender.class);
        criteria.select(from);
        ParameterExpression<String> params = builder.parameter(String.class);
        criteria.where(builder.equal(from.get("gender"), params));
        TypedQuery<Gender> query = em.createQuery(criteria);
        query.setParameter(params, gender);
        Stream<Gender> queryResult = query.getResultList().stream().filter(g -> g.getStatus() == 1);
//        CrudRepository.searchByName(manager,Gender.class,"gender",gender);
        if (queryResult.count() > 0) {
            return queryResult.findFirst();
        } else {
            throw new ResourceNotFoundException("Record Not Found with gender : " + gender);
        }
    }
}
