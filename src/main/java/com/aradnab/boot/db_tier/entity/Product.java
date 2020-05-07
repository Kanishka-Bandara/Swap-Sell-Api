package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Product {
    private int id;
    private int productSubCategoryId;
    private byte productDealingTypeId;
    private int shopId;
    private int productConditionId;
    private int brandId;
    private String uniqueId;
    private String name;
    private String barcode;
    private String model;
    private String description;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<BarterProduct> barterProductsById;
    private Collection<BuyingProduct> buyingProductsById;
    private Collection<DeliveryProduct> deliveryProductsById;
    private ProductSubCategory productSubCategoryByProductSubCategoryId;
    private ProductDealingType productDealingTypeByProductDealingTypeId;
    private Shop shopByShopId;
    private ProductCondition productConditionByProductConditionId;
    private Brand brandByBrandId;
    private Collection<ProductImage> productImagesById;
    private Collection<ProductRating> productRatingsById;
    private Collection<SavedProduct> savedProductsById;
    private Collection<Specification> specificationsById;
    private Collection<ViewedProduct> viewedProductsById;
    private Collection<Wishlist> wishlistsById;

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
    @Column(name = "product_sub_category_id", nullable = false)
    public int getProductSubCategoryId() {
        return productSubCategoryId;
    }

    public void setProductSubCategoryId(int productSubCategoryId) {
        this.productSubCategoryId = productSubCategoryId;
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
    @Column(name = "shop_id", nullable = false)
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "product_condition_id", nullable = false)
    public int getProductConditionId() {
        return productConditionId;
    }

    public void setProductConditionId(int productConditionId) {
        this.productConditionId = productConditionId;
    }

    @Basic
    @Column(name = "brand_id", nullable = false)
    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "unique_id", nullable = true, length = 45)
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "barcode", nullable = true, length = 50)
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Basic
    @Column(name = "model", nullable = true, length = 100)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Product product = (Product) o;
        return id == product.id &&
                productSubCategoryId == product.productSubCategoryId &&
                productDealingTypeId == product.productDealingTypeId &&
                shopId == product.shopId &&
                productConditionId == product.productConditionId &&
                brandId == product.brandId &&
                Objects.equals(uniqueId, product.uniqueId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(barcode, product.barcode) &&
                Objects.equals(model, product.model) &&
                Objects.equals(description, product.description) &&
                Objects.equals(savedAt, product.savedAt) &&
                Objects.equals(lastUpdatedAt, product.lastUpdatedAt) &&
                Objects.equals(deletedAt, product.deletedAt) &&
                Objects.equals(status, product.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productSubCategoryId, productDealingTypeId, shopId, productConditionId, brandId, uniqueId, name, barcode, model, description, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<BarterProduct> getBarterProductsById() {
        return barterProductsById;
    }

    public void setBarterProductsById(Collection<BarterProduct> barterProductsById) {
        this.barterProductsById = barterProductsById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<BuyingProduct> getBuyingProductsById() {
        return buyingProductsById;
    }

    public void setBuyingProductsById(Collection<BuyingProduct> buyingProductsById) {
        this.buyingProductsById = buyingProductsById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<DeliveryProduct> getDeliveryProductsById() {
        return deliveryProductsById;
    }

    public void setDeliveryProductsById(Collection<DeliveryProduct> deliveryProductsById) {
        this.deliveryProductsById = deliveryProductsById;
    }

    @ManyToOne
    @JoinColumn(name = "product_sub_category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ProductSubCategory getProductSubCategoryByProductSubCategoryId() {
        return productSubCategoryByProductSubCategoryId;
    }

    public void setProductSubCategoryByProductSubCategoryId(ProductSubCategory productSubCategoryByProductSubCategoryId) {
        this.productSubCategoryByProductSubCategoryId = productSubCategoryByProductSubCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "product_dealing_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ProductDealingType getProductDealingTypeByProductDealingTypeId() {
        return productDealingTypeByProductDealingTypeId;
    }

    public void setProductDealingTypeByProductDealingTypeId(ProductDealingType productDealingTypeByProductDealingTypeId) {
        this.productDealingTypeByProductDealingTypeId = productDealingTypeByProductDealingTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Shop getShopByShopId() {
        return shopByShopId;
    }

    public void setShopByShopId(Shop shopByShopId) {
        this.shopByShopId = shopByShopId;
    }

    @ManyToOne
    @JoinColumn(name = "product_condition_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public ProductCondition getProductConditionByProductConditionId() {
        return productConditionByProductConditionId;
    }

    public void setProductConditionByProductConditionId(ProductCondition productConditionByProductConditionId) {
        this.productConditionByProductConditionId = productConditionByProductConditionId;
    }

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Brand getBrandByBrandId() {
        return brandByBrandId;
    }

    public void setBrandByBrandId(Brand brandByBrandId) {
        this.brandByBrandId = brandByBrandId;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<ProductImage> getProductImagesById() {
        return productImagesById;
    }

    public void setProductImagesById(Collection<ProductImage> productImagesById) {
        this.productImagesById = productImagesById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<ProductRating> getProductRatingsById() {
        return productRatingsById;
    }

    public void setProductRatingsById(Collection<ProductRating> productRatingsById) {
        this.productRatingsById = productRatingsById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<SavedProduct> getSavedProductsById() {
        return savedProductsById;
    }

    public void setSavedProductsById(Collection<SavedProduct> savedProductsById) {
        this.savedProductsById = savedProductsById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<Specification> getSpecificationsById() {
        return specificationsById;
    }

    public void setSpecificationsById(Collection<Specification> specificationsById) {
        this.specificationsById = specificationsById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<ViewedProduct> getViewedProductsById() {
        return viewedProductsById;
    }

    public void setViewedProductsById(Collection<ViewedProduct> viewedProductsById) {
        this.viewedProductsById = viewedProductsById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<Wishlist> getWishlistsById() {
        return wishlistsById;
    }

    public void setWishlistsById(Collection<Wishlist> wishlistsById) {
        this.wishlistsById = wishlistsById;
    }
}
