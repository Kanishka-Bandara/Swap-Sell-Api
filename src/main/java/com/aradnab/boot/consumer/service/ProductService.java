package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.ProductServiceInterface;
import com.aradnab.boot.db_tier.entity.Product;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ProductRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Product create(Product product) {
        product.setSavedAt(new Date());
        product.setLastUpdatedAt(new Date());
        product.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        List<Product> l = em.createQuery("from Product  x where x.id = " + product.getId() + " and x.status!=" + Status.DELETE_STATUS, Product.class).getResultList();
        if (l.size() > 0) {
            product.setLastUpdatedAt(new Date());
            return repository.save(product);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + product.getId());
        }
    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("from Product  x where x.status!=" + Status.DELETE_STATUS, Product.class).getResultList();
    }

    @Override
    public Product getByID(int id) {
        List<Product> l = em.createQuery("from Product  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Product.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Product> l = em.createQuery("from Product  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Product.class).getResultList();
        if (l.size() > 0) {
            Product product = l.get(0);
            product.setLastUpdatedAt(new Date());
            product.setDeletedAt(new Date());
            product.setStatus(Status.DELETE_STATUS);
            repository.save(product);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

