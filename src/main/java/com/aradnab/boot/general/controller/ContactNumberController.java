package com.aradnab.boot.general.controller;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.ContactNumber;
import com.aradnab.boot.db_tier.entity.ContactNumberType;
import com.aradnab.boot.general.model.ContactNumberModel;
import com.aradnab.boot.general.service.ContactNumberService;
import com.aradnab.boot.general.service.ContactNumberTypeService;
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
@RequestMapping("/user/contactNumber")
public class ContactNumberController {
    @Autowired
    ContactNumberTypeService contactNumberTypeService;
    @Autowired
    ContactNumberService contactNumberService;
    @Autowired
    UserService userService;

    @GetMapping("/types")
    public ResponseEntity<Map<Integer, String>> getContactNumberTypes() {
        Map<Integer, String> t = new HashMap<>();
        contactNumberTypeService.getAll().forEach(contactNumberType -> t.put(contactNumberType.getId(), contactNumberType.getType()));
        return ResponseEntity.ok().body(t);
    }

    @GetMapping("/type/getById/{id}")
    public ResponseEntity<String> getTypeById(@PathVariable int id) {
        return ResponseEntity.ok().body(contactNumberTypeService.getByID(id).getType());
    }

    @GetMapping("/type/getIdByType/{type}")
    public ResponseEntity<Integer> typeIdByType(@PathVariable String type) {
        return ResponseEntity.ok().body(contactNumberTypeService.getByName(type).getId());
    }

    @PostMapping("/create")
    public ResponseEntity<ContactNumberModel> create(@RequestBody ContactNumberModel contactNumberModel) {
        Date d = new Date();
        ContactNumber e = new ContactNumber();
        ContactNumberType contactNumberType = contactNumberTypeService.getByID(contactNumberModel.getContactNumberTypeId());
        e.setContactNumberTypeId(contactNumberType.getId());
        e.setUserId(contactNumberModel.getUserId());
        e.setNumber(contactNumberModel.getContactNumber());
        e.setIsDefault(contactNumberModel.getIsDefault());
        e.setSavedAt(d);
        e.setLastUpdatedAt(d);
        e.setStatus(Status.LIVE_ACTIVE_STATUS);
        e.setContactNumberTypeByContactNumberTypeId(contactNumberType);
        return ResponseEntity.ok().body(ContactNumberModel.entityToModel(contactNumberService.create(e)));
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<ContactNumberModel>> getContactNumberByUserId(@PathVariable int userId) {
        return ResponseEntity.ok().body(ContactNumberModel.entityToModel(userService.getByID(userId).getContactNumbersById()));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ContactNumberModel> getContactNumberById(@PathVariable int id) {
        return ResponseEntity.ok().body(ContactNumberModel.entityToModel(contactNumberService.getByID(id)));
    }

    @PostMapping("/edit")
    public ResponseEntity<ContactNumberModel> edit(@RequestBody ContactNumberModel contactNumberModel) {
        this.deleteById(contactNumberModel.getId());
        return this.create(contactNumberModel);
    }

    @GetMapping("/deleteById/{id}")
    public ResponseEntity<CRUDStatus> deleteById(@PathVariable int id) {
        return ResponseEntity.ok().body(contactNumberService.delete(id));
    }

}
