package com.aradnab.boot.consumer.service.service_controller;

import com.aradnab.boot.db_tier.entity.ProductMainCategory;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface ProductMainCategoryServiceInterface extends ServiceController<ProductMainCategory> {
    ProductMainCategory getByName(String name);
}
