package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Country;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.CountryRepository;
import com.aradnab.boot.general.service.service_controller.CountryServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CountryService implements CountryServiceInterface {

    @Autowired
    private CountryRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Country create(Country country) {
        country.setSavedAt(new Date());
        country.setLastUpdatedAt(new Date());
        country.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(country);
    }

    @Override
    public Country update(Country country) {
        List<Country> l = em.createQuery("from Country  x where x.id = " + country.getId() + " and x.status!=" + Status.DELETE_STATUS, Country.class).getResultList();
        if (l.size() > 0) {
            country.setLastUpdatedAt(new Date());
            return repository.save(country);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + country.getId());
        }
    }

    @Override
    public List<Country> getAll() {
        return em.createQuery("from Country  x where x.status!=" + Status.DELETE_STATUS, Country.class).getResultList();
    }

    @Override
    public Country getByID(int id) {
        List<Country> l = em.createQuery("from Country  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Country.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Country> l = em.createQuery("from Country  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Country.class).getResultList();
        if (l.size() > 0) {
            Country country = l.get(0);
            country.setLastUpdatedAt(new Date());
            country.setDeletedAt(new Date());
            country.setStatus(Status.DELETE_STATUS);
            repository.save(country);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public Country getByName(String name) {
        List<Country> l = em.createQuery("from Country  x where x.country = '" + name + "' and x.status!=" + Status.DELETE_STATUS, Country.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with country : " + name);
        }
    }
}

