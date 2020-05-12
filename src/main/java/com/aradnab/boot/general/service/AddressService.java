package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Address;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.AddressRepository;
import com.aradnab.boot.general.service.service_controller.AddressServiceInterface;
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
public class AddressService implements AddressServiceInterface {

    @Autowired
    private AddressRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Address create(Address address) {
        address.setSavedAt(new Date());
        address.setLastUpdatedAt(new Date());
        address.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(address);
    }

    @Override
    public Address update(Address address) {
        List<Address> l = em.createQuery("from Address  x where x.id = " + address.getId() + " and x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
        if (l.size() > 0) {
            address.setLastUpdatedAt(new Date());
            return repository.save(address);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + address.getId());
        }
    }

    @Override
    public List<Address> getAll() {
        return em.createQuery("from Address  x where x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
    }

    @Override
    public Address getByID(int id) {
        List<Address> l = em.createQuery("from Address  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Address> l = em.createQuery("from Address  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
        if (l.size() > 0) {
            Address address = l.get(0);
            address.setLastUpdatedAt(new Date());
            address.setDeletedAt(new Date());
            address.setStatus(Status.DELETE_STATUS);
            repository.save(address);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public List<Address> getByTypeId(int typeId, int userId) {
        return em.createQuery("from Address  x where x.addressTypeId = " + typeId + " and x.userId = "+userId +" and x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
    }

    @Override
    public Address getDefaultByTypeId(int typeId, int userId) {
        List<Address> l = em.createQuery("from Address  x where x.addressTypeId = " + typeId + " and x.userId = "+userId +" and x.isDefault = "+1+" and x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + typeId);
        }
    }

    @Override
    public List<Address> getByUserId(int userId) {
        return em.createQuery("from Address  x where x.userId = "+userId +" and x.isDefault = "+1+" and x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
    }

    @Override
    public Collection<Address> getByUserIdAsCollection(int userId) {
        return em.createQuery("from Address  x where x.userId = "+userId +" and x.isDefault = "+1+" and x.status!=" + Status.DELETE_STATUS, Address.class).getResultList();
    }
}

