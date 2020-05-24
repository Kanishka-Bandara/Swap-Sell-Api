package com.aradnab.boot.consumer.model;

import com.aradnab.boot.config.DefaultModel;
import com.aradnab.boot.db_tier.entity.ProductSubCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryModel extends DefaultModel<SubCategoryModel, ProductSubCategory> {
    int catID;
    String catName;
    String imgPath;
    MainCategoryModel mainCategory;
    byte status;
    public static SubCategoryModel defaultModel;

    @Override
    public SubCategoryModel entityToModel(ProductSubCategory service) {
        return new SubCategoryModel(service.getId(), service.getCategoryName(), null, MainCategoryModel.defaultModel.entityToModel(service.getProductMainCategoryByProductMainCategoryId()), service.getStatus());
    }
}
