package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Image;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ImageServiceInterface extends ServiceController<Image> {
    String writeProfileImage(Map<String,String> body) throws Exception;

    List<String> writeProductImages(int productId, List<String> images) throws Exception;

    void writeImage(String base64EncodedImage, String path) throws Exception;
    void writeImage(MultipartFile file) throws Exception;

    String getSendAbleProfileImageUrl(String fileName);

    String getSendAbleProductImageUrl(String fileName);

    List<String> getSendAbleProductImageUrl(List<String> files);
}
