package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.ProductMainCategoryServiceInterface;
import com.aradnab.boot.db_tier.entity.ProductMainCategory;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductMainCategoryRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductMainCategoryService implements ProductMainCategoryServiceInterface {

    @Autowired
    private ProductMainCategoryRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ProductMainCategory create(ProductMainCategory productMainCategory) {
        productMainCategory.setSavedAt(new Date());
        productMainCategory.setLastUpdatedAt(new Date());
        productMainCategory.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(productMainCategory);
    }

    @Override
    public ProductMainCategory update(ProductMainCategory productMainCategory) {
        List<ProductMainCategory> l = em.createQuery("from ProductMainCategory  x where x.id = " + productMainCategory.getId() + " and x.status!=" + Status.DELETE_STATUS, ProductMainCategory.class).getResultList();
        if (l.size() > 0) {
            productMainCategory.setLastUpdatedAt(new Date());
            return repository.save(productMainCategory);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + productMainCategory.getId());
        }
    }

    @Override
    public List<ProductMainCategory> getAll() {
        return em.createQuery("from ProductMainCategory  x where x.status!=" + Status.DELETE_STATUS, ProductMainCategory.class).getResultList();
    }

    @Override
    public ProductMainCategory getByID(int id) {
        List<ProductMainCategory> l = em.createQuery("from ProductMainCategory  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductMainCategory.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ProductMainCategory> l = em.createQuery("from ProductMainCategory  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductMainCategory.class).getResultList();
        if (l.size() > 0) {
            ProductMainCategory productMainCategory = l.get(0);
            productMainCategory.setLastUpdatedAt(new Date());
            productMainCategory.setDeletedAt(new Date());
            productMainCategory.setStatus(Status.DELETE_STATUS);
            repository.save(productMainCategory);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

