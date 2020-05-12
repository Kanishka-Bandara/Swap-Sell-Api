package com.aradnab.boot.general.model;

public enum LoginAccountType {
    FB_ACCOUNT("login_credential_fb"),
    GOOGLE_ACCOUNT("login_credential_google"),
    NORMAL_ACCOUNT("login_credential_fb");

    public String getDbTableName() {
        return dbTableName;
    }

    private String dbTableName;

    LoginAccountType(String dbTableName) {
        this.dbTableName = dbTableName;
    }
}
