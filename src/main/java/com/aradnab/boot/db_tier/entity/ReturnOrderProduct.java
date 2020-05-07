package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "return_order_product", schema = "swap_sell")
public class ReturnOrderProduct {
    private int id;
    private Double qty;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private int returnOrderId;
    private int orderProductId;
    private int returnReasonId;
    private Byte status;
    private ReturnOrder returnOrderByReturnOrderId;
    private OrderProduct orderProductByOrderProductId;
    private ReturnReason returnReasonByReturnReasonId;

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
    @Column(name = "qty", nullable = true, precision = 0)
    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
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
    @Column(name = "return_order_id", nullable = false)
    public int getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(int returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    @Basic
    @Column(name = "order_product_id", nullable = false)
    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    @Basic
    @Column(name = "return_reason_id", nullable = false)
    public int getReturnReasonId() {
        return returnReasonId;
    }

    public void setReturnReasonId(int returnReasonId) {
        this.returnReasonId = returnReasonId;
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
        ReturnOrderProduct that = (ReturnOrderProduct) o;
        return id == that.id &&
                returnOrderId == that.returnOrderId &&
                orderProductId == that.orderProductId &&
                returnReasonId == that.returnReasonId &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qty, savedAt, lastUpdatedAt, deletedAt, returnOrderId, orderProductId, returnReasonId, status);
    }

    @ManyToOne
    @JoinColumn(name = "return_order_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public ReturnOrder getReturnOrderByReturnOrderId() {
        return returnOrderByReturnOrderId;
    }

    public void setReturnOrderByReturnOrderId(ReturnOrder returnOrderByReturnOrderId) {
        this.returnOrderByReturnOrderId = returnOrderByReturnOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "order_product_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public OrderProduct getOrderProductByOrderProductId() {
        return orderProductByOrderProductId;
    }

    public void setOrderProductByOrderProductId(OrderProduct orderProductByOrderProductId) {
        this.orderProductByOrderProductId = orderProductByOrderProductId;
    }

    @ManyToOne
    @JoinColumn(name = "return_reason_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public ReturnReason getReturnReasonByReturnReasonId() {
        return returnReasonByReturnReasonId;
    }

    public void setReturnReasonByReturnReasonId(ReturnReason returnReasonByReturnReasonId) {
        this.returnReasonByReturnReasonId = returnReasonByReturnReasonId;
    }
}
