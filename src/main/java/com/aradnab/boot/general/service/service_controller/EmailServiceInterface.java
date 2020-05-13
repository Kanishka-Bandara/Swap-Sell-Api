package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Email;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface EmailServiceInterface extends ServiceController<Email> {
    List<Email> getByTypeId(@Param("typeId") int typeId, @Param("userId") int userId);

    Email getDefaultByTypeId(@Param("typeId") int typeId, @Param("userId") int userId);

    List<Email> getByUserId(@Param("userId") int userId);

    Collection<Email> getByUserIdAsCollection(@Param("userId") int userId);

    boolean isEmailAlreadyExists(String email);
}
