package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Province;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceServiceInterface extends ServiceController<Province> {
    Province getByName(@Param("id") String name);
    List<Province> getProvincesByCountryId(@Param("id")int id);
}
