package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.LoginCredentialFb;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.LoginCredentialFbRepository;
import com.aradnab.boot.general.service.service_controller.LoginCredentialFbServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LoginCredentialFbService implements LoginCredentialFbServiceInterface {

    @Autowired
    private LoginCredentialFbRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public LoginCredentialFb create(LoginCredentialFb loginCredentialFb) {
        loginCredentialFb.setSavedAt(new Date());
        loginCredentialFb.setLastUpdatedAt(new Date());
        loginCredentialFb.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(loginCredentialFb);
    }

    @Override
    public LoginCredentialFb update(LoginCredentialFb loginCredentialFb) {
        List<LoginCredentialFb> l = em.createQuery("from LoginCredentialFb  x where x.id = " + loginCredentialFb.getId() + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialFb.class).getResultList();
        if (l.size() > 0) {
            loginCredentialFb.setLastUpdatedAt(new Date());
            return repository.save(loginCredentialFb);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + loginCredentialFb.getId());
        }
    }

    @Override
    public List<LoginCredentialFb> getAll() {
        return em.createQuery("from LoginCredentialFb  x where x.status!=" + Status.DELETE_STATUS, LoginCredentialFb.class).getResultList();
    }

    @Override
    public LoginCredentialFb getByID(int id) {
        List<LoginCredentialFb> l = em.createQuery("from LoginCredentialFb  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialFb.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<LoginCredentialFb> l = em.createQuery("from LoginCredentialFb  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, LoginCredentialFb.class).getResultList();
        if (l.size() > 0) {
            LoginCredentialFb loginCredentialFb = l.get(0);
            loginCredentialFb.setLastUpdatedAt(new Date());
            loginCredentialFb.setDeletedAt(new Date());
            loginCredentialFb.setStatus(Status.DELETE_STATUS);
            repository.save(loginCredentialFb);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public User getAuth(String fbId) {
        List<LoginCredentialFb> l = em.createQuery("from LoginCredentialFb f where f.fbId='" + fbId + "' and f.status=" + Status.LIVE_ACTIVE_STATUS , LoginCredentialFb.class).getResultList();
        if (l.size() > 0) {
            return l.get(0).getUserByUserId();
        } else {
            throw new ResourceNotFoundException("Bad credentials");
        }
    }
}

