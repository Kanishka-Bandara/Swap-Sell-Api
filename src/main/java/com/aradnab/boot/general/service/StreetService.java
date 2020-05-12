package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Street;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.StreetRepository;
import com.aradnab.boot.general.service.service_controller.StreetServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StreetService implements StreetServiceInterface {

    @Autowired
    private StreetRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Street create(Street street) {
        street.setSavedAt(new Date());
        street.setLastUpdatedAt(new Date());
        street.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(street);
    }

    @Override
    public Street update(Street street) {
        List<Street> l = em.createQuery("from Street  x where x.id = " + street.getId() + " and x.status!=" + Status.DELETE_STATUS, Street.class).getResultList();
        if (l.size() > 0) {
            street.setLastUpdatedAt(new Date());
            return repository.save(street);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + street.getId());
        }
    }

    @Override
    public List<Street> getAll() {
        return em.createQuery("from Street  x where x.status!=" + Status.DELETE_STATUS, Street.class).getResultList();
    }

    @Override
    public Street getByID(int id) {
        List<Street> l = em.createQuery("from Street  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Street.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Street> l = em.createQuery("from Street  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Street.class).getResultList();
        if (l.size() > 0) {
            Street street = l.get(0);
            street.setLastUpdatedAt(new Date());
            street.setDeletedAt(new Date());
            street.setStatus(Status.DELETE_STATUS);
            repository.save(street);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

