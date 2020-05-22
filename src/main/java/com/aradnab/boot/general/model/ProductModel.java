package com.aradnab.boot.general.model;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.*;
import com.aradnab.boot.general.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Autowired
    static ImageService imageService;

    int id;
    String uniqueID;
    String name;
    int headCategoryId;//
    int mainCategoryId;//
    int subCategoryId;//
    String headCategory;
    String mainCategory;
    String subCategory;
    String model;
    int brandId;//
    String brand;
    List<Map<Integer, String>> images;//
    String description;
    String barcode;
    Map<String, String> specifications;
    int conditionId;//
    String condition;
    String inTheBox;
    double rating;
    int qty;
    int currencyId;//
    String currency;
    int dealingStateId;//
    String dealingState;
    double barterPrice;
    double retailPrice;
    double deliveryPrice;
    int discountId;//
    double discountPrice;
    int saleCount;
    int barterCount;
    int returnCount;
    ShopModel shop;
    int isFavorite;
    int status;

    public static ProductModel entityToModel(DeliveryProduct deliveryProduct) {
        Product product = deliveryProduct.getProductByProductId();
        ProductSubCategory subCategory = product.getProductSubCategoryByProductSubCategoryId();
        ProductMainCategory mainCategory = subCategory.getProductMainCategoryByProductMainCategoryId();
        ProductHeadCategory headCategory = mainCategory.getProductHeadCategoryByProductHeadCategoryId();
        List<Map<Integer, String>> images = new ArrayList<>();
        product.getProductImagesById().forEach(productImage -> {
            if (productImage.getStatus() != Status.DELETE_STATUS) {
                Image image = productImage.getImageByImageId();
                Map<Integer, String> m = new HashMap<>();
                m.put(image.getId(), imageService.getSendAbleProductImageUrl(image.getImgUrl()));
                images.add(m);
            }
        });
        Map<String, String> specifications = new HashMap<>();
        product.getSpecificationsById().forEach(specification -> {
            if (specification.getStatus() != Status.DELETE_STATUS) {
                specifications.put(specification.getKey(),specification.getValue());
            }
        });
        Discount d = null;
        ProductModel pm = new ProductModel();
        pm.setId(product.getId());
        pm.setUniqueID(product.getUniqueId());
        pm.setName(product.getName());
        pm.setHeadCategoryId(headCategory.getId());
        pm.setHeadCategory(headCategory.getCategoryName());
        pm.setMainCategoryId(mainCategory.getId());
        pm.setMainCategory(mainCategory.getCategoryName());
        pm.setSubCategoryId(subCategory.getId());
        pm.setSubCategory(subCategory.getCategoryName());
        pm.setModel(product.getModel());
        pm.setBrandId(product.getBrandId());
        pm.setBrand(product.getBrandByBrandId().getBrand());
        pm.setImages(images);
        pm.setDescription(product.getDescription());
        pm.setBarcode(product.getBarcode());
        pm.setSpecifications(specifications);
        pm.setConditionId(product.getProductConditionId());
        pm.setCondition(product.getProductConditionByProductConditionId().getCondition());
        pm.setInTheBox(deliveryProduct.getInTheBox());
        pm.setRating(0);//TODO::connect with suitable controller
        pm.setQty(deliveryProduct.getQty());
        pm.setCurrencyId(deliveryProduct.getCurrencyId());
        pm.setCurrency(deliveryProduct.getCurrencyByCurrencyId().getCurrency());
        pm.setDealingStateId(product.getProductDealingTypeId());
        pm.setDealingState(product.getProductDealingTypeByProductDealingTypeId().getType());
        pm.setBarterPrice(deliveryProduct.getBarterPrice());
        pm.setRetailPrice(deliveryProduct.getRetailPrice());
        pm.setDeliveryPrice(0.0);
        pm.setDiscountPrice(0.0);//TODO::connect with discount controller
        pm.setSaleCount(0);//TODO::connect with suitable controller
        pm.setReturnCount(0);//TODO::connect with suitable controller
        pm.setShop(ShopModel.entityToModel(product.getShopByShopId()));
        pm.setIsFavorite(0);//TODO::connect with suitable controller
        pm.setStatus(deliveryProduct.getStatus());
        return pm;
    }

    public static List<ProductModel> entityToModel(List<DeliveryProduct> list) {
        List<ProductModel> l = new ArrayList<>();
        list.forEach(deliveryProduct -> {
            if (deliveryProduct.getStatus() == com.aradnab.boot.config.Status.LIVE_ACTIVE_STATUS) {
                l.add(entityToModel(deliveryProduct));
            }
        });
        return l;
    }

    public static List<ProductModel> entityToModel(Collection<DeliveryProduct> list) {
        List<ProductModel> l = new ArrayList<>();
        list.forEach(deliveryProduct -> {
            if (deliveryProduct.getStatus() == com.aradnab.boot.config.Status.LIVE_ACTIVE_STATUS) {
                l.add(entityToModel(deliveryProduct));
            }
        });
        return l;
    }

}
