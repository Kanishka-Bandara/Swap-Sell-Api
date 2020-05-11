package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.UserType;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.UserTypeRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import com.aradnab.boot.general.service.service_controller.UserTypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserTypeService implements UserTypeServiceInterface {

    @Autowired
    private UserTypeRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public UserType create(UserType userType) {
        userType.setSavedAt(new Date());
        userType.setLastUpdatedAt(new Date());
        userType.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(userType);
    }

    @Override
    public UserType update(UserType userType) {
        List<UserType> l = em.createQuery("from UserType  x where x.id = " + userType.getId() + " and x.status!=" + Status.DELETE_STATUS, UserType.class).getResultList();
        if (l.size() > 0) {
            userType.setLastUpdatedAt(new Date());
            return repository.save(userType);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + userType.getId());
        }
    }

    @Override
    public List<UserType> getAll() {
        return em.createQuery("from UserType  x where x.status!=" + Status.DELETE_STATUS, UserType.class).getResultList();
    }

    @Override
    public UserType getByID(int id) {
        List<UserType> l = em.createQuery("from UserType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, UserType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<UserType> l = em.createQuery("from UserType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, UserType.class).getResultList();
        if (l.size() > 0) {
            UserType userType = l.get(0);
            userType.setLastUpdatedAt(new Date());
            userType.setDeletedAt(new Date());
            userType.setStatus(Status.DELETE_STATUS);
            repository.save(userType);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public UserType getByTypeName(String userType) {
        List<UserType> l = em.createQuery("from UserType  x where x.type = " + userType + " and x.status!=" + Status.DELETE_STATUS, UserType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with userType : " + userType);
        }
    }
}

