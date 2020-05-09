package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.City;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface CityServiceInterface extends ServiceController<City> {
    City getByName(@Param("id") String name);
}
