package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.ContactNumber;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ContactNumberRepository;
import com.aradnab.boot.general.service.service_controller.ContactNumberServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContactNumberService implements ContactNumberServiceInterface {

    @Autowired
    private ContactNumberRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public ContactNumber create(ContactNumber contactNumber) {
        contactNumber.setSavedAt(new Date());
        contactNumber.setLastUpdatedAt(new Date());
        contactNumber.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(contactNumber);
    }

    @Override
    public ContactNumber update(ContactNumber contactNumber) {
        List<ContactNumber> l = em.createQuery("from ContactNumber  x where x.id = " + contactNumber.getId() + " and x.status!=" + Status.DELETE_STATUS, ContactNumber.class).getResultList();
        if (l.size() > 0) {
            contactNumber.setLastUpdatedAt(new Date());
            return repository.save(contactNumber);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + contactNumber.getId());
        }
    }

    @Override
    public List<ContactNumber> getAll() {
        return em.createQuery("from ContactNumber  x where x.status!=" + Status.DELETE_STATUS, ContactNumber.class).getResultList();
    }

    @Override
    public ContactNumber getByID(int id) {
        List<ContactNumber> l = em.createQuery("from ContactNumber  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ContactNumber.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<ContactNumber> l = em.createQuery("from ContactNumber  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, ContactNumber.class).getResultList();
        if (l.size() > 0) {
            ContactNumber contactNumber = l.get(0);
            contactNumber.setLastUpdatedAt(new Date());
            contactNumber.setDeletedAt(new Date());
            contactNumber.setStatus(Status.DELETE_STATUS);
            repository.save(contactNumber);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public List<ContactNumber> getByTypeId(int typeId, int userId) {
        return em.createQuery("from ContactNumber  x where x.contactNumberTypeId = " + typeId + " and x.userId = "+userId +" and x.status!=" + Status.DELETE_STATUS, ContactNumber.class).getResultList();
    }

    @Override
    public ContactNumber getDefaultByTypeId(int typeId, int userId) {
        List<ContactNumber> l = em.createQuery("from ContactNumber  x where x.contactNumberTypeId = " + typeId + " and x.userId = "+userId +" and x.isDefault = "+1+" and x.status!=" + Status.DELETE_STATUS, ContactNumber.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + typeId);
        }
    }

    @Override
    public List<ContactNumber> getByUserId(int userId) {
        return em.createQuery("from ContactNumber  x where  x.userId = "+userId +" and x.status!=" + Status.DELETE_STATUS, ContactNumber.class).getResultList();
    }

    @Override
    public Collection<ContactNumber> getByUserIdAsCollection(int userId) {
        return getByUserId(userId);
    }
}

