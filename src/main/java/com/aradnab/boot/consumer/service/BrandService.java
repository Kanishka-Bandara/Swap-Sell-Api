package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.BrandServiceInterface;
import com.aradnab.boot.db_tier.entity.Brand;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.BrandRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BrandService implements BrandServiceInterface {

    @Autowired
    private BrandRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Brand create(Brand brand) {
        brand.setSavedAt(new Date());
        brand.setLastUpdatedAt(new Date());
        brand.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        List<Brand> l = em.createQuery("from Brand  x where x.id = " + brand.getId() + " and x.status!=" + Status.DELETE_STATUS, Brand.class).getResultList();
        if (l.size() > 0) {
            brand.setLastUpdatedAt(new Date());
            return repository.save(brand);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + brand.getId());
        }
    }

    @Override
    public List<Brand> getAll() {
        return em.createQuery("from Brand  x where x.status!=" + Status.DELETE_STATUS, Brand.class).getResultList();
    }

    @Override
    public Brand getByID(int id) {
        List<Brand> l = em.createQuery("from Brand  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Brand.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Brand> l = em.createQuery("from Brand  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Brand.class).getResultList();
        if (l.size() > 0) {
            Brand brand = l.get(0);
            brand.setLastUpdatedAt(new Date());
            brand.setDeletedAt(new Date());
            brand.setStatus(Status.DELETE_STATUS);
            repository.save(brand);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

