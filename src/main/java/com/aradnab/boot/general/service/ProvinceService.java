package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Province;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProvinceRepository;
import com.aradnab.boot.general.service.service_controller.ProvinceServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProvinceService implements ProvinceServiceInterface {

    @Autowired
    private ProvinceRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Province create(Province province) {
        province.setSavedAt(new Date());
        province.setLastUpdatedAt(new Date());
        province.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(province);
    }

    @Override
    public Province update(Province province) {
        List<Province> l = em.createQuery("from Province  x where x.id = " + province.getId() + " and x.status!=" + Status.DELETE_STATUS, Province.class).getResultList();
        if (l.size() > 0) {
            province.setLastUpdatedAt(new Date());
            return repository.save(province);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + province.getId());
        }
    }

    @Override
    public List<Province> getAll() {
        return em.createQuery("from Province  x where x.status!=" + Status.DELETE_STATUS, Province.class).getResultList();
    }

    @Override
    public Province getByID(int id) {
        List<Province> l = em.createQuery("from Province  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Province.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Province> l = em.createQuery("from Province  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Province.class).getResultList();
        if (l.size() > 0) {
            Province province = l.get(0);
            province.setLastUpdatedAt(new Date());
            province.setDeletedAt(new Date());
            province.setStatus(Status.DELETE_STATUS);
            repository.save(province);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public Province getByName(String name) {
        List<Province> l = em.createQuery("from Province  x where x.province = " + name + " and x.status!=" + Status.DELETE_STATUS, Province.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with province : " + name);
        }
    }

    @Override
    public List<Province> getProvincesByCountryId(int id) {
        return em.createQuery("from Province  x where x.countryId = " + id + " and x.status!=" + Status.DELETE_STATUS, Province.class).getResultList();
    }
}

