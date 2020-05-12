package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.PostOfficeBox;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.PostOfficeBoxRepository;
import com.aradnab.boot.general.service.service_controller.PostOfficeBoxServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostOfficeBoxService implements PostOfficeBoxServiceInterface {

    @Autowired
    private PostOfficeBoxRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public PostOfficeBox create(PostOfficeBox postOfficeBox) {
        postOfficeBox.setSavedAt(new Date());
        postOfficeBox.setLastUpdatedAt(new Date());
        postOfficeBox.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(postOfficeBox);
    }

    @Override
    public PostOfficeBox update(PostOfficeBox postOfficeBox) {
        List<PostOfficeBox> l = em.createQuery("from PostOfficeBox  x where x.id = " + postOfficeBox.getId() + " and x.status!=" + Status.DELETE_STATUS, PostOfficeBox.class).getResultList();
        if (l.size() > 0) {
            postOfficeBox.setLastUpdatedAt(new Date());
            return repository.save(postOfficeBox);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + postOfficeBox.getId());
        }
    }

    @Override
    public List<PostOfficeBox> getAll() {
        return em.createQuery("from PostOfficeBox  x where x.status!=" + Status.DELETE_STATUS, PostOfficeBox.class).getResultList();
    }

    @Override
    public PostOfficeBox getByID(int id) {
        List<PostOfficeBox> l = em.createQuery("from PostOfficeBox  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, PostOfficeBox.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<PostOfficeBox> l = em.createQuery("from PostOfficeBox  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, PostOfficeBox.class).getResultList();
        if (l.size() > 0) {
            PostOfficeBox postOfficeBox = l.get(0);
            postOfficeBox.setLastUpdatedAt(new Date());
            postOfficeBox.setDeletedAt(new Date());
            postOfficeBox.setStatus(Status.DELETE_STATUS);
            repository.save(postOfficeBox);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public PostOfficeBox getByPostalCode(String code) {
        List<PostOfficeBox> l = em.createQuery("from PostOfficeBox  x where x.postalCode = " + code + " and x.status!=" + Status.DELETE_STATUS, PostOfficeBox.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with postal code : " + code);
        }
    }
}

