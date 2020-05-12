package com.aradnab.boot.general.controller;

import com.aradnab.boot.general.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<HttpStatus> uploadImage(@RequestBody Map<String, String> body) throws Exception {
        String s = imageService.writeProfileImage(body);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getImage(){
        String fileName = "cristiano_ronaldo.jpg";
        String rootDir = System.getProperty("user.dir");
        String imgDir = "\\uploads\\prof_img".replace("/", Matcher.quoteReplacement("\\"));
        String imgPath = rootDir + imgDir + "\\" + fileName;
        Path path = Paths.get(imgPath);
        String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path(imgDir + "\\" + fileName).toUriString();
        return ResponseEntity.ok().body(uriString);
    }

}
