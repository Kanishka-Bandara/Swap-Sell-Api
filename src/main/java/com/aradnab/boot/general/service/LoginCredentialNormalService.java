package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.LoginCredentialNormal;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.LoginCredentialNormalRepository;
import com.aradnab.boot.general.service.service_controller.LoginCredentialNormalServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LoginCredentialNormalService implements LoginCredentialNormalServiceInterface {

    @Autowired
    private LoginCredentialNormalRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public LoginCredentialNormal create(LoginCredentialNormal loginCredentialNormal) {
        loginCredentialNormal.setSavedAt(new Date());
        loginCredentialNormal.setLastUpdatedAt(new Date());
        loginCredentialNormal.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(loginCredentialNormal);
    }

    @Override
    public LoginCredentialNormal update(LoginCredentialNormal loginCredentialNormal) {
        List<LoginCredentialNormal> l = em.createQuery("from LoginCredentialNormal  x where x.id = " + loginCredentialNormal.getId() + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialNormal.class).getResultList();
        if (l.size() > 0) {
            loginCredentialNormal.setLastUpdatedAt(new Date());
            return repository.save(loginCredentialNormal);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + loginCredentialNormal.getId());
        }
    }

    @Override
    public List<LoginCredentialNormal> getAll() {
        return em.createQuery("from LoginCredentialNormal  x where x.status!=" + Status.DELETE_STATUS, LoginCredentialNormal.class).getResultList();
    }

    @Override
    public LoginCredentialNormal getByID(int id) {
        List<LoginCredentialNormal> l = em.createQuery("from LoginCredentialNormal  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialNormal.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<LoginCredentialNormal> l = em.createQuery("from LoginCredentialNormal  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialNormal.class).getResultList();
        if (l.size() > 0) {
            LoginCredentialNormal loginCredentialNormal = l.get(0);
            loginCredentialNormal.setLastUpdatedAt(new Date());
            loginCredentialNormal.setDeletedAt(new Date());
            loginCredentialNormal.setStatus(Status.DELETE_STATUS);
            repository.save(loginCredentialNormal);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public User getAuth(String un, String pw) {
        List<LoginCredentialNormal> l = em.createQuery("from LoginCredentialNormal  x where x.username = '" + un + "' and x.password = '" + pw + "' and x.status!=" + Status.DELETE_STATUS, LoginCredentialNormal.class).getResultList();
        if (l.size()>0){
            return l.get(0).getUserByUserId();
        } else {
            throw new ResourceNotFoundException("Bad credentials");
        }
    }
}

