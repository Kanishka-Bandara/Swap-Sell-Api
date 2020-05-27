package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.ProductConditionServiceInterface;
import com.aradnab.boot.db_tier.entity.ProductCondition;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductConditionRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductConditionService implements ProductConditionServiceInterface {

    @Autowired
    private ProductConditionRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ProductCondition create(ProductCondition productCondition) {
        productCondition.setSavedAt(new Date());
        productCondition.setLastUpdatedAt(new Date());
        productCondition.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(productCondition);
    }

    @Override
    public ProductCondition update(ProductCondition productCondition) {
        List<ProductCondition> l = em.createQuery("from ProductCondition  x where x.id = " + productCondition.getId() + " and x.status!=" + Status.DELETE_STATUS, ProductCondition.class).getResultList();
        if (l.size() > 0) {
            productCondition.setLastUpdatedAt(new Date());
            return repository.save(productCondition);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + productCondition.getId());
        }
    }

    @Override
    public List<ProductCondition> getAll() {
        return em.createQuery("from ProductCondition  x where x.status!=" + Status.DELETE_STATUS, ProductCondition.class).getResultList();
    }

    @Override
    public ProductCondition getByID(int id) {
        List<ProductCondition> l = em.createQuery("from ProductCondition  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductCondition.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public ProductCondition getByName(String condition) {
        List<ProductCondition> l = em.createQuery("from ProductCondition  x where x.condition = '" + condition + "' and x.status!=" + Status.DELETE_STATUS, ProductCondition.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with product condition : " + condition);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ProductCondition> l = em.createQuery("from ProductCondition  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductCondition.class).getResultList();
        if (l.size() > 0) {
            ProductCondition productCondition = l.get(0);
            productCondition.setLastUpdatedAt(new Date());
            productCondition.setDeletedAt(new Date());
            productCondition.setStatus(Status.DELETE_STATUS);
            repository.save(productCondition);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

