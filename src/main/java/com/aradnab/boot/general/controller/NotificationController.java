package com.aradnab.boot.general.controller;

import com.aradnab.boot.general.model.NotificationModel;
import com.aradnab.boot.general.service.NotificationService;
import com.aradnab.boot.general.service.UserNotificationService;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;
    @Autowired
    UserNotificationService userNotificationService;

    //    BEGIN::Get all user notifications
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<List<NotificationModel>> getAllByUser(@PathVariable("userId") int userId) {
        return ResponseEntity.ok().body(NotificationModel.defaultModel.entityToModel(userNotificationService.getByUserId(userId)));
    }
    //    END::Get all user notifications

    //    BEGIN::Get user notification by notification id
    @GetMapping("/get/{id}")
    public ResponseEntity<NotificationModel> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(NotificationModel.defaultModel.entityToModel(userNotificationService.getByID(id)));
    }
    //    END::Get user notification by notification id

    //    BEGIN::Set User Notification as read
    @PostMapping("/setAsRead")
    public ResponseEntity<NotificationModel> setAsRead(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok().body(NotificationModel.defaultModel.entityToModel(userNotificationService.setAsRead(Integer.parseInt(request.get("id")))));
    }
    //    END::Set User Notification as read

    //    BEGIN::Set User Notifications as read
    @PostMapping("/setAsReadBulk/{userId}")
    public ResponseEntity<List<NotificationModel>> setAsRead(@PathVariable("userId") int userId, @RequestBody List<Integer> ids) {
        System.out.println("\n\n\n\n\n====================================");
        System.out.println(ids);
        System.out.println("\n\n\n\n\n====================================");
        return ResponseEntity.ok().body(NotificationModel.defaultModel.entityToModel(userNotificationService.setAsRead(ids)));
    }
    //    END::Set User Notifications as read

    //    BEGIN::Delete User Notification
    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        CRUDStatus status = userNotificationService.delete(id);
        if (status == CRUDStatus.DELETED)
            return ResponseEntity.ok().body(true);
        return ResponseEntity.ok().body(false);
    }
    //    END::Delete User Notification

}
