package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.LoginCredentialGoogle;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.LoginCredentialGoogleRepository;
import com.aradnab.boot.general.service.service_controller.LoginCredentialGoogleServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LoginCredentialGoogleService implements LoginCredentialGoogleServiceInterface {

    @Autowired
    private LoginCredentialGoogleRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public LoginCredentialGoogle create(LoginCredentialGoogle loginCredentialGoogle) {
        loginCredentialGoogle.setSavedAt(new Date());
        loginCredentialGoogle.setLastUpdatedAt(new Date());
        loginCredentialGoogle.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(loginCredentialGoogle);
    }

    @Override
    public LoginCredentialGoogle update(LoginCredentialGoogle loginCredentialGoogle) {
        List<LoginCredentialGoogle> l = em.createQuery("from LoginCredentialGoogle  x where x.id = " + loginCredentialGoogle.getId() + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialGoogle.class).getResultList();
        if (l.size() > 0) {
            loginCredentialGoogle.setLastUpdatedAt(new Date());
            return repository.save(loginCredentialGoogle);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + loginCredentialGoogle.getId());
        }
    }

    @Override
    public List<LoginCredentialGoogle> getAll() {
        return em.createQuery("from LoginCredentialGoogle  x where x.status!=" + Status.DELETE_STATUS, LoginCredentialGoogle.class).getResultList();
    }

    @Override
    public LoginCredentialGoogle getByID(int id) {
        List<LoginCredentialGoogle> l = em.createQuery("from LoginCredentialGoogle  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialGoogle.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<LoginCredentialGoogle> l = em.createQuery("from LoginCredentialGoogle  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialGoogle.class).getResultList();
        if (l.size() > 0) {
            LoginCredentialGoogle loginCredentialGoogle = l.get(0);
            loginCredentialGoogle.setLastUpdatedAt(new Date());
            loginCredentialGoogle.setDeletedAt(new Date());
            loginCredentialGoogle.setStatus(Status.DELETE_STATUS);
            repository.save(loginCredentialGoogle);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public User getAuth(String gId) {
        List<LoginCredentialGoogle> l = em.createQuery("from LoginCredentialGoogle g where g.gId='" + gId + "' and g.status='" + Status.LIVE_ACTIVE_STATUS + "'", LoginCredentialGoogle.class).getResultList();
        if (l.size() > 0) {
            return l.get(0).getUserByUserId();
        } else {
            throw new ResourceNotFoundException("Bad credentials");
        }
    }
}

