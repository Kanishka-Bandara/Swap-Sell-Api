package com.aradnab.boot.consumer.model;

import com.aradnab.boot.config.DefaultModel;
import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.ProductHeadCategory;
import com.aradnab.boot.db_tier.entity.ProductMainCategory;
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
public class HeadCategoryModel extends DefaultModel<HeadCategoryModel, ProductHeadCategory> {
    int catID;
    String catName;
    String imgPath;
    List<MainCategoryModel> mainCategories;
    byte status;
    public static HeadCategoryModel defaultModel;

    @Override
    public HeadCategoryModel entityToModel(ProductHeadCategory service) {
        List<ProductMainCategory> mc = new ArrayList<>();
        service.getProductMainCategoriesById().forEach(productSubCategory -> {
            if (productSubCategory.getStatus() != Status.DELETE_STATUS) {
                mc.add(productSubCategory);
            }
        });
        return new HeadCategoryModel(
                service.getId(),
                service.getCategoryName(),
                null,
                MainCategoryModel.defaultModel.entityToModel(mc),
                service.getStatus()
        );
    }
}