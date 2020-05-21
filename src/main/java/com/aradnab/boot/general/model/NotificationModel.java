package com.aradnab.boot.general.model;

import com.aradnab.boot.db_tier.entity.Notification;
import com.aradnab.boot.db_tier.entity.UserNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationModel {

    int id;
    String notificationHeader;
    String notification;
    Date date;
    int isRead;
    int state;


    public static NotificationModel entityToModel(UserNotification userNotification) {
        Notification notification = userNotification.getNotificationByNotificationId();
        return new NotificationModel(
                notification.getId(),
                "Notification",
                notification.getNotification(),
                userNotification.getSavedAt(),
                userNotification.getIsRead(),
                userNotification.getStatus()
        );
    }

    public static List<NotificationModel> entityToModel(List<UserNotification> userNotification) {
        List<NotificationModel> l = new ArrayList<>();
        userNotification.forEach(userNotification1 -> l.add(entityToModel(userNotification1)));
        return l;
    }

    public static List<NotificationModel> entityToModel(Collection<UserNotification> userNotification) {
        List<NotificationModel> l = new ArrayList<>();
        userNotification.forEach(userNotification1 -> l.add(entityToModel(userNotification1)));
        return l;
    }
}
