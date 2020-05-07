package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bank_card", schema = "swap_sell")
public class BankCard {
    private int id;
    private int bankId;
    private int userId;
    private int cardTypeId;
    private Date expDate;
    private String cardNumber;
    private java.util.Date savedAt;
    private java.util.Date lastUpdatedAt;
    private java.util.Date deletedAt;
    private Byte status;
    private Bank bankByBankId;
    private User userByUserId;
    private CardType cardTypeByCardTypeId;
    private Collection<PaymentCard> paymentCardsById;

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
    @Column(name = "bank_id", nullable = false)
    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
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
    @Column(name = "card_type_id", nullable = false)
    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    @Basic
    @Column(name = "exp_date", nullable = true)
    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Basic
    @Column(name = "card_number", nullable = true, length = 20)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "saved_at", nullable = true)
    public java.util.Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(java.util.Date savedAt) {
        this.savedAt = savedAt;
    }

    @Basic
    @Column(name = "last_updated_at", nullable = true)
    public java.util.Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(java.util.Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Basic
    @Column(name = "deleted_at", nullable = true)
    public java.util.Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(java.util.Date deletedAt) {
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
        BankCard bankCard = (BankCard) o;
        return id == bankCard.id &&
                bankId == bankCard.bankId &&
                userId == bankCard.userId &&
                cardTypeId == bankCard.cardTypeId &&
                Objects.equals(expDate, bankCard.expDate) &&
                Objects.equals(cardNumber, bankCard.cardNumber) &&
                Objects.equals(savedAt, bankCard.savedAt) &&
                Objects.equals(lastUpdatedAt, bankCard.lastUpdatedAt) &&
                Objects.equals(deletedAt, bankCard.deletedAt) &&
                Objects.equals(status, bankCard.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankId, userId, cardTypeId, expDate, cardNumber, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Bank getBankByBankId() {
        return bankByBankId;
    }

    public void setBankByBankId(Bank bankByBankId) {
        this.bankByBankId = bankByBankId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "card_type_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public CardType getCardTypeByCardTypeId() {
        return cardTypeByCardTypeId;
    }

    public void setCardTypeByCardTypeId(CardType cardTypeByCardTypeId) {
        this.cardTypeByCardTypeId = cardTypeByCardTypeId;
    }

    @OneToMany(mappedBy = "bankCardByBankCardId")
    public Collection<PaymentCard> getPaymentCardsById() {
        return paymentCardsById;
    }

    public void setPaymentCardsById(Collection<PaymentCard> paymentCardsById) {
        this.paymentCardsById = paymentCardsById;
    }
}
