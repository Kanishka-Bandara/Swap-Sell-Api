package com.aradnab.boot.consumer.service.service_controller;

import com.aradnab.boot.db_tier.entity.ProductDealingType;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface ProductDealingTypeServiceInterface extends ServiceController<ProductDealingType> {
    ProductDealingType getByName(String type);
}
