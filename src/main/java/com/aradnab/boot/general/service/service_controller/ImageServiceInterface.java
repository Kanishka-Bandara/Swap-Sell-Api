package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Image;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageServiceInterface extends ServiceController<Image> {
    String writeProfileImage(Map<String,String> body) throws Exception;
    void writeImage(String base64EncodedImage,String path) throws Exception;
    void writeImage(MultipartFile file) throws Exception;
}
