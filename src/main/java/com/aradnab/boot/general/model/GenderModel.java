package com.aradnab.boot.general.model;

import com.aradnab.boot.db_tier.entity.User;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenderModel {
    private int id;
    private String gender;
    private Byte status;

    public static GenderModel dbGenderToModelGender(com.aradnab.boot.db_tier.entity.Gender entity) {
        GenderModel g = new GenderModel();
        g.setId(entity.getId());
        g.setGender(entity.getGender());
        g.setStatus(entity.getStatus());
        return g;
    }
    public static com.aradnab.boot.db_tier.entity.Gender modelGenderToDBGender(GenderModel g, Date savedAt, Date lastUpdatedAt, Date deletedAt, Collection<User> users) {
        com.aradnab.boot.db_tier.entity.Gender entity = new com.aradnab.boot.db_tier.entity.Gender();
        entity.setId(g.getId());
        entity.setGender(g.getGender());
        entity.setStatus(g.getStatus());
        entity.setLastUpdatedAt(lastUpdatedAt);
        entity.setDeletedAt(deletedAt);
        entity.setSavedAt(savedAt);
        entity.setUsersById(users);
        return entity;
    }
    public static List<GenderModel> dbGenderToModelGender(List<com.aradnab.boot.db_tier.entity.Gender> entities) {
        List<GenderModel> models = new ArrayList<>();
        for (com.aradnab.boot.db_tier.entity.Gender entity : entities) {
            models.add(dbGenderToModelGender(entity));
        }
        return models;
    }
    public static List<Map<String, String>> dbGenderToModelGenderMapList(List<com.aradnab.boot.db_tier.entity.Gender> entities) {
        List<Map<String, String>> models = new ArrayList<>();
        for (com.aradnab.boot.db_tier.entity.Gender entity : entities) {
            Map<String, String> m = new HashMap<>();
            m.put("id", entity.getId() + "");
            m.put("gender", entity.getGender());
            models.add(m);
        }
        return models;
    }
}
