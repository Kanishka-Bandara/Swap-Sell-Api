package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "barter_product", schema = "swap_sell")
public class BarterProduct {
    private int id;
    private int productId;
    private int deliveryProductId;
    private int cartItemId;
    private Integer qty;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Product productByProductId;
    private DeliveryProduct deliveryProductByDeliveryProductId;
    private CartItem cartItemByCartItemId;

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
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "delivery_product_id", nullable = false)
    public int getDeliveryProductId() {
        return deliveryProductId;
    }

    public void setDeliveryProductId(int deliveryProductId) {
        this.deliveryProductId = deliveryProductId;
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
        BarterProduct that = (BarterProduct) o;
        return id == that.id &&
                productId == that.productId &&
                deliveryProductId == that.deliveryProductId &&
                cartItemId == that.cartItemId &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, deliveryProductId, cartItemId, qty, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "delivery_product_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public DeliveryProduct getDeliveryProductByDeliveryProductId() {
        return deliveryProductByDeliveryProductId;
    }

    public void setDeliveryProductByDeliveryProductId(DeliveryProduct deliveryProductByDeliveryProductId) {
        this.deliveryProductByDeliveryProductId = deliveryProductByDeliveryProductId;
    }

    @ManyToOne
    @JoinColumn(name = "cart_item_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public CartItem getCartItemByCartItemId() {
        return cartItemByCartItemId;
    }

    public void setCartItemByCartItemId(CartItem cartItemByCartItemId) {
        this.cartItemByCartItemId = cartItemByCartItemId;
    }
}
