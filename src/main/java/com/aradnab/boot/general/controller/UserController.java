package com.aradnab.boot.general.controller;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.*;
import com.aradnab.boot.general.model.AddressModel;
import com.aradnab.boot.general.model.UserModel;
import com.aradnab.boot.general.service.*;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import com.aradnab.boot.general.service.service_controller.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    AddressController addressController;
    @Autowired
    EmailService emailService;
    @Autowired
    AddressService addressService;
    @Autowired
    EmailController emailController;
    @Autowired
    ContactNumberController contactNumberController;
    @Autowired
    ContactNumberService contactNumberService;

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
        Title title = titleService.getByTitleName(userModel.getTitle());
        Gender gender = genderService.getGenderByName(userModel.getGender());
        Country country = countryService.getByName(userModel.getCountry());
        u.setTitleId(title.getId());
        u.setGenderId(gender.getId());
        u.setImageId(image.getId());
        u.setCountryId(country.getId());
//        BEGIN::FIND User type id
        UserType userType = UserType.valueOf(userModel.getUserType().toString());
//        END::FIND User type id
        u.setUserTypeId(userType.getId());
        u.setUserId(userService.generateUserID(userModel.getUserType()));
        u.setfName(userModel.getFName());
        u.setlName(userModel.getLName());
        u.setsName(userModel.getSName());
        u.setFullName(userModel.getFullName());
        u.setActiveState(userModel.getActiveState());
        u.setSavedAt(d);
        u.setLastUpdatedAt(d);
        u.setStatus(Status.LIVE_ACTIVE_STATUS);
        u.setTitleByTitleId(title);
        u.setGenderByGenderId(gender);
        u.setImageByImageId(image);
        u.setCountryByCountryId(country);
        User nu = userService.create(u);
//        BEGIN::Creat User Address
        if (userModel.getAddresses() != null) {
            if (userModel.getAddresses().size() > 0) {
                userModel.getAddresses().forEach(addressModel -> {
                    addressModel.setUserId(nu.getId());
                    AddressModel ad = addressController.create(addressModel).getBody();
                    u.setAddressesById(nu.getAddressesById());
                });
            }
        }
        u.setAddressesById(addressService.getByUserIdAsCollection(nu.getId()));
//        END::Creat User Address
//        BEGIN::Create User Email
        if (userModel.getEmails() != null) {
            if (userModel.getEmails().size() > 0) {
                userModel.getEmails().forEach(emailModel -> {
                    emailModel.setUserId(nu.getId());
                    emailController.create(emailModel);
                });
            }
        }
        u.setEmailsById(emailService.getByUserIdAsCollection(nu.getId()));
//        END::Create User Email
//        BEGIN::Create User Contact Number
        if (userModel.getContactNumbers() != null) {
            if (userModel.getContactNumbers().size() > 0) {
                userModel.getContactNumbers().forEach(contactNumberModel -> {
                    contactNumberModel.setUserId(nu.getId());
                    contactNumberController.create(contactNumberModel);
                });
            }
        }
        u.setContactNumbersById(contactNumberService.getByUserIdAsCollection(nu.getId()));
//        END::Create User Contact Number
        return get(nu.getId());
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
