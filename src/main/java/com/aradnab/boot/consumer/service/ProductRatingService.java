package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.ProductRatingServiceInterface;
import com.aradnab.boot.db_tier.entity.ProductRating;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductRatingRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductRatingService implements ProductRatingServiceInterface {

    @Autowired
    private ProductRatingRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ProductRating create(ProductRating productRating) {
        productRating.setSavedAt(new Date());
        productRating.setLastUpdatedAt(new Date());
        productRating.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(productRating);
    }

    @Override
    public ProductRating update(ProductRating productRating) {
        List<ProductRating> l = em.createQuery("from ProductRating  x where x.id = " + productRating.getId() + " and x.status!=" + Status.DELETE_STATUS, ProductRating.class).getResultList();
        if (l.size() > 0) {
            productRating.setLastUpdatedAt(new Date());
            return repository.save(productRating);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + productRating.getId());
        }
    }

    @Override
    public List<ProductRating> getAll() {
        return em.createQuery("from ProductRating  x where x.status!=" + Status.DELETE_STATUS, ProductRating.class).getResultList();
    }

    @Override
    public ProductRating getByID(int id) {
        List<ProductRating> l = em.createQuery("from ProductRating  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductRating.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ProductRating> l = em.createQuery("from ProductRating  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductRating.class).getResultList();
        if (l.size() > 0) {
            ProductRating productRating = l.get(0);
            productRating.setLastUpdatedAt(new Date());
            productRating.setDeletedAt(new Date());
            productRating.setStatus(Status.DELETE_STATUS);
            repository.save(productRating);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

