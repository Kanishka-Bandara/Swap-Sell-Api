package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.ProductImageServiceInterface;
import com.aradnab.boot.db_tier.entity.ProductImage;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductImageRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductImageService implements ProductImageServiceInterface {

    @Autowired
    private ProductImageRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ProductImage create(ProductImage productImage) {
        productImage.setSavedAt(new Date());
        productImage.setLastUpdatedAt(new Date());
        productImage.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(productImage);
    }

    @Override
    public ProductImage update(ProductImage productImage) {
        List<ProductImage> l = em.createQuery("from ProductImage  x where x.id = " + productImage.getId() + " and x.status!=" + Status.DELETE_STATUS, ProductImage.class).getResultList();
        if (l.size() > 0) {
            productImage.setLastUpdatedAt(new Date());
            return repository.save(productImage);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + productImage.getId());
        }
    }

    @Override
    public List<ProductImage> getAll() {
        return em.createQuery("from ProductImage  x where x.status!=" + Status.DELETE_STATUS, ProductImage.class).getResultList();
    }

    @Override
    public ProductImage getByID(int id) {
        List<ProductImage> l = em.createQuery("from ProductImage  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductImage.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ProductImage> l = em.createQuery("from ProductImage  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ProductImage.class).getResultList();
        if (l.size() > 0) {
            ProductImage productImage = l.get(0);
            productImage.setLastUpdatedAt(new Date());
            productImage.setDeletedAt(new Date());
            productImage.setStatus(Status.DELETE_STATUS);
            repository.save(productImage);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

