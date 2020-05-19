package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Shop;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;

public interface ShopServiceInterface extends ServiceController<Shop> {
    Shop getByUserID(int userId);
}
