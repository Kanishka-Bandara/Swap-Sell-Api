package com.aradnab.boot.general.model;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.ContactNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactNumberModel {

    private int id;
    private int contactNumberTypeId;
    private String contactNumberType;
    private int userId;
    private String contactNumber;
    private Byte isDefault;
    private Byte status;

    public static ContactNumberModel entityToModel(ContactNumber contactNumber) {
        ContactNumberModel em = new ContactNumberModel();
        em.setId(contactNumber.getId());
        em.setContactNumberTypeId(contactNumber.getContactNumberTypeId());
        em.setContactNumberType(contactNumber.getContactNumberTypeByContactNumberTypeId().getType());
        em.setUserId(contactNumber.getUserId());
        em.setContactNumber(contactNumber.getNumber());
        em.setIsDefault(contactNumber.getIsDefault());
        em.setStatus(contactNumber.getStatus());
        return em;
    }

    public static List<ContactNumberModel> entityToModel(List<ContactNumber> contactNumbers) {
        List<ContactNumberModel> l = new ArrayList<>();
        contactNumbers.forEach(contactNumber -> {
            if (contactNumber.getStatus() != Status.DELETE_STATUS) {
                l.add(entityToModel(contactNumber));
            }
        });
        return l;
    }

    public static List<ContactNumberModel> entityToModel(Collection<ContactNumber> contactNumbers) {
        List<ContactNumberModel> l = new ArrayList<>();
        contactNumbers.forEach(contactNumber -> {
            if (contactNumber.getStatus() != Status.DELETE_STATUS) {
                l.add(entityToModel(contactNumber));
            }
        });
        return l;
    }
}
