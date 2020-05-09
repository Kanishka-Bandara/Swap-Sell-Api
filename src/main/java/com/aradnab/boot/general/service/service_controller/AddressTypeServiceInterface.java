package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.AddressType;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface AddressTypeServiceInterface extends ServiceController<AddressType> {
    AddressType getByName(@Param("id") String name);
}
