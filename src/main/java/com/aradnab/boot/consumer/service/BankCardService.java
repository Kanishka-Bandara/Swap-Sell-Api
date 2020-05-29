package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.BankCardServiceInterface;
import com.aradnab.boot.db_tier.entity.BankCard;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.BankCardRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BankCardService implements BankCardServiceInterface {

    @Autowired
    private BankCardRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public BankCard create(BankCard bankCard) {
        bankCard.setSavedAt(new Date());
        bankCard.setLastUpdatedAt(new Date());
        bankCard.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(bankCard);
    }

    @Override
    public BankCard update(BankCard bankCard) {
        List<BankCard> l = em.createQuery("from BankCard  x where x.id = " + bankCard.getId() + " and x.status!=" + Status.DELETE_STATUS, BankCard.class).getResultList();
        if (l.size() > 0) {
            bankCard.setLastUpdatedAt(new Date());
            return repository.save(bankCard);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + bankCard.getId());
        }
    }

    @Override
    public List<BankCard> getAll() {
        return em.createQuery("from BankCard  x where x.status!=" + Status.DELETE_STATUS, BankCard.class).getResultList();
    }

    @Override
    public BankCard getByID(int id) {
        List<BankCard> l = em.createQuery("from BankCard  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, BankCard.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public List<BankCard> getByUserId(int userId) {
        return em.createQuery("from BankCard  x where x.userId='" + userId + "' and x.status!=" + Status.DELETE_STATUS, BankCard.class).getResultList();
    }

    @Override
    public CRUDStatus delete(int id) {
        List<BankCard> l = em.createQuery("from BankCard  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, BankCard.class).getResultList();
        if (l.size() > 0) {
            BankCard bankCard = l.get(0);
            bankCard.setLastUpdatedAt(new Date());
            bankCard.setDeletedAt(new Date());
            bankCard.setStatus(Status.DELETE_STATUS);
            repository.save(bankCard);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

