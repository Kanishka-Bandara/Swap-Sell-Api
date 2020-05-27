package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.ProductHeadCategoryServiceInterface;
import com.aradnab.boot.db_tier.entity.ProductHeadCategory;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductHeadCategoryRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductHeadCategoryService implements ProductHeadCategoryServiceInterface {

    @Autowired
    private ProductHeadCategoryRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ProductHeadCategory create(ProductHeadCategory productHeadCategory) {
        productHeadCategory.setSavedAt(new Date());
        productHeadCategory.setLastUpdatedAt(new Date());
        productHeadCategory.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(productHeadCategory);
    }

    @Override
    public ProductHeadCategory update(ProductHeadCategory productHeadCategory) {
        List<ProductHeadCategory> l = em.createQuery("from ProductHeadCategory  x where x.id = " + productHeadCategory.getId() + " and x.status!=" + Status.DELETE_STATUS, ProductHeadCategory.class).getResultList();
        if (l.size() > 0) {
            productHeadCategory.setLastUpdatedAt(new Date());
            return repository.save(productHeadCategory);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + productHeadCategory.getId());
        }
    }

    @Override
    public List<ProductHeadCategory> getAll() {
        return em.createQuery("from ProductHeadCategory  x where x.status!=" + Status.DELETE_STATUS, ProductHeadCategory.class).getResultList();
    }

    @Override
    public ProductHeadCategory getByID(int id) {
        List<ProductHeadCategory> l = em.createQuery("from ProductHeadCategory  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductHeadCategory.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public ProductHeadCategory getByName(String name) {
        List<ProductHeadCategory> l = em.createQuery("from ProductHeadCategory  x where x.categoryName = '" + name + "' and x.status!=" + Status.DELETE_STATUS, ProductHeadCategory.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }
    @Override
    public CRUDStatus delete(int id) {
        List<ProductHeadCategory> l = em.createQuery("from ProductHeadCategory  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductHeadCategory.class).getResultList();
        if (l.size() > 0) {
            ProductHeadCategory productHeadCategory = l.get(0);
            productHeadCategory.setLastUpdatedAt(new Date());
            productHeadCategory.setDeletedAt(new Date());
            productHeadCategory.setStatus(Status.DELETE_STATUS);
            repository.save(productHeadCategory);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

