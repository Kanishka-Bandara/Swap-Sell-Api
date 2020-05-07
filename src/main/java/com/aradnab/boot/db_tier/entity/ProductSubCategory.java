package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product_sub_category", schema = "swap_sell")
public class ProductSubCategory {
    private int id;
    private int productMainCategoryId;
    private String categoryName;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<Product> productsById;
    private ProductMainCategory productMainCategoryByProductMainCategoryId;

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
    @Column(name = "product_main_category_id", nullable = false)
    public int getProductMainCategoryId() {
        return productMainCategoryId;
    }

    public void setProductMainCategoryId(int productMainCategoryId) {
        this.productMainCategoryId = productMainCategoryId;
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
        ProductSubCategory that = (ProductSubCategory) o;
        return id == that.id &&
                productMainCategoryId == that.productMainCategoryId &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productMainCategoryId, categoryName, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "productSubCategoryByProductSubCategoryId")
    public Collection<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<Product> productsById) {
        this.productsById = productsById;
    }

    @ManyToOne
    @JoinColumn(name = "product_main_category_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public ProductMainCategory getProductMainCategoryByProductMainCategoryId() {
        return productMainCategoryByProductMainCategoryId;
    }

    public void setProductMainCategoryByProductMainCategoryId(ProductMainCategory productMainCategoryByProductMainCategoryId) {
        this.productMainCategoryByProductMainCategoryId = productMainCategoryByProductMainCategoryId;
    }
}
