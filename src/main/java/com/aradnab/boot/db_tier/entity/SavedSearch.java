package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "saved_search", schema = "swap_sell")
public class SavedSearch {
    private int id;
    private int savedBy;
    private String keyword;
    private Date searchedAt;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private User userBySavedBy;

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
    @Column(name = "keyword", nullable = true, length = -1)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Basic
    @Column(name = "searched_at", nullable = true)
    public Date getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(Date searchedAt) {
        this.searchedAt = searchedAt;
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
        SavedSearch that = (SavedSearch) o;
        return id == that.id &&
                savedBy == that.savedBy &&
                Objects.equals(keyword, that.keyword) &&
                Objects.equals(searchedAt, that.searchedAt) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, savedBy, keyword, searchedAt, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "saved_by", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public User getUserBySavedBy() {
        return userBySavedBy;
    }

    public void setUserBySavedBy(User userBySavedBy) {
        this.userBySavedBy = userBySavedBy;
    }
}
