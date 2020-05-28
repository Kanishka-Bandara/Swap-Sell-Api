package com.aradnab.boot.consumer.service.service_controller;

import com.aradnab.boot.db_tier.entity.DeliveryProduct;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryProductServiceInterface extends ServiceController<DeliveryProduct> {
    DeliveryProduct getByProductId(int id);

    List<DeliveryProduct> getByUserId(int userId);
}
