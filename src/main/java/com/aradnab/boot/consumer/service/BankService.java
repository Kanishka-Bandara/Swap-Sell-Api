package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.BankServiceInterface;
import com.aradnab.boot.db_tier.entity.Bank;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.BankRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BankService implements BankServiceInterface {

    @Autowired
    private BankRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Bank create(Bank bank) {
        bank.setSavedAt(new Date());
        bank.setLastUpdatedAt(new Date());
        bank.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(bank);
    }

    @Override
    public Bank update(Bank bank) {
        List<Bank> l = em.createQuery("from Bank  x where x.id = " + bank.getId() + " and x.status!=" + Status.DELETE_STATUS, Bank.class).getResultList();
        if (l.size() > 0) {
            bank.setLastUpdatedAt(new Date());
            return repository.save(bank);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + bank.getId());
        }
    }

    @Override
    public List<Bank> getAll() {
        return em.createQuery("from Bank  x where x.status!=" + Status.DELETE_STATUS, Bank.class).getResultList();
    }

    @Override
    public Bank getByID(int id) {
        List<Bank> l = em.createQuery("from Bank  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Bank.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Bank getByName(String name) {
        List<Bank> l = em.createQuery("from Bank  x where x.bankName = '" + name + "' and x.status!=" + Status.DELETE_STATUS, Bank.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Bank> l = em.createQuery("from Bank  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Bank.class).getResultList();
        if (l.size() > 0) {
            Bank bank = l.get(0);
            bank.setLastUpdatedAt(new Date());
            bank.setDeletedAt(new Date());
            bank.setStatus(Status.DELETE_STATUS);
            repository.save(bank);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

