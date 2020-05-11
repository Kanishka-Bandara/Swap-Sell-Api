package com.aradnab.boot.general.model;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.general.service.service_controller.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    int id;
    String userId;
    UserType userType;
    String title;
    String gender;
    String fName;
    String lName;
    String sName;
    String fullName;
    byte activeState;
    Map<Integer, String> emails;
    String country;
    String note;
    String profilePicUrl;
    List<AddressModel> addresses;
    List<Map<String, String>> contactNumbers;
    String username;
    int status;


    public static UserModel entityToModel(User user) {
        UserModel um = new UserModel();
        um.setId(user.getId());
        um.setUserId(user.getUserId());
        um.setTitle(user.getTitleByTitleId().getTitle());
        um.setGender(user.getGenderByGenderId().getGender());
        um.setFName(user.getfName());
        um.setLName(user.getlName());
        um.setSName(user.getsName());
        um.setFullName(user.getFullName());
        um.setActiveState(user.getActiveState());
        um.setCountry(user.getCountryByCountryId().getCountry());
        um.setProfilePicUrl(user.getImageByImageId().getImgUrl());
//        BEGIN::Setting Emails
        Map<Integer, String> emails = new HashMap<>();
        user.getEmailsById().forEach(email -> {
            if (email.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                emails.put(email.getId(), email.getEmail());
            }
        });
        um.setEmails(emails);
//        END::Setting Emails
//        BEGIN::Setting Note
        user.getNotesById().forEach(note1 -> {
            if (note1.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                um.setNote(note1.getNote());
            }
        });
//        END::Setting Note
//        BEGIN::Setting Addresses
        List<AddressModel> addresses = new ArrayList<>();
        user.getAddressesById().forEach(address -> {
            if (address.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                addresses.add(AddressModel.entityToModel(address));
            }
        });
        um.setAddresses(addresses);
//        END::Setting Addresses
//        BEGIN::Setting Contact Numbers
        List<Map<String, String>> contactNumbers = new ArrayList<>();
        user.getContactNumbersById().forEach(contactNumber -> {
            if (contactNumber.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                Map<String, String> cn = new HashMap<>();
                cn.put("id", contactNumber.getId() + "");
                cn.put("type_id", contactNumber.getContactNumberTypeByContactNumberTypeId().getId() + "");
                cn.put("type", contactNumber.getContactNumberTypeByContactNumberTypeId().getType());
                cn.put("number", contactNumber.getNumber());
                cn.put("is_default", contactNumber.getIsDefault() + "");
                contactNumbers.add(cn);
            }
        });
        um.setContactNumbers(contactNumbers);
//        END::Setting Contact Numbers
//        BEGIN::Setting Username
        user.getLoginCredentialNormalsById().forEach(loginCredentialNormal -> {
            if (loginCredentialNormal.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                um.setUsername(loginCredentialNormal.getUsername());
            }
        });
//        END::Setting Username
        return um;
    }

    public static List<UserModel> entityToModel(List<User> users) {
        List<UserModel> l = new ArrayList<>();
        users.forEach(user -> l.add(entityToModel(user)));
        return l;
    }

    public static List<UserModel> entityToModel(Collection<User> users) {
        List<UserModel> l = new ArrayList<>();
        users.forEach(user -> l.add(entityToModel(user)));
        return l;
    }


}
