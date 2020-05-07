package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order_status", schema = "swap_sell")
public class OrderStatus {
    private byte id;
    private String status;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Collection<OrderProduct> orderProductsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 15)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return id == that.id &&
                Objects.equals(status, that.status) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, savedAt, lastUpdatedAt, deletedAt);
    }

    @OneToMany(mappedBy = "orderStatusByOrderStatusId")
    public Collection<OrderProduct> getOrderProductsById() {
        return orderProductsById;
    }

    public void setOrderProductsById(Collection<OrderProduct> orderProductsById) {
        this.orderProductsById = orderProductsById;
    }
}