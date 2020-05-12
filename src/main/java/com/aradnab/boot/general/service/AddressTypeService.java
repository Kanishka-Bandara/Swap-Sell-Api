package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.AddressType;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.AddressTypeRepository;
import com.aradnab.boot.general.service.service_controller.AddressTypeServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AddressTypeService implements AddressTypeServiceInterface {

    @Autowired
    private AddressTypeRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public AddressType create(AddressType addressType) {
        addressType.setSavedAt(new Date());
        addressType.setLastUpdatedAt(new Date());
        addressType.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(addressType);
    }

    @Override
    public AddressType update(AddressType addressType) {
        List<AddressType> l = em.createQuery("from AddressType  x where x.id = " + addressType.getId() + " and x.status!=" + Status.DELETE_STATUS, AddressType.class).getResultList();
        if (l.size() > 0) {
            addressType.setLastUpdatedAt(new Date());
            return repository.save(addressType);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + addressType.getId());
        }
    }

    @Override
    public List<AddressType> getAll() {
        return em.createQuery("from AddressType  x where x.status!=" + Status.DELETE_STATUS, AddressType.class).getResultList();
    }

    @Override
    public AddressType getByID(int id) {
        List<AddressType> l = em.createQuery("from AddressType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, AddressType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<AddressType> l = em.createQuery("from AddressType  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, AddressType.class).getResultList();
        if (l.size() > 0) {
            AddressType addressType = l.get(0);
            addressType.setLastUpdatedAt(new Date());
            addressType.setDeletedAt(new Date());
            addressType.setStatus(Status.DELETE_STATUS);
            repository.save(addressType);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public AddressType getByName(String name) {
        System.out.println(name);
        List<AddressType> l = em.createQuery("from AddressType  x where x.type = '" + name + "' and x.status!=" + Status.DELETE_STATUS, AddressType.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with name : " + name);
        }
    }
}