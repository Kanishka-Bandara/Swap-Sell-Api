package com.aradnab.boot.consumer.service.service_controller;

import com.aradnab.boot.db_tier.entity.ProductImage;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageServiceInterface extends ServiceController<ProductImage> {
    List<ProductImage> getByProductId(int productId);
}
