package com.aradnab.boot.consumer.service.service_controller;

import com.aradnab.boot.db_tier.entity.ProductCondition;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface ProductConditionServiceInterface extends ServiceController<ProductCondition> {
    ProductCondition getByName(String condition);
}
