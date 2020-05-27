package com.aradnab.boot.consumer.controller;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.model.ProductModel;
import com.aradnab.boot.consumer.service.*;
import com.aradnab.boot.db_tier.entity.*;
import com.aradnab.boot.general.service.ImageService;
import com.aradnab.boot.general.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductHeadCategoryService productHeadCategoryService;
    @Autowired
    ProductMainCategoryService productMainCategoryService;
    @Autowired
    ProductSubCategoryService productSubCategoryService;
    @Autowired
    ProductDealingTypeService productDealingTypeService;
    @Autowired
    ShopService shopService;
    @Autowired
    ProductConditionService productConditionService;
    @Autowired
    BrandService brandService;
    @Autowired
    SpecificationService specificationService;
    @Autowired
    ImageService imageService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    DiscountService discountService;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    DeliveryProductService deliveryProductService;

    @PostMapping("/create")
    public ResponseEntity<ProductModel> create(@RequestBody ProductModel productModel) {
        Date d = new Date();
//        BEGIN::Head Category getting or saving
        ProductHeadCategory headCategory = productHeadCategoryService.getByName(productModel.getHeadCategory());
        if (headCategory == null) {
            ProductHeadCategory hc = new ProductHeadCategory();
            hc.setCategoryName(productModel.getHeadCategory());
            hc.setSavedAt(d);
            hc.setLastUpdatedAt(d);
            headCategory = productHeadCategoryService.create(hc);
        }
//        END::Head Category getting or saving
//        BEGIN::Main Category getting or saving
        ProductMainCategory mainCategory = productMainCategoryService.getByName(productModel.getMainCategory());
        if (mainCategory == null) {
            ProductMainCategory hc = new ProductMainCategory();
            hc.setProductHeadCategoryId(headCategory.getId());
            hc.setProductHeadCategoryByProductHeadCategoryId(headCategory);
            hc.setCategoryName(productModel.getMainCategory());
            hc.setSavedAt(d);
            hc.setLastUpdatedAt(d);
            mainCategory = productMainCategoryService.create(hc);
        }
//        END::Main Category getting or saving
//        BEGIN::Sub Category getting or saving
        ProductSubCategory subCategory = productSubCategoryService.getByName(productModel.getSubCategory());
        if (subCategory == null) {
            ProductSubCategory hc = new ProductSubCategory();
            hc.setProductMainCategoryId(mainCategory.getId());
            hc.setProductMainCategoryByProductMainCategoryId(mainCategory);
            hc.setCategoryName(productModel.getSubCategory());
            hc.setSavedAt(d);
            hc.setLastUpdatedAt(d);
            subCategory = productSubCategoryService.create(hc);
        }
//        END::Sub Category getting or saving
//        BEGIN::Product dealing type getting
        ProductDealingType dealingType = productDealingTypeService.getByName(productModel.getDealingState());
//        END::Product dealing type getting
//        BEGIN::Product condition getting
        ProductCondition condition = productConditionService.getByName(productModel.getCondition());
//        END::Product condition getting
//        BEGIN::Getting Shop
        Shop shop = shopService.getByID(productModel.getShop().getId());
//        END::Getting Shop
//        BEGIN::Saving product brand
        Brand brand = new Brand();
        brand.setBrand(productModel.getBrand());
        brand.setSavedAt(d);
        brand.setLastUpdatedAt(d);
        brand.setStatus(Status.LIVE_ACTIVE_STATUS);
        brand = brandService.create(brand);
//        END::Saving product brand
//        BEGIN::Saving product
        Product product = new Product();
        product.setProductSubCategoryId(subCategory.getId());
        product.setProductSubCategoryByProductSubCategoryId(subCategory);
        product.setProductDealingTypeId(dealingType.getId());
        product.setProductDealingTypeByProductDealingTypeId(dealingType);
        product.setShopId(shop.getId());
        product.setShopByShopId(shop);
        product.setProductConditionId(condition.getId());
        product.setProductConditionByProductConditionId(condition);
        product.setBrandId(brand.getId());
        product.setBrandByBrandId(brand);
        product.setUniqueId("p_" + d.getTime());
        product.setName(productModel.getName());
        product.setBarcode(productModel.getBarcode());
        product.setModel(productModel.getModel());
        product.setDescription(productModel.getDescription());
        product.setSavedAt(d);
        product.setLastUpdatedAt(d);
        product.setStatus(Status.LIVE_ACTIVE_STATUS);
        product = productService.create(product);
//        END::Saving product
//        BEGIN::Saving Specifications
        final int productId = product.getId();
        productModel.getSpecifications().forEach((k, v) -> {
            Specification s = new Specification();
            s.setKey(k);
            s.setValue(v);
            s.setProductId(productId);
            s.setLastUpdatedAt(d);
            s.setSavedAt(d);
            s.setStatus(Status.LIVE_ACTIVE_STATUS);
            specificationService.create(s);
        });
        product.setSpecificationsById(specificationService.getByProductId(productId));
//        END::Saving Specifications
//        BEGIN::Saving product images
        List<String> urls = new ArrayList<>();
        List<String> imgs = imageService.writeProductImages(productId, productModel.getImages());
        AtomicInteger i = new AtomicInteger();
        imgs.forEach(path -> {
            Image image = new Image();
            image.setImgUrl(path);
            image.setSavedAt(d);
            image.setLastUpdatedAt(d);
            image.setStatus(Status.LIVE_ACTIVE_STATUS);
            image = imageService.create(image);
            ProductImage productImage = new ProductImage();
            productImage.setProductId(productId);
            productImage.setImageId(image.getId());
            productImage.setOrderNumber(i.getAndIncrement());
            productImage.setSavedAt(d);
            productImage.setLastUpdatedAt(d);
            productImage.setStatus(Status.LIVE_ACTIVE_STATUS);
            productImageService.create(productImage);
            urls.add(imageService.getSendAbleProductImageUrl(path));
        });
        product.setProductImagesById(productImageService.getByProductId(productId));
//        END::Saving product images
//        BEGIN::Saving currency
        Currency currency = currencyService.getByCurrency(productModel.getCurrency());
        if (currency == null) {
            currency = new Currency();
            currency.setCurrency(productModel.getCurrency());
            currency.setSavedAt(d);
            currency.setLastUpdatedAt(d);
            currency.setStatus(Status.LIVE_ACTIVE_STATUS);
            currency = currencyService.create(currency);
        }
//        END::Saving currency
//        BEGIN::Saving delivery product
        DeliveryProduct deliveryProduct = new DeliveryProduct();
        deliveryProduct.setCurrencyId(currency.getId());
        deliveryProduct.setCurrencyByCurrencyId(currency);
        deliveryProduct.setProductId(productId);
        deliveryProduct.setProductByProductId(product);
        deliveryProduct.setRetailPrice(productModel.getRetailPrice());
        deliveryProduct.setBarterPrice(productModel.getBarterPrice());
        deliveryProduct.setQty(productModel.getQty());
        deliveryProduct.setInTheBox(productModel.getInTheBox());
        deliveryProduct.setSavedAt(d);
        deliveryProduct.setLastUpdatedAt(d);
        deliveryProduct.setStatus(Status.LIVE_ACTIVE_STATUS);
        deliveryProduct = deliveryProductService.create(deliveryProduct);
//        END::Saving  delivery product
//        BEGIN::Saving Product discount
//        if (productModel.getDiscountPrice() > 0) {
//            Discount discount = new Discount();
//            discount.setDeliveryProductId(deliveryProduct.getId());
//            discount.setDeliveryProductByDeliveryProductId(deliveryProduct);
//            discount.setDiscount(productModel.getDiscountPrice());
//            discount.setDiscountCode(null);
//            discount.set
////        discount.set;
//        }
//        END::Saving Product discount
        return ResponseEntity.ok().body(ProductModel.defaultModel.entityToModel(deliveryProduct));
    }

}

