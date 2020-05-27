package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.ProductDealingTypeServiceInterface;
import com.aradnab.boot.db_tier.entity.ProductDealingType;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductDealingTypeRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductDealingTypeService implements ProductDealingTypeServiceInterface {

    @Autowired
    private ProductDealingTypeRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ProductDealingType create(ProductDealingType productDealingType) {
        productDealingType.setSavedAt(new Date());
        productDealingType.setLastUpdatedAt(new Date());
        productDealingType.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(productDealingType);
    }

    @Override
    public ProductDealingType update(ProductDealingType productDealingType) {
        List<ProductDealingType> l = em.createQuery("from ProductDealingType  x where x.id = " + productDealingType.getId() + " and x.status!=" + Status.DELETE_STATUS, ProductDealingType.class).getResultList();
        if (l.size() > 0) {
            productDealingType.setLastUpdatedAt(new Date());
            return repository.save(productDealingType);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + productDealingType.getId());
        }
    }

    @Override
    public List<ProductDealingType> getAll() {
        return em.createQuery("from ProductDealingType  x where x.status!=" + Status.DELETE_STATUS, ProductDealingType.class).getResultList();
    }

    @Override
    public ProductDealingType getByID(int id) {
        List<ProductDealingType> l = em.createQuery("from ProductDealingType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductDealingType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public ProductDealingType getByName(String type) {
        List<ProductDealingType> l = em.createQuery("from ProductDealingType  x where x.type = '" + type + "' and x.status!=" + Status.DELETE_STATUS, ProductDealingType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with dealing type : " + type);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ProductDealingType> l = em.createQuery("from ProductDealingType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductDealingType.class).getResultList();
        if (l.size() > 0) {
            ProductDealingType productDealingType = l.get(0);
            productDealingType.setLastUpdatedAt(new Date());
            productDealingType.setDeletedAt(new Date());
            productDealingType.setStatus(Status.DELETE_STATUS);
            repository.save(productDealingType);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

