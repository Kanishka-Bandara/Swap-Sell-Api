package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.District;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface DistrictServiceInterface extends ServiceController<District> {
    District getByName(@Param("id") String name);
}
