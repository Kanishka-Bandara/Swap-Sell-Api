package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order_product", schema = "swap_sell")
public class OrderProduct {
    private int id;
    private int orderId;
    private int cartItemId;
    private byte orderStatusId;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Order orderByOrderId;
    private CartItem cartItemByCartItemId;
    private OrderStatus orderStatusByOrderStatusId;
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
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "cart_item_id", nullable = false)
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    @Basic
    @Column(name = "order_status_id", nullable = false)
    public byte getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(byte orderStatusId) {
        this.orderStatusId = orderStatusId;
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
        OrderProduct that = (OrderProduct) o;
        return id == that.id &&
                orderId == that.orderId &&
                cartItemId == that.cartItemId &&
                orderStatusId == that.orderStatusId &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, cartItemId, orderStatusId, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "cart_item_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public CartItem getCartItemByCartItemId() {
        return cartItemByCartItemId;
    }

    public void setCartItemByCartItemId(CartItem cartItemByCartItemId) {
        this.cartItemByCartItemId = cartItemByCartItemId;
    }

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public OrderStatus getOrderStatusByOrderStatusId() {
        return orderStatusByOrderStatusId;
    }

    public void setOrderStatusByOrderStatusId(OrderStatus orderStatusByOrderStatusId) {
        this.orderStatusByOrderStatusId = orderStatusByOrderStatusId;
    }

    @OneToMany(mappedBy = "orderProductByOrderProductId")
    public Collection<ReturnOrderProduct> getReturnOrderProductsById() {
        return returnOrderProductsById;
    }

    public void setReturnOrderProductsById(Collection<ReturnOrderProduct> returnOrderProductsById) {
        this.returnOrderProductsById = returnOrderProductsById;
    }
}
