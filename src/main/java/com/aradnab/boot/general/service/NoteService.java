package com.aradnab.boot.general.service;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Note;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.NoteRepository;
import com.aradnab.boot.general.service.service_controller.NoteServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NoteService implements NoteServiceInterface {

    @Autowired
    private NoteRepository repository;
    @Autowired
    private EntityManager em;

    @Override
    public Note create(Note note) {
        note.setSavedAt(new Date());
        note.setLastUpdatedAt(new Date());
        note.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(note);
    }

    @Override
    public Note update(Note note) {
        List<Note> l = em.createQuery("from Note  x where x.id = " + note.getId() + " and x.status!=" + Status.DELETE_STATUS, Note.class).getResultList();
        if (l.size() > 0) {
            note.setLastUpdatedAt(new Date());
            return repository.save(note);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + note.getId());
        }
    }

    @Override
    public List<Note> getAll() {
        return em.createQuery("from Note  x where x.status!=" + Status.DELETE_STATUS, Note.class).getResultList();
    }

    @Override
    public Note getByID(int id) {
        List<Note> l = em.createQuery("from Note  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Note.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Note> l = em.createQuery("from Note  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Note.class).getResultList();
        if (l.size() > 0) {
            Note note = l.get(0);
            note.setLastUpdatedAt(new Date());
            note.setDeletedAt(new Date());
            note.setStatus(Status.DELETE_STATUS);
            repository.save(note);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

}

