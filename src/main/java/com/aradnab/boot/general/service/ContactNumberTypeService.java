package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.ContactNumberType;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ContactNumberTypeRepository;
import com.aradnab.boot.general.service.service_controller.ContactNumberTypeServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContactNumberTypeService implements ContactNumberTypeServiceInterface {

    @Autowired
    private ContactNumberTypeRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ContactNumberType create(ContactNumberType contactNumberType) {
        contactNumberType.setSavedAt(new Date());
        contactNumberType.setLastUpdatedAt(new Date());
        contactNumberType.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(contactNumberType);
    }

    @Override
    public ContactNumberType update(ContactNumberType contactNumberType) {
        List<ContactNumberType> l = em.createQuery("from ContactNumberType  x where x.id = " + contactNumberType.getId() + " and x.status!=" + Status.DELETE_STATUS, ContactNumberType.class).getResultList();
        if (l.size() > 0) {
            contactNumberType.setLastUpdatedAt(new Date());
            return repository.save(contactNumberType);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + contactNumberType.getId());
        }
    }

    @Override
    public List<ContactNumberType> getAll() {
        return em.createQuery("from ContactNumberType  x where x.status!=" + Status.DELETE_STATUS, ContactNumberType.class).getResultList();
    }

    @Override
    public ContactNumberType getByID(int id) {
        List<ContactNumberType> l = em.createQuery("from ContactNumberType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ContactNumberType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ContactNumberType> l = em.createQuery("from ContactNumberType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ContactNumberType.class).getResultList();
        if (l.size() > 0) {
            ContactNumberType contactNumberType = l.get(0);
            contactNumberType.setLastUpdatedAt(new Date());
            contactNumberType.setDeletedAt(new Date());
            contactNumberType.setStatus(Status.DELETE_STATUS);
            repository.save(contactNumberType);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public ContactNumberType getByName(String name) {
        List<ContactNumberType> l = em.createQuery("from ContactNumberType  x where x.type = " + name + " and x.status!=" + Status.DELETE_STATUS, ContactNumberType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with name : " + name);
        }
    }
}

