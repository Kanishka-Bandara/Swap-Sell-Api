package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "saved_user", schema = "swap_sell")
public class SavedUser {
    private int id;
    private int savedBy;
    private int userId;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private User userBySavedBy;
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
    @Column(name = "saved_by", nullable = false)
    public int getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(int savedBy) {
        this.savedBy = savedBy;
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
        SavedUser savedUser = (SavedUser) o;
        return id == savedUser.id &&
                savedBy == savedUser.savedBy &&
                userId == savedUser.userId &&
                Objects.equals(savedAt, savedUser.savedAt) &&
                Objects.equals(lastUpdatedAt, savedUser.lastUpdatedAt) &&
                Objects.equals(deletedAt, savedUser.deletedAt) &&
                Objects.equals(status, savedUser.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, savedBy, userId, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "saved_by", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public User getUserBySavedBy() {
        return userBySavedBy;
    }

    public void setUserBySavedBy(User userBySavedBy) {
        this.userBySavedBy = userBySavedBy;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
