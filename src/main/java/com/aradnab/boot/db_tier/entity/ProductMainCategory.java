package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product_main_category", schema = "swap_sell")
public class ProductMainCategory {
    private int id;
    private int productHeadCategoryId;
    private String categoryName;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private ProductHeadCategory productHeadCategoryByProductHeadCategoryId;
    private Collection<ProductSubCategory> productSubCategoriesById;

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
    @Column(name = "product_head_category_id", nullable = false)
    public int getProductHeadCategoryId() {
        return productHeadCategoryId;
    }

    public void setProductHeadCategoryId(int productHeadCategoryId) {
        this.productHeadCategoryId = productHeadCategoryId;
    }

    @Basic
    @Column(name = "category_name", nullable = true, length = 45)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        ProductMainCategory that = (ProductMainCategory) o;
        return id == that.id &&
                productHeadCategoryId == that.productHeadCategoryId &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productHeadCategoryId, categoryName, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "product_head_category_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public ProductHeadCategory getProductHeadCategoryByProductHeadCategoryId() {
        return productHeadCategoryByProductHeadCategoryId;
    }

    public void setProductHeadCategoryByProductHeadCategoryId(ProductHeadCategory productHeadCategoryByProductHeadCategoryId) {
        this.productHeadCategoryByProductHeadCategoryId = productHeadCategoryByProductHeadCategoryId;
    }

    @OneToMany(mappedBy = "productMainCategoryByProductMainCategoryId")
    public Collection<ProductSubCategory> getProductSubCategoriesById() {
        return productSubCategoriesById;
    }

    public void setProductSubCategoriesById(Collection<ProductSubCategory> productSubCategoriesById) {
        this.productSubCategoriesById = productSubCategoriesById;
    }
}
