package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Discount {
    private int id;
    private int deliveryProductId;
    private Double discount;
    private String discountCode;
    private Date startAt;
    private Date endAt;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<BuyingProduct> buyingProductsById;
    private DeliveryProduct deliveryProductByDeliveryProductId;

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
    @Column(name = "delivery_product_id", nullable = false)
    public int getDeliveryProductId() {
        return deliveryProductId;
    }

    public void setDeliveryProductId(int deliveryProductId) {
        this.deliveryProductId = deliveryProductId;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "discount_code", nullable = true, length = 30)
    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Basic
    @Column(name = "start_at", nullable = true)
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    @Basic
    @Column(name = "end_at", nullable = true)
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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
        Discount discount1 = (Discount) o;
        return id == discount1.id &&
                deliveryProductId == discount1.deliveryProductId &&
                Objects.equals(discount, discount1.discount) &&
                Objects.equals(discountCode, discount1.discountCode) &&
                Objects.equals(startAt, discount1.startAt) &&
                Objects.equals(endAt, discount1.endAt) &&
                Objects.equals(savedAt, discount1.savedAt) &&
                Objects.equals(lastUpdatedAt, discount1.lastUpdatedAt) &&
                Objects.equals(deletedAt, discount1.deletedAt) &&
                Objects.equals(status, discount1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryProductId, discount, discountCode, startAt, endAt, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "discountByDiscountId")
    public Collection<BuyingProduct> getBuyingProductsById() {
        return buyingProductsById;
    }

    public void setBuyingProductsById(Collection<BuyingProduct> buyingProductsById) {
        this.buyingProductsById = buyingProductsById;
    }

    @ManyToOne
    @JoinColumn(name = "delivery_product_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public DeliveryProduct getDeliveryProductByDeliveryProductId() {
        return deliveryProductByDeliveryProductId;
    }

    public void setDeliveryProductByDeliveryProductId(DeliveryProduct deliveryProductByDeliveryProductId) {
        this.deliveryProductByDeliveryProductId = deliveryProductByDeliveryProductId;
    }
}
