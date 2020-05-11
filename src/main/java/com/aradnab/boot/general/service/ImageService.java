package com.aradnab.boot.general.service;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Image;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ImageRepository;
import com.aradnab.boot.general.service.service_controller.ImageServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ImageService implements ImageServiceInterface {

    @Autowired
    private ImageRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Image create(Image image) {
        image.setSavedAt(new Date());
        image.setLastUpdatedAt(new Date());
        image.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(image);
    }

    @Override
    public Image update(Image image) {
        List<Image> l = em.createQuery("from Image  x where x.id = " + image.getId() + " and x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
        if (l.size() > 0) {
            image.setLastUpdatedAt(new Date());
            return repository.save(image);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + image.getId());
        }
    }

    @Override
    public List<Image> getAll() {
        return em.createQuery("from Image  x where x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
    }

    @Override
    public Image getByID(int id) {
        List<Image> l = em.createQuery("from Image  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Image> l = em.createQuery("from Image  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
        if (l.size() > 0) {
            Image image = l.get(0);
            image.setLastUpdatedAt(new Date());
            image.setDeletedAt(new Date());
            image.setStatus(Status.DELETE_STATUS);
            repository.save(image);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

