package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "payment_method", schema = "swap_sell")
public class PaymentMethod {
    private int id;
    private String method;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<OrderPayment> orderPaymentsById;
    private Collection<ReturnOrderPayment> returnOrderPaymentsById;

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
    @Column(name = "method", nullable = true, length = 45)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
        PaymentMethod that = (PaymentMethod) o;
        return id == that.id &&
                Objects.equals(method, that.method) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, method, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "paymentMethodByPaymentMethodId")
    public Collection<OrderPayment> getOrderPaymentsById() {
        return orderPaymentsById;
    }

    public void setOrderPaymentsById(Collection<OrderPayment> orderPaymentsById) {
        this.orderPaymentsById = orderPaymentsById;
    }

    @OneToMany(mappedBy = "paymentMethodByPaymentMethodId")
    public Collection<ReturnOrderPayment> getReturnOrderPaymentsById() {
        return returnOrderPaymentsById;
    }

    public void setReturnOrderPaymentsById(Collection<ReturnOrderPayment> returnOrderPaymentsById) {
        this.returnOrderPaymentsById = returnOrderPaymentsById;
    }
}
