package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.AuthenticatedUser;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.AuthenticatedUserRepository;
import com.aradnab.boot.general.service.service_controller.AuthenticatedUserServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AuthenticatedUserService implements AuthenticatedUserServiceInterface {

    @Autowired
    private AuthenticatedUserRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public AuthenticatedUser create(AuthenticatedUser authenticatedUser) {
        authenticatedUser.setSavedAt(new Date());
        authenticatedUser.setLastUpdatedAt(new Date());
        authenticatedUser.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(authenticatedUser);
    }

    @Override
    public AuthenticatedUser update(AuthenticatedUser authenticatedUser) {
        List<AuthenticatedUser> l = em.createQuery("from AuthenticatedUser  x where x.id = " + authenticatedUser.getId() + " and x.status!=" + Status.DELETE_STATUS, AuthenticatedUser.class).getResultList();
        if (l.size() > 0) {
            authenticatedUser.setLastUpdatedAt(new Date());
            return repository.save(authenticatedUser);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + authenticatedUser.getId());
        }
    }

    @Override
    public List<AuthenticatedUser> getAll() {
        return em.createQuery("from AuthenticatedUser  x where x.status!=" + Status.DELETE_STATUS, AuthenticatedUser.class).getResultList();
    }

    @Override
    public AuthenticatedUser getByID(int id) {
        List<AuthenticatedUser> l = em.createQuery("from AuthenticatedUser  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, AuthenticatedUser.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<AuthenticatedUser> l = em.createQuery("from AuthenticatedUser  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, AuthenticatedUser.class).getResultList();
        if (l.size() > 0) {
            AuthenticatedUser authenticatedUser = l.get(0);
            authenticatedUser.setLastUpdatedAt(new Date());
            authenticatedUser.setDeletedAt(new Date());
            authenticatedUser.setStatus(Status.DELETE_STATUS);
            repository.save(authenticatedUser);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

