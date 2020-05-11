package com.aradnab.boot.general.controller;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Email;
import com.aradnab.boot.db_tier.entity.EmailType;
import com.aradnab.boot.general.model.EmailModel;
import com.aradnab.boot.general.service.EmailService;
import com.aradnab.boot.general.service.EmailTypeService;
import com.aradnab.boot.general.service.UserService;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/email")
public class EmailController {
    @Autowired
    EmailTypeService emailTypeService;
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;

    @GetMapping("/types")
    public ResponseEntity<Map<Integer, String>> getEmailTypes() {
        Map<Integer, String> t = new HashMap<>();
        emailTypeService.getAll().forEach(emailType -> t.put(emailType.getId(), emailType.getType()));
        return ResponseEntity.ok().body(t);
    }

    @GetMapping("/type/getById/{id}")
    public ResponseEntity<String> getTypeById(@PathVariable int id) {
        return ResponseEntity.ok().body(emailTypeService.getByID(id).getType());
    }

    @GetMapping("/type/getIdByType/{type}")
    public ResponseEntity<Integer> typeIdByType(@PathVariable String type) {
        return ResponseEntity.ok().body(emailTypeService.getByName(type).getId());
    }

    @PostMapping("/create")
    public ResponseEntity<EmailModel> create(@RequestBody EmailModel emailModel) {
        Date d = new Date();
        Email e = new Email();
        EmailType emailType = emailTypeService.getByID(emailModel.getEmailTypeId());
        e.setEmailTypeId(emailType.getId());
        e.setUserId(emailModel.getUserId());
        e.setEmail(emailModel.getEmail());
        e.setIsDefault(emailModel.getIsDefault());
        e.setSavedAt(d);
        e.setLastUpdatedAt(d);
        e.setStatus(Status.LIVE_ACTIVE_STATUS);
        e.setEmailTypeByEmailTypeId(emailType);
        return ResponseEntity.ok().body(EmailModel.entityToModel(emailService.create(e)));
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<EmailModel>> getEmailByUserId(@PathVariable int userId) {
        return ResponseEntity.ok().body(EmailModel.entityToModel(userService.getByID(userId).getEmailsById()));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EmailModel> getEmailById(@PathVariable int id) {
        return ResponseEntity.ok().body(EmailModel.entityToModel(emailService.getByID(id)));
    }

    @PostMapping("/edit")
    public ResponseEntity<EmailModel> edit(@RequestBody EmailModel emailModel) {
        this.deleteById(emailModel.getId());
        return this.create(emailModel);
    }

    @GetMapping("/deleteById/{id}")
    public ResponseEntity<CRUDStatus> deleteById(@PathVariable int id) {
        return ResponseEntity.ok().body(emailService.delete(id));
    }

}
