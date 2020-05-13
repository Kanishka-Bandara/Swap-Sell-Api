package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface UserServiceInterface extends ServiceController<User> {
    String generateUserID(UserType userType);
    boolean isUserNameAlreadyExists(String username);
}
