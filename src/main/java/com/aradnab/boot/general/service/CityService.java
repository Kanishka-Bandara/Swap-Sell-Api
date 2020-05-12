package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.City;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.CityRepository;
import com.aradnab.boot.general.service.service_controller.CityServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CityService implements CityServiceInterface {

    @Autowired
    private CityRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public City create(City city) {
        city.setSavedAt(new Date());
        city.setLastUpdatedAt(new Date());
        city.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(city);
    }

    @Override
    public City update(City city) {
        List<City> l = em.createQuery("from City  x where x.id = " + city.getId() + " and x.status!=" + Status.DELETE_STATUS, City.class).getResultList();
        if (l.size() > 0) {
            city.setLastUpdatedAt(new Date());
            return repository.save(city);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + city.getId());
        }
    }

    @Override
    public List<City> getAll() {
        return em.createQuery("from City  x where x.status!=" + Status.DELETE_STATUS, City.class).getResultList();
    }

    @Override
    public City getByID(int id) {
        List<City> l = em.createQuery("from City  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, City.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<City> l = em.createQuery("from City  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, City.class).getResultList();
        if (l.size() > 0) {
            City city = l.get(0);
            city.setLastUpdatedAt(new Date());
            city.setDeletedAt(new Date());
            city.setStatus(Status.DELETE_STATUS);
            repository.save(city);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public City getByName(String name) {
        List<City> l = em.createQuery("from City  x where x.city = " + name + " and x.status!=" + Status.DELETE_STATUS, City.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with City name : " + name);
        }
    }
}

