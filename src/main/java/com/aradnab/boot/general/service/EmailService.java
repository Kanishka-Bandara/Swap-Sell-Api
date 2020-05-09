package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Email;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.EmailRepository;
import com.aradnab.boot.general.service.service_controller.EmailServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmailService implements EmailServiceInterface {

    @Autowired
    private EmailRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Email create(Email email) {
        email.setSavedAt(new Date());
        email.setLastUpdatedAt(new Date());
        email.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(email);
    }

    @Override
    public Email update(Email email) {
        List<Email> l = em.createQuery("from Email  x where x.id = " + email.getId() + " and x.status!=" + Status.DELETE_STATUS, Email.class).getResultList();
        if (l.size() > 0) {
            email.setLastUpdatedAt(new Date());
            return repository.save(email);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + email.getId());
        }
    }

    @Override
    public List<Email> getAll() {
        return em.createQuery("from Email  x where x.status!=" + Status.DELETE_STATUS, Email.class).getResultList();
    }

    @Override
    public Email getByID(int id) {
        List<Email> l = em.createQuery("from Email  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Email.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Email> l = em.createQuery("from Email  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Email.class).getResultList();
        if (l.size() > 0) {
            Email email = l.get(0);
            email.setLastUpdatedAt(new Date());
            email.setDeletedAt(new Date());
            email.setStatus(Status.DELETE_STATUS);
            repository.save(email);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public List<Email> getByTypeId(int typeId, int userId) {
        return em.createQuery("from Email  x where x.emailTypeId = " + typeId + " and x.userId = "+userId +" and x.status!=" + Status.DELETE_STATUS, Email.class).getResultList();
    }

    @Override
    public Email getDefaultByTypeId(int typeId, int userId) {
        List<Email> l = em.createQuery("from Email  x where x.emailTypeId = " + typeId + " and x.userId = "+userId +" and x.isDefault = "+1+" and x.status!=" + Status.DELETE_STATUS, Email.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + typeId);
        }
    }
}

