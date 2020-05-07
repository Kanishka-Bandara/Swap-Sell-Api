package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Invoice {
    private int id;
    private int paymentOptionId;
    private String invoiceNumber;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private PaymentOption paymentOptionByPaymentOptionId;
    private Collection<InvoiceItem> invoiceItemsById;

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
    @Column(name = "payment_option_id", nullable = false)
    public int getPaymentOptionId() {
        return paymentOptionId;
    }

    public void setPaymentOptionId(int paymentOptionId) {
        this.paymentOptionId = paymentOptionId;
    }

    @Basic
    @Column(name = "invoice_number", nullable = true, length = 20)
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                paymentOptionId == invoice.paymentOptionId &&
                Objects.equals(invoiceNumber, invoice.invoiceNumber) &&
                Objects.equals(savedAt, invoice.savedAt) &&
                Objects.equals(lastUpdatedAt, invoice.lastUpdatedAt) &&
                Objects.equals(deletedAt, invoice.deletedAt) &&
                Objects.equals(status, invoice.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentOptionId, invoiceNumber, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "payment_option_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public PaymentOption getPaymentOptionByPaymentOptionId() {
        return paymentOptionByPaymentOptionId;
    }

    public void setPaymentOptionByPaymentOptionId(PaymentOption paymentOptionByPaymentOptionId) {
        this.paymentOptionByPaymentOptionId = paymentOptionByPaymentOptionId;
    }

    @OneToMany(mappedBy = "invoiceByInvoiceId")
    public Collection<InvoiceItem> getInvoiceItemsById() {
        return invoiceItemsById;
    }

    public void setInvoiceItemsById(Collection<InvoiceItem> invoiceItemsById) {
        this.invoiceItemsById = invoiceItemsById;
    }
}
