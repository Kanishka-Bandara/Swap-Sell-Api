package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "saved_shop", schema = "swap_sell")
public class SavedShop {
    private int id;
    private int savedBy;
    private int shopId;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private User userBySavedBy;
    private Shop shopByShopId;

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
    @Column(name = "shop_id", nullable = false)
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
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
        SavedShop savedShop = (SavedShop) o;
        return id == savedShop.id &&
                savedBy == savedShop.savedBy &&
                shopId == savedShop.shopId &&
                Objects.equals(savedAt, savedShop.savedAt) &&
                Objects.equals(lastUpdatedAt, savedShop.lastUpdatedAt) &&
                Objects.equals(deletedAt, savedShop.deletedAt) &&
                Objects.equals(status, savedShop.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, savedBy, shopId, savedAt, lastUpdatedAt, deletedAt, status);
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
    @JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Shop getShopByShopId() {
        return shopByShopId;
    }

    public void setShopByShopId(Shop shopByShopId) {
        this.shopByShopId = shopByShopId;
    }
}
