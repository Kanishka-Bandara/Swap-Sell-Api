package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.ContactNumber;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ContactNumberServiceInterface extends ServiceController<ContactNumber> {
    List<ContactNumber> getByTypeId(@Param("typeId") int typeId, @Param("userId") int userId);

    ContactNumber getDefaultByTypeId(@Param("typeId") int typeId, @Param("userId") int userId);

    List<ContactNumber> getByUserId(@Param("userId") int userId);

    Collection<ContactNumber> getByUserIdAsCollection(@Param("userId") int userId);
}
