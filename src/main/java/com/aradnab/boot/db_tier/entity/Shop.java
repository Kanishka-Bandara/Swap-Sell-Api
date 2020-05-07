package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Shop {
    private int id;
    private int userId;
    private int imageId;
    private Byte isActive;
    private String shopName;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<Product> productsById;
    private Collection<SavedShop> savedShopsById;
    private User userByUserId;
    private Image imageByImageId;
    private Collection<ShopRating> shopRatingsById;

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
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "image_id", nullable = false)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "is_active", nullable = true)
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "shop_name", nullable = true, length = 45)
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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
        Shop shop = (Shop) o;
        return id == shop.id &&
                userId == shop.userId &&
                imageId == shop.imageId &&
                Objects.equals(isActive, shop.isActive) &&
                Objects.equals(shopName, shop.shopName) &&
                Objects.equals(savedAt, shop.savedAt) &&
                Objects.equals(lastUpdatedAt, shop.lastUpdatedAt) &&
                Objects.equals(deletedAt, shop.deletedAt) &&
                Objects.equals(status, shop.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, imageId, isActive, shopName, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "shopByShopId")
    public Collection<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<Product> productsById) {
        this.productsById = productsById;
    }

    @OneToMany(mappedBy = "shopByShopId")
    public Collection<SavedShop> getSavedShopsById() {
        return savedShopsById;
    }

    public void setSavedShopsById(Collection<SavedShop> savedShopsById) {
        this.savedShopsById = savedShopsById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }

    @OneToMany(mappedBy = "shopByShopId")
    public Collection<ShopRating> getShopRatingsById() {
        return shopRatingsById;
    }

    public void setShopRatingsById(Collection<ShopRating> shopRatingsById) {
        this.shopRatingsById = shopRatingsById;
    }
}
