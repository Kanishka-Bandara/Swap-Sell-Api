package com.aradnab.boot.consumer.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.ProductSubCategory;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductSubCategoryRepository;
import com.aradnab.boot.general.service.service_controller.ProductSubCategoryServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductSubCategoryService implements ProductSubCategoryServiceInterface {

    @Autowired
    private ProductSubCategoryRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ProductSubCategory create(ProductSubCategory productSubCategory) {
        productSubCategory.setSavedAt(new Date());
        productSubCategory.setLastUpdatedAt(new Date());
        productSubCategory.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(productSubCategory);
    }

    @Override
    public ProductSubCategory update(ProductSubCategory productSubCategory) {
        List<ProductSubCategory> l = em.createQuery("from ProductSubCategory  x where x.id = " + productSubCategory.getId() + " and x.status!=" + Status.DELETE_STATUS, ProductSubCategory.class).getResultList();
        if (l.size() > 0) {
            productSubCategory.setLastUpdatedAt(new Date());
            return repository.save(productSubCategory);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + productSubCategory.getId());
        }
    }

    @Override
    public List<ProductSubCategory> getAll() {
        return em.createQuery("from ProductSubCategory  x where x.status!=" + Status.DELETE_STATUS, ProductSubCategory.class).getResultList();
    }

    @Override
    public ProductSubCategory getByID(int id) {
        List<ProductSubCategory> l = em.createQuery("from ProductSubCategory  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductSubCategory.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ProductSubCategory> l = em.createQuery("from ProductSubCategory  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductSubCategory.class).getResultList();
        if (l.size() > 0) {
            ProductSubCategory productSubCategory = l.get(0);
            productSubCategory.setLastUpdatedAt(new Date());
            productSubCategory.setDeletedAt(new Date());
            productSubCategory.setStatus(Status.DELETE_STATUS);
            repository.save(productSubCategory);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

