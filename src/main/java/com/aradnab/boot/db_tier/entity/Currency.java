package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Currency {
    private int id;
    private String currency;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<DeliveryProduct> deliveryProductsById;
    private Collection<OrderPayment> orderPaymentsById;
    private Collection<Transaction> transactionsById;

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
    @Column(name = "currency", nullable = true, length = 10)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        Currency currency1 = (Currency) o;
        return id == currency1.id &&
                Objects.equals(currency, currency1.currency) &&
                Objects.equals(savedAt, currency1.savedAt) &&
                Objects.equals(lastUpdatedAt, currency1.lastUpdatedAt) &&
                Objects.equals(deletedAt, currency1.deletedAt) &&
                Objects.equals(status, currency1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "currencyByCurrencyId")
    public Collection<DeliveryProduct> getDeliveryProductsById() {
        return deliveryProductsById;
    }

    public void setDeliveryProductsById(Collection<DeliveryProduct> deliveryProductsById) {
        this.deliveryProductsById = deliveryProductsById;
    }

    @OneToMany(mappedBy = "currencyByCurrencyId")
    public Collection<OrderPayment> getOrderPaymentsById() {
        return orderPaymentsById;
    }

    public void setOrderPaymentsById(Collection<OrderPayment> orderPaymentsById) {
        this.orderPaymentsById = orderPaymentsById;
    }

    @OneToMany(mappedBy = "currencyByCurrencyId")
    public Collection<Transaction> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<Transaction> transactionsById) {
        this.transactionsById = transactionsById;
    }
}
