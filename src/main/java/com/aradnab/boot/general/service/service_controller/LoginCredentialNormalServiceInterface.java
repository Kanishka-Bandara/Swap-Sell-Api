package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.LoginCredentialNormal;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface LoginCredentialNormalServiceInterface extends ServiceController<LoginCredentialNormal> {
    User getAuth(String un, String pw);
}
