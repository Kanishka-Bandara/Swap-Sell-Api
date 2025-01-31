package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.LoginCredentialFb;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface LoginCredentialFbServiceInterface extends ServiceController<LoginCredentialFb> {
   User getAuth(String fbId);
}
