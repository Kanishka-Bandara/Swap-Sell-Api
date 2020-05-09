package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.ContactNumberType;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface ContactNumberTypeServiceInterface extends ServiceController<ContactNumberType> {
    ContactNumberType getByName(@Param("id") String name);
}
