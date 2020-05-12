package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.EmailType;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.EmailTypeRepository;
import com.aradnab.boot.general.service.service_controller.EmailTypeServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmailTypeService implements EmailTypeServiceInterface {

    @Autowired
    private EmailTypeRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public EmailType create(EmailType emailType) {
        emailType.setSavedAt(new Date());
        emailType.setLastUpdatedAt(new Date());
        emailType.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(emailType);
    }

    @Override
    public EmailType update(EmailType emailType) {
        List<EmailType> l = em.createQuery("from EmailType  x where x.id = " + emailType.getId() + " and x.status!=" + Status.DELETE_STATUS, EmailType.class).getResultList();
        if (l.size() > 0) {
            emailType.setLastUpdatedAt(new Date());
            return repository.save(emailType);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + emailType.getId());
        }
    }

    @Override
    public List<EmailType> getAll() {
        return em.createQuery("from EmailType  x where x.status!=" + Status.DELETE_STATUS, EmailType.class).getResultList();
    }

    @Override
    public EmailType getByID(int id) {
        List<EmailType> l = em.createQuery("from EmailType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, EmailType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<EmailType> l = em.createQuery("from EmailType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, EmailType.class).getResultList();
        if (l.size() > 0) {
            EmailType emailType = l.get(0);
            emailType.setLastUpdatedAt(new Date());
            emailType.setDeletedAt(new Date());
            emailType.setStatus(Status.DELETE_STATUS);
            repository.save(emailType);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public EmailType getByName(String name) {
        List<EmailType> l = em.createQuery("from EmailType  x where x.type = '" + name + "' and x.status!=" + Status.DELETE_STATUS, EmailType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with name : " + name);
        }
    }
}

