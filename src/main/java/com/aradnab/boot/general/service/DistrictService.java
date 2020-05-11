package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.District;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.DistrictRepository;
import com.aradnab.boot.general.service.service_controller.DistrictServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DistrictService implements DistrictServiceInterface {

    @Autowired
    private DistrictRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public District create(District district) {
        district.setSavedAt(new Date());
        district.setLastUpdatedAt(new Date());
        district.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(district);
    }

    @Override
    public District update(District district) {
        List<District> l = em.createQuery("from District  x where x.id = " + district.getId() + " and x.status!=" + Status.DELETE_STATUS, District.class).getResultList();
        if (l.size() > 0) {
            district.setLastUpdatedAt(new Date());
            return repository.save(district);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + district.getId());
        }
    }

    @Override
    public List<District> getAll() {
        return em.createQuery("from District  x where x.status!=" + Status.DELETE_STATUS, District.class).getResultList();
    }

    @Override
    public District getByID(int id) {
        List<District> l = em.createQuery("from District  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, District.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<District> l = em.createQuery("from District  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, District.class).getResultList();
        if (l.size() > 0) {
            District district = l.get(0);
            district.setLastUpdatedAt(new Date());
            district.setDeletedAt(new Date());
            district.setStatus(Status.DELETE_STATUS);
            repository.save(district);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public District getByName(String name) {
        List<District> l = em.createQuery("from District  x where x.district = '" + name + "' and x.status!=" + Status.DELETE_STATUS, District.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with district name : " + name);
        }
    }
}

