package com.aradnab.boot.general.controller;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Image;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.general.model.UserModel;
import com.aradnab.boot.general.service.*;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import com.aradnab.boot.general.service.service_controller.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TitleService titleService;
    @Autowired
    GenderService genderService;
    @Autowired
    ImageService imageService;
    @Autowired
    CountryService countryService;

    @PostMapping("/create")
    public ResponseEntity<UserModel> create(@RequestBody UserModel userModel) {
        Date d = new Date();
        Image i = new Image();
        i.setImgUrl(userModel.getProfilePicUrl());
        i.setSavedAt(d);
        i.setLastUpdatedAt(d);
        i.setStatus(Status.LIVE_ACTIVE_STATUS);
        Image image = imageService.create(i);
        User u = new User();
        u.setTitleId(titleService.getByTitleName(userModel.getTitle()).getId());
        u.setGenderId(genderService.getGenderByName(userModel.getGender()).getId());
        u.setImageId(image.getId());
        u.setCountryId(countryService.getByName(userModel.getCountry()).getId());
        u.setUserTypeId(UserType.getUserTypeForEntity(userModel.getUserType()).getId());
        u.setUserId(userService.generateUserID(userModel.getUserType()));
        u.setfName(userModel.getFName());
        u.setlName(userModel.getLName());
        u.setsName(userModel.getSName());
        u.setFullName(userModel.getFullName());
        u.setActiveState(userModel.getActiveState());
        u.setSavedAt(d);
        u.setLastUpdatedAt(d);
        u.setStatus(Status.LIVE_ACTIVE_STATUS);
        return ResponseEntity.ok().body(UserModel.entityToModel(userService.create(u)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.ok().body(UserModel.entityToModel(userService.getAll()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserModel> get(@PathVariable int id) {
        return ResponseEntity.ok().body(UserModel.entityToModel(userService.getByID(id)));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<CRUDStatus> delete(@PathVariable int id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }




}
