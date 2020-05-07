package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Email {
    private int id;
    private int emailTypeId;
    private int userId;
    private String email;
    private Byte isDefault;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private EmailType emailTypeByEmailTypeId;
    private User userByUserId;

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
    @Column(name = "email_type_id", nullable = false)
    public int getEmailTypeId() {
        return emailTypeId;
    }

    public void setEmailTypeId(int emailTypeId) {
        this.emailTypeId = emailTypeId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 250)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "is_default", nullable = true)
    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
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
        Email email1 = (Email) o;
        return id == email1.id &&
                emailTypeId == email1.emailTypeId &&
                userId == email1.userId &&
                Objects.equals(email, email1.email) &&
                Objects.equals(isDefault, email1.isDefault) &&
                Objects.equals(savedAt, email1.savedAt) &&
                Objects.equals(lastUpdatedAt, email1.lastUpdatedAt) &&
                Objects.equals(deletedAt, email1.deletedAt) &&
                Objects.equals(status, email1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailTypeId, userId, email, isDefault, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "email_type_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public EmailType getEmailTypeByEmailTypeId() {
        return emailTypeByEmailTypeId;
    }

    public void setEmailTypeByEmailTypeId(EmailType emailTypeByEmailTypeId) {
        this.emailTypeByEmailTypeId = emailTypeByEmailTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
