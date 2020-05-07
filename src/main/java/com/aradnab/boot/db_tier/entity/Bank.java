package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Bank {
    private int id;
    private String bankName;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<BankCard> bankCardsById;

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
    @Column(name = "bank_name", nullable = true, length = 100)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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
        Bank bank = (Bank) o;
        return id == bank.id &&
                Objects.equals(bankName, bank.bankName) &&
                Objects.equals(savedAt, bank.savedAt) &&
                Objects.equals(lastUpdatedAt, bank.lastUpdatedAt) &&
                Objects.equals(deletedAt, bank.deletedAt) &&
                Objects.equals(status, bank.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankName, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "bankByBankId")
    public Collection<BankCard> getBankCardsById() {
        return bankCardsById;
    }

    public void setBankCardsById(Collection<BankCard> bankCardsById) {
        this.bankCardsById = bankCardsById;
    }
}
