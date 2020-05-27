package com.aradnab.boot.consumer.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.service.service_controller.SpecificationServiceInterface;
import com.aradnab.boot.db_tier.entity.Specification;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.SpecificationRepository;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpecificationService implements SpecificationServiceInterface {

    @Autowired
    private SpecificationRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Specification create(Specification specification) {
        specification.setSavedAt(new Date());
        specification.setLastUpdatedAt(new Date());
        specification.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(specification);
    }

    @Override
    public Specification update(Specification specification) {
        List<Specification> l = em.createQuery("from Specification  x where x.id = " + specification.getId() + " and x.status!=" + Status.DELETE_STATUS, Specification.class).getResultList();
        if (l.size() > 0) {
            specification.setLastUpdatedAt(new Date());
            return repository.save(specification);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + specification.getId());
        }
    }

    @Override
    public List<Specification> getAll() {
        return em.createQuery("from Specification  x where x.status!=" + Status.DELETE_STATUS, Specification.class).getResultList();
    }

    @Override
    public Specification getByID(int id) {
        List<Specification> l = em.createQuery("from Specification  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Specification.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public List<Specification> getByProductId(int productId) {
        return em.createQuery("from Specification  x where x.productId='"+productId+"' and x.status!=" + Status.DELETE_STATUS, Specification.class).getResultList();
    }


    @Override
    public CRUDStatus delete(int id) {
        List<Specification> l = em.createQuery("from Specification  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Specification.class).getResultList();
        if (l.size() > 0) {
            Specification specification = l.get(0);
            specification.setLastUpdatedAt(new Date());
            specification.setDeletedAt(new Date());
            specification.setStatus(Status.DELETE_STATUS);
            repository.save(specification);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

