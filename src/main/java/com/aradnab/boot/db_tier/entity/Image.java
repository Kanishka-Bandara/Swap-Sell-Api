package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Image {
    private int id;
    private String imgUrl;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<ProductImage> productImagesById;
    private Collection<Shop> shopsById;
    private Collection<User> usersById;

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
    @Column(name = "img_url", nullable = true, length = -1)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        Image image = (Image) o;
        return id == image.id &&
                Objects.equals(imgUrl, image.imgUrl) &&
                Objects.equals(savedAt, image.savedAt) &&
                Objects.equals(lastUpdatedAt, image.lastUpdatedAt) &&
                Objects.equals(deletedAt, image.deletedAt) &&
                Objects.equals(status, image.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imgUrl, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "imageByImageId")
    public Collection<ProductImage> getProductImagesById() {
        return productImagesById;
    }

    public void setProductImagesById(Collection<ProductImage> productImagesById) {
        this.productImagesById = productImagesById;
    }

    @OneToMany(mappedBy = "imageByImageId")
    public Collection<Shop> getShopsById() {
        return shopsById;
    }

    public void setShopsById(Collection<Shop> shopsById) {
        this.shopsById = shopsById;
    }

    @OneToMany(mappedBy = "imageByImageId")
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }
}
