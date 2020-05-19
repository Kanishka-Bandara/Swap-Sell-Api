package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Shop;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ShopRepository;
import com.aradnab.boot.general.service.service_controller.ShopServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShopService implements ShopServiceInterface {

    @Autowired
    private ShopRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Shop create(Shop shop) {
        shop.setSavedAt(new Date());
        shop.setLastUpdatedAt(new Date());
        shop.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(shop);
    }

    @Override
    public Shop update(Shop shop) {
        List<Shop> l = em.createQuery("from Shop  x where x.id = " + shop.getId() + " and x.status!=" + Status.DELETE_STATUS, Shop.class).getResultList();
        if (l.size() > 0) {
            shop.setLastUpdatedAt(new Date());
            return repository.save(shop);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + shop.getId());
        }
    }

    @Override
    public List<Shop> getAll() {
        return em.createQuery("from Shop  x where x.status!=" + Status.DELETE_STATUS, Shop.class).getResultList();
    }

    @Override
    public Shop getByID(int id) {
        List<Shop> l = em.createQuery("from Shop  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Shop.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Shop> l = em.createQuery("from Shop  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Shop.class).getResultList();
        if (l.size() > 0) {
            Shop shop = l.get(0);
            shop.setLastUpdatedAt(new Date());
            shop.setDeletedAt(new Date());
            shop.setStatus(Status.DELETE_STATUS);
            repository.save(shop);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public Shop getByUserID(int userId) {
        List<Shop> l = em.createQuery("from Shop  x where x.userId = " + userId + " and x.status!=" + Status.DELETE_STATUS, Shop.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with user id : " + userId);
        }
    }
}

