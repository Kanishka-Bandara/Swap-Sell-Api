package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Notification;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.NotificationRepository;
import com.aradnab.boot.general.service.service_controller.NotificationServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotificationService implements NotificationServiceInterface {

    @Autowired
    private NotificationRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Notification create(Notification notification) {
        notification.setSavedAt(new Date());
        notification.setLastUpdatedAt(new Date());
        notification.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(notification);
    }

    @Override
    public Notification update(Notification notification) {
        List<Notification> l = em.createQuery("from Notification  x where x.id = " + notification.getId() + " and x.status!=" + Status.DELETE_STATUS, Notification.class).getResultList();
        if (l.size() > 0) {
            notification.setLastUpdatedAt(new Date());
            return repository.save(notification);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + notification.getId());
        }
    }

    @Override
    public List<Notification> getAll() {
        return em.createQuery("from Notification  x where x.status!=" + Status.DELETE_STATUS, Notification.class).getResultList();
    }

    @Override
    public Notification getByID(int id) {
        List<Notification> l = em.createQuery("from Notification  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Notification.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Notification> l = em.createQuery("from Notification  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Notification.class).getResultList();
        if (l.size() > 0) {
            Notification notification = l.get(0);
            notification.setLastUpdatedAt(new Date());
            notification.setDeletedAt(new Date());
            notification.setStatus(Status.DELETE_STATUS);
            repository.save(notification);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

