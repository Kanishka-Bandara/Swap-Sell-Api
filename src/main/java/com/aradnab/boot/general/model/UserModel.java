package com.aradnab.boot.general.model;

import com.aradnab.boot.config.ResourceUrl;
import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Title;
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
    List<EmailModel> emails;
    String country;
    String note;
    String profilePicUrl;
    List<AddressModel> addresses;
    List<ContactNumberModel> contactNumbers;
    String username;
    int status;

    public static UserModel entityToModel(User user) {
        System.out.println("User Id = " + user.getId());
        UserModel um = new UserModel();
        um.setId(user.getId());
        um.setUserId(user.getUserId());
        Title titleByTitleId = user.getTitleByTitleId();
        String title = titleByTitleId.getTitle();
        for (UserType value : UserType.values()) {
            if (user.getUserTypeId() == value.getId()) {
                um.setUserType(value);
            }
        }
        um.setTitle(title);
        um.setGender(user.getGenderByGenderId().getGender());
        um.setFName(user.getFName());
        um.setLName(user.getLName());
        um.setSName(user.getSName());
        um.setFullName(user.getFullName());
        um.setActiveState(user.getActiveState());
        um.setCountry(user.getCountryByCountryId().getCountry());
        String imgUrl = user.getImageByImageId().getImgUrl();
        if (imgUrl != null) {
            if (imgUrl.contains("http")) {
                um.setProfilePicUrl(imgUrl);
            } else {
                um.setProfilePicUrl(ResourceUrl.VIRTUAL_HOST_URL + imgUrl);
            }
        }
//        BEGIN::Setting Emails
        if (user.getEmailsById() != null) {
            List<EmailModel> emails = new ArrayList<>();
            user.getEmailsById().forEach(email -> {
                if (email.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                    EmailModel em = new EmailModel();
                    em.setId(email.getId());
                    em.setEmailTypeId(email.getEmailTypeId());
                    em.setEmailType(email.getEmailTypeByEmailTypeId().getType());
                    em.setEmail(email.getEmail());
                    em.setStatus(email.getStatus());
                    em.setIsDefault(email.getIsDefault());
                    emails.add(em);
                }
            });
            um.setEmails(emails);
        }
//        END::Setting Emails
//        BEGIN::Setting Note
        if (user.getNotesById() != null) {
            user.getNotesById().forEach(note1 -> {
                if (note1.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                    um.setNote(note1.getNote());
                }
            });
        }
//        END::Setting Note
//        BEGIN::Setting Addresses
        if (user.getAddressesById() != null) {
            List<AddressModel> addresses = new ArrayList<>();
            user.getAddressesById().forEach(address -> {
                if (address.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                    addresses.add(AddressModel.entityToModel(address));
                }
            });
            um.setAddresses(addresses);
        }
//        END::Setting Addresses
//        BEGIN::Setting Contact Numbers
        if (user.getContactNumbersById() != null) {
            List<ContactNumberModel> contactNumbers = new ArrayList<>();
            user.getContactNumbersById().forEach(contactNumber -> {
                if (contactNumber.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                    ContactNumberModel cm = new ContactNumberModel();
                    cm.setId(contactNumber.getId());
                    cm.setContactNumberTypeId(contactNumber.getContactNumberTypeByContactNumberTypeId().getId());
                    cm.setContactNumberType(contactNumber.getContactNumberTypeByContactNumberTypeId().getType());
                    cm.setContactNumber(contactNumber.getNumber());
                    cm.setIsDefault(contactNumber.getIsDefault());
                    cm.setStatus(contactNumber.getStatus());
                    Map<String, String> cn = new HashMap<>();
                    contactNumbers.add(cm);
                }
            });
            um.setContactNumbers(contactNumbers);
        }
//        END::Setting Contact Numbers
//        BEGIN::Setting Username
        if (user.getLoginCredentialNormalsById() != null) {
            user.getLoginCredentialNormalsById().forEach(loginCredentialNormal -> {
                if (loginCredentialNormal.getStatus() == Status.LIVE_ACTIVE_STATUS) {
                    um.setUsername(loginCredentialNormal.getUsername());
                }
            });
        }
        um.setStatus(user.getStatus());
        UserType[] values = UserType.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getDbPhrase() == user.getTitleByTitleId().getTitle()) {
                um.setUserType(values[i]);
            }
        }
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
