package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "return_order", schema = "swap_sell")
public class ReturnOrder {
    private int id;
    private String uniqueId;
    private Integer qty;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<ReturnOrderPayment> returnOrderPaymentsById;
    private Collection<ReturnOrderProduct> returnOrderProductsById;

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
    @Column(name = "unique_id", nullable = true, length = 25)
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Basic
    @Column(name = "qty", nullable = true)
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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
        ReturnOrder that = (ReturnOrder) o;
        return id == that.id &&
                Objects.equals(uniqueId, that.uniqueId) &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueId, qty, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "returnOrderByReturnOrderId")
    public Collection<ReturnOrderPayment> getReturnOrderPaymentsById() {
        return returnOrderPaymentsById;
    }

    public void setReturnOrderPaymentsById(Collection<ReturnOrderPayment> returnOrderPaymentsById) {
        this.returnOrderPaymentsById = returnOrderPaymentsById;
    }

    @OneToMany(mappedBy = "returnOrderByReturnOrderId")
    public Collection<ReturnOrderProduct> getReturnOrderProductsById() {
        return returnOrderProductsById;
    }

    public void setReturnOrderProductsById(Collection<ReturnOrderProduct> returnOrderProductsById) {
        this.returnOrderProductsById = returnOrderProductsById;
    }
}
