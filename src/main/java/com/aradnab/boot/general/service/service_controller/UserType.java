package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.general.service.UserTypeService;

import java.util.ArrayList;
import java.util.List;

public enum UserType {
    SELLER_ONLY(1, "Seller", (byte) 1, "Consumer", "Seller", "SE"),
    BUYER_ONLY(2, "Buyer", (byte) 1, "Consumer", "Buyer", "BY"),
    SELLER_AND_BUYER(3, "Seller_And_Buyer", (byte) 1, "Consumer", "Seller and Buyer", "SB"),
    SYSTEM_USER_ADMIN(4, "System_User", (byte) 1, "System User", "Admin", "UA"),
    SYSTEM_USER_NORMAL(5, "System_User", (byte) 1, "System User", "Normal", "UN");
//    SYSTEM_USER_DATA_OPERATOR(6, "System User",(byte)1,"Data Operator");

    public int getId() {
        return id;
    }

    public String getDbPhrase() {
        return dbPhrase;
    }

    public byte getHierarchyCode() {
        return hierarchyCode;
    }

    public String getMainHierarchyPhrase() {
        return mainHierarchyPhrase;
    }

    public String subMainHierarchyPhrase() {
        return subHierarchyPhrase;
    }

    public String getIndexPhrase() {
        return indexPhrase;
    }

    private final int id;

    private final String dbPhrase;

    private final byte hierarchyCode;

    private final String mainHierarchyPhrase;

    private final String subHierarchyPhrase;

    private final String indexPhrase;

    public static List<UserType> getAll() {
        List<UserType> l = new ArrayList<>();
        l.add(SELLER_ONLY);
        l.add(BUYER_ONLY);
        l.add(SELLER_AND_BUYER);
        l.add(SYSTEM_USER_ADMIN);
        l.add(SYSTEM_USER_NORMAL);
        return l;
    }

    public static List<UserType> getConsumerUserTypes() {
        List<UserType> l = new ArrayList<>();
        l.add(SELLER_ONLY);
        l.add(BUYER_ONLY);
        l.add(SELLER_AND_BUYER);
        return l;
    }

    public static List<UserType> getSystemUserTypes() {
        List<UserType> l = new ArrayList<>();
        l.add(SELLER_AND_BUYER);
        l.add(SYSTEM_USER_ADMIN);
        l.add(SYSTEM_USER_NORMAL);
        return l;
    }

    public static UserType getUserTypeByEntity(com.aradnab.boot.db_tier.entity.UserType ut) {
        final UserType[] uty = new UserType[1];
        UserType.getAll().forEach(userType -> {
            if (ut.getId() == userType.getId()) {
                uty[0] = userType;
            }
        });
        return uty[0];
    }
    public static com.aradnab.boot.db_tier.entity.UserType getUserTypeForEntity(UserType ut) {
        UserTypeService service = new UserTypeService();
        return service.getByID(ut.getId());
    }

    UserType(int id, String dbPhrase, byte hierarchyCode, String mainHierarchyPhrase, String subHierarchyPhrase, String indexPhrase) {
        this.id = id;
        this.dbPhrase = dbPhrase;
        this.hierarchyCode = hierarchyCode;
        this.mainHierarchyPhrase = mainHierarchyPhrase;
        this.subHierarchyPhrase = subHierarchyPhrase;
        this.indexPhrase = indexPhrase;
    }
}