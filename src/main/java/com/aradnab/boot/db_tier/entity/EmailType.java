package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "email_type", schema = "swap_sell")
public class EmailType {
    private int id;
    private String type;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<Email> emailsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "saved_at", nullable = true)
    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    @Basic
    @Column(name = "last_updated_at", nullable = true)
    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Basic
    @Column(name = "deleted_at", nullable = true)
    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailType emailType = (EmailType) o;
        return id == emailType.id &&
                Objects.equals(type, emailType.type) &&
                Objects.equals(savedAt, emailType.savedAt) &&
                Objects.equals(lastUpdatedAt, emailType.lastUpdatedAt) &&
                Objects.equals(deletedAt, emailType.deletedAt) &&
                Objects.equals(status, emailType.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "emailTypeByEmailTypeId")
    public Collection<Email> getEmailsById() {
        return emailsById;
    }

    public void setEmailsById(Collection<Email> emailsById) {
        this.emailsById = emailsById;
    }
}
