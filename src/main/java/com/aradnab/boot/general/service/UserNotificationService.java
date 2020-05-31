package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.UserNotification;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.UserNotificationRepository;
import com.aradnab.boot.general.service.service_controller.UserNotificationServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
public class UserNotificationService implements UserNotificationServiceInterface {

    @Autowired
    private UserNotificationRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public UserNotification create(UserNotification userNotification) {
        userNotification.setSavedAt(new Date());
        userNotification.setLastUpdatedAt(new Date());
        userNotification.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(userNotification);
    }

    @Override
    public UserNotification update(UserNotification userNotification) {
        List<UserNotification> l = em.createQuery("from UserNotification  x where x.id = " + userNotification.getId() + " and x.status!=" + Status.DELETE_STATUS, UserNotification.class).getResultList();
        if (l.size() > 0) {
            userNotification.setLastUpdatedAt(new Date());
            return repository.save(userNotification);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + userNotification.getId());
        }
    }

    @Override
    public List<UserNotification> getAll() {
        return em.createQuery("from UserNotification  x where x.status!=" + Status.DELETE_STATUS, UserNotification.class).getResultList();
    }

    @Override
    public List<UserNotification> getByUserId(int userId) {
        return em.createQuery("from UserNotification  x where x.userId='" + userId + "' and x.status!=" + Status.DELETE_STATUS, UserNotification.class).getResultList();
    }

    @Override
    public UserNotification getByID(int id) {
        List<UserNotification> l = em.createQuery("from UserNotification  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, UserNotification.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public UserNotification setAsRead(int id) {
        List<UserNotification> l = em.createQuery("from UserNotification  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, UserNotification.class).getResultList();
        final Date d = new Date();
        if (l.size() > 0) {
            UserNotification userNotification = l.get(0);
            userNotification.setLastUpdatedAt(d);
            userNotification.setReadAt(d);
            userNotification.setIsRead((byte) 1);
            return repository.save(userNotification);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public List<UserNotification> setAsRead(List<Integer> ids) {
        List<UserNotification> l = new ArrayList<>();
       final Date d = new Date();
        ids.forEach(i -> {
            UserNotification userNotification = em.createQuery("from UserNotification  x where x.id = " + i + " and x.status!=" + Status.DELETE_STATUS, UserNotification.class).getSingleResult();
            userNotification.setLastUpdatedAt(d);
            userNotification.setReadAt(d);
            userNotification.setIsRead((byte) 1);
            UserNotification save = repository.save(userNotification);
            if (save != null) l.add(save);
        });
        return l;
    }

    @Override
    public boolean setAllAsRead(int userId) {
        AtomicBoolean status = new AtomicBoolean(true);
        final Date d = new Date();
        List<UserNotification> l = em.createQuery("from UserNotification  x where x.userId = " + userId + " and x.status!=" + Status.DELETE_STATUS, UserNotification.class).getResultList();
        l.forEach(userNotification -> {
            userNotification.setLastUpdatedAt(d);
            userNotification.setReadAt(d);
            userNotification.setIsRead((byte) 1);
            UserNotification save = repository.save(userNotification);
            if (save == null) status.set(false);
        });
        return status.get();
    }

    @Override
    public CRUDStatus delete(int id) {
        List<UserNotification> l = em.createQuery("from UserNotification  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, UserNotification.class).getResultList();
        if (l.size() > 0) {
            UserNotification userNotification = l.get(0);
            userNotification.setLastUpdatedAt(new Date());
            userNotification.setDeletedAt(new Date());
            userNotification.setStatus(Status.DELETE_STATUS);
            repository.save(userNotification);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

