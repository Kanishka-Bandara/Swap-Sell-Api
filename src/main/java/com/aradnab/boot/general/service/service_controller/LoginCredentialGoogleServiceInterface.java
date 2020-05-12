package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.LoginCredentialGoogle;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface LoginCredentialGoogleServiceInterface extends ServiceController<LoginCredentialGoogle> {
    User getAuth(String gId);
}
