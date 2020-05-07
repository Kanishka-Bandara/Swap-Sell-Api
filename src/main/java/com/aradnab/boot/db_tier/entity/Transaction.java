package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Transaction {
    private int id;
    private int transactionTypeId;
    private int userId;
    private int transactionedBy;
    private int transactionStatusId;
    private int currencyId;
    private String uniqueId;
    private String transactionId;
    private String description;
    private Double amount;
    private Date transactionTime;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<OrderPayment> orderPaymentsById;
    private Collection<PaymentCard> paymentCardsById;
    private Collection<ReturnOrderPayment> returnOrderPaymentsById;
    private TransactionType transactionTypeByTransactionTypeId;
    private User userByUserId;
    private Account accountByTransactionedBy;
    private TransactionStatus transactionStatusByTransactionStatusId;
    private Currency currencyByCurrencyId;

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
    @Column(name = "transaction_type_id", nullable = false)
    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "transactioned_by", nullable = false)
    public int getTransactionedBy() {
        return transactionedBy;
    }

    public void setTransactionedBy(int transactionedBy) {
        this.transactionedBy = transactionedBy;
    }

    @Basic
    @Column(name = "transaction_status_id", nullable = false)
    public int getTransactionStatusId() {
        return transactionStatusId;
    }

    public void setTransactionStatusId(int transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    @Basic
    @Column(name = "currency_id", nullable = false)
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
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
    @Column(name = "transaction_id", nullable = true, length = 45)
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "transaction_time", nullable = true)
    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
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
        Transaction that = (Transaction) o;
        return id == that.id &&
                transactionTypeId == that.transactionTypeId &&
                userId == that.userId &&
                transactionedBy == that.transactionedBy &&
                transactionStatusId == that.transactionStatusId &&
                currencyId == that.currencyId &&
                Objects.equals(uniqueId, that.uniqueId) &&
                Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transactionTime, that.transactionTime) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionTypeId, userId, transactionedBy, transactionStatusId, currencyId, uniqueId, transactionId, description, amount, transactionTime, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "transactionByTransactionId")
    public Collection<OrderPayment> getOrderPaymentsById() {
        return orderPaymentsById;
    }

    public void setOrderPaymentsById(Collection<OrderPayment> orderPaymentsById) {
        this.orderPaymentsById = orderPaymentsById;
    }

    @OneToMany(mappedBy = "transactionByTransactionId")
    public Collection<PaymentCard> getPaymentCardsById() {
        return paymentCardsById;
    }

    public void setPaymentCardsById(Collection<PaymentCard> paymentCardsById) {
        this.paymentCardsById = paymentCardsById;
    }

    @OneToMany(mappedBy = "transactionByTransactionId")
    public Collection<ReturnOrderPayment> getReturnOrderPaymentsById() {
        return returnOrderPaymentsById;
    }

    public void setReturnOrderPaymentsById(Collection<ReturnOrderPayment> returnOrderPaymentsById) {
        this.returnOrderPaymentsById = returnOrderPaymentsById;
    }

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public TransactionType getTransactionTypeByTransactionTypeId() {
        return transactionTypeByTransactionTypeId;
    }

    public void setTransactionTypeByTransactionTypeId(TransactionType transactionTypeByTransactionTypeId) {
        this.transactionTypeByTransactionTypeId = transactionTypeByTransactionTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "transactioned_by", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Account getAccountByTransactionedBy() {
        return accountByTransactionedBy;
    }

    public void setAccountByTransactionedBy(Account accountByTransactionedBy) {
        this.accountByTransactionedBy = accountByTransactionedBy;
    }

    @ManyToOne
    @JoinColumn(name = "transaction_status_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public TransactionStatus getTransactionStatusByTransactionStatusId() {
        return transactionStatusByTransactionStatusId;
    }

    public void setTransactionStatusByTransactionStatusId(TransactionStatus transactionStatusByTransactionStatusId) {
        this.transactionStatusByTransactionStatusId = transactionStatusByTransactionStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Currency getCurrencyByCurrencyId() {
        return currencyByCurrencyId;
    }

    public void setCurrencyByCurrencyId(Currency currencyByCurrencyId) {
        this.currencyByCurrencyId = currencyByCurrencyId;
    }
}
