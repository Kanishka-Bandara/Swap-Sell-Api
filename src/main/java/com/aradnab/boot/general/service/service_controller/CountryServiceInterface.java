package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Country;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface CountryServiceInterface extends ServiceController<Country> {
    Country getByName(@Param("id") String name);
}
