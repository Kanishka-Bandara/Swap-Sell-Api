package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.UserNotification;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;

import java.util.List;

public interface UserNotificationServiceInterface extends ServiceController<UserNotification> {
    List<UserNotification> getByUserId(int userId);

    UserNotification setAsRead(int id);

    List<UserNotification> setAsRead(List<Integer> ids);

    boolean setAllAsRead(int userId);
}
