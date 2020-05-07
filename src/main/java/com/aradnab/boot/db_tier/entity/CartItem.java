package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cart_item", schema = "swap_sell")
public class CartItem {
    private int id;
    private int cartId;
    private int cartItemStatusId;
    private byte productDealingTypeId;
    private Byte isSelected;
    private Date addedAt;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<BarterProduct> barterProductsById;
    private Collection<BuyingProduct> buyingProductsById;
    private Cart cartByCartId;
    private CartItemStatus cartItemStatusByCartItemStatusId;
    private ProductDealingType productDealingTypeByProductDealingTypeId;
    private Collection<InvoiceItem> invoiceItemsById;
    private Collection<OrderProduct> orderProductsById;

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
    @Column(name = "cart_id", nullable = false)
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "cart_item_status_id", nullable = false)
    public int getCartItemStatusId() {
        return cartItemStatusId;
    }

    public void setCartItemStatusId(int cartItemStatusId) {
        this.cartItemStatusId = cartItemStatusId;
    }

    @Basic
    @Column(name = "product_dealing_type_id", nullable = false)
    public byte getProductDealingTypeId() {
        return productDealingTypeId;
    }

    public void setProductDealingTypeId(byte productDealingTypeId) {
        this.productDealingTypeId = productDealingTypeId;
    }

    @Basic
    @Column(name = "is_selected", nullable = true)
    public Byte getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Byte isSelected) {
        this.isSelected = isSelected;
    }

    @Basic
    @Column(name = "added_at", nullable = true)
    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
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
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id &&
                cartId == cartItem.cartId &&
                cartItemStatusId == cartItem.cartItemStatusId &&
                productDealingTypeId == cartItem.productDealingTypeId &&
                Objects.equals(isSelected, cartItem.isSelected) &&
                Objects.equals(addedAt, cartItem.addedAt) &&
                Objects.equals(savedAt, cartItem.savedAt) &&
                Objects.equals(lastUpdatedAt, cartItem.lastUpdatedAt) &&
                Objects.equals(deletedAt, cartItem.deletedAt) &&
                Objects.equals(status, cartItem.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartId, cartItemStatusId, productDealingTypeId, isSelected, addedAt, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "cartItemByCartItemId")
    public Collection<BarterProduct> getBarterProductsById() {
        return barterProductsById;
    }

    public void setBarterProductsById(Collection<BarterProduct> barterProductsById) {
        this.barterProductsById = barterProductsById;
    }

    @OneToMany(mappedBy = "cartItemByCartItemId")
    public Collection<BuyingProduct> getBuyingProductsById() {
        return buyingProductsById;
    }

    public void setBuyingProductsById(Collection<BuyingProduct> buyingProductsById) {
        this.buyingProductsById = buyingProductsById;
    }

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Cart getCartByCartId() {
        return cartByCartId;
    }

    public void setCartByCartId(Cart cartByCartId) {
        this.cartByCartId = cartByCartId;
    }

    @ManyToOne
    @JoinColumn(name = "cart_item_status_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public CartItemStatus getCartItemStatusByCartItemStatusId() {
        return cartItemStatusByCartItemStatusId;
    }

    public void setCartItemStatusByCartItemStatusId(CartItemStatus cartItemStatusByCartItemStatusId) {
        this.cartItemStatusByCartItemStatusId = cartItemStatusByCartItemStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "product_dealing_type_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public ProductDealingType getProductDealingTypeByProductDealingTypeId() {
        return productDealingTypeByProductDealingTypeId;
    }

    public void setProductDealingTypeByProductDealingTypeId(ProductDealingType productDealingTypeByProductDealingTypeId) {
        this.productDealingTypeByProductDealingTypeId = productDealingTypeByProductDealingTypeId;
    }

    @OneToMany(mappedBy = "cartItemByCartItemId")
    public Collection<InvoiceItem> getInvoiceItemsById() {
        return invoiceItemsById;
    }

    public void setInvoiceItemsById(Collection<InvoiceItem> invoiceItemsById) {
        this.invoiceItemsById = invoiceItemsById;
    }

    @OneToMany(mappedBy = "cartItemByCartItemId")
    public Collection<OrderProduct> getOrderProductsById() {
        return orderProductsById;
    }

    public void setOrderProductsById(Collection<OrderProduct> orderProductsById) {
        this.orderProductsById = orderProductsById;
    }
}
