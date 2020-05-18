package com.aradnab.boot.general.controller;

import com.aradnab.boot.config.ResourceUrl;
import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.*;
import com.aradnab.boot.general.model.AddressModel;
import com.aradnab.boot.general.model.UserModel;
import com.aradnab.boot.general.service.*;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import com.aradnab.boot.general.service.service_controller.UserType;
import com.aradnab.boot.validator.TextValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        u.setFName(userModel.getFName());
        u.setLName(userModel.getLName());
        System.out.println("f name = " + userModel.getFName());
        System.out.println("l name = " + userModel.getLName());
        u.setSName(userModel.getSName());
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
    //    BEGIN::EDIT USER

    @PostMapping("/edit")
    public ResponseEntity<UserModel> edit(@RequestBody UserModel userModel) {
        Date d = new Date();
        boolean isUserModified = false;
        User userOld = userService.getByID(userModel.getId());
        Gender genderOld = userOld.getGenderByGenderId();
        if (!userOld.getTitleByTitleId().getTitle().equals(userModel.getTitle())) {
            Title title = titleService.getByTitleName(userModel.getTitle());
            userOld.setTitleId(title.getId());
            userOld.setTitleByTitleId(title);
            isUserModified = true;
        }
        if (!userOld.getGenderByGenderId().getGender().equals(userModel.getGender())) {
            Gender gender = genderService.getGenderByName(userModel.getGender());
            userOld.setGenderId(gender.getId());
            userOld.setGenderByGenderId(gender);
            isUserModified = true;
        }
        if (!userOld.getCountryByCountryId().getCountry().equals(userModel.getCountry())) {
            Country country = countryService.getByName(userModel.getCountry());
            userOld.setCountryId(country.getId());
            userOld.setCountryByCountryId(country);
            isUserModified = true;
        }
        if (!userOld.getUserId().equals(userModel.getUserId())) {
            userOld.setUserId(userModel.getUserId());
            isUserModified = true;
        }
        if (!TextValidator.equals(userOld.getFName(),userModel.getFName())) {
            userOld.setFName(userModel.getFName());
            isUserModified = true;
        }
        if (!TextValidator.equals(userOld.getLName(),userModel.getLName())) {
            userOld.setLName(userModel.getLName());
            isUserModified = true;
        }
        if (!TextValidator.equals(userOld.getSName(),userModel.getSName())) {
            userOld.setSName(userModel.getSName());
            isUserModified = true;
        }
        if (!TextValidator.equals(userOld.getFullName(),userModel.getFullName())) {
            userOld.setFullName(userModel.getFullName());
            isUserModified = true;
        }
        if (userOld.getActiveState() != userModel.getActiveState()) {
            userOld.setActiveState(userModel.getActiveState());
            isUserModified = true;
        }
        if (isUserModified) {
            userOld.setLastUpdatedAt(d);
            return ResponseEntity.ok().body(UserModel.entityToModel(userService.update(userOld)));
        }
//        END::Create User Contact Number
        return null;
    }
    //    END::EDIT USER

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

    @GetMapping("/title/getAll")
    public ResponseEntity<List<Map<String, String>>> getAllTitles() {
        List<Map<String, String>> l = new ArrayList<>();
        titleService.getAll().forEach(title -> {
            Map<String, String> m = new HashMap<>();
            m.put("id", title.getId() + "");
            m.put("title", title.getTitle());
            l.add(m);
        });
        return ResponseEntity.ok().body(l);
    }

    @PostMapping("/updateProfilePicture")
    public ResponseEntity updateProfilePicture(@RequestBody Map<String, String> body) throws Exception {
        String url = imageService.writeProfileImage(body);
        String userId = body.get("userId");
        Image img = userService.getByID(Integer.parseInt(userId)).getImageByImageId();
        img.setImgUrl(url);
        Image update = imageService.update(img);
        Map<String, String> response = new HashMap<>();
        response.put("profilePicUrl", ResourceUrl.VIRTUAL_HOST_URL + update.getImgUrl());
        response.put("userId", userId);
        return ResponseEntity.ok().body(response);
    }


}
