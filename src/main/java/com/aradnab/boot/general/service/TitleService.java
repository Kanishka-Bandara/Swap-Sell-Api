package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Title;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.TitleRepository;
import com.aradnab.boot.general.service.service_controller.TitleServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TitleService implements TitleServiceInterface {

    @Autowired
    private TitleRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Title create(Title title) {
        title.setSavedAt(new Date());
        title.setLastUpdatedAt(new Date());
        title.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(title);
    }

    @Override
    public Title update(Title title) {
        List<Title> l = em.createQuery("from Title  x where x.id = " + title.getId() + " and x.status!=" + Status.DELETE_STATUS, Title.class).getResultList();
        if (l.size() > 0) {
            title.setLastUpdatedAt(new Date());
            return repository.save(title);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + title.getId());
        }
    }

    @Override
    public List<Title> getAll() {
        return em.createQuery("from Title  x where x.status!=" + Status.DELETE_STATUS, Title.class).getResultList();
    }

    @Override
    public Title getByID(int id) {
        List<Title> l = em.createQuery("from Title  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Title.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Title> l = em.createQuery("from Title  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Title.class).getResultList();
        if (l.size() > 0) {
            Title title = l.get(0);
            title.setLastUpdatedAt(new Date());
            title.setDeletedAt(new Date());
            title.setStatus(Status.DELETE_STATUS);
            repository.save(title);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public Title getByTitleName(String title) {
        List<Title> l = em.createQuery("from Title  x where x.title = '" + title + "' and x.status!=" + Status.DELETE_STATUS, Title.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with title : " + title);
        }
    }
}

