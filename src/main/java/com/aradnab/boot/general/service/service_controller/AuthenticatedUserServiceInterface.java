package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.AuthenticatedUser;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface AuthenticatedUserServiceInterface extends ServiceController<AuthenticatedUser> {
}
