package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.CurrencyServiceInterface;
import com.aradnab.boot.db_tier.entity.Currency;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.CurrencyRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CurrencyService implements CurrencyServiceInterface {

    @Autowired
    private CurrencyRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Currency create(Currency currency) {
        currency.setSavedAt(new Date());
        currency.setLastUpdatedAt(new Date());
        currency.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(currency);
    }

    @Override
    public Currency update(Currency currency) {
        List<Currency> l = em.createQuery("from Currency  x where x.id = " + currency.getId() + " and x.status!=" + Status.DELETE_STATUS, Currency.class).getResultList();
        if (l.size() > 0) {
            currency.setLastUpdatedAt(new Date());
            return repository.save(currency);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + currency.getId());
        }
    }

    @Override
    public List<Currency> getAll() {
        return em.createQuery("from Currency  x where x.status!=" + Status.DELETE_STATUS, Currency.class).getResultList();
    }

    @Override
    public Currency getByID(int id) {
        List<Currency> l = em.createQuery("from Currency  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Currency.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public Currency getByCurrency(String currency) {
        List<Currency> l = em.createQuery("from Currency  x where x.currency = '" + currency + "' and x.status!=" + Status.DELETE_STATUS, Currency.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Currency> l = em.createQuery("from Currency  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Currency.class).getResultList();
        if (l.size() > 0) {
            Currency currency = l.get(0);
            currency.setLastUpdatedAt(new Date());
            currency.setDeletedAt(new Date());
            currency.setStatus(Status.DELETE_STATUS);
            repository.save(currency);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

