package com.aradnab.boot.general.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gender")
public class Gender {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "gender")
    private String gender;
    @CreationTimestamp
    @Column(name = "saved_at")
    private Date savedAt;
    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;
    @UpdateTimestamp
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "status")
    private int status;

    public Gender(){}
    public Gender(int id, String gender) {
        this.setId(id);
        this.setGender(gender);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}