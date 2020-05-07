package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "delivery_product", schema = "swap_sell")
public class DeliveryProduct {
    private int id;
    private int currencyId;
    private int productId;
    private Double retailPrice;
    private Double barterPrice;
    private Integer qty;
    private String inTheBox;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<BarterProduct> barterProductsById;
    private Collection<BuyingProduct> buyingProductsById;
    private Currency currencyByCurrencyId;
    private Product productByProductId;
    private Collection<Discount> discountsById;

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
    @Column(name = "currency_id", nullable = false)
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
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
    @Column(name = "retail_price", nullable = true, precision = 0)
    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Basic
    @Column(name = "barter_price", nullable = true, precision = 0)
    public Double getBarterPrice() {
        return barterPrice;
    }

    public void setBarterPrice(Double barterPrice) {
        this.barterPrice = barterPrice;
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
    @Column(name = "in_the_box", nullable = true, length = -1)
    public String getInTheBox() {
        return inTheBox;
    }

    public void setInTheBox(String inTheBox) {
        this.inTheBox = inTheBox;
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
        DeliveryProduct that = (DeliveryProduct) o;
        return id == that.id &&
                currencyId == that.currencyId &&
                productId == that.productId &&
                Objects.equals(retailPrice, that.retailPrice) &&
                Objects.equals(barterPrice, that.barterPrice) &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(inTheBox, that.inTheBox) &&
                Objects.equals(savedAt, that.savedAt) &&
                Objects.equals(lastUpdatedAt, that.lastUpdatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyId, productId, retailPrice, barterPrice, qty, inTheBox, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "deliveryProductByDeliveryProductId")
    public Collection<BarterProduct> getBarterProductsById() {
        return barterProductsById;
    }

    public void setBarterProductsById(Collection<BarterProduct> barterProductsById) {
        this.barterProductsById = barterProductsById;
    }

    @OneToMany(mappedBy = "deliveryProductByDeliveryProductId")
    public Collection<BuyingProduct> getBuyingProductsById() {
        return buyingProductsById;
    }

    public void setBuyingProductsById(Collection<BuyingProduct> buyingProductsById) {
        this.buyingProductsById = buyingProductsById;
    }

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Currency getCurrencyByCurrencyId() {
        return currencyByCurrencyId;
    }

    public void setCurrencyByCurrencyId(Currency currencyByCurrencyId) {
        this.currencyByCurrencyId = currencyByCurrencyId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @OneToMany(mappedBy = "deliveryProductByDeliveryProductId")
    public Collection<Discount> getDiscountsById() {
        return discountsById;
    }

    public void setDiscountsById(Collection<Discount> discountsById) {
        this.discountsById = discountsById;
    }
}
