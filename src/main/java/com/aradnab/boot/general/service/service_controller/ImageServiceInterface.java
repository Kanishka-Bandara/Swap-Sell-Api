package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Image;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageServiceInterface extends ServiceController<Image> {
    String writeImage(Map<String,String> body) throws Exception;
    void writeImage(MultipartFile file) throws Exception;
}
