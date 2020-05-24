package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.DiscountServiceInterface;
import com.aradnab.boot.db_tier.entity.Discount;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.DiscountRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DiscountService implements DiscountServiceInterface {

    @Autowired
    private DiscountRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Discount create(Discount discount) {
        discount.setSavedAt(new Date());
        discount.setLastUpdatedAt(new Date());
        discount.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(discount);
    }

    @Override
    public Discount update(Discount discount) {
        List<Discount> l = em.createQuery("from Discount  x where x.id = " + discount.getId() + " and x.status!=" + Status.DELETE_STATUS, Discount.class).getResultList();
        if (l.size() > 0) {
            discount.setLastUpdatedAt(new Date());
            return repository.save(discount);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + discount.getId());
        }
    }

    @Override
    public List<Discount> getAll() {
        return em.createQuery("from Discount  x where x.status!=" + Status.DELETE_STATUS, Discount.class).getResultList();
    }

    @Override
    public Discount getByID(int id) {
        List<Discount> l = em.createQuery("from Discount  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Discount.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Discount> l = em.createQuery("from Discount  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Discount.class).getResultList();
        if (l.size() > 0) {
            Discount discount = l.get(0);
            discount.setLastUpdatedAt(new Date());
            discount.setDeletedAt(new Date());
            discount.setStatus(Status.DELETE_STATUS);
            repository.save(discount);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

