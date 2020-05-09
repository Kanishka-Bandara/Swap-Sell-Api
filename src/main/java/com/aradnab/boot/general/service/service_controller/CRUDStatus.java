package com.aradnab.boot.general.service.service_controller;

public enum CRUDStatus {
    OK(1, "OK"),
    NOT_FOUND(2, "Not found"),
    CANCEL(3, "Cancel"),
    DELETED(0, "Deleted");

    private final int value;

    private final String reasonPhrase;


    CRUDStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }


    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

}
