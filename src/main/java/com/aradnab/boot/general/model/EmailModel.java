package com.aradnab.boot.general.model;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailModel {

    private int id;
    private int emailTypeId;
    private String emailType;
    private int userId;
    private String email;
    private Byte isDefault;
    private Byte status;

    public static EmailModel entityToModel(Email email) {
        EmailModel em = new EmailModel();
        em.setId(email.getId());
        em.setEmailTypeId(email.getEmailTypeId());
        em.setEmailType(email.getEmailTypeByEmailTypeId().getType());
        em.setUserId(email.getUserId());
        em.setEmail(email.getEmail());
        em.setIsDefault(email.getIsDefault());
        em.setStatus(email.getStatus());
        return em;
    }

    public static List<EmailModel> entityToModel(List<Email> emails) {
        List<EmailModel> l = new ArrayList<>();
        emails.forEach(email -> {
            if (email.getStatus() != Status.DELETE_STATUS) {
                l.add(entityToModel(email));
            }
        });
        return l;
    }

    public static List<EmailModel> entityToModel(Collection<Email> emails) {
        List<EmailModel> l = new ArrayList<>();
        emails.forEach(email -> {
            if (email.getStatus() != Status.DELETE_STATUS) {
                l.add(entityToModel(email));
            }
        });
        return l;
    }


}
