package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Address;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressServiceInterface extends ServiceController<Address> {
    List<Address> getByTypeId(@Param("typeId") int typeId, @Param("userId") int userId);

    Address getDefaultByTypeId(@Param("typeId") int typeId, @Param("userId") int userId);
}
