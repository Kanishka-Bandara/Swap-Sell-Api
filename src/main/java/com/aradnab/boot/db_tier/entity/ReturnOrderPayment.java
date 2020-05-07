package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "return_order_payment", schema = "swap_sell")
public class ReturnOrderPayment {
    private int id;
    private int returnOrderId;
    private int paymentMethodId;
    private int transactionId;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private ReturnOrder returnOrderByReturnOrderId;
    private PaymentMethod paymentMethodByPaymentMethodId;
    private Transaction transactionByTransactionId;

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
    @Column(name = "return_order_id", nullable = false)
    public int getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(int returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    @Basic
    @Column(name = "payment_method_id", nullable = false)
    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Basic
    @Column(name = "transaction_id", nullable = false)
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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
        ReturnOrderPayment that = (ReturnOrderPayment) o;
        return id == that.id &&
                returnOrderId == that.returnOrderId &&
                paymentMethodId == that.paymentMethodId &&
                transactionId == that.transactionId &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, returnOrderId, paymentMethodId, transactionId, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "return_order_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public ReturnOrder getReturnOrderByReturnOrderId() {
        return returnOrderByReturnOrderId;
    }

    public void setReturnOrderByReturnOrderId(ReturnOrder returnOrderByReturnOrderId) {
        this.returnOrderByReturnOrderId = returnOrderByReturnOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public PaymentMethod getPaymentMethodByPaymentMethodId() {
        return paymentMethodByPaymentMethodId;
    }

    public void setPaymentMethodByPaymentMethodId(PaymentMethod paymentMethodByPaymentMethodId) {
        this.paymentMethodByPaymentMethodId = paymentMethodByPaymentMethodId;
    }

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Transaction getTransactionByTransactionId() {
        return transactionByTransactionId;
    }

    public void setTransactionByTransactionId(Transaction transactionByTransactionId) {
        this.transactionByTransactionId = transactionByTransactionId;
    }
}
