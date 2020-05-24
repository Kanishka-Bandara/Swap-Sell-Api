package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.DeliveryProductServiceInterface;
import com.aradnab.boot.db_tier.entity.DeliveryProduct;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.DeliveryProductRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DeliveryProductService implements DeliveryProductServiceInterface {

    @Autowired
    private DeliveryProductRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public DeliveryProduct create(DeliveryProduct deliveryProduct) {
        deliveryProduct.setSavedAt(new Date());
        deliveryProduct.setLastUpdatedAt(new Date());
        deliveryProduct.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(deliveryProduct);
    }

    @Override
    public DeliveryProduct update(DeliveryProduct deliveryProduct) {
        List<DeliveryProduct> l = em.createQuery("from DeliveryProduct  x where x.id = " + deliveryProduct.getId() + " and x.status!=" + Status.DELETE_STATUS, DeliveryProduct.class).getResultList();
        if (l.size() > 0) {
            deliveryProduct.setLastUpdatedAt(new Date());
            return repository.save(deliveryProduct);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + deliveryProduct.getId());
        }
    }

    @Override
    public List<DeliveryProduct> getAll() {
        return em.createQuery("from DeliveryProduct  x where x.status!=" + Status.DELETE_STATUS, DeliveryProduct.class).getResultList();
    }

    @Override
    public DeliveryProduct getByID(int id) {
        List<DeliveryProduct> l = em.createQuery("from DeliveryProduct  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, DeliveryProduct.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<DeliveryProduct> l = em.createQuery("from DeliveryProduct  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, DeliveryProduct.class).getResultList();
        if (l.size() > 0) {
            DeliveryProduct deliveryProduct = l.get(0);
            deliveryProduct.setLastUpdatedAt(new Date());
            deliveryProduct.setDeletedAt(new Date());
            deliveryProduct.setStatus(Status.DELETE_STATUS);
            repository.save(deliveryProduct);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

