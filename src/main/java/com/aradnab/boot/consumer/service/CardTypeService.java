package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.CardTypeServiceInterface;
import com.aradnab.boot.db_tier.entity.CardType;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.CardTypeRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CardTypeService implements CardTypeServiceInterface {

    @Autowired
    private CardTypeRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public CardType create(CardType cardType) {
        cardType.setSavedAt(new Date());
        cardType.setLastUpdatedAt(new Date());
        cardType.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(cardType);
    }

    @Override
    public CardType update(CardType cardType) {
        List<CardType> l = em.createQuery("from CardType  x where x.id = " + cardType.getId() + " and x.status!=" + Status.DELETE_STATUS, CardType.class).getResultList();
        if (l.size() > 0) {
            cardType.setLastUpdatedAt(new Date());
            return repository.save(cardType);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + cardType.getId());
        }
    }

    @Override
    public List<CardType> getAll() {
        return em.createQuery("from CardType  x where x.status!=" + Status.DELETE_STATUS, CardType.class).getResultList();
    }

    @Override
    public CardType getByID(int id) {
        List<CardType> l = em.createQuery("from CardType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, CardType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<CardType> l = em.createQuery("from CardType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, CardType.class).getResultList();
        if (l.size() > 0) {
            CardType cardType = l.get(0);
            cardType.setLastUpdatedAt(new Date());
            cardType.setDeletedAt(new Date());
            cardType.setStatus(Status.DELETE_STATUS);
            repository.save(cardType);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

