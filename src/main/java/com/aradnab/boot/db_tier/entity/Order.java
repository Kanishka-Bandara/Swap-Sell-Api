package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Order {
    private int id;
    private int orderBy;
    private String uniqueId;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private User userByOrderBy;
    private Collection<OrderPayment> orderPaymentsById;
    private Collection<OrderProduct> orderProductsById;

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
    @Column(name = "order_by", nullable = false)
    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    @Basic
    @Column(name = "unique_id", nullable = true, length = 20)
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
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
        Order order = (Order) o;
        return id == order.id &&
                orderBy == order.orderBy &&
                Objects.equals(uniqueId, order.uniqueId) &&
                Objects.equals(savedAt, order.savedAt) &&
                Objects.equals(lastUpdatedAt, order.lastUpdatedAt) &&
                Objects.equals(deletedAt, order.deletedAt) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderBy, uniqueId, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "order_by", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getUserByOrderBy() {
        return userByOrderBy;
    }

    public void setUserByOrderBy(User userByOrderBy) {
        this.userByOrderBy = userByOrderBy;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderPayment> getOrderPaymentsById() {
        return orderPaymentsById;
    }

    public void setOrderPaymentsById(Collection<OrderPayment> orderPaymentsById) {
        this.orderPaymentsById = orderPaymentsById;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderProduct> getOrderProductsById() {
        return orderProductsById;
    }

    public void setOrderProductsById(Collection<OrderProduct> orderProductsById) {
        this.orderProductsById = orderProductsById;
    }
}
