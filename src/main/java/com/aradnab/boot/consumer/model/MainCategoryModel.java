package com.aradnab.boot.consumer.model;

import com.aradnab.boot.config.DefaultModel;
import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.ProductMainCategory;
import com.aradnab.boot.db_tier.entity.ProductSubCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainCategoryModel extends DefaultModel<MainCategoryModel, ProductMainCategory> {
    int catID;
    String catName;
    String imgPath;
    //    HeadCategoryModel headCategory;
    int headCategoryId;
    List<SubCategoryModel> subCategories;
    byte status;
    public static MainCategoryModel defaultModel;

    @Override
    public MainCategoryModel entityToModel(ProductMainCategory service) {
        List<ProductSubCategory> sc = new ArrayList<>();
        service.getProductSubCategoriesById().forEach(productSubCategory -> {
            if (productSubCategory.getStatus() != Status.DELETE_STATUS) {
                sc.add(productSubCategory);
            }
        });
        return new MainCategoryModel(
                service.getId(),
                service.getCategoryName(),
                null,
                service.getProductHeadCategoryId(),
//                HeadCategoryModel.defaultModel.entityToModel(service.getProductHeadCategoryByProductHeadCategoryId()),
                SubCategoryModel.defaultModel.entityToModel(sc),
                service.getStatus()
        );
    }
}
