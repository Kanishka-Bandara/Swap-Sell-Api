package com.aradnab.boot.config;

public class ResourceUrl {
    public static String ROOT_DIR = System.getProperty("user.dir");
    public static String VIRTUAL_PROFILE_DIR = "/profile";
    public static String VIRTUAL_PRODUCT_DIR = "/product";
    public static String REAL_PROFILE_DIR = "/WEB-INF"+VIRTUAL_PROFILE_DIR;
    public static String REAL_PRODUCT_DIR = "/WEB-INF"+VIRTUAL_PRODUCT_DIR;
    public static String WRITABLE_PROFILE_DIR = ROOT_DIR+"/src/main/webapp"+REAL_PROFILE_DIR;
    public static String WRITABLE_PRODUCT_DIR = ROOT_DIR+"/src/main/webapp"+REAL_PRODUCT_DIR;
    public static String VIRTUAL_PROFILE_PICTURE_PATH = ROOT_DIR+"/src/main/webapp"+VIRTUAL_PROFILE_DIR;
    public static String VIRTUAL_HOST_URL = "http://localhost:8080";

}
