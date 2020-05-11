package com.aradnab.boot.general.controller;

import com.aradnab.boot.db_tier.entity.User;
import com.aradnab.boot.general.service.ImageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.regex.Matcher;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    ImageService imageService;

//    @PostMapping("/upload/img")
    @RequestMapping(value = "/upload/img", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            imageService.writeImage(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

        @PostMapping("/upload/image")
    public ResponseEntity<String> uploadImage(@RequestBody Map<String,String> body) throws Exception {
        return ResponseEntity.ok().body(imageService.writeImage(body));
    }
}
