package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.UserRepository;
import com.aradnab.boot.general.service.service_controller.UserServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import com.aradnab.boot.general.service.service_controller.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public User create(User user) {
        user.setSavedAt(new Date());
        user.setLastUpdatedAt(new Date());
        user.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        List<User> l = em.createQuery("from User  x where x.id = " + user.getId() + " and x.status!=" + Status.DELETE_STATUS, User.class).getResultList();
        if (l.size() > 0) {
            user.setLastUpdatedAt(new Date());
            return repository.save(user);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + user.getId());
        }
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("from User  x where x.status!=" + Status.DELETE_STATUS, User.class).getResultList();
    }

    @Override
    public User getByID(int id) {
        List<User> l = em.createQuery("from User  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, User.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<User> l = em.createQuery("from User  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, User.class).getResultList();
        if (l.size() > 0) {
            User user = l.get(0);
            user.setLastUpdatedAt(new Date());
            user.setDeletedAt(new Date());
            user.setStatus(Status.DELETE_STATUS);
            repository.save(user);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public String generateUserID(UserType userType) {
        DecimalFormat f = new DecimalFormat("#########");
        String d = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return userType.getIndexPhrase()+d+f.format((repository.findAll().size()+1));
    }
}

